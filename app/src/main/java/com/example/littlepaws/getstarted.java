package com.example.littlepaws;

import static com.example.littlepaws.details.xyz;
import static com.example.littlepaws.login.xyzz;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getstarted extends AppCompatActivity {
    Button move;
    TextView namez,pinz,cityz,statez,phonez;
    String na,pi,st,ci,ph;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);
        namez = findViewById(R.id.editTextText);
        pinz = findViewById(R.id.editTextTextPostalAddress);
        statez = findViewById(R.id.editTextText3);
        cityz = findViewById(R.id.editTextText2);
        phonez = findViewById(R.id.editTextPhone);
        String bina = ".";
        namez.setText("Name - ".concat(bina));
        pinz.setText("Pin Code - ".concat(bina));
        statez.setText("State - ".concat(bina));
        cityz.setText("City - ".concat(bina));
        phonez.setText("Phone - ".concat(bina));

        String username = getIntent().getStringExtra("keyname");




        if (username != null && !username.isEmpty()) {
            readData(username);
        }


        move = findViewById(R.id.button);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getstarted.this,Navigation_bar.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void readData(String username) {
        reference= FirebaseDatabase.getInstance().getReference("users1");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete( Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot dataSnapshot=task.getResult();
                    na=String.valueOf(dataSnapshot.child("name").getValue());
                    pi=String.valueOf(dataSnapshot.child("pin").getValue());
                    st=String.valueOf(dataSnapshot.child("state").getValue());
                    ci=String.valueOf(dataSnapshot.child("city").getValue());
                    ph=String.valueOf(dataSnapshot.child("phone_number").getValue());
                    namez.setText("Name - ".concat(na));
                    pinz.setText("Pin - ".concat(pi));
                    statez.setText("State - ".concat(st));
                    cityz.setText("City - ".concat(ci));
                    phonez.setText("Phone - ".concat(ph));
                }
            }
        });


    }
}

//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class getstarted extends AppCompatActivity {
//    TextView namez, pinz, cityz, statez, phonez;
//    String na, pi, st, ci, ph;
//    DatabaseReference reference;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_getstarted);
//
//        // Initialize TextViews
//        namez = findViewById(R.id.editTextText);
//        pinz = findViewById(R.id.editTextTextPostalAddress);
//        statez = findViewById(R.id.editTextText3);
//        cityz = findViewById(R.id.editTextText2);
//        phonez = findViewById(R.id.editTextPhone);
//
//        // Set initial placeholder values
//        String bina = ".";
//        namez.setText("Name - ".concat(bina));
//        pinz.setText("Pin Code - ".concat(bina));
//        statez.setText("State - ".concat(bina));
//        cityz.setText("City - ".concat(bina));
//        phonez.setText("Phone - ".concat(bina));
//
//        // Get username from Intent
//        String username = getIntent().getStringExtra("keyname");
//
//        if (username != null && !username.isEmpty()) {
//            readData(username);
//        } else {
//            Log.e("GetStarted", "Invalid or empty username received");
//        }
//    }
//
//    private void readData(String username) {
//        reference = FirebaseDatabase.getInstance().getReference("users1");
//        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DataSnapshot dataSnapshot = task.getResult();
//                    if (dataSnapshot.exists()) {
//                        na = String.valueOf(dataSnapshot.child("name").getValue());
//                        pi = String.valueOf(dataSnapshot.child("pin").getValue());
//                        st = String.valueOf(dataSnapshot.child("state").getValue());
//                        ci = String.valueOf(dataSnapshot.child("city").getValue());
//                        ph = String.valueOf(dataSnapshot.child("phone_number").getValue());
//
//                        // Log retrieved values
//                        Log.d("GetStarted", "Name: " + na);
//                        Log.d("GetStarted", "Pin: " + pi);
//                        Log.d("GetStarted", "State: " + st);
//                        Log.d("GetStarted", "City: " + ci);
//                        Log.d("GetStarted", "Phone: " + ph);
//
//                        // Update TextViews
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                namez.setText("Name - ".concat(na));
//                                pinz.setText("Pin - ".concat(pi));
//                                statez.setText("State - ".concat(st));
//                                cityz.setText("City - ".concat(ci));
//                                phonez.setText("Phone - ".concat(ph));
//                            }
//                        });
//                    } else {
//                        Log.d("GetStarted", "No data found for username: " + username);
//                    }
//                } else {
//                    Log.e("GetStarted", "Error getting data", task.getException());
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.e("GetStarted", "Firebase data retrieval failed: " + e.getMessage());
//            }
//        });
//
//    }
//    }

