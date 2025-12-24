package org.backend.gpds.main.repository.jpa;

import org.backend.gpds.main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Product, Long> {
}