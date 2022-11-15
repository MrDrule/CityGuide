/*package com.example.project1;

import org.json.JSONArray;
import org.json.JSONObject;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class mainAPI {

    private static HttpURLConnection connection;

    private static int count=0;

    public static void main(String[] args) {
        ArrayList<String> requests = new ArrayList<String>();
        //SERRES
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=5000&type=restaurant&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=5000&type=cafe&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=5000&type=museum&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //DRAMA
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.149632%2C24.148287&radius=5000&type=restaurant&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.149632%2C24.148287&radius=5000&type=cafe&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.149632%2C24.148287&radius=5000&type=museum&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //KAVALA
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.939556%2C24.401867&radius=5000&type=restaurant&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.939556%2C24.401867&radius=5000&type=cafe&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.939556%2C24.401867&radius=5000&type=museum&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //KILKIS
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.995049%2C22.876435&radius=5000&type=restaurant&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.995049%2C22.876435&radius=5000&type=cafe&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.995049%2C22.876435&radius=5000&type=museum&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");

        for (int i=0;i<requests.size();i++) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requests.get(i))).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(mainAPI::parse)
                    .join();
        }

    }

    public static void parse(String responseBody) {


        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        int shopID=0;
        count=count+1;

        int n = jsonArray.length();

        for(int i=0;i<n;i++) {
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

                // Opening hours
                JSONObject open_now=null;
                String open_close=false+"";
                try{
                    open_now=jsonObject1.getJSONObject("opening_hours");
                    open_close=open_now.toString();
                } catch (Exception e){
                    open_close="Not Available";
                }
                System.out.println(""+open_close);


                // Rating
                double rating=0;
                if(jsonObject1.toMap().containsKey("rating")) {
                    rating = ((Number) jsonObject1.get("rating")).doubleValue();
                }
                System.out.println("Rating:" + rating);

                // Vicinity Address
                String vicinity = (String) jsonObject1.get("vicinity");
                System.out.println("Vicinity: " + vicinity);


                //Price Level
                double price_level=0;
                if(jsonObject1.toMap().containsKey("price_level"))  {
                    price_level= ((Number) jsonObject1.get("price_level")).doubleValue();
                }
                System.out.println(price_level);

                //types
                ArrayList<String> type=new ArrayList<String>();
                JSONArray types =jsonObject1.getJSONArray("types");
                System.out.println(""+types);

                //town_id
                int town_id;
                if (count<=3){
                    town_id=1;
                }else if (count<=6){
                    town_id=2;
                }else if (count<=9){
                    town_id=3;
                }else{
                    town_id=4;
                }
                System.out.println(town_id);
                System.out.println(" ");

                //add shop to database
                addShopToDatabase(Integer.valueOf(town_id), placeId, name, open_close.toString(), rating, vicinity, types.toString(),price_level);

            }

        }

    }
    public static Shop addShopToDatabase(Integer town_id, String placeID, String name, String open_now, double rating, String vicinity, String type,double price_level){
        Shop shop = null;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/cityguide", "root", "");
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO places (town_id,place_id,name, open_now,rating, vicinity, type,price_level) VALUES (?,?,?, ?, ?, ?, ?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, town_id);
            preparedStatement.setString(2, placeID);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, open_now);
            preparedStatement.setDouble(5, rating);
            preparedStatement.setString(6, vicinity);
            preparedStatement.setString(7, type);
            preparedStatement.setDouble(8,price_level);
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
                shop.price_level=price_level;
            }

            stmt.close();
            conn.close();
        } catch (Exception var17) {
            var17.printStackTrace();
        }

        return shop;


    }


}*/