package com.neelk.srchacks;

import android.os.StrictMode;

import com.google.gson.JsonArray;

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

public class GetNewsInfo {

    private static String jsonAsString;
    private static ArrayList <ArrayList> articleInfo;



    public static void getNewsJSON() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        StringBuilder builder = new StringBuilder();

        try {

            URL newsURL = new URL("https://newsapi.org/v2/top-headlines?sources=national-geographic&apiKey=adc1ac57c0e6401eaf1108934cf03268");
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
        jsonAsString = builder.toString();
    }

    public static ArrayList<ArrayList> parseJSON(){
            articleInfo = new ArrayList<>(3);

        try {
            JSONObject  jResponse =  new JSONObject(jsonAsString);
            JSONArray articles =  (JSONArray) jResponse.get("articles");
            for(int i = 0; i<3; i++){

                ArrayList<String> currentArticleInfo = new ArrayList<String>(4);
                JSONObject currentArticle = (JSONObject) articles.get(i);
                String currentAuthor = (String) currentArticle.get("author");
                String currentArticleTitle =  (String) currentArticle.get("title");
                String currentArticleURL = (String) currentArticle.get("url");
                String currentArticleImageURL = (String) currentArticle.get("urlToImage");

                currentArticleInfo.add(currentArticleTitle);
                currentArticleInfo.add(currentAuthor);
                currentArticleInfo.add(currentArticleURL);
                currentArticleInfo.add(currentArticleImageURL);
                articleInfo.add(currentArticleInfo);
                }





        } catch (JSONException e) {
            e.printStackTrace();
        }
            return articleInfo;
    }

    public static ArrayList<ArrayList> getArticleInfoArrayList() {
        return articleInfo;
    }

}
