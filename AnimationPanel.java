import javafx.scene.media.AudioClip;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class AnimationPanel extends JPanel implements Runnable {
    private ArrayList<Ball> balls = new ArrayList<>();

    private Dimension dim = null;

    private Thread animationThread = null;

    void start() {
        if (animationThread == null) {
            animationThread = new Thread(this);

            animationThread.start();
        }
    }

    void stop() {
        animationThread.interrupt();

        animationThread = null;
    }

    @Override
    public void run() {
        while (Thread.currentThread() == animationThread) {
            try{
                Thread.sleep(9);
                this.repaint();
            } catch (InterruptedException e) {
                System.out.println("Thread error.");
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Dimension canvasSize = null;

        if (dim == null) {
            balls.add(new Ball(Color.BLUE, 20, 21, 21, 3, -2));
            //balls.add(new Ball(Color.CYAN, 30, 50, 50, 3, -1));
            //balls.add(new Ball(Color.GREEN, 40, 150, 150, 10, -3));

            canvasSize = getSize();
        }

        if (canvasSize != null) {
            g.drawRect(0, 0, canvasSize.width, canvasSize.height);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, canvasSize.width, canvasSize.height);
        }

        balls.get(0).move(canvasSize);
        balls.get(0).draw(g);
/*
        balls.get(1).move(canvasSize);
        balls.get(1).draw(g);

        balls.get(2).move(canvasSize);
        balls.get(2).draw(g);
        */
    }
}
