package com.haberchid.crud_backend.dto;

import lombok.Builder;
import org.springframework.security.core.userdetails.UserDetails;

@Builder
public record AuthenticationResponseDto(UserDetails userDetails, String jwt) { }
