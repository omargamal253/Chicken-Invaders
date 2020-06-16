package com.example.shipx;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Bullet {
    private Bitmap bullet ;
    private int bulletx , bullety ;
    public Bullet(int shipx , int shipy , Resources res){
        bullet = BitmapFactory.decodeResource(res , R.drawable.bullet1) ;
        bullet=Bitmap.createScaledBitmap(bullet ,30 ,60, false) ;
        bulletx = shipx ;
        bullety = shipy ;


    }
    public Bitmap getBitmap(){
        return bullet ;
    }
    public void moving(){
        bullety-=20 ;

    }
    public int getx(){
        return bulletx  ;

    }
    public int gety(){
        return bullety  ;

    }
    public boolean bullet_collision(Bitmap ghost , int ghostx , int ghosty){
        Rect x = new Rect(ghostx,ghosty, ghostx + ghost.getWidth() , ghosty +ghost.getHeight()) ;
        Rect y = new Rect(bulletx , bullety , bulletx + bullet.getWidth() ,bullety + bullet.getHeight()) ;


        if(Rect.intersects(x,y)){
            return true ;
        }else return false ;
    }
    public boolean remove_bullet_from_list(){
        if(bullety<0) return true ;
        else return false ;
    }

}
