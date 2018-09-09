package com.neelk.srchacks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PedometerActivity extends AppCompatActivity {

    private TextView noOfStep;
    private Button prizes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);

        noOfStep = findViewById(R.id.noStepsTextView);
        prizes = findViewById(R.id.prizesButton);

        setPrizesOnClick();


    }

    private void setPrizesOnClick() {

        prizes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PedometerActivity.this, PrizesActivity.class));
            }
        });
    }



}
