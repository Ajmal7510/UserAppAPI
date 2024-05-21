package com.spring.exaple.jwt.user.app.api.userAppapi.Service.User;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.spring.exaple.jwt.user.app.api.userAppapi.DTO.RegistrationDTO;
import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import com.spring.exaple.jwt.user.app.api.userAppapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@Service
public class Userservice {

    @Autowired
    private AmazonS3 amazonS3ClintAmazonS3;

    @Value("${s3.bucketName}")
    private String bucketName;

    private String uploadDir="https://spring-ecommerce.s3.eu-north-1.amazonaws.com/";

    @Autowired
    private UserRepository userRepository;
    public User updateProfile(Principal principal, RegistrationDTO req) throws IOException {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (req.getFirstName() != null) {
            user.setFirstName(req.getFirstName());
        }
        if (req.getLastName() != null) {
            user.setLastName(req.getLastName());
        }
        if (req.getImage() != null) {
            String fileName = UUID.randomUUID().toString() + "_" + req.getImage().getOriginalFilename();
            amazonS3ClintAmazonS3.putObject(new PutObjectRequest(bucketName, fileName, req.getImage().getInputStream(), new ObjectMetadata()));
            String imageUrl = uploadDir + fileName;
            user.setImageUrl(imageUrl);
        }

        if (req.getEmail() != null) {
            System.out.println(req.getEmail());
            user.setEmail(req.getEmail());
        }
        userRepository.save(user);

        return user;
    }

}
