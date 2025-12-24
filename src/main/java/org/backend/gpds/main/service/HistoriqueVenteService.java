package org.backend.gpds.main.service;

import org.backend.gpds.main.model.HistoriqueVente;

import java.util.List;

public interface HistoriqueVenteService {

    void enregistrerVente(Long produitId, int quantite);
    List<HistoriqueVente> getHistoriqueAdmin();
    List<HistoriqueVente> getHistoriqueGestionnaire();
}
