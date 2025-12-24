package org.backend.gpds.main.controller;


import org.backend.gpds.main.dto.*;
import org.backend.gpds.main.dto.request.ProduitAdminDTO;
import org.backend.gpds.main.dto.request.ProduitCreateDTO;
import org.backend.gpds.main.dto.request.ProduitUpdateDTO;
import org.backend.gpds.main.mapper.ProduitMapper;
import org.backend.gpds.main.service.ProduitService;
import jakarta.validation.Valid;
import org.backend.gpds.main.service.impl.ProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitServiceImpl produitService;


    @Autowired
    public ProduitController(ProduitServiceImpl produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public List<?> getAll(Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        return produitService.findAll()
                .stream()
                .map(p -> isAdmin
                        ? ProduitMapper.toAdminDTO(p)
                        : ProduitMapper.toGestionnaireDTO(p))
                .toList();
    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Long id, Authentication authentication) {

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        return isAdmin
                ? ProduitMapper.toAdminDTO(produitService.findById(id))
                : ProduitMapper.toGestionnaireDTO(produitService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProduitAdminDTO create(@RequestBody @Valid ProduitCreateDTO dto) {
        return ProduitMapper.toAdminDTO(produitService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ProduitAdminDTO update(
            @PathVariable Long id,
            @RequestBody @Valid ProduitUpdateDTO dto) {
        return ProduitMapper.toAdminDTO(produitService.update(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produitService.delete(id);
    }
}
