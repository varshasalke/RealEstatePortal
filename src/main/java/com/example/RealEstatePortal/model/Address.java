package com.example.RealEstatePortal.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class Address {

    // private String id;
    private String locality;
    private String city;
    private String state;
    private String pincode;
}
