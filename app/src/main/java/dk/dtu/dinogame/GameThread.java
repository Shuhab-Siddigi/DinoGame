package dk.dtu.dinogame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    // Private Properties for the Game Thread Class
    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    private boolean running;
    public static Canvas canvas;

    public GameThread(SurfaceHolder surfaceHolder, GameView gameView) {


        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;
    }

    @Override
    public void run() {

        while (running) {
            // Makes sure there is nothing else drawing on canvas
            canvas = null;
            try {
                // Then tries to lock the canvas so it cant be used by others
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {
            } finally {
                if (canvas != null) {
                    try {
                        // this operations unlocks the canvas for other uses
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }
}

