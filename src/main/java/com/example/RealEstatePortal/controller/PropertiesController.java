package com.example.RealEstatePortal.controller;

import com.example.RealEstatePortal.config.CustomAggregationOperation;
import com.example.RealEstatePortal.model.Agent;
import com.example.RealEstatePortal.model.Properties;
import com.example.RealEstatePortal.service.FileStorageService;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@RestController
@RequestMapping(value = "/api")
public class PropertiesController{

    @Autowired
    private com.example.RealEstatePortal.repos.propertiesRepo propertiesRepo;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private com.example.RealEstatePortal.repos.agentRepos agentRepos;

    @RequestMapping(value = "/addProperty", method = RequestMethod.POST)
    public ResponseEntity<?> addProperty(
            @RequestParam(required = false) String id,
            @RequestParam String propertyFor,
            @RequestParam Boolean isFeaturedProperty,
            @RequestParam String projectName,
            @RequestParam String state,
            @RequestParam String city,
            @RequestParam String locality,
            @RequestParam String address,
            @RequestParam String fullName,
            @RequestParam String contact,
            @RequestParam String email,
            @RequestParam String price,
            @RequestParam String maintenanceCharges,
            @RequestPart("photo") MultipartFile photo,
            @RequestParam String propertyType,
            @RequestParam Boolean furnishedStatus,
            @RequestParam String bedrooms,
            @RequestParam String bathrooms,
            @RequestParam String floor,
            @RequestParam Boolean openSides,
            @RequestParam Boolean roadFacing,
            @RequestParam String area,
            @RequestParam String plotArea,
            @RequestParam String plotLength,
            @RequestParam String plotBreadth,
            @RequestParam Boolean visible,
            @RequestParam String agentId) {


        Properties properties = new Properties();
        Agent agent = agentRepos.findById(agentId);
        properties.setAgent(agent);
        properties.setId(id);
        properties.setPropertyFor(propertyFor);
        properties.setIsFeaturedProperty(isFeaturedProperty);
        properties.setProjectName(projectName);
        properties.setCity(city);
        properties.setState(state);
        properties.setLocality(locality);
        properties.setAddress(address);
        properties.setFullName(fullName);
        properties.setContact(contact);
        properties.setEmail(email);
        properties.setPrice(price);
        properties.setMaintenanceCharges(maintenanceCharges);
        properties.setPropertyType(propertyType);
        properties.setFurnishedStatus(furnishedStatus);
        properties.setBedrooms(bedrooms);
        properties.setBathrooms(bathrooms);
        properties.setFloor(floor);
        properties.setOpenSides(openSides);
        properties.setRoadFacing(roadFacing);
        properties.setArea(area);
        properties.setPlotArea(plotArea);
        properties.setPlotLength(plotLength);
        properties.setPlotBreadth(plotBreadth);
        properties.setVisible(visible);

        fileStorageService.storeFile(photo);
        String filename = photo.getOriginalFilename();
        properties.setImage(filename);


        propertiesRepo.save(properties);
        return ResponseEntity.ok(properties);
    }

    //search by propertfor
    @RequestMapping(value = "/searchByProperty/{propertyFor}", method = RequestMethod.GET)
    public ResponseEntity<?> searchByProperty(@PathVariable String propertyFor) {
        Query query = new Query();
        ResponseEntity<Map<String, Object>> entity = null;
        query.addCriteria(Criteria.where("propertyFor").is(propertyFor));
        List<Properties> properties = mongoTemplate.find(query, Properties.class);
        return ResponseEntity.ok(properties);
    }

    //sort by price
    @RequestMapping(value = "/sortByPrice", method = RequestMethod.GET)
    public ResponseEntity<?> sortByPrice() {
        Query query = new Query();

        query.with(new Sort(Sort.Direction.ASC, "price"));
        List<Properties> users = mongoTemplate.find(query, Properties.class);
        return ResponseEntity.ok(users);
    }


//text search

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> search(@RequestParam(value = "locality", defaultValue = " ", required = false) String locality,
                                    @RequestParam(value = "city", defaultValue = " ", required = false) String city,
                                    @RequestParam(defaultValue = " ", required = false) String state) {
        List<Properties> properties = propertiesRepo.findByLocalityOrCityOrStateLike(locality, city, state);
        return ResponseEntity.ok(properties);
    }


    //delete property
    @RequestMapping(value = "/deleteProperty/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> deleteProperty(@PathVariable String id) {
        Properties entity = null;

        entity = mongoTemplate.findAndRemove(new Query(Criteria.where("id").is(id)), Properties.class);
        return ResponseEntity.ok(entity);
    }


    //search by Propertytype
    @RequestMapping(value = "/searchByPropertyType/{propertyType}", method = RequestMethod.GET)
    public ResponseEntity<?> searchByPropertyType(@PathVariable String propertyType) {
        Query query = new Query();
        query.addCriteria(Criteria.where("propertyType").is(propertyType));
        List<Properties> properties = mongoTemplate.find(query, Properties.class);
        return ResponseEntity.ok(properties);
    }

    //search by featured property
    @RequestMapping(value = "/searchByFeaturedProperty/{isFeaturedProperty}", method = RequestMethod.GET)
    public ResponseEntity<?> searchByFeaturedProperty(@PathVariable boolean isFeaturedProperty) {
        Query query = new Query();
        ResponseEntity<Map<String, Object>> entity = null;
        query.addCriteria(Criteria.where("isFeaturedProperty").is(isFeaturedProperty));
        List<Properties> properties = mongoTemplate.find(query, Properties.class);
        return ResponseEntity.ok(properties);
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestParam String id,
                                    @RequestParam(required = false) String propertyFor,
                                    @RequestParam(required = false) Boolean isFeaturedProperty,
                                    @RequestParam(required = false) String projectName,
                                    @RequestParam(required = false) String state,
                                    @RequestParam(required = false) String city,
                                    @RequestParam(required = false) String locality,
                                    @RequestParam(required = false) String address,
                                    @RequestParam(required = false) String fullName,
                                    @RequestParam(required = false) String contact,
                                    @RequestParam(required = false) String email,
                                    @RequestParam(required = false) String price,
                                    @RequestParam(required = false) String maintenanceCharges,
                                    @RequestPart(required = false) MultipartFile photo,
                                    @RequestParam(required = false) String propertyType,
                                    @RequestParam(required = false) Boolean furnishedStatus,
                                    @RequestParam(required = false) String bedrooms,
                                    @RequestParam(required = false) String bathrooms,
                                    @RequestParam(required = false) String floor,
                                    @RequestParam(required = false) Boolean openSides,
                                    @RequestParam(required = false) Boolean roadFacing,
                                    @RequestParam(required = false) String area,
                                    @RequestParam(required = false) String plotArea,
                                    @RequestParam(required = false) String plotLength,
                                    @RequestParam(required = false) String plotBreadth,
                                    @RequestParam(required = false) Boolean visible) {

        try {
            Properties properties = propertiesRepo.findById(id);
            if (propertyFor == null) {
                properties.setPropertyFor(propertiesRepo.findById(id).getPropertyFor());
            } else {
                properties.setPropertyFor(propertyFor);
            }

            if (isFeaturedProperty) {
                properties.setIsFeaturedProperty(propertiesRepo.findById(id).getIsFeaturedProperty());
            } else {
                properties.setIsFeaturedProperty(isFeaturedProperty);
            }

            if (projectName == null) {
                properties.setProjectName(propertiesRepo.findById(id).getProjectName());
            } else {
                properties.setProjectName(projectName);
            }

            if (state == null) {
                properties.setState(propertiesRepo.findById(id).getState());
            } else {
                properties.setState(state);
            }

            if (city == null) {
                properties.setCity(propertiesRepo.findById(id).getCity());
            } else {
                properties.setCity(city);
            }


            if (locality == null) {
                properties.setLocality(propertiesRepo.findById(id).getLocality());
            } else {
                properties.setLocality(locality);
            }


            if (address == null) {
                properties.setAddress(propertiesRepo.findById(id).getAddress());
            } else {
                properties.setAddress(address);
            }

            if (fullName == null) {
                properties.setFullName(propertiesRepo.findById(id).getFullName());
            } else {
                properties.setFullName(fullName);
            }

            if (contact == null) {
                properties.setContact(propertiesRepo.findById(id).getContact());
            } else {
                properties.setContact(contact);
            }

            if (email == null) {
                properties.setEmail(propertiesRepo.findById(id).getEmail());
            } else {
                properties.setEmail(email);
            }

            if (price == null) {
                properties.setPrice(propertiesRepo.findById(id).getPrice());
            } else {
                properties.setPrice(price);
            }

            if (maintenanceCharges == null) {
                properties.setMaintenanceCharges(propertiesRepo.findById(id).getMaintenanceCharges());
            } else {
                properties.setMaintenanceCharges(maintenanceCharges);
            }

            if (photo == null) {
                properties.setImage(propertiesRepo.findById(id).getImage());
            } else {
                properties.setImage(photo.getOriginalFilename());
            }

            if (propertyType == null) {
                properties.setPropertyType(propertiesRepo.findById(id).getPropertyType());
            } else {
                properties.setPropertyType(propertyType);
            }

            if (furnishedStatus) {
                properties.setFurnishedStatus(propertiesRepo.findById(id).getFurnishedStatus());
            } else {
                properties.setFurnishedStatus(furnishedStatus);
            }

            if (bedrooms == null) {
                properties.setBedrooms(propertiesRepo.findById(id).getBedrooms());
            } else {
                properties.setBedrooms(bedrooms);
            }

            if (bathrooms == null) {
                properties.setBathrooms(propertiesRepo.findById(id).getBathrooms());
            } else {
                properties.setBathrooms(bathrooms);
            }

            if (floor == null) {
                properties.setFloor(propertiesRepo.findById(id).getFloor());
            } else {
                properties.setFloor(floor);
            }

            if (openSides) {
                properties.setOpenSides(propertiesRepo.findById(id).getOpenSides());
            } else {
                properties.setOpenSides(openSides);
            }

            if (roadFacing) {
                properties.setRoadFacing(propertiesRepo.findById(id).getRoadFacing());
            } else {
                properties.setRoadFacing(roadFacing);
            }

            if (area == null) {
                properties.setArea(propertiesRepo.findById(id).getArea());
            } else {
                properties.setArea(area);
            }

            if (plotArea == null) {
                properties.setPlotArea(propertiesRepo.findById(id).getPlotArea());
            } else {
                properties.setPlotArea(plotArea);
            }

            if (plotLength == null) {
                properties.setPlotLength(propertiesRepo.findById(id).getPlotLength());
            } else {
                properties.setPlotLength(plotLength);
            }

            if (plotBreadth == null) {
                properties.setPlotBreadth(propertiesRepo.findById(id).getPlotBreadth());
            } else {
                properties.setPlotBreadth(plotBreadth);
            }

            if (visible) {
                properties.setVisible(propertiesRepo.findById(id).getVisible());
            } else {
                properties.setVisible(visible);
            }

            propertiesRepo.save(properties);
            return ResponseEntity.ok(properties);
        } catch (Exception ex) {
            throw ex;
        }
    }


    @RequestMapping(value = "/search/{agent}", method = RequestMethod.GET)
    public ResponseEntity<?> searchProperty(@RequestParam String agent) {

        Query query = new Query();
        query.addCriteria(Criteria.where("agent").is(agent));
        List<Properties> properties = mongoTemplate.find(query, Properties.class);
        return ResponseEntity.ok(properties);

    }

//    @RequestMapping(value="/randomFeaturedProperty",method = RequestMethod.GET)
//    public ResponseEntity<?> randomFeaturedProperty(){
//        Query query=new Query();
//        Random random=new Random();
//       query.addCriteria(Criteria.where("isFeaturedProperty").is(true));
//
//
//       // List<Properties> properties = mongoTemplate.find(query, Properties.class);
//         Integer count= Math.toIntExact(mongoTemplate.count(query, Properties.class));
//         System.out.println(count);
//         random.nextInt(count);
//        System.out.println(random.nextInt(count));
//        List<Properties> properties = mongoTemplate.find(query, Properties.class);
//         return ResponseEntity.ok(properties);
//
//    }





    @RequestMapping(value="/randomFeaturedProperty",method = RequestMethod.GET)
    public ResponseEntity<?> randomFeaturedProperty1(){

        Aggregation aggregation = newAggregation(
                // custom pipeline stage
                new CustomAggregationOperation(
                        new BasicDBObject(
                                "$sample",
                                new BasicDBObject( "size", 3)
                        )
                ),
                // Standard match pipeline stage
                match(Criteria.where("isFeaturedProperty").is(true)

                )
        );


        AggregationResults data = mongoTemplate.aggregate(aggregation, "properties", AggregationResults.class);

       return ResponseEntity.ok(data.getMappedResults());






//       Aggregation aggregation = newAggregation(
//                match(Criteria.where("isFeaturedProperty").is(true)),
//                unwind("properties")
//        );
//        AggregationResults<AggregationResults> data = mongoTemplate.aggregate(aggregation, "properties", AggregationResults.class);
//
//        return ResponseEntity.ok(data);
    }


//    @RequestMapping(value="/randomFeaturedProperty",method = RequestMethod.GET)
//    public List<Properties> findRandom() {
//        CustomAggregationOperation customAggregationOperation = new CustomAggregationOperation(new BasicDBObject("$sample", new BasicDBObject("size", 4).append("isFeaturedProperty",true)));
//        TypedAggregation<Properties> aggregation = new TypedAggregation<>(Properties.class, customAggregationOperation);
//        AggregationResults<Properties> aggregationResults = mongoTemplate.aggregate(aggregation, Properties.class);
//        return aggregationResults.getMappedResults();
//    }


}
