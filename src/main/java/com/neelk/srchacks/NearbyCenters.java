package com.neelk.srchacks;


import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import static android.os.StrictMode.*;

public class NearbyCenters {

    private static String jsonAsString;
    private  static String  key = "AIzaSyDif3Yf5hwKI16RWw64zCPQvgqfrVF3SVU";
    private static String hardCodedUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=37.548700,-122.058975&radius=30000&keyword=recycling&key=" + key;




    public static void findNearbyCenters() {

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                     ThreadPolicy policy = new ThreadPolicy.Builder().permitAll().build();
                // setThreadPolicy(policy);
                StringBuilder builder = new StringBuilder();
                Fragment2 fragment2 = new Fragment2();

                try {


                    // URL placesRequest = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + fragment2.getLat() + "," + fragment2.getLng()  + "&radius=30000&type=restaurant&keyword=indian&key=" + key);
                    URL placesRequest = new URL(hardCodedUrl);
                    HttpsURLConnection yConn = (HttpsURLConnection) placesRequest.openConnection();
                    yConn.setRequestProperty("Accept", "application/json");
                    if (yConn.getResponseCode() == 200) {
                        InputStream responseBody = yConn.getInputStream();
                        InputStreamReader responseBodyReader =
                                new InputStreamReader(responseBody, "UTF-8");
                        try (BufferedReader in = new BufferedReader(responseBodyReader)) {
                            String line;
                            while ((line = in.readLine()) != null) {
                                builder.append(line); // + "\r\n"(no need, json has no line breaks!)
                            }
                            in.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jsonAsString = builder.toString();


            }
        });
        thread.start();


    }

    public static ArrayList<ArrayList> parseCenterJson() throws JSONException {

        ArrayList<ArrayList> placesInfo = new ArrayList<>(3);
            System.out.println(jsonAsString);
            JSONObject jResponse = new JSONObject(jsonAsString);
            JSONArray results = (JSONArray) jResponse.get("results");

            for(int i = 0; i < 3; i++) {
                ArrayList<Object> place = new ArrayList<>(4);
                JSONObject firstElement = (JSONObject) results.get(i);
                String name = firstElement.getString("name");
                JSONObject geometry = (JSONObject) firstElement.get("geometry");
                JSONObject location = (JSONObject) geometry.get("location");
                Double placeLat = Double.parseDouble(location.getString("lat"));
                Double placeLong = Double.parseDouble(location.getString("lng"));
                String address = firstElement.getString("vicinity");
                place.add(name);
                place.add(address);
                place.add(placeLat);
                place.add(placeLong);

                placesInfo.add(place);


            }

            return placesInfo;


    }

}
