package com.example.project1;

public class Dest {
    public static String Name;
    public static String Address;
    public static Double Rating;
    public static Double Price;


    public Dest (String on, String di, Double aks, Double pr){
        this.Name =on;
        this.Address=di;
        this.Rating=aks;
        this.Price = pr;
    }

    public Dest(){
        this.Name=null;
        this.Address=null;
        this.Rating=null;
        this.Price = null;
    }

    public String getName(){
        return this.Name;
    }

    public String getAddress(){return this.Address;}

    public Double getRating(){return this.Rating;}

    public Double getPrice(){return this.Price;}



}

