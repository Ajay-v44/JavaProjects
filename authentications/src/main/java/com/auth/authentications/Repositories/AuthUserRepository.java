package com.auth.authentications.Repositories;

import com.auth.authentications.Models.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser,Long> {

    AuthUser findByUsername(String username);
}
