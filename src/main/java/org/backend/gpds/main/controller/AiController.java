package org.backend.gpds.main.controller;


import org.backend.gpds.main.model.Prevision;
import org.backend.gpds.main.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService){
        this.aiService = aiService;
    }

    @PostMapping("predict/{produitId}/{entrepotId}")
    public ResponseEntity<Prevision>triggerPrediction(
            @PathVariable Long produitId,
            @PathVariable Long entrepotId
    ){
        Prevision results = aiService.generatePredictions(produitId , entrepotId);
        return ResponseEntity.ok(results);
    }
}
