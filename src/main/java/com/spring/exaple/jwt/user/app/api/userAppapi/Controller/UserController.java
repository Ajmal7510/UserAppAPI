package com.spring.exaple.jwt.user.app.api.userAppapi.Controller;

import com.spring.exaple.jwt.user.app.api.userAppapi.DTO.RegistrationDTO;
import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import com.spring.exaple.jwt.user.app.api.userAppapi.Service.User.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private Userservice userService;

    @GetMapping("")
    public String home(){
        return "welcome user home";
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestParam(value = "firstName", required = false) String firstName,
                                           @RequestParam(value = "lastName", required = false) String lastName,
                                           @RequestParam(value = "email", required = false) String email,
                                           @RequestParam(value = "image", required = false) MultipartFile image,
                                           Principal principal) {
        try {
            RegistrationDTO req = new RegistrationDTO();

            // Check if fields are provided in the request and set them accordingly
            if (firstName != null) {
                req.setFirstName(firstName);
            }
            if (lastName != null) {
                req.setLastName(lastName);
            }
            if (email != null) {
                System.out.println(email);
                req.setEmail(email);
            }
            if (image != null) {
                req.setImage(image);
            }
            System.out.println(req);

            User user = userService.updateProfile(principal, req);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

}
