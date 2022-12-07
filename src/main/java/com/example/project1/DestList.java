package com.example.project1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class DestList {
    private final SimpleStringProperty Name;
    private final SimpleStringProperty Address;
    private final SimpleStringProperty Rating;
    private final SimpleStringProperty Price;
    private final SimpleStringProperty PlaceId;

    public DestList(String Name, String Address, String Rating, String Price, String PlaceId) {
        this.Name = new SimpleStringProperty(Name);
        this.Address = new SimpleStringProperty(Address);
        this.Rating = new SimpleStringProperty(Rating);
        this.Price = new SimpleStringProperty(Price);
        this.PlaceId = new SimpleStringProperty(PlaceId);
    }

    public String getName(){
        return Name.get();
    }

    public String getAddress(){
        return Address.get();
    }

    public String getRating(){
        return Rating.get();
    }
    public String getPrice(){
        return Price.get();
    }
    public String getPlaceId(){
        return PlaceId.get();
    }

    //setters
    public void setName(String value){Name.set(value);}
    public void setAddress(String value){Address.set(value);}
    public void setRating(String value){Rating.set(value);}
    public void setPrice(String value){Price.set(value);}
    public void setPlaceId(String value){PlaceId.set(value);}

    //Property Values

    public SimpleStringProperty NameProperty(){return Name;}
    public SimpleStringProperty AddressProperty(){return Address;}
    public SimpleStringProperty RatingProperty(){return Rating;}
    public SimpleStringProperty PriceProperty(){return Price;}
    public SimpleStringProperty PlaceIdProperty(){return PlaceId;}
}


