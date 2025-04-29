package com.example.ecarthub.DTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetCartRequestDTO {
    private String UserId;
    private String CartType;
}
