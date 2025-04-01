package com.example.authenticationservice.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Date createdAt;
    private Date modifiedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

}
