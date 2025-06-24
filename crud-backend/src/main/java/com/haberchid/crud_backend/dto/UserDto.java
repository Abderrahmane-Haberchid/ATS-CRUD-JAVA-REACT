package com.haberchid.crud_backend.dto;

import com.haberchid.crud_backend.entitys.Product;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record UserDto (UUID userId,
                       String name,
                       String email,
                       String password) { }
