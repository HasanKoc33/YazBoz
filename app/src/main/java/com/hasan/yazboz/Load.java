package com.hasan.yazboz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Load extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Handler hndler = new Handler();
        hndler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Load.this,MainActivity.class);
                startActivity(i);
            }
        }, 300);


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Handler hndler = new Handler();
        hndler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Load.this,MainActivity.class);
                startActivity(i);
            }
        }, 300);

    }
}
