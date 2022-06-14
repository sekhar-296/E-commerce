package com.stackroute.shopsListService.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pincode;
    private String landmark;



}
