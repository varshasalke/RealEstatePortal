package com.example.RealEstatePortal.repos;

import com.example.RealEstatePortal.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface addressRepo extends MongoRepository<Address, String> {
    //Address findById(String id);
}
