package org.backend.gpds.main.controller;

import lombok.AllArgsConstructor;
import org.backend.gpds.main.dto.request.VenteRequestDTO;
import org.backend.gpds.main.service.VenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ventes")
@AllArgsConstructor
public class VenteController {

    private final VenteService venteService;

    @PostMapping("/record")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>recordSale(@RequestBody @Valid VenteRequestDTO dto){
        venteService.processSale(dto);
        return ResponseEntity.ok().build();
    }




}
