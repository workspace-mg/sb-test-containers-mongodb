package com.gajula.sbtestcontainersmongodb.repository;

import com.gajula.sbtestcontainersmongodb.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
