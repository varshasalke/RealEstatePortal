package com.example.RealEstatePortal.controller;

import com.example.RealEstatePortal.model.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AgentController {
    @Autowired
    private com.example.RealEstatePortal.repos.agentRepos agentRepos;

    @Autowired
    private MongoTemplate mongoTemplate;


    @RequestMapping(value = "/addAgent", method = RequestMethod.POST)
    public ResponseEntity<?> addPost(@RequestBody Agent agent) {
        agentRepos.save(agent);
        return ResponseEntity.ok(agent);

    }


    //Update Agent
    @RequestMapping(value = "/updateAgent/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAgent(@RequestParam String id, @RequestBody Agent agent) {

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        mongoTemplate.findOne(query, Agent.class);
        agentRepos.save(agent);
        return ResponseEntity.ok(agent);

    }


    // Delete Agent
    @RequestMapping(value = "/deleteAgent/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> deleteAgent(@RequestParam String id) {
        Agent agent = agentRepos.findById(id);
        mongoTemplate.findAndRemove(new Query(Criteria.where("id").is(id)), Agent.class);
        return ResponseEntity.ok(agent);
    }

//    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
//    public ResponseEntity<?> searchProperty(@RequestParam String id) {
//        Agent Agent=agentRepos.findById(id);
//        // Properties Properties = propertiesRepo.findById(id);
//        return ResponseEntity.ok(Agent);
//
//    }
}
