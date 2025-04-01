package com.example.authenticationservice.Controllers;

import com.example.authenticationservice.DTOs.*;
import com.example.authenticationservice.Exceptions.AlreadyExistsException;
import com.example.authenticationservice.Exceptions.DoesNotExistException;
import com.example.authenticationservice.Exceptions.MisMatchException;
import com.example.authenticationservice.Models.User;
import com.example.authenticationservice.Services.IAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupRequestDto signupRequestDto)
    {
        try {
            User user=authService.signUp(signupRequestDto.getEmail(),signupRequestDto.getPassword());
            return new ResponseEntity<>(convert(user),HttpStatus.CREATED);

        }
        catch (AlreadyExistsException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(userExceptionDto(e, UserDto.class), HttpStatus.CONFLICT);
        }
        catch (DoesNotExistException | JsonProcessingException e)
        {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(userExceptionDto(e, UserDto.class), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) throws MisMatchException {
        try {
            System.out.println("Controller Password received: "+loginRequestDto.getPassword());
            Pair<User,String> userPair=authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
            MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.SET_COOKIE,userPair.getSecond());
            return new ResponseEntity<>(convert(userPair.getFirst()),headers,HttpStatus.OK);
        }
        catch (MisMatchException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(userExceptionDto(e, UserDto.class), HttpStatus.BAD_REQUEST);
        } catch (DoesNotExistException e) {
            return new ResponseEntity<>(userExceptionDto(e, UserDto.class), HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping("/verify")
    public ResponseEntity<VerifyResponseDto> verify(@RequestBody VerifyRequestDto verifyRequestDto) throws DoesNotExistException {
        try {
            boolean result=authService.validateToken(verifyRequestDto.getToken(),verifyRequestDto.getUserId());
            VerifyResponseDto verifyResponseDto=new VerifyResponseDto();
            if (!result)
            {
                verifyResponseDto.setTokenValidity(false);
                verifyResponseDto.setResponseMessage("Invalid token");
                return new ResponseEntity<>(verifyResponseDto,HttpStatus.UNAUTHORIZED);
            }

            verifyResponseDto.setTokenValidity(true);
            verifyResponseDto.setResponseMessage("Successfully verified");
            verifyResponseDto.setToken(verifyRequestDto.getToken());
            verifyRequestDto.setUserId(verifyRequestDto.getUserId());

            return new ResponseEntity<>(verifyResponseDto,HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(userExceptionDto(e, VerifyResponseDto.class), HttpStatus.UNAUTHORIZED);
        }

    }



    //This method sets exception message in response entity
//    private UserDto userExceptionDto(Exception e)
//    {
//        UserDto userDto=new UserDto();
//        userDto.setResponseMessage(e.getMessage());
//        return userDto;
//    }
    private <T> T userExceptionDto(Exception e, Class<T> tClass)
    {
        try{
            T t=tClass.getDeclaredConstructor().newInstance();
            if (t instanceof BaseResponseDTO) {
                ((BaseResponseDTO) t).setResponseMessage(e.getMessage()+" "+e.getClass());
            }
            return t;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }

    }

    //user to userDTO mapper
    private UserDto convert(User user)
    {
        if (user==null)
        {
            return new UserDto();
        }
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setRolesList(user.getRolesList());
        userDto.setResponseMessage("Success");
        return userDto;
    }
}
