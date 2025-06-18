package com.haberchid.crud_backend.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private UUID userId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToOne
    private List<Product> productList;
}
