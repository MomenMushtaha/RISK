package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * AIView of the GUI of Risk!.
 *
 * @author Momin Mushtaha
 **/
public class AIView extends JFrame {
  //initialize labels
  static JLabel label;
  //initialize comboBox
  public JComboBox<String> AIPlayerComboBox;
  //AIPlayers
  public int AIPlayers;
  //initialize buttons
  private JButton AIButton;
  //game

  public AIView(int AIPlayers) {
    this.AIPlayers = AIPlayers;
    setTitle("Risk Starts");
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(AIDialog());
    pack();
    setVisible(true);
    //to make sure the dialog is appearing at the front
    toFront();
  }


  private JPanel AIDialog() {
    //Creates the panel, Labels and Layouts
    //initialize panel
    JPanel AIPanel = new JPanel();
    label = new JLabel("Please select how many players are AI ");
    // Sets Layout
    //initialize layout
    GridLayout startLayout = new GridLayout(3, 1, 5, 5);
    AIPanel.setLayout(startLayout);
    //new Combobox for players size
    // Creating buttons
    AIButton = new JButton("Start");
    // Setting button commands
    String AIString = "StartBtn";
    AIButton.setActionCommand(AIString);
    //buttons on AIPanel
    AIPanel.add(label);
    AIPanel.add(AIPlayerComboBox(AIPlayers));
    AIPanel.add(AIButton);
    return AIPanel;
  }

  // Action listeners for AIView
  public void AIViewActionListeners(ActionListener evt) {
    AIButton.addActionListener(evt);
  }

  public JComboBox<String> AIPlayerComboBox(int AIPlayers) {
    int num = 0;
    int i = 0;
    String[] r = new String[AIPlayers];
    while (num < AIPlayers) {
      r[i] = String.valueOf(num);
      num += 1;
      i++;
    }
    AIPlayerComboBox = new JComboBox<>(r);
    return AIPlayerComboBox;
  }
}