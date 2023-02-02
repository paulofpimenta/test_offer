package com.api.demo.repository;

import com.api.demo.model.UserApi;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserApi, String> {
    @Query("{'id': ?0}")
    Optional<UserApi> findById(String id);

}
