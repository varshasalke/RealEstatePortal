package com.example.RealEstatePortal.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Data
@Getter
@Setter
public class Agent {
    @Id
    private String id;
    private String name;
    private String email;
    private String contact;

    private Address address;


}
