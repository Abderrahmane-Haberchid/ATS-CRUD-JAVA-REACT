package com.haberchid.crud_backend.dto;

import com.haberchid.crud_backend.entitys.User;
import com.haberchid.crud_backend.enumeration.Category;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record ProductDto(UUID productId,
                         String title,
                         String description,
                         Category category,
                         Date createdAt,
                         Date updatedAt,
                         User user) {
}
