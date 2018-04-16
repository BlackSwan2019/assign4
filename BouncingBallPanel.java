import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
* This class is holds the buttons and animation panel
*
*/
class BouncingBallPanel extends JPanel {
    private JButton start = new JButton("Start");       // start button for animation
    private JButton stop = new JButton("Stop");         // stop button animation
    private JButton faster = new JButton("Faster");         // stop button animation
    private JButton slower = new JButton("Slower");         // stop button animation

    private AnimationPanel animationPanel = new AnimationPanel();   // balls will be animated on this panel

    private Music song = new Music();

    /*
    * Constructor for BouncingBallPanel class.
    *
    */
    BouncingBallPanel() {
        JPanel buttonPanel = new JPanel();      // panel that holds the Start and Stop animation buttons.

        // Set layout of panel to BorderLayout.
        setLayout(new BorderLayout());

        // Add UI elements and set listeners for buttons.
        add(buttonPanel, BorderLayout.PAGE_START);
        buttonPanel.add(start);
        buttonPanel.add(stop);
        buttonPanel.add(faster);
        buttonPanel.add(slower);
        start.addActionListener(new startStopListener());
        stop.addActionListener(new startStopListener());
        faster.addActionListener(new startStopListener());
        slower.addActionListener(new startStopListener());
        stop.setEnabled(false);

        add(animationPanel, BorderLayout.CENTER);
    }

    /*
    * ActionListener for start button. This is an inner class.
    *
    */
    class startStopListener implements ActionListener {
        /**
        * This method listens for an action event on the start and stop buttons.
        *
        * @param e      ActionEvent of a button
        */
        @Override
        public void actionPerformed(ActionEvent e) {
            // If Start button is clicked, disable Start button and enable Stop button and start animation.
            if (e.getActionCommand().equalsIgnoreCase("Start")) {
                start.setEnabled(false);
                stop.setEnabled(true);
                animationPanel.start();
                song.start();
            }
            // Else if Stop button is clicked, disable Stop button and enable Star button and stop animation.
            else if (e.getActionCommand().equalsIgnoreCase("Stop")) {
                start.setEnabled(true);
                stop.setEnabled(false);
                animationPanel.stop();
                song.stop();
            }
            // Else if Faster button is clicked, speed up animation.
            else if (e.getActionCommand().equalsIgnoreCase("Faster")) {
                if (animationPanel.speed - 2 >= 0)
                    animationPanel.speed -= 2;
            }
            // Else if Slower button is clicked, slow down animation.
            else if (e.getActionCommand().equalsIgnoreCase("Slower")) {
                    animationPanel.speed += 2;
            }
        }
    }
}
