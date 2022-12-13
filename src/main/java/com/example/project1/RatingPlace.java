package com.example.project1;

public class RatingPlace {
    public static String name;

    public RatingPlace(String name){
        RatingPlace.name =name;
    }

    public static String getName(){
        return RatingPlace.name;
    }
}
