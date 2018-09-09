package com.neelk.srchacks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import static com.android.volley.VolleyLog.TAG;


public class HomeFragment extends Fragment {

    private TextView article1;
    private TextView article2;
    private TextView article3;
    private de.hdodenhof.circleimageview.CircleImageView image1;
    private de.hdodenhof.circleimageview.CircleImageView image2;
    private de.hdodenhof.circleimageview.CircleImageView image3;


    public HomeFragment() {


    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        article1 = view.findViewById(R.id.textViewArticle1);
        article2 = view.findViewById(R.id.textViewArticle2);
        article3 = view.findViewById(R.id.textViewArticle3);
        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);



        GetNewsInfo.getNewsJSON();
        GetNewsInfo.parseJSON();
        System.out.println(Arrays.toString(GetNewsInfo.getArticleInfoArrayList().toArray()));
        setText();
        setImages();
        setOnClickListeners();
        // articleTitles.add("hello");
        // mImageUrls.add("https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1497645355/04-banff-national-park-moraine-lake-canada-150CANADA0617.jpg?itok=O6eAZSSV");

        return view;
    }

    public void setText(){

        String article1Text = (String) GetNewsInfo.getArticleInfoArrayList().get(0).get(0);
        String article2Text = (String) GetNewsInfo.getArticleInfoArrayList().get(1).get(0);
        String article3Text = (String) GetNewsInfo.getArticleInfoArrayList().get(2).get(0);

        article1.setText(article1Text);
        article2.setText(article2Text);
        article3.setText(article3Text);
    }

    public void setImages(){

        String url1 = (String) GetNewsInfo.getArticleInfoArrayList().get(0).get(3);
        String url2  = (String) GetNewsInfo.getArticleInfoArrayList().get(1).get(3);
        String url3  = (String) GetNewsInfo.getArticleInfoArrayList().get(2).get(3);

        Picasso.get().load(url1).into(image1);
        Picasso.get().load(url2).into(image2);
        Picasso.get().load(url3).into(image3);



    }

    public void setOnClickListeners(){

        final String article1TextUrl = (String) GetNewsInfo.getArticleInfoArrayList().get(0).get(2);
        final String article2TextUrl  = (String) GetNewsInfo.getArticleInfoArrayList().get(1).get(2);
        final String article3TextUrl  = (String) GetNewsInfo.getArticleInfoArrayList().get(2).get(2);

        article1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article1TextUrl));
                startActivity(browserIntent);
            }

        });

        article2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article2TextUrl));
                startActivity(browserIntent);
            }

        });

        article3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(article3TextUrl));
                startActivity(browserIntent);
            }

        });
    }
}


