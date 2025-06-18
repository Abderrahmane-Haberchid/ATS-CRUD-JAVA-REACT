package com.haberchid.crud_backend.repositories;

import com.haberchid.crud_backend.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
