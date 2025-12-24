package org.backend.gpds.main.service.impl;


import org.backend.gpds.main.dto.request.EntrepotCreateDTO;
import org.backend.gpds.main.dto.request.EntrepotUpdateDTO;
import org.backend.gpds.main.model.Entrepot;
import org.backend.gpds.main.exeptions.ResourceNotFoundException;
import org.backend.gpds.main.mapper.EntrepotMapper;
import org.backend.gpds.main.repository.jpa.EntrepotRepository;
import lombok.RequiredArgsConstructor;
import org.backend.gpds.main.service.EntrepotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EntrepotServiceImpl implements EntrepotService {

    private final EntrepotRepository entrepotRepository;

    @Override
    public Entrepot createEntrepot(EntrepotCreateDTO dto) {
        return entrepotRepository.save(EntrepotMapper.toEntity(dto));
    }

    @Override
    public Entrepot updateEntrepot(Long id, EntrepotUpdateDTO dto) {
        Entrepot entrepot = getEntrepotById(id);

        entrepot.setNom(dto.getNom());
        entrepot.setVille(dto.getVille());
        entrepot.setAdresse(dto.getAdresse());
        entrepot.setActif(dto.isActif());

        return entrepot;
    }

    @Override
    public void deleteEntrepot(Long id) {
        entrepotRepository.delete(getEntrepotById(id));
    }

    @Override
    public List<Entrepot> getAllEntrepots() {
        return entrepotRepository.findAll();
    }

    @Override
    public Entrepot getEntrepotById(Long id) {
        return entrepotRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Entrepôt introuvable"));
    }
}
