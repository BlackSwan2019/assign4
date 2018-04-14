import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.Clip;
import java.awt.*;

class Ball {
    private Color ballColor;    // color of ball

    private int radius; // radius of ball

    private int x;      // upper left x coordinate of ball's bounding square
    private int y;      // upper left y coordinate of ball's bounding square

    private int dx;     // X-axis speed (pixels per repaint)
    private int dy;     // Y-axis speed (pixels per repaint)

    private Clip sound;     // ball bouncing sound effect

    Ball(Color newColor, int newRadius, int newX, int newY, int newDx, int newDy) {
        ballColor = newColor;

        radius = newRadius;
        x = newX;
        y = newY;

        dx = newDx;
        dy = newDy;

        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();
        Mixer mixer = AudioSystem.getMixer(mixInfos[0]);
        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);

        try {
            sound = (Clip) mixer.getLine(dataInfo);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("bounce.wav"));
            System.out.println(audioStream.getFormat());

            sound.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    void move(Dimension canvas) {
        if (x <= 0 || x >= (canvas.width - radius)) {
            dx *= -1;
            sound.loop(1);
            sound.stop();
        }

        if (y <= 0 || y >= (canvas.height - radius)) {
            dy *= -1;
            sound.loop(1);
            sound.stop();
        }

        x += dx;
        y += dy;
    }

    void draw(Graphics g) {
        g.setColor(ballColor);

        g.fillOval(x, y, radius, radius);
    }
}
