package com.haberchid.crud_backend.entitys;

import com.haberchid.crud_backend.enumeration.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    private UUID productId;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Date createdAt;
    private Date updatedAt;
}
