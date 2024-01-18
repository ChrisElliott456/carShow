package com.binarychris.carShow.repository;

import com.binarychris.carShow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {// import Entity User
    Optional<User>findUserByUsername(String username);
}
