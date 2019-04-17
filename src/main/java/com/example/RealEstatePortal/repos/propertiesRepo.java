package com.example.RealEstatePortal.repos;

import com.example.RealEstatePortal.model.Agent;
import com.example.RealEstatePortal.model.Properties;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface propertiesRepo extends MongoRepository<Properties,String> {

    //List<Properties> findByLocalityOrCityOrStateLike(String locality, String city, String state);

    Properties findById(String id);

    List<Properties> findByLocalityOrCityOrStateLike(String locality, String city, String state);



    //@Query("{$sample: {size: ?0} }")
   // List<Properties> findRandom(Criteria isFeaturedProperty);






    //  Properties findByAgent(List<Agent> Agent);
    // List<Properties> findByStateOrCityOrLocalityLike(String state, String city, String locality);

   // List<Properties> findByLocalityOrCityOrStateLike(String locality, String city, String state);



    // List<Properties> findByLocalityOrCityOrStateLike(String locality, String city, String state);

  //  List<Properties> findByLocalityAndCityAndStateLike(String locality, String city, String state);
}
