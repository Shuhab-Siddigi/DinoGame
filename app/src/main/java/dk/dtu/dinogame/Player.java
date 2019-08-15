package dk.dtu.dinogame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {

    private Bitmap bitmap;

    // Charater Motions
    private int xPosition;
    private int yPosition;


    public Player(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(bitmap,xPosition,yPosition,null);
    }

    public void update(){
        yPosition++;
    }
}
