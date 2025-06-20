package org.mosesidowu.empirezone.data.repository;

import org.mosesidowu.empirezone.data.models.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdRepository extends MongoRepository<Ad, String> {

}
