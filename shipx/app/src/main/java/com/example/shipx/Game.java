package com.example.shipx;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;

public class Game extends View  {

      public static  int x= 30 , y = 450 , Yper ;

    private  Bitmap [] ship= new Bitmap[2];
    private int shipx =30 ;  private int shipy ;
    private int shipSpeed ;
    private int canvasWidth  ,  canvasHieght;
    private  boolean touch = false ;

    private  Bitmap background;
    private Paint scorepaint = new Paint();
    private  Bitmap [] life = new Bitmap[2];


    public Game(Context context) {
        super(context);

        background= BitmapFactory.decodeResource(getResources() , R.drawable.background) ;

        ship[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
        ship[1] = BitmapFactory.decodeResource(getResources(), R.drawable.ship_up);

        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD) ;
        scorepaint.setAntiAlias(true);

        life[1] = BitmapFactory.decodeResource(getResources() , R.drawable.heart1) ;
        life[0] = BitmapFactory.decodeResource(getResources() , R.drawable.heart2) ;


        shipy= 450 ;

    }
    @Override
    protected void  onDraw(Canvas  canvas){
     super.onDraw(canvas);

         canvasWidth = canvas.getWidth() ;
         canvasHieght = canvas.getHeight() ;

        int minShipY = ship[0].getHeight() ;
        int maxShipY =canvasHieght - ship[0].getHeight()*3 ;

        shipy =shipy+shipSpeed ;

       if(shipy<minShipY){

            shipy=minShipY ;
        }
        if(shipy>maxShipY){
             shipy=maxShipY ;
        }
        shipSpeed = shipSpeed +2 ;

        if(touch){
            canvas.drawBitmap(ship[1] , shipx , shipy , null );
            touch = false ;
        }else {
            canvas.drawBitmap(ship[0] , shipx , shipy , null );
        }


        canvas.drawBitmap(background, 0 , 0 , null);


    canvas.drawBitmap(ship[0], x, y, null);

     canvas.drawText("Score : ", 40, 150, scorepaint);

        canvas.drawBitmap(life[0], 750, 90, null);
        canvas.drawBitmap(life[0], 860, 90, null);
        canvas.drawBitmap(life[0], 970, 90, null);
 }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

       try {
           Thread.sleep(150);
       }catch (InterruptedException e){
           e.printStackTrace();
       }
      if(event.getAction() == MotionEvent.ACTION_DOWN){
          touch = true ;
           shipSpeed = -22 ;
           x = (int)event.getX() -400 ;
           y =(int) event.getY() -400 ;


       }
        if(event.getAction() == MotionEvent.ACTION_UP){
           // y= y+10 ;
            x = (int)event.getX() -400 ;
            y =(int) event.getY() -500 ;

        }
       if(event.getAction() == MotionEvent.ACTION_MOVE){

           x = (int)event.getX() -400 ;
           y =(int) event.getY() -400 ;

            if(y<Yper){
                ship[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ship_up);
            }else{
                ship[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ship_down);
            }

        }else{
           ship[0] = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
       }
         Yper = y ;
        return true ;
    }
}




/*
    while (true){

            if (ship_count == 0) {
                ship = BitmapFactory.decodeResource(getResources(), R.drawable.ship2);

                ship_count++;
            } else {
                ship = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
                ship_count--;
            }

        }
 */
