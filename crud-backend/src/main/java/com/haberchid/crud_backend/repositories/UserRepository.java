package com.haberchid.crud_backend.repositories;

import com.haberchid.crud_backend.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

     User findByEmail(String email);
}
