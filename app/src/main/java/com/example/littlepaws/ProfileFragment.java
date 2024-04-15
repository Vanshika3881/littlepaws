package com.example.littlepaws;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ProfileFragment extends Fragment {

    Activity context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        context=getActivity();
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_profile,container,false);
        return root;
    }
    public void onStart(){
        super.onStart();
        Button m= context.findViewById(R.id.Button19);

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri= Uri.parse("https://forms.gle/cgGcurkzxwEkutsM7");
                Intent intentw=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intentw);
            }
        });

    }

}