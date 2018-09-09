package com.neelk.srchacks;

import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class NearbyCenters {

    public static void main(String[] args) {
        NearbyCenters centers = new NearbyCenters();
        centers.findNearbyCenters();
    }

    public void findNearbyCenters(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        StringBuilder builder = new StringBuilder();
        Fragment2 fragment2 = new Fragment2();

        try {


            URL newsURL = new URL("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input=recycling%20centers&inputtype=textquery&fields=photos,formatted_address,name,opening_hours,rating&locationbias=circle:80000@" + fragment2.getLat() +  "," +  fragment2.getLng() + "&key=AIzaSyDif3Yf5hwKI16RWw64zCPQvgqfrVF3SVU");
            HttpsURLConnection yConn = (HttpsURLConnection) newsURL.openConnection();
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
        System.out.println(builder.toString());
        
    }

    private void parseCenterJson(String s) {
    }

}
