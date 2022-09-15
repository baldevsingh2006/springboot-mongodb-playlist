package com.visitpi.visitor.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.visitpi.visitor.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
	
	@Query("{'name': ?0}")
	Optional<User> findByName(String user);

}
