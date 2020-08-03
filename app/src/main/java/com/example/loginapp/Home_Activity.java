package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home_Activity extends AppCompatActivity {

    private TextView tv_content;
    private Button btn_signout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final int TIME_INTERVAL = 2000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        tv_content=findViewById(R.id.tv_content);
        btn_signout=findViewById(R.id.btn_signout);

        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Home_Activity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onBackPressed() {

        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()){
            FirebaseAuth.getInstance().signOut();
            finishAffinity();
        }
        else{
            Toast.makeText(getBaseContext(), "Click again to Exit",Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();

    }

}
