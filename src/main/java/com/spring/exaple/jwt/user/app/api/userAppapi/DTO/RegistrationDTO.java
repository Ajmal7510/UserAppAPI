package com.spring.exaple.jwt.user.app.api.userAppapi.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegistrationDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private MultipartFile image;

}
