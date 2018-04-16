import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

class Music {
    private Clip music;        // ball bouncing sound effect

    private long clipTime = 0;

    /**
     * Method for initializing and starting animation thread with AnimationPanel.
     *
     */
    void start() {
        try {
            // Set sound file names.
            String musicFile = "music.wav";

            // Open audio stream from file.
            AudioInputStream audioInputStreamMusic = AudioSystem.getAudioInputStream(new File(musicFile));

            // Open a clip and use audio stream for top and bottom walls.
            music = AudioSystem.getClip();
            music.open(audioInputStreamMusic);
            music.setMicrosecondPosition(clipTime);
            music.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops animation thread and resetting it to null.
     *
     */
    void stop() {
        clipTime = music.getMicrosecondPosition();

        music.stop();
    }
}
