package com.example.authenticationservice.Utils;

import com.example.authenticationservice.DTOs.UserDto;
import com.example.authenticationservice.Models.User;

public class DtoUtil {

        //user to userDTO mapper
    public static UserDto convert(User user)
    {
        if (user==null)
        {
            return new UserDto();
        }
        UserDto userDto=new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setRolesList(user.getRolesList().stream().toList());
        userDto.setResponseMessage("Success");
        return userDto;
    }
}
