package com.niit.authenticationservice.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address {

    private String streetName;
    private String city;
    private String landMark;
    private String state;
    private String pin;
    private String nationality;
}
