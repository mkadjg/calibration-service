package com.calibration.repository;

import com.calibration.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRolesRepository extends JpaRepository<UserRoles, Integer> {

    @Query("select ur from UserRoles ur where ur.user.id =:userId")
    UserRoles findByUserId(int userId);

}
