package org.backend.gpds.main.service.impl;


import org.backend.gpds.main.dto.request.ProduitCreateDTO;
import org.backend.gpds.main.dto.request.ProduitUpdateDTO;
import org.backend.gpds.main.model.Product;
import org.backend.gpds.main.exeptions.ResourceNotFoundException;
import org.backend.gpds.main.mapper.ProduitMapper;
import org.backend.gpds.main.repository.jpa.ProduitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProduitServiceImpl {


    private final ProduitRepository produitRepository;

    public Product create(ProduitCreateDTO dto) {
        return produitRepository.save(ProduitMapper.toEntity(dto));
    }

    public Product update(Long id, ProduitUpdateDTO dto) {
        Product produit = findById(id);

        produit.setNom(dto.getNom());
        produit.setDescription(dto.getDescription());
        produit.setCategorie(dto.getCategorie());
        produit.setPrixVente(dto.getPrixVente());
        produit.setPrixAchat(dto.getPrixAchat());
        produit.setMarge(dto.getMarge());
        produit.setPoids(dto.getPoids());
        produit.setUnite(dto.getUnite());
        produit.setActif(dto.isActif());

        return produit;
    }

    public List<Product> findAll() {
        return produitRepository.findAll();
    }

    public Product findById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit introuvable"));
    }

    public void delete(Long id) {
        produitRepository.delete(findById(id));
    }
}
