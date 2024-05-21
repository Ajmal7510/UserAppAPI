package com.spring.exaple.jwt.user.app.api.userAppapi.DTO;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String password;
}
