package org.backend.gpds.main.service.impl;

import org.backend.gpds.main.dto.request.HistoriqueVente;
import org.backend.gpds.main.exeptions.ResourceNotFoundException;
import org.backend.gpds.main.repository.jpa.StockRepository;
import org.backend.gpds.main.repository.mongo.HistoriqueVenteRepository;
import org.backend.gpds.main.repository.mongo.PrevisionRepository;
import org.springframework.ai.chat.client.ChatClient;

import java.util.List;

public class AiServiceImpl {

    private ChatClient chatClient;

    private  HistoriqueVenteRepository historiqueVenteRepository;
    private  PrevisionRepository previsionRepository;
    private  StockRepository stockRepository;


    public AiServiceImpl(ChatClient.Builder builder){
        this.chatClient = builder.build();
    }

    public void GeneratePredictions(Long productId ,Long entrepotId){

        List<HistoriqueVente> history = historiqueVenteRepository.findByProductIdAndEntrepotId(productId,entrepotId).orElseThrow(()
                -> new ResourceNotFoundException("THER'S no History for this productId and entrepotId"));

            Integer currentStock =stockRepository.findByProductIdAndIntrepotId(productId,entrepotId).orElseThtrow(()->new ResourceNotFoundException("THER'S no History for this productId and entrepotId"));



    }




}
