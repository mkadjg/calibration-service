package com.calibration.service;

import com.calibration.auth.JwtConfig;
import com.calibration.dto.LoginDto;
import com.calibration.model.Users;
import com.calibration.repository.UsersRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    UsersRepository usersRepository;

    JwtConfig jwtConfig;

    @Autowired
    AuthServiceImpl(UsersRepository usersRepository, JwtConfig jwtConfig) {
        this.usersRepository = usersRepository;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public String authenticate(LoginDto dto) {
        Users user = usersRepository.findByUsername(dto.getUsername()).orElse(null);

        // check username
        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // check password
        if (dto.getPassword().equals(user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return Jwts.builder()
                .setSubject(dto.getUsername())
                .claim("authorities", new ArrayList<>())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 1000L))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();
    }


}
