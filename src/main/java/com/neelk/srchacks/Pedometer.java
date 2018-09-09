package com.neelk.srchacks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Pedometer extends Fragment {

    private TextView noOfStep;
    private Button prizes;

    public Pedometer() {
    }



    public static Pedometer newInstance() {
        Pedometer fragment = new Pedometer();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_pedometer, container, false);

        noOfStep = view.findViewById(R.id.noStepsTextView);
        prizes = view.findViewById(R.id.prizesButton);

        setPrizesOnClick();

        try {
            simulateSteps();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void setPrizesOnClick() {

        prizes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PrizesActivity.class));
            }
        });
    }

    private void simulateSteps() throws InterruptedException {
        for (int i = 0; i < 50; i++){
            Thread.sleep(1000);
            noOfStep.setText(Integer.toString(i));
        }
    }

}
