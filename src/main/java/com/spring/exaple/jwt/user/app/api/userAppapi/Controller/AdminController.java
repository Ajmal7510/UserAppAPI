package com.spring.exaple.jwt.user.app.api.userAppapi.Controller;

import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import com.spring.exaple.jwt.user.app.api.userAppapi.Service.Admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/userlist")
    public List<User> userList(){
        return adminService.userList();
    }

    @PutMapping("/user/unlock/{id}")
    public ResponseEntity<?> unlockuser(@PathVariable Long id){

        return adminService.unlockuser(id);
    }

    @PutMapping("/user/block/{id}")
    public ResponseEntity<?> blockuser(@PathVariable Long id){
        System.out.println("block its work");
        return adminService.blockuser(id);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return adminService.deleteuser(id);
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<?> edituser(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password,
            @PathVariable Long id
    ){
        System.out.println(firstName+" / "+lastName+" / "+email+" / "+password+" "+id);

        return adminService.updateUser(firstName,lastName,email,password,id);
    }
}
