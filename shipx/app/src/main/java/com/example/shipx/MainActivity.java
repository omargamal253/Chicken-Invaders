package com.example.shipx;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



   private GameView gameView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView  =new GameView(this) ;
        setContentView(gameView );


    }

   @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();

    }
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        GameView gameView ;

    }
}






/*
public class MainActivity extends AppCompatActivity {


    private Game  game ;
    private Handler handler = new Handler() ;
    private final static long Interval = 30 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game(this ) ;
        setContentView(game);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        game.invalidate();
                    }
                });
            }
        } , 0, Interval );
    }
}
*/