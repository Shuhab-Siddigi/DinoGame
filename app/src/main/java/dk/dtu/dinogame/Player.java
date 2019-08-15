package dk.dtu.dinogame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player {

    private GameView gameView;
    private Bitmap bitmap;
    // Charater Motions
    private int xPosition;
    private int yPosition;
    private int velocityX = 15;
    private int velocityY = 20;
    private int SCREENWIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int SCREENHEIGTH = Resources.getSystem().getDisplayMetrics().heightPixels;


    public Player(Bitmap bitmap) {
        this.bitmap = bitmap;
        xPosition = 100;
        yPosition = 100;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, xPosition, yPosition, null);
    }

    public void update() {
        collision();

    }

    // Movement Methods -------


    private void collision() {

        if (xPosition < 0 && yPosition < 0) {
            xPosition = SCREENWIDTH / 2;
            xPosition = SCREENHEIGTH / 2;
        } else {
            xPosition += velocityX;
            yPosition += velocityY;
            if ((xPosition > SCREENWIDTH - bitmap.getWidth() || (xPosition < 0))) {
                velocityX = velocityX * -1;
            }
            if ((yPosition > SCREENHEIGTH - bitmap.getHeight() || (yPosition < 0))) {
                velocityY = velocityY * -1;
            }
        }

    }
}
