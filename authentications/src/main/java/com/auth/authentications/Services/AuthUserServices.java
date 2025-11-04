package com.auth.authentications.Services;

import com.auth.authentications.Models.AuthUser;
import com.auth.authentications.Models.DTO.TokenUserResponse;
import com.auth.authentications.Models.DTO.UserRegister;
import com.auth.authentications.Repositories.AuthUserRepository;
import com.auth.authentications.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class AuthUserServices {
    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    JWTUtils jwt;

    public String save(UserRegister userRegister) {
        AuthUser obj = new AuthUser();
        AuthUser user = authUserRepository.findByUsername(userRegister.username());
        if (user != null)
            return "Username Already Exists";
        obj.setUsername(userRegister.username());
        obj.setPassword(encoder.encode(userRegister.password()));
        authUserRepository.save(obj);
        return "User Created Successfully";
    }

    public ResponseEntity<String> login(UserRegister userRegister) {
        AuthUser user = authUserRepository.findByUsername(userRegister.username());
        if (user == null)
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        if (encoder.matches(userRegister.password(), user.getPassword()))
            return new ResponseEntity<>(jwt.generateToken(user.getId(), user.getUsername()), HttpStatus.OK);
        return new ResponseEntity<>("Invalid Password", HttpStatus.BAD_REQUEST);
    }

    public TokenUserResponse getTokenUser(String token) {
        Map<String, Object> map = jwt.decodeToken(token);
        if (map.containsKey("userId")) {
            Long userId = ((Number) map.get("userId")).longValue();
            return authUserRepository
                    .findById(userId)
                    .map(user -> new TokenUserResponse(user.getId(), user.getUsername())).
                    orElse(null);
        }
        throw new RuntimeException("Invalid Token");
    }
}
