package com.example.project1;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CallsApi {
    public static void main( String[] args ){
        JSONParser jsonParser=new JSONParser();
        try{
            JSONObject jsonObject = (JSONObject)jsonParser.parse(new FileReader("C:\\Users\\pan_d\\IdeaProjects\\cityguidefx\\.idea\\httpRequests\\restaurant-serres.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");

            for(int i=0;i<jsonArray.size();i++) {
                JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                //  Business Status
                String business_status = (String) jsonObject1.get("business_status");

                // See if Business is Operational
                if (business_status.equals("OPERATIONAL")) {
                    System.out.println("Business status:" + business_status);
                    // Name of place
                    String name = (String) jsonObject1.get("name");
                    System.out.println("Name: " + name);

                    //place id
                    String placeId = (String) jsonObject1.get("place_id");
                    System.out.println("Place ID: " + placeId);

                    // Open right now
                    Map<Object, Object> opening_hours = (Map<Object, Object>) jsonObject1.get("opening_hours");
                    opening_hours.forEach((key, value) -> System.out.println(key + ": " + value));

                    // Rating
                    double rating = (double) jsonObject1.get("rating");
                    System.out.println("Rating:" + rating);

                    // Vicinity Address
                    String vicinity = (String) jsonObject1.get("vicinity");
                    System.out.println("Vicinity: " + vicinity);

                    //price level Βγάζει error γιατί δεν υπάρχουν σε όλα τα μαγαζιά price_level απο το API
                    // double  price_level=(Double)jsonObject1.get("price_level");
                    // System.out.println("price level:"+price_level);


                }
            }
        }
        catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
