package com.example.authenticationservice.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
//@ToString
//@Getter
//@Setter
public class User extends BaseModel{

    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private Set<Role> rolesList= new HashSet<>();

//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public List<Role> getRolesList() {
//        return rolesList;
//    }
//
//    public void setRolesList(List<Role> rolesList) {
//        this.rolesList = rolesList;
//    }
}
