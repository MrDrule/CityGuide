package com.example.project1;

public class RatingPlace {
    public static String name;
    public static String id;

    public RatingPlace(String name,String place_id){
        RatingPlace.id=place_id;
        RatingPlace.name =name;
    }

    public static String getName(){
        return RatingPlace.name;

    }
    public static String getId(){
        return RatingPlace.id;
    }
}
