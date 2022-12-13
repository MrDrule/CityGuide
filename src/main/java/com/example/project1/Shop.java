package com.example.project1;

public class Shop {

    public Integer town_id;

    public String type;

    public String placeID;

    public String name;
    public String open_now;
    public double rating;
    public String vicinity;

    public double price_level;

    public Shop(Integer town_id, String type , String placeID , String name , String open_now, Double rating, String vicinity, Double price_level){
        this.town_id = town_id;
        this.type = type;
        this.placeID = placeID;
        this.name = name;
        this.open_now = open_now;
        this.rating = rating;
        this.vicinity = vicinity;
        this.price_level = price_level;
    }

    public Shop(){

    }


}