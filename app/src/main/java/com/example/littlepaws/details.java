package com.example.littlepaws;

import static com.example.littlepaws.Registration.ron;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class details extends AppCompatActivity {
    public static String xyz;
    EditText signupName;
    EditText pin_code;
    EditText state_name;
    EditText city_name;
    EditText phone_number;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        signupButton=findViewById(R.id.button);
        pin_code=findViewById(R.id.editTextTextPostalAddress);
        state_name=findViewById(R.id.editTextText3);
        city_name=findViewById(R.id.editTextText2);
        signupName=findViewById(R.id.editTextText);
        phone_number=findViewById(R.id.editTextPhone);

//


        signupButton.setEnabled(false);
        pin_code.addTextChangedListener(loginTextWatcher);
        state_name.addTextChangedListener(loginTextWatcher);
        city_name.addTextChangedListener(loginTextWatcher);
        signupButton.addTextChangedListener(loginTextWatcher);
       pin_code.addTextChangedListener(loginTextWatcher);



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=FirebaseDatabase.getInstance();
                reference =database.getReference("users1");
//
                String mail=ron;
                String name=signupName.getText().toString();

                String p=pin_code.getText().toString();
                String s=state_name.getText().toString();
                String c=city_name.getText().toString();
                String ph=phone_number.getText().toString();
                xyz=ph;
                HelperClass helperClass=new HelperClass(name,p,s,c,mail,ph);
                reference.child(ph).setValue(helperClass);
                Intent intentx=new Intent(details.this,getstarted.class);
              intentx.putExtra("keyname",xyz);
                Toast.makeText(details.this," signed in successfully",Toast.LENGTH_SHORT).show();
                startActivity(intentx);
                finish();
            }
        });
    }
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String name = signupName.getText().toString();
            String phone = phone_number.getText().toString();
            String state_details = state_name.getText().toString();
            String city_details = city_name.getText().toString();
            String pin_details=pin_code.getText().toString();


//            VALIDATION

            if(name.length()==0){
                signupName.setError("Field is required!");
            }

            if(phone.length()>0 && phone.length()!=10){
                phone_number.setError("Invalid Mobile Number!");
            }
            if (state_details.length()>0 && state_details.length()<2 ){
                state_name.setError("Field is required!");
            }
            if (pin_details.length()>0 && pin_details.length()!=6){
                pin_code.setError("Enter a valid Pin Code!");
            }

            // Enable the button only if all fields are not empty
           signupButton.setEnabled(true);
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}