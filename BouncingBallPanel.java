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

    private AnimationPanel animationPanel = new AnimationPanel();   // balls will be animated on this panel

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
        start.addActionListener(new startStopListener());
        stop.addActionListener(new startStopListener());
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
            // If Start button is clicked, disable Start, enable Stop button, and start animations.
            if ((e.getActionCommand()).equalsIgnoreCase("Start")) {
                start.setEnabled(false);
                stop.setEnabled(true);
                animationPanel.start();
            }
            // Else, enable Start button, disable Stop button, and stop animations.
            else {
                start.setEnabled(true);
                stop.setEnabled(false);
                animationPanel.stop();
            }
        }
    }
}
