package com.example.shipx;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Ghost_bullet {
    private Bitmap bullet , bullet_show ;
    private int bulletx , bullety , animation_count;


    public Ghost_bullet (int ghostx , int ghosty , Resources res ){
         bullet= BitmapFactory.decodeResource( res , R.drawable.fire_red );
        bullet=Bitmap.createScaledBitmap(bullet ,800 ,84, false) ;
        bullet_show = Bitmap.createBitmap(bullet , animation_count * 50 , 0 , 50 , 84) ;

        bulletx =  ghostx + 60  ;
        bullety = ghosty ;
        animation_count = 0;
    }
    public Bitmap getBitmap(){

      //  bullet_show = Bitmap.createBitmap(bullet , animation_count * 32 , 0 , 32 , 64) ;
        bullet_show = Bitmap.createBitmap(bullet , animation_count * 50 , 0 , 50 , 84) ;
        animation_count++ ;
        if(animation_count>15) animation_count = 0 ;

        return bullet_show ;
    }
    public void moving(){
        bullety += 15 ;
    }
    public int getx(){
        return bulletx  ;

    }
    public int gety(){
        return bullety  ;

    }
    public boolean bullet_ghost_collision(Bitmap ship , int shipx , int shipy){
        Rect x = new Rect(shipx,shipy, shipx + ship.getWidth() , shipy +ship.getHeight() ) ;
        Rect y = new Rect(bulletx , bullety , bulletx + bullet_show.getWidth() ,bullety + bullet_show.getHeight() -40) ;


        if(Rect.intersects(x,y)){
            return true ;
        }else return false ;
    }
    public boolean remove_from_list(){
        if(bullety>3000) return true ;
        else return false ;
    }
}
