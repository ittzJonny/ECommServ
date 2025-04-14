package com.example.authenticationservice.Services;

import com.example.authenticationservice.DTOs.ClientDto.SignupEmailDTO;
import com.example.authenticationservice.Exceptions.AlreadyExistsException;
import com.example.authenticationservice.Exceptions.DoesNotExistException;
import com.example.authenticationservice.Exceptions.MisMatchException;
import com.example.authenticationservice.Models.Role;
import com.example.authenticationservice.Models.Session;
import com.example.authenticationservice.Models.Status;
import com.example.authenticationservice.Models.User;
import com.example.authenticationservice.Repos.RoleRepo;
import com.example.authenticationservice.Repos.SessionRepo;
import com.example.authenticationservice.Repos.UserRepo;
import com.example.authenticationservice.clients.KafkaProducerClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl extends IAuthService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SecretKey secretKey;

    @Autowired
    private KafkaProducerClient kafkaProducerClient;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public User signUp(String email, String password) throws AlreadyExistsException, JsonProcessingException {

        Optional<User> userOptional=userRepo.findByEmail(email);
        if(userOptional.isPresent()){
            throw new AlreadyExistsException("User Already Exists");
        }

        User user= new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));

//        user.setRolesList(List.of(this.getRole(null)));

        user=userRepo.save(user);

        user.setEmail("mananbansal1600@gmail.com");
        sendEmail(user.getEmail());


        return user;


    }

    private void sendEmail(String toEmail) throws JsonProcessingException {

        SignupEmailDTO signupEmailDTO=new SignupEmailDTO();
        signupEmailDTO.setToEmail(toEmail);
        signupEmailDTO.setFromEmail("mananbansal1600@gmail.com");
        signupEmailDTO.setSubject("test Email");
        signupEmailDTO.setBody("this is the body of test email");

        kafkaProducerClient.sendEventToQueue("test-topic",objectMapper.writeValueAsString(signupEmailDTO));
    }

    @Override
    public Pair<User,String> login(String email, String password) throws MisMatchException, DoesNotExistException {

        Optional<User> userOptional=userRepo.findByEmail(email);

        if(userOptional.isEmpty()){
            throw new DoesNotExistException("User with Email does not exist");
        }

        User user=userOptional.get();

        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw new MisMatchException("Password Mismatch");
        }


        //Generating JWT
//        String msg=" this string msg";
//        byte[] bArr=msg.getBytes(StandardCharsets.UTF_8);

//        MacAlgorithm algo= Jwts.SIG.HS256;
//        SecretKey secretKey=algo.key().build();

        HashMap<String,Object> payload=new HashMap<>();

        long currTime=System.currentTimeMillis();

        payload.put("Issue Time",currTime);
        payload.put("Exp Time",currTime+100000);
        payload.put("UserId",user.getId());
        payload.put("Issuer","Authentication Service Impl");
        payload.put("Scope",user.getRolesList());



        String token= Jwts.builder().claims(payload).signWith(secretKey).compact();


        Session session= new Session();
        session.setToken(token);
        session.setUser(user);
        session.setStatus(Status.ACTIVE);
        sessionRepo.save(session);


        return Pair.of(userOptional.get(),token);

    }


    public boolean validateToken(String token, UUID userId) throws DoesNotExistException {
        Session session=sessionRepo.findByTokenAndUser_id(token,userId).orElse(null);

        if (session==null || session.getStatus() != Status.ACTIVE) {
            return false;
        }

        String dbToken=session.getToken();

        JwtParser parser=Jwts.parser().verifyWith(secretKey).build();
        Claims claims=parser.parseSignedClaims(token).getPayload();

        Long tokenExpirey=claims.get("Exp Time",Long.class);
        Long currTime=System.currentTimeMillis();

        System.out.println(tokenExpirey-currTime);

        if (tokenExpirey<currTime){

            session.setStatus(Status.INACTIVE);
            sessionRepo.save(session);
            return false;
        }

        return true;

    }




    private Role getRole(String roleName) throws DoesNotExistException {
        if (roleName==null)
        {
            return roleRepo.findByRole("ROLE_Customer").orElseThrow(()->new DoesNotExistException("Default Role Does Not Exist"));
        }
        return roleRepo.findByRole(roleName).orElseThrow(()->new DoesNotExistException("Default Role Does Not Exist"));
    }
}
