package dk.dtu.dinogame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    Player player;


    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        // Creates a new instants of the thread
        gameThread = new GameThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.dinowalk_1));

        // Starts the game Thread
        gameThread.setRunning(true);
        // Starts the Synchronisation of the thread in the Thread superclass
        gameThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        // Security to stop the thread if it does not stop first time
        boolean retry = true;
        while (retry) {
            try {
                gameThread.setRunning(false);
                // Waits for the thread to die / (Removes Fork)
                gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        player.update();
    }


    // This is where you draw Items
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (canvas != null) {
            player.draw(canvas);
        }


    }


}
