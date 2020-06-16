package com.example.shipx;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /*

    ImageView imageView;
    Bitmap animation1;
    Bitmap animation2;
        imageView = findViewById(R.id.main_spaceship);
        animation1 = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship2_main);
        animation2 = BitmapFactory.decodeResource(getResources(), R.drawable.spaceship22_main);

*/



        Thread thread =new Thread()
        {
            @Override
            public void run() {
                try
                {
                    sleep(5000);

                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally {
                            Intent intent =new Intent(Main2Activity.this , Main3Activity.class) ;
                    startActivity(intent) ;
                }
            }
        };
        thread.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();


    }
}


