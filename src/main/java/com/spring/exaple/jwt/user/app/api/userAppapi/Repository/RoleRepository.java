package com.spring.exaple.jwt.user.app.api.userAppapi.Repository;

import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByAuthority(String authority);
}
