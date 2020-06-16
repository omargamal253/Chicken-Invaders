package com.example.shipx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void play(View view) {
        Intent intent =new Intent(Main3Activity.this , MainActivity2.class)   ;
        startActivity(intent) ;
    }
    public void level(View view) {
        Intent intent =new Intent(Main3Activity.this , Main6Activity.class)   ;
        startActivity(intent) ;
    }

}
