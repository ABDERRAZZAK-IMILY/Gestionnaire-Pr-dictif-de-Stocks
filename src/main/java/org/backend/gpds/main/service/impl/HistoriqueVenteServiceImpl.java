package org.backend.gpds.main.service.impl;

import lombok.RequiredArgsConstructor;
import org.backend.gpds.main.model.HistoriqueVente;
import org.backend.gpds.main.model.Product;
import org.backend.gpds.main.model.User;
import org.backend.gpds.main.repository.jpa.ProduitRepository;
import org.backend.gpds.main.repository.jpa.UserRepository;
import org.backend.gpds.main.repository.mongo.HistoriqueVenteRepository;
import org.backend.gpds.main.service.HistoriqueVenteService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class HistoriqueVenteServiceImpl implements HistoriqueVenteService {

    private final HistoriqueVenteRepository repository;
    private final ProduitRepository produitRepository;
    private final UserRepository userRepository;

    @Override
    public void enregistrerVente(Long produitId, int quantite) {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Product product = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        LocalDate today = LocalDate.now();

        HistoriqueVente vente = HistoriqueVente.builder()
                .produitId(produitId)
                .produitNom(product.getNom())
                .entrepotId(currentUser.getEntrepot().getId())
                .entrepotNom(currentUser.getEntrepot().getNom())
                .quantiteVendue(quantite)
                .dateVente(today)
                .jourSemaine(today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH))
                .mois(today.getMonthValue())
                .annee(today.getYear())
                .build();

        repository.save(vente);
    }

    @Override
    public List<HistoriqueVente> getHistoriqueAdmin() {
        return repository.findAll();
    }

    @Override
    public List<HistoriqueVente> getHistoriqueGestionnaire() {
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByLogin(login).get();
        return repository.findByEntrepotId(currentUser.getEntrepot().getId());
    }
}