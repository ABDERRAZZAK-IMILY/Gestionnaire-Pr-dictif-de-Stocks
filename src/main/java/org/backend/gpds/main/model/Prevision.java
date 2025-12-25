package org.backend.gpds.main.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@Document(collection = "previsions")
public class Prevision {

    private String Id;
    private Long productId;
    private Long EntrepotId;
    private LocalDate datePrevision;
    private Integer quantitePrevue30Jours;
    private Double niveauConfiance;
    private String recommandation;
}
