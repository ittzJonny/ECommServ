package com.example.authenticationservice.DTOs;

import com.example.authenticationservice.Models.Role;
import com.example.authenticationservice.Models.User;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@ToString
public class UserDto extends BaseResponseDTO{

    private UUID id;
    private String email;
    private List<Role> rolesList= new ArrayList<>();


//    //user to userDTO mapper
//    public static UserDto convertFrom(User user)
//    {
//        if (user==null)
//        {
//            return new UserDto();
//        }
//        UserDto userDto=new UserDto();
//        userDto.setEmail(user.getEmail());
//        userDto.setId(user.getId());
//        userDto.setRolesList(user.getRolesList());
//        userDto.setResponseMessage("Success");
//        return userDto;
//    }

}
