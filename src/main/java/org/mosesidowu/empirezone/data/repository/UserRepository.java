package org.mosesidowu.empirezone.data.repository;

import org.mosesidowu.empirezone.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUsersByEmail(String email);

    Optional<User> findUsersByPassword(String password);

    Optional<User> existsByEmail(String email);

    Optional<User> existsByPhoneNumber(String phoneNumber);
}
