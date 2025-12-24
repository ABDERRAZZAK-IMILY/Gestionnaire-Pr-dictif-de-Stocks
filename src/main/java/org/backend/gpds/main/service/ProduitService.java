package org.backend.gpds.main.service;



import org.backend.gpds.main.dto.request.ProduitCreateDTO;
import org.backend.gpds.main.dto.request.ProduitUpdateDTO;
import org.backend.gpds.main.model.Product;

import java.util.List;

public interface ProduitService {

    /**
     * Création d'un produit
     * Accessible uniquement par ADMIN
     */
    Product createProduit(ProduitCreateDTO dto);

    /**
     * Modification d'un produit existant
     * Accessible uniquement par ADMIN
     */
    Product updateProduit(Long id, ProduitUpdateDTO dto);

    /**
     * Suppression d'un produit
     * Accessible uniquement par ADMIN
     */
    void deleteProduit(Long id);

    /**
     * Récupérer tous les produits
     * ADMIN : tous les champs
     * GESTIONNAIRE : champs limités (via DTO)
     */
    List<Product> getAllProduits();

    /**
     * Récupérer un produit par ID
     */
    Product getProduitById(Long id);
}
