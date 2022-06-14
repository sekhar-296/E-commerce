package com.stackroute.shopsListService.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@AllArgsConstructor
@Getter
@Setter
public class Shop {

    @Id
    private String email;

    private String shopName;
    private Address address;
    private Location location;
    private String rating;
    private byte[] shopImage;
    private String ownerName;
    private String contactNo;
    private String description;

    public Shop(){
        address=new Address();
        location=new Location();
    }


}
