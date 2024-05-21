package com.spring.exaple.jwt.user.app.api.userAppapi.Repository;

import com.spring.exaple.jwt.user.app.api.userAppapi.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u JOIN u.authorities r WHERE r.authority = :role")
    List<User> findAllUsersWithRole(@Param("role") String role);
}
