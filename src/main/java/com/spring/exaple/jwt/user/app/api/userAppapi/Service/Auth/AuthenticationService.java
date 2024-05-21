package com.spring.exaple.jwt.user.app.api.userAppapi.Service.Auth;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.spring.exaple.jwt.user.app.api.userAppapi.DTO.LoginDTO;
import com.spring.exaple.jwt.user.app.api.userAppapi.DTO.LoginResponseDTO;
import com.spring.exaple.jwt.user.app.api.userAppapi.DTO.RegistrationDTO;
import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.Role;
import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import com.spring.exaple.jwt.user.app.api.userAppapi.Repository.RoleRepository;
import com.spring.exaple.jwt.user.app.api.userAppapi.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class AuthenticationService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AmazonS3 amazonS3ClintAmazonS3;

    @Value("${s3.bucketName}")
    private String bucketName;

    private String uploadDir="https://spring-ecommerce.s3.eu-north-1.amazonaws.com/";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    // Method to register a new user
    public User register(RegistrationDTO req) throws Exception {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + req.getImage().getOriginalFilename();

            // Upload the file to S3
            amazonS3ClintAmazonS3.putObject(new PutObjectRequest(bucketName, fileName, req.getImage().getInputStream(), new ObjectMetadata()));

            String imageUrl = uploadDir  + fileName;
            Role userRole=roleRepository.findByAuthority("USER").get();

            Set<Role> setAuthorities=new HashSet<>();
            setAuthorities.add(userRole);


            User newUser = new User();
            newUser.setFirstName(req.getFirstName());
            newUser.setLastName(req.getLastName());
            newUser.setPassword(passwordEncoder.encode(req.getPassword()));
            newUser.setEmail(req.getEmail());
            newUser.setImageUrl(imageUrl);
            newUser.setAuthorities(setAuthorities);
            return userRepository.save(newUser); // Return the saved user

        } catch (Exception e) {
            // Log the exception or handle it appropriately
            throw new Exception("Registration failed", e);
        }
    }


    public LoginResponseDTO loginUser(LoginDTO req){
        try {
            Authentication auth= authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(req.getEmail(),req.getPassword()));

            String token=tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByEmail(req.getEmail()).get(),token);
        }catch (Exception ignored){

            return new LoginResponseDTO(null,"");
        }
    }


}
