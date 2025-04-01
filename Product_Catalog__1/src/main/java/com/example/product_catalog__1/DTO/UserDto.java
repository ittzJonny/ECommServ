package com.example.product_catalog__1.DTO;


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
    String message;
//    private List<Role> rolesList= new ArrayList<>();


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
