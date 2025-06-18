package com.haberchid.crud_backend.dto;

import java.util.UUID;

public record UserDto (UUID userId, String name, String email, String password) { }
