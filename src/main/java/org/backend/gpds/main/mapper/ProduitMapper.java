package org.backend.gpds.main.mapper;


import org.backend.gpds.main.dto.*;
import org.backend.gpds.main.dto.request.ProduitAdminDTO;
import org.backend.gpds.main.dto.request.ProduitGestionnaireDTO;
import org.backend.gpds.main.model.Product;

public class ProduitMapper {

    public static Product toEntity(ProduitGestionnaireDTO dto) {
        return Product.builder()
                .nom(dto.getNom())
                .description(dto.getDescription())
                .categorie(dto.getCategorie())
                .prixVente(dto.getPrixVente())
                .prixAchat(dto.getPrixAchat())
                .marge(dto.getMarge())
                .poids(dto.getPoids())
                .unite(dto.getUnite())
                .build();
    }

    public static ProduitAdminDTO toAdminDTO(Product p) {
        ProduitAdminDTO dto = new ProduitAdminDTO();
        dto.setId(p.getId());
        dto.setNom(p.getNom());
        dto.setDescription(p.getDescription());
        dto.setCategorie(p.getCategorie());
        dto.setPrixVente(p.getPrixVente());
        dto.setPrixAchat(p.getPrixAchat());
        dto.setMarge(p.getMarge());
        dto.setPoids(p.getPoids());
        dto.setUnite(p.getUnite());
        return dto;
    }

    public static ProduitGestionnaireDTO toGestionnaireDTO(Product p) {
        ProduitGestionnaireDTO dto = new ProduitGestionnaireDTO();
        dto.setId(p.getId());
        dto.setNom(p.getNom());
        dto.setDescription(p.getDescription());
        dto.setCategorie(p.getCategorie());
        dto.setPrixVente(p.getPrixVente());
        dto.setPoids(p.getPoids());
        dto.setUnite(p.getUnite());
        return dto;
    }
}
