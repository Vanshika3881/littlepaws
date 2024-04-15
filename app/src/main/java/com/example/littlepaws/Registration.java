package com.example.littlepaws;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    public static String ron;
    EditText email_d;
    EditText phone_d;
    EditText address_d;
    CheckBox c;


    private Button move2;
    private EditText edit_username;
    private EditText phn_no;
    private EditText addr;
    private EditText password;
    private Button button_confirm;
    boolean passwordVisible;
    private FirebaseAuth mAuth;
    Button signup;

    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    //    Extra
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent2 = new Intent(Registration.this, details.class);
            startActivity(intent2);
            finish();
        }
    }
    //    End
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        email_d=findViewById(R.id.button15);
        phone_d=findViewById(R.id.phone_box);
        address_d=findViewById(R.id.address_box);

        
        move2 = findViewById(R.id.button5);
        edit_username = findViewById(R.id.button15);
        phn_no = findViewById(R.id.phone_box);
        addr = findViewById(R.id.address_box);
        password = findViewById(R.id.password_box);
        button_confirm = findViewById(R.id.button5);
        mAuth=FirebaseAuth.getInstance();


        // Disable the button initially
        button_confirm.setEnabled(false);

        // Add TextWatchers to all EditText fields
        edit_username.addTextChangedListener(loginTextWatcher);
        phn_no.addTextChangedListener(loginTextWatcher);
        addr.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);

//        Password Visiblity
        password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>=password.getRight()-password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=password.getSelectionEnd();
                        if(passwordVisible){
//                            Set drawable image:
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
//                           For disable password
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
//                            FOR SHOW PASSWORD
                            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });


//      FOR DATA STORAGE
















//SWITCHING ACTIVITY
        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password1;
                email=String.valueOf(edit_username.getText());
                ron=email;
                password1=String.valueOf(password.getText());
finish();



                try {
                    Intent intent2 = new Intent(Registration.this, details.class);
                    startActivity(intent2);

                } catch (Exception e) {
                    e.printStackTrace();
                }


//                Firebase code for Registration

                mAuth.createUserWithEmailAndPassword(email, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task ){
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Registration.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();


                                }
                                else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Registration.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();


                                }
                            }
                        });

            }
        });

//        DATABASE




    }

    // TextWatcher for all EditText fields
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String name = edit_username.getText().toString();
            String phone = phn_no.getText().toString();
            String address = addr.getText().toString();
            String pass = password.getText().toString();


//            VALIDATION

            if(name.length()==0){
                edit_username.setError("Field is required!");
            }

            if(phone.length()>0 && phone.length()!=10){
                phn_no.setError("Invalid Mobile Number!");
            }
            if(pass.length()>0){
                if(pass.length()<8){
                    password.setError("Minimum 8 characters required!");
                }
                else if(pass.matches("(.*[A-Z].*)")==false){
                    password.setError("Atleast one Uppercase!");
                }
                else if(pass.matches("(.*[0-9].*)")==false){
                    password.setError("Atleast one digit!");
                }
                else if(pass.matches("(?=.*[_.()$@]).*")==false){
                    password.setError("Atleast one special character!");

                }
            }

            // Enable the button only if all fields are not empty
            button_confirm.setEnabled(!name.isEmpty() && !phone.isEmpty() && !address.isEmpty() && !pass.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}
