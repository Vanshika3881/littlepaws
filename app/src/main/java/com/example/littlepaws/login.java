package com.example.littlepaws;
import static com.example.littlepaws.Registration.ron;
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
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import static com.example.littlepaws.details.xyz;

public class login extends AppCompatActivity {
///

    public static String xyzz;
    private Button move2;
//    private EditText ph_db;
    private Button move3;
    private EditText edit_username;
    private EditText edit_password;
    private Button button_confirm;
    boolean passwordVisible;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent11 = new Intent(login.this, Navigation_bar.class);
            startActivity(intent11);
            finish();

        }
    }
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        move2 = findViewById(R.id.button5);
        move3=findViewById(R.id.button6);
        edit_username = findViewById(R.id.button15);
        edit_password = findViewById(R.id.password_box);
        button_confirm = findViewById(R.id.button5);
        button_confirm.setEnabled(false);
//        ph_db=findViewById(R.id.button16);
        edit_username.addTextChangedListener(loginTextWatcher);
        edit_password.addTextChangedListener(loginTextWatcher);





//        new
//        ph_db.addTextChangedListener(loginTextWatcher);


//        new
        mAuth=FirebaseAuth.getInstance();

        edit_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int Right=2;
                if(event.getAction()==MotionEvent.ACTION_UP)
                {
                    if(event.getRawX()>=edit_password.getRight()-edit_password.getCompoundDrawables()[Right].getBounds().width()){
                        int selection=edit_password.getSelectionEnd();
                        if(passwordVisible){
//                            Set drawable image:
                            edit_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_off_24,0);
//                           For disable password
                            edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else{
                            edit_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.baseline_visibility_24,0);
//                            FOR SHOW PASSWORD
                            edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        edit_password.setSelection(selection);
                        return true;
                    }
                }
                return false;
            }
        });



        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password11;


                email=String.valueOf(edit_username.getText());
                password11=String.valueOf(edit_password.getText());
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }



//                Firebase Code
                mAuth.signInWithEmailAndPassword(email, password11)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
//                                    String ph1=ph_db.getText().toString();
//                                    xyzz=ph1;
                                    Toast.makeText(login.this, "Login successful",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent11 = new Intent(login.this, Navigation_bar.class);

                                    startActivity(intent11);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

//                end






            }
        });
//        For switching between the activities


        move3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(login.this,Registration.class);
                startActivity(intent4);
                finish();
            }
        });
    }



    private void setSupportActionBar(Toolbar toolbar) {
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            String phone_login=ph_db.getText().toString();
//            if(phone_login.length()>0 && phone_login.length()!=10){
//               ph_db.setError("Invalid Mobile Number!");
//            }


            String usernameInput = edit_username.getText().toString();
            String passwordInput = edit_password.getText().toString();
            button_confirm.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}
