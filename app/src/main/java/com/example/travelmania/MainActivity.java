package com.example.travelmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.travelmania.common.LocalSession;
import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity {
    LocalSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session=new LocalSession(MainActivity.this);

        FirebaseApp.initializeApp(MainActivity.this);


        //code to navigate to the next screen without any clicking event

        new Handler().postDelayed(() ->{
            if(session.getLoginStatus()){
                startActivity(new Intent(MainActivity.this, DrawerHomeActivity.class));
            }
            else{
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
            MainActivity.this.finish();
        },1000);
    }
}