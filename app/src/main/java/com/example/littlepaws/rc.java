package com.example.littlepaws;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class rc extends AppCompatActivity {
    Button b1;
    Button b2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc);

        b1=findViewById(R.id.bl);


//        --------------------------Code for going to link-----------------------

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://thepetnest.com/report-animal-abuse#:~:text=In%20India%2C%20to%20report%20cruelty,area%20where%20the%20cruelty%20happens");
            }
        });


//        For Helpline Number
        b2=findViewById(R.id.cb);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:+919820122602"));
                startActivity(intent1);
            }
        });






    }

    private void gotoUrl(String s) {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}