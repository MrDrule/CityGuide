package com.example.project1;

import javafx.beans.property.SimpleStringProperty;
import org.controlsfx.control.Rating;

import javax.lang.model.element.Name;

public class Dest {
    public static String Name;
    public static String Address;
    public static String Rating;
    public static String Price;
    public static String PlaceId;


    public Dest (String na, String ad, String ra, String pr, String pl){
        this.Name =na;
        this.Address=ad;
        this.Rating=ra;
        this.Price = pr;
        this.PlaceId = pl;
    }
    public Dest() {
        this.Name = null;
        this.Address = null;
        this.Rating = null;
        this.Price = null;
        this.PlaceId = null;
    }


    //getters

    public String getName(){return this.Name;}

    public String getAddress(){return this.Address;}

    public String getRating(){return this.Rating;}

    public String getPrice(){return this.Price;}

    public String getPlaceId(){return this.PlaceId;}}







