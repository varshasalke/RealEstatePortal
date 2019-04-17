package com.example.RealEstatePortal.controller;

import com.example.RealEstatePortal.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")

public class AddressController {

@Autowired
    private com.example.RealEstatePortal.repos.addressRepo addressRepo;

@Autowired
    private MongoTemplate mongoTemplate;

@RequestMapping(value="/addAddress",method = RequestMethod.POST)
    public ResponseEntity<?> addAddress(@RequestBody Address address){
    addressRepo.save(address);
    return ResponseEntity.ok(address);
}


//@RequestMapping(value="/updateAddress",method = RequestMethod.PUT)
//    public ResponseEntity<?> updateAddress(@RequestBody Address Address){
//
//    addressRepo.findById(id);
//    if (locality == null) { Address.setLocality(addressRepo.findById(id).getLocality()); }
//    else { Address.setLocality(locality); }
//
//    if (city == null) { Address.setCity(addressRepo.findById(id).getCity()); }
//    else { Address.setCity(city); }
//
//    if (state == null) { Address.setState(addressRepo.findById(id).getState()); }
//    else { Address.setState(state); }
//
//    if (pincode == null) { Address.setPincode(addressRepo.findById(id).getPincode()); }
//    else { Address.setPincode(pincode); }
//
//
//    addressRepo.save(Address);
//    return ResponseEntity.ok(Address);
//}


}
