package com.example.littlepaws;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class rescue extends AppCompatActivity {
    Button compl;
    Button b1;
    Button b2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescue);
        compl=findViewById(R.id.buttonr);

        b1=findViewById(R.id.buttonu);
        b2=findViewById(R.id.buttonf);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(rescue.this,upl.class);
                startActivity(intent1);
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(rescue.this,ShowActivity.class);
                startActivity(intent2);
            }
        });






        compl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("https://www.change.org/decision-makers/animal-rescue");
            }

            private void goToUrl(String s) {
                Uri uri=Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });
    }
}