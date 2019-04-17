package com.example.RealEstatePortal.repos;

import com.example.RealEstatePortal.model.Agent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface agentRepos extends MongoRepository<Agent,String> {
    Agent findById(String aid);
}
