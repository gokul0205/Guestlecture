package com.example.guestlec;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    EditText email,pass,user;
    FirebaseAuth firebaseAuth;
    TextView already_signed_up;
    Button SignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        email=findViewById(R.id.atvEmailReg);
        pass=findViewById(R.id.atvPasswordReg);
        user=findViewById(R.id.username);
        SignUp=findViewById(R.id.btnSignUp);
        firebaseAuth= FirebaseAuth.getInstance();
        already_signed_up=findViewById(R.id.tvSignIn);
        SharedPreferences sharedPref = getSharedPreferences("pref",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email_id=email.getText().toString();
                String password=pass.getText().toString();
                if(email_id.trim().isEmpty()) {
                    email.setError("Cannot be empty!");
                    email.requestFocus();
                }
                if(password.trim().isEmpty())
                {
                    pass.setError("Cannot be empty!");
                    pass.requestFocus();
                }
                if(!email_id.trim().isEmpty() && !password.trim().isEmpty()){
                    firebaseAuth.createUserWithEmailAndPassword(email_id,password).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                editor.putString(email_id,user.getText().toString());
                                editor.commit();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this,"Sign Up unsuccessful,please try again!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        already_signed_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });
    }
}
