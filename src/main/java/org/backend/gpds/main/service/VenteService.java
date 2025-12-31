package org.backend.gpds.main.service;


import org.backend.gpds.main.dto.request.VenteRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface VenteService {
    void processSale(VenteRequestDTO dto);
}
