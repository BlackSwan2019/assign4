import javafx.scene.media.AudioClip;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/*
* This class is the panel upon which animations will occur.
*
*/
class AnimationPanel extends JPanel implements Runnable {
    private ArrayList<Ball> balls = new ArrayList<>();      // array of balls.

    private Dimension canvasSize = null;                           // size of animation panel (canvas)

    private Thread animationThread = null;                  // thread to wrap around the animation panel

    int speed = 9;

    /**
     * Method for initializing and starting animation thread with AnimationPanel.
     *
     */
    void start() {
        // If thread hasn't started yet, make and run it.
        if (animationThread == null) {
            animationThread = new Thread(this);

            animationThread.start();
        }
    }

    /**
     * Stops animation thread and resetting it to null.
     *
     */
    void stop() {
        animationThread.interrupt();

        animationThread = null;
    }

    /**
     * Runs a thread. This is like the main of assignment 4.
     *
     */
    @Override
    public void run() {
        // While currently running thread is the animation thread...
        while (Thread.currentThread() == animationThread) {
            try{
                // pause it and repaint it to move the balls.
                Thread.sleep(speed);
                this.repaint();
            } catch (InterruptedException e) {
                System.out.println("Thread error.");
            }
        }
    }

    /**
     * This method draws/paints the animation panel background and the balls moving within it.
     *
     * @param g     animation panel background
     *
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Paint superclass stuff first (before we paint our own stuff below).
        super.paintComponent(g);

        // If canvas (animation panel) is new (i.e. we don't know dimension of it yet), then create a set of balls and get size of canvas.
        if (canvasSize == null) {
            balls.add(new Ball(Color.BLUE, 20, 21, 21, 3, -2));
            balls.add(new Ball(Color.CYAN, 30, 50, 50, 3, -1));
            balls.add(new Ball(Color.GREEN, 40, 150, 150, 2, -3));

            canvasSize = getSize();
        }

        // If we know the size of the canvas, draw a white rectangle on the canvas.
        if (canvasSize != null) {
            g.drawRect(0, 0, canvasSize.width, canvasSize.height);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, canvasSize.width, canvasSize.height);
        }

        // For every repaint of scene, move and daw balls.
        balls.get(0).move(canvasSize);
        balls.get(0).draw(g);

        balls.get(1).move(canvasSize);
        balls.get(1).draw(g);

        balls.get(2).move(canvasSize);
        balls.get(2).draw(g);

    }
}
