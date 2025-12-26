package org.backend.gpds.main.repository.mongo;

import org.backend.gpds.main.model.Prevision;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrevisionRepository extends MongoRepository<Prevision,String> {
}
