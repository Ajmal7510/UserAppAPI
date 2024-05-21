package com.spring.exaple.jwt.user.app.api.userAppapi.Controller;

import com.spring.exaple.jwt.user.app.api.userAppapi.DTO.LoginDTO;
import com.spring.exaple.jwt.user.app.api.userAppapi.DTO.LoginResponseDTO;
import com.spring.exaple.jwt.user.app.api.userAppapi.DTO.RegistrationDTO;
import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import com.spring.exaple.jwt.user.app.api.userAppapi.Service.Auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public User register(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                         @RequestParam("email") String email, @RequestParam("password") String password,
                         @RequestParam("image")MultipartFile image) throws Exception {
        RegistrationDTO req=new RegistrationDTO();
        req.setFirstName(firstName);
        req.setLastName(lastName);
        req.setEmail(email);
        req.setPassword(password);
        req.setImage(image);
        System.out.println("its work");

        return authenticationService.register(req);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO req){
        try {
            System.out.println(req);

            LoginResponseDTO dto=authenticationService.loginUser(req);
            System.out.println(dto);
            if(dto.getUser().equals(null)){
                return ResponseEntity.badRequest().body(null);
            }
            return ResponseEntity.ok(dto);
        } catch (Exception ignored){
            return  ResponseEntity.badRequest().body(null);
        }
    }


}
