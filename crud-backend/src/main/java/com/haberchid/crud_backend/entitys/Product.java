package com.haberchid.crud_backend.entitys;

import com.haberchid.crud_backend.enumeration.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private UUID productId;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Date createdAt;
    private Date updatedAt;
    @OneToMany
    private User user;
}
