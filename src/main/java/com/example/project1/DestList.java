package com.example.project1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DestList{
    private final SimpleStringProperty Name;
    private final SimpleStringProperty Address;
    private final SimpleDoubleProperty Rating;
    private final SimpleDoubleProperty Price;

    public DestList(String Name, String Address, Double Rating, Double Price){
        this.Name= new SimpleStringProperty(Name);
        this.Address = new SimpleStringProperty(Address);
        this.Rating = new SimpleDoubleProperty(Rating);
        this.Price = new SimpleDoubleProperty(Price);
    }

    //getters
    public String getName() {
        return Name.get();
    }


    public String getAddress() {
        return Address.get();
    }

    public Double getRating() {
        return Rating.get();
    }

    public Double getPrice(){return Price.get();}

    //setters
    public void setName(String value){
        Name.set(value);
    }
    public void setAddress(String value){
        Address.set(value);
    }
    public void setRating(Double value){Rating.set(value);}
    public void setPrice(Double value){Price.set(value);}

    //Property Values

    public SimpleStringProperty NameProperty(){
        return Name;
    }
    public SimpleStringProperty AddressProperty(){
        return Address;
    }
    public SimpleDoubleProperty RatingProperty(){
        return Rating;
    }
    public SimpleDoubleProperty PriceProperty(){
        return Price;
    }
}

