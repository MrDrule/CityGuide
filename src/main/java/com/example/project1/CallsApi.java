package com.example.project1;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;

public class CallsApi {
    public static void main( String[] args ){
        JSONParser jsonParser=new JSONParser();
        try{
            JSONObject jsonObject = (JSONObject)jsonParser.parse(new FileReader(".idea//httpRequests//restaurant-serres.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            int shopID=0;

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

                    //print BS
                    System.out.println("Buissness status:" + business_status);

                    // Rating
                    double rating = (double) jsonObject1.get("rating");
                    System.out.println("Rating:" + rating);

                    // Vicinity Address
                    String vicinity = (String) jsonObject1.get("vicinity");
                    System.out.println("Vicinity: " + vicinity);

                    //price level Βγάζει error γιατί δεν υπάρχουν σε όλα τα μαγαζιά price_level απο το API
                    // double  price_level=(Double)jsonObject1.get("price_level");
                    // System.out.println("price level:"+price_level);
                    shopID++;
                    System.out.println(shopID);

                    int town_id=4;
                    String type="museum";


                    addShopToDatabase(town_id,placeId, name, opening_hours.toString(), rating, vicinity,type);


                }
            }
        }
        catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }
    public static Shop addShopToDatabase(Integer town_id, String placeID, String name, String open_now, double rating, String vicinity, String type) {
        Shop shop = null;
        String DB_URL = "jdbc:mysql://localhost/cityguide";
        String USERNAME = "root";
        String PASSWORD = "";

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cityguide", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO places (town_id,place_id,name, open_now,rating, vicinity, type) VALUES (?,?,?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, town_id);
            preparedStatement.setString(2, placeID);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, open_now);
            preparedStatement.setDouble(5, rating);
            preparedStatement.setString(6, vicinity);
            preparedStatement.setString(7, type);
            preparedStatement.executeUpdate();
            int addedRows = preparedStatement.executeUpdate();
            if (addedRows > 0) {
                shop = new Shop();
                shop.placeID = placeID;
                shop.name = name;
                shop.open_now = open_now;
                shop.rating = (float)rating;
                shop.vicinity = vicinity;
                shop.town_id = town_id;
                shop.type = type;
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shop;
    }
}
