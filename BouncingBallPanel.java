import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BouncingBallPanel extends JPanel {
    private JButton start = new JButton("Start");
    private JButton stop = new JButton("Stop");

    private AnimationPanel animationPanel = new AnimationPanel();

    BouncingBallPanel() {
        JPanel buttonPanel = new JPanel();

        setLayout(new BorderLayout());

        add(buttonPanel, BorderLayout.PAGE_START);
        buttonPanel.add(start);
        buttonPanel.add(stop);
        start.addActionListener(new startStopListener());
        stop.addActionListener(new startStopListener());
        stop.setEnabled(false);

        add(animationPanel, BorderLayout.CENTER);
    }

    class startStopListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ((e.getActionCommand()).equalsIgnoreCase("Start")) {
                start.setEnabled(false);
                stop.setEnabled(true);
                animationPanel.start();
            }
            else {
                start.setEnabled(true);
                stop.setEnabled(false);
                animationPanel.stop();
            }
        }
    }
}
