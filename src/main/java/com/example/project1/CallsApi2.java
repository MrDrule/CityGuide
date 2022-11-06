package com.example.project1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.math.BigDecimal;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;


public class CallsApi2 {

    private static HttpURLConnection connection;

    public static void main(String[] args) {
        ArrayList<String> requests = new ArrayList<String>();
        //SERRES
        requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=2000&type=cafe&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=2000&type=restaurants&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.088904%2C23.546338&radius=2000&type=museum&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //DRAMA
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.149632%2C24.148287&radius=4000&type=restaurant&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.149632%2C24.148287&radius=4000&type=cafe&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=41.149632%2C24.148287&radius=4000&type=museum&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //KAVALA
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.939556%2C24.401867&radius=3000&type=restaurant&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.939556%2C24.401867&radius=3000&type=cafe&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.939556%2C24.401867&radius=3000&type=museum&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //KILKIS
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.995049%2C22.876435&radius=3000&type=restaurant&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.995049%2C22.876435&radius=3000&type=cafe&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");
        //requests.add("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=40.995049%2C22.876435&radius=3000&type=museum&key=AIzaSyCuxY5FNOMQ89uQ0YrHFWzlfn-B5n3Mhcg");

        for (int i=0;i<requests.size();i++) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(requests.get(i))).build();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    //.thenAccept(mainAPI::parse)
                    .join();


        }

    }

    public static void parse(String responseBody) {

        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        int shopID=0;

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


                //  Map<Object, Object> opening_hours = (Map<Object, Object>) jsonObject1.get("opening_hours");
                //  opening_hours.forEach((key, value) -> System.out.println(key + ": " + value));

                // Rating
                BigDecimal rating = (BigDecimal) (jsonObject1.get("rating"));
                System.out.println("Rating:" + rating);

                // Vicinity Address
                String vicinity = (String) jsonObject1.get("vicinity");
                System.out.println("Vicinity: " + vicinity);

                shopID++;
                System.out.println(shopID);


                // price level Βγάζει error γιατί δεν υπάρχουν σε όλα τα μαγαζιά price_level απο το API
                // double  price_level=(Double)jsonObject1.get("price_level");
                // System.out.println("price level:"+price_level);


            }


        }

    }

}
