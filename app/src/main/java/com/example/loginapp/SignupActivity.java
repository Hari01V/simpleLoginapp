package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText et_email , et_password;
    private Button btn_signup;
    private TextView tv_signin;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance();
        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        btn_signup=findViewById(R.id.btn_signup);
        tv_signin=findViewById(R.id.tv_signin);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                if(email.isEmpty()){
                    et_email.setError("Email_Id is required !");
                    et_email.requestFocus();
                }
                if(password.isEmpty()){
                    et_password.setError("Password is required !");
                    et_password.requestFocus();
                }
                if(!(email.isEmpty() && password.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!(task.isSuccessful())){
                                Toast.makeText(SignupActivity.this,"Unsuccessful Login",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(SignupActivity.this,Home_Activity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(SignupActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
