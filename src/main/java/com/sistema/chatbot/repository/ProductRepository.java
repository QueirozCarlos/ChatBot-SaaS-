package com.sistema.chatbot.repository;

import com.sistema.chatbot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
