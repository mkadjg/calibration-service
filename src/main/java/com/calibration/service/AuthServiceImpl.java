package com.calibration.service;

import com.calibration.auth.JwtConfig;
import com.calibration.dto.LoginDto;
import com.calibration.model.UserRoles;
import com.calibration.model.Users;
import com.calibration.repository.CustomersRepository;
import com.calibration.repository.EmployeesRepository;
import com.calibration.repository.UserRolesRepository;
import com.calibration.repository.UsersRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    UsersRepository usersRepository;

    UserRolesRepository userRolesRepository;

    CustomersRepository customersRepository;

    EmployeesRepository employeesRepository;

    JwtConfig jwtConfig;

    @Autowired
    AuthServiceImpl(UsersRepository usersRepository,
                    UserRolesRepository userRolesRepository,
                    CustomersRepository customersRepository,
                    EmployeesRepository employeesRepository,
                    JwtConfig jwtConfig) {
        this.usersRepository = usersRepository;
        this.userRolesRepository = userRolesRepository;
        this.customersRepository = customersRepository;
        this.employeesRepository = employeesRepository;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Object authenticate(LoginDto dto) {
        Users user = usersRepository.findByUsername(dto.getUsername()).orElse(null);

        // check username
        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // check password
        if (dto.getPassword().equals(user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        Map<String, Object> result = new HashMap<>();

        UserRoles userRoles = userRolesRepository.findByUserId(user.getId());
        result.put("role", userRoles.getRole());

        String token = Jwts.builder()
                .setSubject(dto.getUsername())
                .claim("authorities", List.of(userRoles.getRole().getRoleName()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration() * 1000L))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();
        result.put("token", token);

        switch (userRoles.getRole().getRoleName().toLowerCase()) {
            case "customer": {
                result.put("userProfile", customersRepository.findByUserId(user.getId()));
                break;
            }
            case "employee": {
                result.put("userProfile", employeesRepository.findByUserId(user.getId()));
                break;
            }
            default: {
                break;
            }
        }

        return result;
    }


}
