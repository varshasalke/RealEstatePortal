package com.example.RealEstatePortal.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;


@Data
@Getter
@Setter
public class Properties {

    @Id
    private String id;
    private String propertyFor;
    private Boolean isFeaturedProperty=true;


    //Properties location
    private String projectName;
    @TextIndexed
    private String state;
    @TextIndexed
    private String city;
    @TextIndexed
    private String locality;
    private String address;

    //Contact Information
    private String fullName;
    private String contact;
    private String email;

    //Sale Information
    private String price;
    private String maintenanceCharges;
    private String image;

    //property info
    private String propertyType;
    private Boolean furnishedStatus;
    private String bedrooms;
    private String bathrooms;
    private String floor;
    private Boolean openSides;
    private Boolean roadFacing;
    private String area;
    private String plotArea;
    private String plotLength;
    private String plotBreadth;

    //for admin
    private Boolean visible=true;

    //Agent
    @DBRef
    private Agent agent;

}
