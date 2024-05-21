package com.spring.exaple.jwt.user.app.api.userAppapi.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roleId;
    private String authority;



    public Role(){
        super();
    }

    public Role(String authority){
        this.authority = authority;
    }

    public Role(Long roleId, String authority){
        this.roleId = roleId;
        this.authority = authority;
    }

    @Override
    public String getAuthority() {

        return this.authority;
    }

}
