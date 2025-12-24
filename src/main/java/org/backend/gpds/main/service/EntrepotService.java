package org.backend.gpds.main.service;



import org.backend.gpds.main.dto.request.EntrepotCreateDTO;
import  org.backend.gpds.main.dto.request.EntrepotUpdateDTO;
import org.backend.gpds.main.model.Entrepot;

import java.util.List;

public interface EntrepotService {

    Entrepot createEntrepot(EntrepotCreateDTO dto);

    Entrepot updateEntrepot(Long id, EntrepotUpdateDTO dto);

    void deleteEntrepot(Long id);

    List<Entrepot> getAllEntrepots();

    Entrepot getEntrepotById(Long id);
}
