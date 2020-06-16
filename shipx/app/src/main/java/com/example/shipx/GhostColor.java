package com.example.shipx;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class GhostColor {
   private  Bitmap ghost ;
   // private  Bitmap animation_sheet , animation_sheet_show  ;

    private int maxHeight , animation_count , count =2  , chicken_count , order_copy  ;
    public int num_bullet ;

    private int ghostx , ghosty ,ghostx_copy;
    private int shipx , shipy ;

    private int speed , move_direction=1;
    private Bitmap color1 , blue1 , blue2;
    private Bitmap  red1 , red2;

    private boolean ghost_die ;
    public GhostColor(int x , int y , int Max_Height , Resources res , int order){

       maxHeight =  Max_Height ;

        ghostx =x ;
        ghosty =y ;
        ghostx_copy = x ;  order_copy  =order;
        if(order==1) ghost = BitmapFactory.decodeResource(res , R.drawable.c1);
        if(order==2) ghost = BitmapFactory.decodeResource(res , R.drawable.c2);
        if(order==3) ghost = BitmapFactory.decodeResource(res , R.drawable.c3);
        if(order==4) ghost = BitmapFactory.decodeResource(res , R.drawable.c4);
        if(order==5) ghost = BitmapFactory.decodeResource(res , R.drawable.c5);
        if(order==6) ghost = BitmapFactory.decodeResource(res , R.drawable.c6);
        if(order==7) ghost = BitmapFactory.decodeResource(res , R.drawable.c7);
        if(order==8) ghost = BitmapFactory.decodeResource(res , R.drawable.c8);
        if(order==9) ghost = BitmapFactory.decodeResource(res , R.drawable.c9);
        if(order==10) ghost = BitmapFactory.decodeResource(res , R.drawable.c10);
        if(order==11) ghost = BitmapFactory.decodeResource(res , R.drawable.c11);

        if(order==0) {
            blue2  = BitmapFactory.decodeResource(res , R.drawable.blue_chicken2);
            blue1  = BitmapFactory.decodeResource(res , R.drawable.blue_chicken1);
            blue1  = Bitmap.createScaledBitmap(blue1 ,60*3 ,28*4 , false);
            blue2  = Bitmap.createScaledBitmap(blue2 ,60*3 ,28*4 , false);
            ghost = blue1 ;

        }
        if(order==-1) {
            red1  = BitmapFactory.decodeResource(res , R.drawable.red_chicken1);
            red2 = BitmapFactory.decodeResource(res , R.drawable.red_chicken2);
            red1  = Bitmap.createScaledBitmap(red1 ,60*3 ,28*4 , false);
            red2  = Bitmap.createScaledBitmap(red2 ,60*3 ,28*4 , false);
            ghost = red1 ;

        }


        if(order == 2 || order == 3|| order == 4 )
       ghost = Bitmap.createScaledBitmap(ghost ,60*3 ,28*3 , false);

     /*   animation_sheet = BitmapFactory.decodeResource(res , R.drawable.fire1_down);
        animation_sheet = Bitmap.createScaledBitmap(animation_sheet , 7200 , 150 , false) ;
        */
        animation_count =0 ; chicken_count=0 ;
        ghost_die = false ;
        num_bullet = 0 ;

    }

    public void setBitmap(Bitmap x ){
        ghost = x ;
    }
    public int getx(){
        return ghostx ;
    }
    public int gety(){
        return ghosty ;
    }
    public void setx(int x){
        ghostx = x ;
    }
    public void sety(int y){
        ghosty = y ;
    }

    public void  moving(int X , int Y ){
        shipx = X ;
        shipy = Y ;
        if(num_bullet <6 ) {
            if (ghosty < maxHeight) {
                ghosty += 5;
            }
            if (ghosty >= maxHeight) {
                if (move_direction == 1) ghostx++;
                if (ghostx == ghostx_copy + 20) move_direction = 0;
                if (move_direction == 0) ghostx--;
                if (ghostx == ghostx_copy - 20) move_direction = 1;
            }
        }



    }

    public Bitmap  getBitmap(Bitmap animation_sheet ){


       if(num_bullet>5 && count%2==0 ){


           if(animation_count<47) ghost = Bitmap.createBitmap(animation_sheet, animation_count* (animation_sheet.getWidth()/48), 0, (animation_sheet.getWidth()/48), animation_sheet.getHeight());

           animation_count++ ;
           ghost_die =true ;

        }else if (count%2==0 ){
               if( order_copy==0  ){
               if(chicken_count % 4 == 0){
                   ghost= blue1;
               }else ghost= blue2;
               chicken_count++ ;
           }else if( order_copy==-1  ){
               if(chicken_count % 4 == 0){
                   ghost= red1;
               }else ghost= red2;
               chicken_count++ ;
           }
       }


        count++ ;
        return ghost ;
    }
    public boolean  Remove_from_list(){
        if(ghosty > 2400) return true ;
        else if(animation_count > 47) return true ;

        else return false ;
    }
   public boolean collosin_ghost_color(Bitmap ship , int shipx , int shipy)
    {
        Rect x = new Rect(shipx,shipy, shipx + ship.getWidth() , shipy +ship.getHeight()) ;
        Rect y = new Rect(ghostx,ghosty,ghostx +ghost.getWidth() ,ghosty + ghost.getHeight()) ;


        if(Rect.intersects(x,y ) && ghost_die ==false ){
            return true ;
        }else return false ;
    }
    public void set_ghost_die_or_not() {

        num_bullet++;

    }
    public void setMaxHeight(int x){

        maxHeight += x ;
    }
}
