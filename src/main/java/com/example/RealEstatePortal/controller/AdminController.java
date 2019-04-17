package com.example.RealEstatePortal.controller;

import com.example.RealEstatePortal.config.ConfigInterface;
import com.example.RealEstatePortal.model.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class AdminController implements ConfigInterface {


    @Autowired
    private com.example.RealEstatePortal.repos.propertiesRepo propertiesRepo;

//Change Visibility
    @PutMapping(value = "/changeVisibility/{id}/{visible}")
    public ResponseEntity<?> changeVisibility(@PathVariable String id, @PathVariable boolean visible){
        Map<String,Object> map=new HashMap();
   Properties properties=propertiesRepo.findById(id);
   properties.setVisible(visible);
        propertiesRepo.save(properties);
        if(properties.getVisible()) {
            return ResponseEntity.ok(properties);
        }
        else {
                  map.clear();
                  map.put(pstatus,HttpStatus.OK);
                  return  ResponseEntity.ok(map);
            }
    }

    //isFeaturedPropertyChange
    @PutMapping(value="/isFeaturedPropertyChange/{id}/{isFeaturedProperty}")
    public ResponseEntity<?>isFeaturedProperty(@PathVariable String id,@PathVariable boolean isFeaturedProperty){
     Properties properties=propertiesRepo.findById(id);
     properties.setIsFeaturedProperty(isFeaturedProperty);
     propertiesRepo.save(properties);
     return ResponseEntity.ok(properties);
    }

}
