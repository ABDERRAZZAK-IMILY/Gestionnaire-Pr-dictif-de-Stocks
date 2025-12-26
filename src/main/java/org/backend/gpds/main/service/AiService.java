package org.backend.gpds.main.service;

import org.backend.gpds.main.model.HistoriqueVente;
import org.backend.gpds.main.model.Prevision;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AiService {

    Prevision generatePredictions(Long produitId ,Long enrepotID);
    double calculateAverageSpeed(List<HistoriqueVente> history);
}
