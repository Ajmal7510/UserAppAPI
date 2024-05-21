package com.spring.exaple.jwt.user.app.api.userAppapi.DTO;

import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import lombok.Data;

@Data
public class LoginResponseDTO {

    private User user;
    private String token;

    public LoginResponseDTO(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
