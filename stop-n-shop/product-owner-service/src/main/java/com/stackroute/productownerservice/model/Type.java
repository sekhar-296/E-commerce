package com.stackroute.productownerservice.model;

public enum Type {
    ORGANIZATION("Organization"),
    TITLE("Title");
    private String type;

    Type(String type){
        this.type=type;
    }

    public String getName(){
        return type;
    }
}
