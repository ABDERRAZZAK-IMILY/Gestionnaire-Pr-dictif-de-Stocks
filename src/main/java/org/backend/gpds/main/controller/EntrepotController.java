package org.backend.gpds.main.controller;

import org.backend.gpds.main.dto.*;
import org.backend.gpds.main.dto.request.EntrepotCreateDTO;
import org.backend.gpds.main.dto.request.EntrepotDTO;
import org.backend.gpds.main.dto.request.EntrepotUpdateDTO;
import org.backend.gpds.main.mapper.EntrepotMapper;
import org.backend.gpds.main.service.EntrepotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrepots")
@RequiredArgsConstructor
public class EntrepotController {

    private final EntrepotService entrepotService;

    // ADMIN uniquement
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public EntrepotDTO create(@RequestBody @Valid EntrepotCreateDTO dto) {
        return EntrepotMapper.toDTO(entrepotService.createEntrepot(dto));
    }

    // ADMIN uniquement
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public EntrepotDTO update(
            @PathVariable Long id,
            @RequestBody @Valid EntrepotUpdateDTO dto) {
        return EntrepotMapper.toDTO(entrepotService.updateEntrepot(id, dto));
    }

    // ADMIN uniquement
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        entrepotService.deleteEntrepot(id);
    }

    // ADMIN (et éventuellement GESTIONNAIRE en lecture)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<EntrepotDTO> getAll() {
        return entrepotService.getAllEntrepots()
                .stream()
                .map(EntrepotMapper::toDTO)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public EntrepotDTO getById(@PathVariable Long id) {
        return EntrepotMapper.toDTO(entrepotService.getEntrepotById(id));
    }
}
