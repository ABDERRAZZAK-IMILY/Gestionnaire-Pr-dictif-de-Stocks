package org.backend.gpds.main.mapper;

import org.backend.gpds.main.dto.*;
import org.backend.gpds.main.dto.request.EntrepotCreateDTO;
import org.backend.gpds.main.dto.request.EntrepotDTO;
import org.backend.gpds.main.model.Entrepot;

public class EntrepotMapper {

    public static Entrepot toEntity(EntrepotCreateDTO dto) {
        return Entrepot.builder()
                .nom(dto.getNom())
                .ville(dto.getVille())
                .adresse(dto.getAdresse())
                .actif(true)
                .build();
    }

    public static EntrepotDTO toDTO(Entrepot e) {
        EntrepotDTO dto = new EntrepotDTO();
        dto.setId(e.getId());
        dto.setNom(e.getNom());
        dto.setVille(e.getVille());
        dto.setAdresse(e.getAdresse());
        dto.setActif(e.isActif());
        return dto;
    }
}
