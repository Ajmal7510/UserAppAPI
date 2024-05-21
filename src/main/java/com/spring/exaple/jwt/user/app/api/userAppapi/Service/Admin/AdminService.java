package com.spring.exaple.jwt.user.app.api.userAppapi.Service.Admin;

import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import com.spring.exaple.jwt.user.app.api.userAppapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<User> userList() {
        return userRepository.findAllUsersWithRole("USER");
    }

    public ResponseEntity<?> deleteuser(Long id) {
        try {
            User user=userRepository.findById(id).get();
            userRepository.delete(user);
            return ResponseEntity.ok(null);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<?> blockuser(Long id) {
        try {
            User user=userRepository.findById(id).get();
             user.setEnabled(false);
             userRepository.save(user);
            return ResponseEntity.ok(null);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<?> unlockuser(Long id) {
        try {
            User user=userRepository.findById(id).get();
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok(null);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(null);
        }
    }



    public ResponseEntity<?> updateUser(String firstName, String lastName, String email, String password,  Long id) {

        try {

            User user=userRepository.findById(id).get();
            if(password!=null){
                user.setPassword(passwordEncoder.encode(password));
            }

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            userRepository.save(user);
            return ResponseEntity.ok("success");

        }catch (Exception e){

            return ResponseEntity.badRequest().body("some thing wrong");
        }
    }
}
