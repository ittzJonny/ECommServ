package com.example.authenticationservice.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Session extends BaseModel{

    //alter table session modify token varchar(512);
    private String token;

    @ManyToOne
    private User user;
}
