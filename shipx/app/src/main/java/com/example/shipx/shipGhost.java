package com.example.shipx;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class shipGhost {
    private Bitmap ghost;


    private int maxHeight, animation_count, count = 2 , count_finsh , chicken_count , order_copy  ;
    public int num_bullet;

    private int ghostx, ghosty, ghostx_copy;
    private int shipx, shipy;

    private int speed, move_direction = 1;
    private Bitmap color1;

    private boolean ghost_die;

    private long count_timer;
    public boolean start;
    private Bitmap  blue1 , blue2;
    private Bitmap  red1 , red2;


    public shipGhost(int x, int y, int Max_Height, Resources res, int order) {
        maxHeight = Max_Height;

        ghostx = x;
        ghosty = y;
        ghostx_copy = x;

        order_copy  =order;
        if (order == 1)
            ghost = BitmapFactory.decodeResource(res, R.drawable.sss);
        else if (order == 2)
            ghost = BitmapFactory.decodeResource(res, R.drawable.hhh);
        else if (order == 3) {
            ghost = BitmapFactory.decodeResource(res, R.drawable.finsh_ship);
            ghost = Bitmap.createScaledBitmap(ghost, 300, 150, false);
        }
        else if (order == 4) {
            blue2  = BitmapFactory.decodeResource(res , R.drawable.blue_chicken2);
            blue1  = BitmapFactory.decodeResource(res , R.drawable.blue_chicken1);
            blue1  = Bitmap.createScaledBitmap(blue1 ,60*6 ,28*8 , false);
            blue2  = Bitmap.createScaledBitmap(blue2 ,60*6 ,28*8 , false);
            ghost = blue1 ;
        }else if (order == 5) {
            red1  = BitmapFactory.decodeResource(res , R.drawable.red_chicken1);
            red2 = BitmapFactory.decodeResource(res , R.drawable.red_chicken2);
            red1  = Bitmap.createScaledBitmap(red1 ,60*6 ,28*8 , false);
            red2  = Bitmap.createScaledBitmap(red2 ,60*6 ,28*8 , false);
            ghost = red1 ;

        }

        animation_count = 0;
        ghost_die = false;
        num_bullet = 0;


        start = false;
        chicken_count=0 ;
        count_timer = 0;   count_finsh=0 ;
    }


    public void setBitmap(Bitmap x) {
        ghost = x;
    }

    public int getx() {
        return ghostx;
    }

    public int gety() {
        return ghosty;
    }

    public void setx(int x) {
        ghostx = x;
    }

    public void sety(int y) {
        ghosty = y;
    }

    public void moving(int X, int Y) {
        shipx = X;
        shipy = Y;


        if (num_bullet < 21) {
            if (ghosty < 400) {
                ghosty+=2;
            }
            if (ghostx > shipx && Math.abs(ghostx - shipx) >= speed && ghosty < shipy) {
                ghostx -= 10;

            } else if (ghostx < shipx && Math.abs(ghostx - shipx) >= speed && ghosty < shipy) {
                ghostx += 10;
            } else {

                ghostx = shipx;
            }
        }
    }


    public Bitmap getBitmap(Bitmap animation_sheet) {


        if (num_bullet > 20 && count % 2 == 0) {


            if (animation_count < 47)
                ghost = Bitmap.createBitmap(animation_sheet, animation_count * (animation_sheet.getWidth() / 48), 0, (animation_sheet.getWidth() / 48), animation_sheet.getHeight());

            animation_count++;
            ghost_die = true;

        }else if ( count % 2 == 0 ) {
            if( order_copy==4 ){
                if(chicken_count % 4 == 0){
                    ghost= blue1;
                }else ghost= blue2;
                chicken_count++ ;
            }else if( order_copy==5 ){
                if(chicken_count % 4 == 0){
                    ghost= red1;
                }else ghost= red2;
                chicken_count++ ;
            }
        }
        count++;
        return ghost;
    }

    public boolean Remove_from_list() {
        if (ghosty > 2400) return true;
        else if (animation_count > 47) return true;

        else return false;
    }

    public boolean collosin_ghost_color(Bitmap ship, int shipx, int shipy) {
        Rect x = new Rect(shipx, shipy, shipx + ship.getWidth(), shipy + ship.getHeight());
        Rect y = new Rect(ghostx, ghosty, ghostx + ghost.getWidth(), ghosty + ghost.getHeight());


        if (Rect.intersects(x, y) && ghost_die == false) {
            return true;
        } else return false;
    }

    public void set_ghost_die_or_not() {

        num_bullet++;

    }

    public void setMaxHeight(int x) {

        maxHeight += x;
    }


    public boolean timer_() {
        count_timer++;
        if(count_timer > 1000000) count_timer =0 ;
        if (count_timer % 20 == 0 && !this.Remove_from_list()) {
            return true;
        } else return false;

    }
    public boolean finsh_game(){
       if(Remove_from_list() ) count_finsh++ ;

       if(count_finsh > 100) return true ;
       else return false  ;
    }



}
/*
    public boolean timer_(){
        new CountDownTimer(10000, 100){
            public void onTick(long millisUntilFinished){
                return true;
            }
            public void onFinish(){

            }
        }.start();
    }

}
*/