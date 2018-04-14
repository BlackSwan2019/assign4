/*
 CSCI 470 section 1
 TA:
 Partner 1      Ben Lane
 zID:		    z1806979
 Partner 2:     Jinhong Yao
 zID:		    z178500
 Assignment:    3
 Date Due:	    4/4/2018

 Purpose:       Obtain RSS feed of Apple iTunes music and parse its XML.
                Then, display results in an application.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * This class produces & populates the GUI and handles actions.
 */
public class XMLDownloader extends JFrame {
    private XMLDownloadPanel panel = new XMLDownloadPanel();
    private BouncingBallPanel ballPanel = new BouncingBallPanel();

    private XMLDownloader(String title) {
        super.setTitle(title);
    }

    public static void main(String[] args) {
        XMLDownloader frame = new XMLDownloader("Apple Music List");

        frame.createAndShowGUI();
    }

    /**
     * Create GUI elements.
    */
    private void createAndShowGUI() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);    // Make execution stop upon clicking X.
        this.setSize(1300, 600);        // Set JFrame size.
        this.setResizable(false);                   // Make JFrame not resizable.

        panel.setPreferredSize(new Dimension(1000, 600));   // Set size of album list panel.
        this.add(panel, BorderLayout.CENTER);       // Add main JPanel to JFrame.

        ballPanel.setPreferredSize(new Dimension(400, 600));   // Set size of ball animation panel.
        this.add(ballPanel, BorderLayout.LINE_END); // Add animation JPanel to JFrame.

        this.createMenu();                          // Create and add menus to the JFrame.

        this.setVisible(true);                      // Make JFrame visible.
    }

    /**
     * Create app menus.
     */
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu type = new JMenu("Type");
        type.setToolTipText("Type of release");
        menuBar.add(type);

        ButtonGroup typeGroup = new ButtonGroup();

        // Create and add button for new music in the Type menu.
        JRadioButtonMenuItem newMusic = new JRadioButtonMenuItem("New Music");
        newMusic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newMusic.addActionListener(new typeListener());
        typeGroup.add(newMusic);
        type.add(newMusic);

        // Create and add button for recent releases in the Type menu.
        JRadioButtonMenuItem recentReleases = new JRadioButtonMenuItem("Recent Releases");
        recentReleases.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        recentReleases.addActionListener(new typeListener());
        typeGroup.add(recentReleases);
        type.add(recentReleases);

        // Create and add button for top albums in the Type menu.
        JRadioButtonMenuItem topAlbums = new JRadioButtonMenuItem("Top Albums");
        topAlbums.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_DOWN_MASK));
        topAlbums.addActionListener(new typeListener());
        typeGroup.add(topAlbums);
        type.add(topAlbums);

        // Create Limit menu and add it to menu bar.
        JMenu limit = new JMenu("Limit");
        limit.setToolTipText("Number of results");
        menuBar.add(limit);

        // Radio button group for Limit options.
        ButtonGroup limitGroup = new ButtonGroup();

        // Create and add button for 10 in the Limit menu.
        JRadioButtonMenuItem ten = new JRadioButtonMenuItem("10");
        ten.addActionListener(new limitListener());
        limitGroup.add(ten);
        limit.add(ten);

        // Create and add button for 25 in the Limit menu.
        JRadioButtonMenuItem twentyFive = new JRadioButtonMenuItem("25");
        twentyFive.addActionListener(new limitListener());
        limitGroup.add(twentyFive);
        limit.add(twentyFive);

        // Create and add button for 50 in the Limit menu.
        JRadioButtonMenuItem fifty = new JRadioButtonMenuItem("50");
        fifty.addActionListener(new limitListener());
        limitGroup.add(fifty);
        limit.add(fifty);

        // Create and add button for 100 in the Limit menu.
        JRadioButtonMenuItem hundred = new JRadioButtonMenuItem("100");
        hundred.addActionListener(new limitListener());
        limitGroup.add(hundred);
        limit.add(hundred);

        // Create Explicit menu and add to menu bar.
        JMenu explicit = new JMenu("Explicit");
        explicit.setToolTipText("Allow explicit albums");
        menuBar.add(explicit);
        ButtonGroup explicitGroup = new ButtonGroup();

        // Explicit option for 'Yes'.
        JRadioButtonMenuItem yes = new JRadioButtonMenuItem("Yes");
        yes.addActionListener(new explicitListener());
        explicitGroup.add(yes);
        explicit.add(yes);

        // Explicit option for 'No'.
        JRadioButtonMenuItem no = new JRadioButtonMenuItem("No");
        no.addActionListener(new explicitListener());
        explicitGroup.add(no);
        explicit.add(no);
    }

    /*
    * This inner class listens for menu selections in the Type menu.
     */
    class typeListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String type;

            type = event.getActionCommand();

            switch(type) {
                case "New Music":
                    panel.setType("new-music");
                    break;
                case "Recent Releases":
                    panel.setType("recent-releases");
                    break;
                case "Top Albums":
                    panel.setType("top-albums");
                    break;
            }
        }
    }

    /*
    * This inner class listens for menu selections in the Limit menu.
     */
    class limitListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String limit;

            limit = event.getActionCommand();

            switch(limit) {
                case "10":
                    panel.setLimit("10");
                    break;
                case "25":
                    panel.setLimit("25");
                    break;
                case "50":
                    panel.setLimit("50");
                    break;
                case "100":
                    panel.setLimit("100");
                    break;
            }
        }
    }

    /*
    * This inner class listens for menu selections in the Explicit menu.
     */
    class explicitListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String explicit;

            explicit = event.getActionCommand();

            switch(explicit) {
                case "Yes":
                    panel.setExplicit("explicit");
                    break;
                case "No":
                    panel.setExplicit("non-explicit");
                    break;
            }
        }
    }
}
