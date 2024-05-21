package com.spring.exaple.jwt.user.app.api.userAppapi.Service.Auth;

import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import com.spring.exaple.jwt.user.app.api.userAppapi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loading loading ");
        System.out.println(username);
        Optional<User> user=userRepository.findByEmail(username);

        if(user.isEmpty()) throw new UsernameNotFoundException("user not present ");
        return user.get();
    }
}
