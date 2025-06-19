package org.mosesidowu.empirezone.data.repository;

import org.mosesidowu.empirezone.data.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {

}
