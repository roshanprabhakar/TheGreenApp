package com.neelk.srchacks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private Button login;
    private FirebaseAuth mAuth;
    private Button signup;
    private TextView forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        Button signup = (Button) findViewById(R.id.sgnup);
        Button login = (Button) findViewById(R.id.login);
        final EditText emailField = (EditText) findViewById(R.id.editTextEmail);
        final EditText passwordField = (EditText) findViewById(R.id.editTextPassword) ;
        TextView forgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String userEmail = emailField.getText().toString().trim();
                final String userPassword = passwordField.getText().toString().trim();

                if (TextUtils.isEmpty(userEmail)) {

                    Toast.makeText(MainActivity.this, "You must enter an email", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (TextUtils.isEmpty(userPassword)) {

                    Toast.makeText(MainActivity.this, "You must enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(userEmail, userPassword)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    startActivity(new Intent(MainActivity.this, Home.class));
                                    //startActivity(new Intent(MainActivity.this, UserArea.class));

                                } else {
                                    Toast.makeText(MainActivity.this, "Login Failed, Please try Again", Toast.LENGTH_SHORT).show();
                                            return;
                                }
                            }
                        });

            }

        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, SignUP.class));

            }


        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, ForgotPassword.class));
            }

        });
    }

}