package com.haberchid.crud_backend.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponseDto(String email, String jwt) { }
