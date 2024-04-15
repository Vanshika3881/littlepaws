package com.example.littlepaws;


import static com.example.littlepaws.details.xyz;
import static com.example.littlepaws.login.xyzz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {
    Activity context;
    private static final String PREFS_NAME="MyPrefsFile";
    private static final String ACTION_BAR_COLOR_KEY="action_bar_color";

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=getActivity();
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences settings=requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int actionBarColor=settings.getInt(ACTION_BAR_COLOR_KEY,R.color.lavender);
        changeActionBarColor(actionBarColor);


        return root;
    }

    public void changeActionBarColor(int actionBarColor) {
        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();

        // Change the action bar color to the selected color
        actionBar.setBackgroundDrawable(getResources().getDrawable(actionBarColor));
    }

    public void onStart(){
        super.onStart();
        Button button1=(Button) context.findViewById(R.id.buttonx);
        Button button2=(Button) context.findViewById(R.id.button12);
        Button button3=(Button) context.findViewById(R.id.button13);
        Button button4=(Button) context.findViewById(R.id.button14);
//        Button button5=(Button) context.findViewById(R.id.fm);

//Rescue
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentx=new Intent(context,rescue.class);
                startActivity(intentx);
            }
        });


//      Ngo
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(context,shelter.class);
                startActivity(intent2);
            }
        });

//        Register complaint
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(context, rc.class);
                startActivity(intent3);
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(context,foster.class);
                startActivity(intent4);
            }
        });


//        button5.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent5=new Intent(context,getstarted.class);
//                intent5.putExtra("keyname",xyz);
//                startActivity(intent5);
//            }
//        });


    }



}