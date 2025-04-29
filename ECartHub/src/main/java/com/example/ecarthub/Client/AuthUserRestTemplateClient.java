package com.example.ecarthub.Client;

import com.example.ecarthub.DTO.ClientDTO.AuthUserGetUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthUserRestTemplateClient implements AuthUserClientAdapter{

    @Autowired
    RestTemplate restTemplate;

    public AuthUserGetUserDTO getUserById(String id) {
        String url = "http://localhost:9000/user/" + id;
        AuthUserGetUserDTO user = restTemplate.getForObject(url, AuthUserGetUserDTO.class);
        System.out.println(user);
        return user;
    }

//@GetMapping("/{id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
//        try {
//            User user= userService.getUserById(id);
//            return new ResponseEntity<>(DtoUtil.convert(user), HttpStatus.OK);
//        } catch (DoesNotExistException e) {
//            return new ResponseEntity<>(ExceptionUtil.getExceptionResponse(e,UserDto.class), HttpStatus.NOT_FOUND);
//        }
//    }
}
