import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.Clip;
import java.awt.*;

class Ball {
    private Color ballColor;    // color of ball

    private int radius;         // radius of ball

    private int x;              // upper left x coordinate of ball's bounding square
    private int y;              // upper left y coordinate of ball's bounding square

    private int dx;             // X-axis speed (pixels per repaint)
    private int dy;             // Y-axis speed (pixels per repaint)

    private Clip soundX;        // ball bouncing sound effect
    private Clip soundY;        // ball bouncing sound effect

    /**
     * Constructor for Ball class.
     *
     * @param   newColor    color of ball
     * @param   newRadius   radius of ball
     * @param   newX        starting X location of upper left corner of bounding square
     * @param   newY        starting Y location of upper left corner of bounding square
     * @param   newDx       how many pixels to travel on X-axis per repaint.
     * @param   newDy       how many pixels to travel on Y-axis per repaint.
     *
     */
    Ball(Color newColor, int newRadius, int newX, int newY, int newDx, int newDy) {
        // Set ball properties.
        ballColor = newColor;

        radius = newRadius;
        x = newX;
        y = newY;

        dx = newDx;
        dy = newDy;

        try {
            // Set sound file names.
            String soundNameX = "bounceY.wav";
            String soundNameY = "bounceY.wav";

            // Open audio stream from file.
            AudioInputStream audioInputStreamX = AudioSystem.getAudioInputStream(new File(soundNameX));
            AudioInputStream audioInputStreamY = AudioSystem.getAudioInputStream(new File(soundNameY));

            // Open a clip and use audio stream for top and bottom walls.
            soundX = AudioSystem.getClip();
            soundX.open(audioInputStreamX);

            // Open a clip and use audio stream for left and right walls.
            soundY = AudioSystem.getClip();
            soundY.open(audioInputStreamY);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
  }

    /**
     * This method controls ball movement and whether to play a sound upon touching walls of canvas.
     *
     * @param   canvas      the animation panel
     *
     */
    void move(Dimension canvas) {
        // If ball touches left or right wall, play bounce sound and reverse direction.
        if (x <= 0 || x >= (canvas.width - radius)) {
            soundX.loop(1);
            dx *= -1;
        }

        // If ball touches top or bottom wall, play bounce sound and reverse direction.
        if (y <= 0 || y >= (canvas.height - radius)) {
            soundY.loop(1);
            dy *= -1;
        }

        // Move ball by a certain x and y amount.
        x += dx;
        y += dy;
    }

    /**
     * This method draws the ball.
     *
     * @param   g      graphics template for a ball.
     *
     */
    void draw(Graphics g) {
        g.setColor(ballColor);

        g.fillOval(x, y, radius, radius);
    }
}
