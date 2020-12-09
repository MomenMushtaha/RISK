package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * MapView of the GUI of Risk!.
 *
 * @author Momin Mushtaha
 **/
public class MapView extends JFrame {
    //initialize labels
    static JLabel label;
    //initialize buttons
    private JButton uploadButton;
    private JButton useOriginalButton;

    //game
    public MapView() {
        setTitle("Choose your Map");
        setPreferredSize(new Dimension(300, 300));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(mapDialog());
        pack();
        setVisible(true);
        //to make sure the dialog is appearing at the front
        toFront();
    }


    private JPanel mapDialog() {
        //Creates the panel, Labels and Layouts
        //initialize panel
        JPanel mapPanel = new JPanel();
        label = new JLabel("Please choose your map");
        // Sets Layout
        //initialize layout
        GridLayout startLayout = new GridLayout(3, 1, 5, 5);
        mapPanel.setLayout(startLayout);
        // Creating buttons
        uploadButton = new JButton("Upload Custom Map");
        useOriginalButton = new JButton("Use Original Map");
        // Setting button commands
        String uploadString = "uploadBtn";
        String useOriginalString = "useOriginalBtn";
        uploadButton.setActionCommand(uploadString);
        useOriginalButton.setActionCommand(useOriginalString);
        //buttons on mapPanel
        mapPanel.add(label);
        mapPanel.add(uploadButton);
        mapPanel.add(useOriginalButton);
        return mapPanel;
    }

    // Action listeners for mapView
    public void mapViewActionListeners(ActionListener evt) {
        uploadButton.addActionListener(evt);
        useOriginalButton.addActionListener(evt);
    }
    }
