package com.example.shipx;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements  Runnable{
    private Thread thread ;
    private boolean isPlaying=true ;
    public long  count_bullet_move =0 ;


    private static  int x= 500 , y = 1850 , Xper ,   count = 0 , score = 0 , count_f =1 , count_life  ;
     private boolean stopMove = false ;


    private  Bitmap ship;
    private int shipx =30 ;  private int shipy ;
   private int minShipY  , maxShipY ;
    private int shipSpeed ;
    private int canvasWidth  ,  canvasHeight;
    private  boolean touch = false ;

    private  Bitmap background;
    private Paint scorepaint = new Paint();
    private  Bitmap [] life = new Bitmap[3];
    private Bitmap pata1  , pata2 ;
         private int p1x   ,p1y ;
         private int p2x   ,p2y ;
             private int pataSpeed = 20 ;

    shipGhost  ghost_finsh = new shipGhost(200, -100 , 300 ,  getResources() , 5)  ;
    /*shipGhost  ghost1 = new shipGhost(10, getResources() , 1)  ;
    shipGhost  ghost2 = new shipGhost(5, getResources() , 2)  ;
*/

    List<GhostColor> ghost_color_list = new ArrayList<>() ;
     public int maxHeight =50 ;
    GhostColor c1 = new GhostColor(50 , -200 , 550 + maxHeight, getResources() ,-1)  ;
    GhostColor c2 = new GhostColor(180, -150 , 700 + maxHeight, getResources() ,-1)  ;
    GhostColor c3 = new GhostColor(320, -100 , 850 + maxHeight, getResources(), -1)  ;
    GhostColor c4 = new GhostColor(560 , -100 , 850+ maxHeight, getResources(), -1)  ;
    GhostColor c5 = new GhostColor(710 , -150 , 700+ maxHeight, getResources(), -1)  ;
    GhostColor c6 = new GhostColor(860 , -200 , 550+ maxHeight, getResources(), -1)  ;

  //  GhostColor c13 = new GhostColor(450 , -400 , 750 + maxHeight, getResources() ,4)  ;

   GhostColor c7 = new GhostColor(50 , -800 , 300 + maxHeight, getResources() ,3)  ;
    GhostColor c8 = new GhostColor(200, -750 , 450 + maxHeight, getResources() ,3)  ;
    GhostColor c9 = new GhostColor(350, -700 , 600 + maxHeight, getResources(), 3)  ;
    GhostColor c10 = new GhostColor(580 , -700 , 600+ maxHeight, getResources(), 3)  ;
    GhostColor c11 = new GhostColor(730 , -750 , 450+ maxHeight, getResources(), 3)  ;
    GhostColor c12 = new GhostColor(880 , -800 , 300+ maxHeight, getResources(), 3)  ;

    GhostColor c14 = new GhostColor(450 , -1000 , 450+ maxHeight, getResources(), -1)  ;
    GhostColor c15 = new GhostColor(350 , -1000 , 300+ maxHeight, getResources(), -1)  ;
    GhostColor c16 = new GhostColor(650 , -1000 , 300+ maxHeight, getResources(), -1)  ;

// Bitmap Button
    private  Bitmap fireButton_down , fireButton_up ;
// Bullet list
        List<Bullet> bullet_list = new ArrayList<>() ;
      List<Ghost_bullet> bullet_ghost_list = new ArrayList<>() ;


     private Bitmap animation_sheet ;




    public GameView(Context context) {

        super(context);
        shipy= 1750 ;
        count_life =3 ;
        background= BitmapFactory.decodeResource(getResources() , R.drawable.background2) ;

        ship = BitmapFactory.decodeResource(getResources(), R.drawable.ship);


        scorepaint.setColor(Color.WHITE);
        scorepaint.setTextSize(70);
        scorepaint.setTypeface(Typeface.DEFAULT_BOLD) ;
        scorepaint.setAntiAlias(true);
        life[0] = BitmapFactory.decodeResource(getResources() , R.drawable.heart2) ;
        life[1] = BitmapFactory.decodeResource(getResources() , R.drawable.heart2) ;
        life[2] = BitmapFactory.decodeResource(getResources() , R.drawable.heart2) ;

        pata1 = BitmapFactory.decodeResource(getResources() , R.drawable.aa) ;
        pata2= BitmapFactory.decodeResource(getResources() , R.drawable.aa) ;
        int pataW , pataH ;
        pataW = pata1.getWidth() -20 ;
        pataH = pata1.getHeight() -20 ;
      pata1 = Bitmap.createScaledBitmap(pata1 ,pataW ,pataH , false ) ;
      pata2 = Bitmap.createScaledBitmap(pata1 ,pataW ,pataH , false ) ;


         /*ghost1.setx(50) ;
        ghost1.sety(-100) ;

        ghost2.setx(100 ) ;
        ghost2.sety(-200) ;
*/


        ghost_color_list.add(c1) ;
        ghost_color_list.add(c2) ;
        ghost_color_list.add(c3) ;
        ghost_color_list.add(c4) ;
        ghost_color_list.add(c5) ;
        ghost_color_list.add(c6) ;

        ghost_color_list.add(c7) ;
        ghost_color_list.add(c8) ;
        ghost_color_list.add(c9) ;
        ghost_color_list.add(c10) ;
        ghost_color_list.add(c11) ;
        ghost_color_list.add(c12) ;
       // ghost_color_list.add(c13) ;

        ghost_color_list.add(c14) ;
        ghost_color_list.add(c15) ;
        ghost_color_list.add(c16) ;

        fireButton_down = BitmapFactory.decodeResource(getResources() , R.drawable.fire1_down) ;
       // fireButton_up = BitmapFactory.decodeResource(getResources() , R.drawable.fire1_up) ;
        fireButton_down = Bitmap.createScaledBitmap(fireButton_down , 250 , 250 ,false ) ;
        //fireButton_up = Bitmap.createScaledBitmap(fireButton_up , 250 , 250 ,false ) ;

          animation_sheet = BitmapFactory.decodeResource(getResources() , R.drawable.type_last);

        //   animation_sheet = BitmapFactory.decodeResource(getResources() , R.drawable.tybe_lll);

        //animation_sheet = Bitmap.createScaledBitmap(animation_sheet , 7200 , 150 , false) ;
         animation_sheet = Bitmap.createScaledBitmap(animation_sheet , 10560 , 220 , false) ;


    }

    @Override
    public void run() {
        while (isPlaying){
            update() ;
            draw();
            sleep() ;
        }
    }
    public  void update() {

        p1y = p1y +pataSpeed ;
       if(collosin(p1x ,p1y , (int)pata1.getWidth()) ) {
            score+= 5 ;
           p1y= 0 - 100 ;
           p1x = (int)  Math.floor(Math.random() * canvasWidth)  -20 ;
        }
        if(p1y> maxShipY){
            p1y= 0 - 20 ;
            p1x = (int)  Math.floor(Math.random() * canvasWidth)  -20  ;
        }
        if(p1x <20 ){
            p1x +=20 ;
        }
        ///
        p2y = p2y +pataSpeed ;
        if(collosin(p2x ,p2y, (int)pata2.getWidth()) ) {
            score+= 10 ;
            p2y= 0 - 100 ;
            p2x = (int)  Math.floor(Math.random() * canvasWidth)  -20  ;
        }
        if(p2y> maxShipY){
            p2y= 0 - 950 ;
            p2x = (int)  Math.floor(Math.random() * canvasWidth)  -20  ;
        }
        if(p2x <20 ){
            p2x +=20 ;
        }

      /*  ghost1.moving(x,y);
        ghost2.moving(x,y);*/

      if( ghost_finsh.start == true ) {
          ghost_finsh.moving(x, y);
          if (ghost_finsh.collosin_ghost_color(ship, x, y)) {
              stopMove = true;
          }
          for (int j = 0; j < bullet_list.size(); j++) {
              if (bullet_list.get(j).bullet_collision(ghost_finsh.getBitmap(animation_sheet), ghost_finsh.getx(), ghost_finsh.gety()) &&
                      ghost_finsh.num_bullet < 21) {
                  bullet_list.remove(j);
                  ghost_finsh.set_ghost_die_or_not();

                  Ghost_bullet new_Bullet = new Ghost_bullet(ghost_finsh.getx(), ghost_finsh.gety(), getResources());
                  bullet_ghost_list.add(new_Bullet);
                  //

              }

          }

          if(ghost_finsh.timer_()) {
              Ghost_bullet new_Bullet = new Ghost_bullet(ghost_finsh.getx(), ghost_finsh.gety(), getResources());
              bullet_ghost_list.add(new_Bullet);
          }
      }

        for(int i=0 ;i<ghost_color_list.size() ;i++){
            ghost_color_list.get(i).moving(x,y);

            if(ghost_color_list.get(i).collosin_ghost_color(ship , x ,y  )) {
            stopMove=true ;
            }
            //bullet collision loop

            for(int j=0 ;j<bullet_list.size() ; j++){
             if(   bullet_list.get(j).bullet_collision(ghost_color_list.get(i).getBitmap(animation_sheet) , ghost_color_list.get(i).getx() , ghost_color_list.get(i).gety()) &&
                     ghost_color_list.get(i).num_bullet < 6) {
                 bullet_list.remove(j);
                 ghost_color_list.get(i).set_ghost_die_or_not();


                 //if(bullet_list.get(j).remove_from_list())  bullet_list.remove(j);
                 //


                 Ghost_bullet new_Bullet = new Ghost_bullet(ghost_color_list.get(i).getx(), ghost_color_list.get(i).gety(), getResources());
                 bullet_ghost_list.add(new_Bullet);
                 //

             }

            }


        }




        //bullet moving loop

        for(int j=0 ;j<bullet_list.size(); j++){
            bullet_list.get(j).moving();

        }
        //bullet ghost moving loop and colliosn
        for(int j=0 ;j<bullet_ghost_list.size(); j++){
            bullet_ghost_list.get(j).moving();

            if (bullet_ghost_list.get(j).bullet_ghost_collision(ship , x , y ) ){
                stopMove=true ;
                bullet_ghost_list.remove(j );

            }

          else  if(bullet_ghost_list.get(j).remove_from_list())   bullet_ghost_list.remove(j );


        }

        if(ghost_color_list.size()  == 0) ghost_finsh.start = true ;

       if( ghost_finsh.finsh_game()){
           Intent intent =new Intent(getContext() , Main5Activity.class)   ;
           getContext().startActivity(intent) ;

       }

    }
    public  void draw() {

        if(getHolder().getSurface().isValid()){
            Canvas canvas  = getHolder().lockCanvas() ;

            canvasWidth = (int) canvas.getWidth() ;
            canvasHeight = (int)canvas.getHeight() ;

            minShipY = ship.getHeight() ;
            maxShipY =canvasHeight - ship.getHeight() ;

            canvas.drawBitmap(background, 0 , 0 , null);

            canvas.drawBitmap(fireButton_down , canvasWidth -300 ,canvasHeight-300, null);

            canvas.drawBitmap(ship, x, y, null);

            canvas.drawText("Score : "+score, 40, 150, scorepaint);

            canvas.drawBitmap(life[0], 750, 80, null);
            canvas.drawBitmap(life[1], 860, 80, null);
            canvas.drawBitmap(life[2], 970, 80, null);
            canvas.drawBitmap(pata1 , p1x ,p1y , null);
            canvas.drawBitmap(pata2 , p2x ,p2y , null);


            for(int i=0 ;i<ghost_color_list.size() ;i++){
                canvas.drawBitmap(ghost_color_list.get(i).getBitmap(animation_sheet) , ghost_color_list.get(i).getx() ,ghost_color_list.get(i).gety(), null);
                if(ghost_color_list.get(i).Remove_from_list())
                {
                    ghost_color_list.remove(i) ;
                    if(ghost_color_list.size()  % 4 == 0)
                    {
                        for(int j=0 ;j<ghost_color_list.size() ;j++){
                            ghost_color_list.get(j).setMaxHeight(200);
                        }
                    }

                }

            }

            if( ghost_finsh.start == true )
            canvas.drawBitmap(ghost_finsh.getBitmap(animation_sheet) , ghost_finsh.getx() ,ghost_finsh.gety(), null);


            if(stopMove==true) collision_ship_die() ;

             for(int i=0 ;i<bullet_list.size(); i++){
                canvas.drawBitmap(bullet_list.get(i).getBitmap() , bullet_list.get(i).getx() ,bullet_list.get(i).gety(), null);
                 if ( bullet_list.get(i).remove_bullet_from_list()) bullet_list.remove(i) ;

             }
            for(int i=0 ;i<bullet_ghost_list.size(); i++){
                canvas.drawBitmap(bullet_ghost_list.get(i).getBitmap() , bullet_ghost_list.get(i).getx() ,bullet_ghost_list.get(i).gety(), null);

            }



            getHolder().unlockCanvasAndPost(canvas);


        }
    }
    public  void sleep() {

        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void resume(){
        isPlaying = true ;
        thread = new Thread( (this)) ;
        thread.start();
    }
    public  void pause(){
        try {
            isPlaying = false ;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public boolean collosin (int a , int b , int pataW ){
    /* if (  x < a && a < ( x + ship.getWidth() ) && y < b && b < ( y + ship.getHeight() ) )
     {
        return true ;
     }*/
        if (  ( (x >=a && x<=a+pataW) || x < a ) &&  a <= ( x + ship.getWidth() ) && y < b && b < ( y + ship.getHeight() ) )        {
            return true ;
        }
     return false ;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (! stopMove) {


            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                shipSpeed = -22;

                Rect button_rect = new Rect(canvasWidth -300 ,canvasHeight -300 , ( canvasWidth -300) +fireButton_down.getWidth() , (canvasHeight -300) +fireButton_down.getHeight()) ;
              int  touch_x = (int) event.getX();
              int  touch_y = (int) event.getY();

             if   ( button_rect.contains(touch_x ,touch_y )){
                 fireButton_down = BitmapFactory.decodeResource(getResources() , R.drawable.fire1_up) ;
                 fireButton_down = Bitmap.createScaledBitmap(fireButton_down , 250 , 250 ,false ) ;

                 Bullet newBullet = new Bullet(x,y,getResources()) ;
                 bullet_list.add(newBullet) ;
             }




            } else if (event.getAction() == MotionEvent.ACTION_UP) {


                Rect button_rect = new Rect(canvasWidth -300 ,canvasHeight -300 , ( canvasWidth -300) +fireButton_down.getWidth() , (canvasHeight -300) +fireButton_down.getHeight()) ;
                int  touch_x = (int) event.getX();
                int  touch_y = (int) event.getY();

                if   ( button_rect.contains(touch_x ,touch_y )){
                    fireButton_down = BitmapFactory.decodeResource(getResources() , R.drawable.fire1_down) ;
                    fireButton_down = Bitmap.createScaledBitmap(fireButton_down , 250 , 250 ,false ) ;

                }


            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

               Rect button_rect = new Rect(canvasWidth -300 ,canvasHeight -300 , ( canvasWidth -300) +fireButton_down.getWidth() , (canvasHeight -300) +fireButton_down.getHeight()) ;
                int  touch_x = (int) event.getX();
                int  touch_y = (int) event.getY();

                if   ( button_rect.contains(touch_x ,touch_y )){
                    fireButton_down = BitmapFactory.decodeResource(getResources() , R.drawable.fire1_down) ;
                   fireButton_down = Bitmap.createScaledBitmap(fireButton_down , 250 , 250 ,false ) ;

                }

             else {

                    touch = true;
                    x = (int) event.getX() -  (ship.getWidth() /2 );
                    y = (int) event.getY() -250;

                    if (x > Xper ) {
                        ship = BitmapFactory.decodeResource(getResources(), R.drawable.ship_right);
                    } else if (x < Xper) {
                        ship = BitmapFactory.decodeResource(getResources(), R.drawable.ship_left);
                    } else {
                        ship = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
                    }

                }

             if(count_bullet_move % 12 ==0) {
                 Bullet newBullet = new Bullet(x, y, getResources());
                 bullet_list.add(newBullet);
             } count_bullet_move++ ;

            } else {
                touch = false;
                ship = BitmapFactory.decodeResource(getResources(), R.drawable.ship);


            }
            Xper = x;

        }
        if ( count_bullet_move > 100000000)  count_bullet_move =0 ;
        return true;
    }

   public void collision_ship_die(){

     //   stopMove = true ;
       if(count_f==1) {
           count_life--;
           if (count_life == 2) {
               life[2] = BitmapFactory.decodeResource(getResources(), R.drawable.heart1);
           } else if (count_life == 1) {
               life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart1);

           } else if (count_life == 0) {
               life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart1);
               try {
                   Thread.sleep(100);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
               Intent intent =new Intent(getContext() , Main4Activity.class)   ;
               getContext().startActivity(intent) ;

           }else {
               Intent intent =new Intent(getContext() , Main4Activity.class)   ;
               getContext().startActivity(intent) ;

           }
       }


        if(count_f ==1 ) {
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f1) ;
            count_f++ ;
        } else if(count_f ==2 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f2) ;
            count_f++ ;
        }
        else  if(count_f ==3 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f3) ;
            count_f++ ;
        }
         else if(count_f ==4 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f4) ;
            count_f++ ;
        }
        else  if(count_f ==5 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f5) ;
            count_f++ ;
        }
        else  if(count_f ==6 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f6) ;
            count_f++ ;
        }
         else if(count_f ==7 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f7) ;
            count_f++ ;
        }
        else  if(count_f ==8 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f8) ;
            count_f++ ;
        }
        else  if(count_f ==9 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f9) ;
            count_f++ ;
        }
        else  if(count_f ==10 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f10) ;
            count_f++ ;
        }
       else  if(count_f ==11 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f11) ;
            count_f++ ;
        }
        else if(count_f ==12 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f12) ;
            count_f++ ;
        }
         else if(count_f ==14 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f14) ;
            count_f++ ;
        }
        else if(count_f ==15 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f15) ;
            count_f++ ;
        }
        else if(count_f ==16 )
        {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            ship= BitmapFactory.decodeResource(getResources() , R.drawable.f16) ;
            count_f++ ;
        }
        else {

            if (count_life >= 0) {


                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ship = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
                x = 500;
                y = 2000;
                stopMove = false;
                count_f = 1;
            } else {
                //  if user finsh all hearts
                ship = BitmapFactory.decodeResource(getResources(), R.drawable.ship);
                x = 500;
                y = 2000;
                stopMove = false;
                count_f = 1;
            }
        }


    }









}
