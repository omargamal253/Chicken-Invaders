package com.example.shipx;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
    }
    public void num1(View view) {

        Intent intent =new Intent(Main6Activity.this , MainActivity2.class)   ;
        startActivity(intent) ;
    }
    public void num2(View view) {


        Intent intent =new Intent(Main6Activity.this , MainActivity.class)   ;
        startActivity(intent) ;
    }
    public void num3(View view) {


        Intent intent =new Intent(Main6Activity.this , MainActivity3.class)   ;
        startActivity(intent) ;
    }
}
