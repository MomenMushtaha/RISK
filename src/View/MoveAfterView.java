package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import Logic.*;

public class MoveAfterView extends JFrame {
  // initialize labels
  static JLabel label;
  // initialize buttons
  private JButton moveAfterButton;
  // initialize combobox
  public JComboBox<String> troopsComboBox;
  //troops
  private final int troops;
  //game
  public Gameplay game;

  public MoveAfterView(Gameplay game, int troops) {
    this.troops = troops;
    this.game = game;
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
    add(MoveAfterDialog());
    //to make sure the dialog is appearing at the front
    toFront();
  }

  private JPanel MoveAfterDialog() {
    // Creates the panel, Labels and Layouts
    //initialize panel
    JPanel moveAfterPanel = new JPanel();
    label = new JLabel("please choose how many troops to move");
    // Sets Layout
    //initialize layouts
    GridLayout moveAfterLayout = new GridLayout(3, 1);
    moveAfterPanel.setLayout(moveAfterLayout);
    moveAfterButton = new JButton("Move Troops!");
    // Setting button commands
    String moveAfterString = "Move Troops!";
    moveAfterButton.setActionCommand(moveAfterString);
    //initialize scroll pane
    moveAfterPanel.add(label);
    moveAfterPanel.add(troopsComboBox(troops));
    moveAfterPanel.add(moveAfterButton);
    return moveAfterPanel;
  }

  // Action listeners for MoveAfterView
  public void MoveAfterViewActionListeners(ActionListener evt) {
    moveAfterButton.addActionListener(evt);
    validate();
    repaint();
  }


  public JComboBox<String> troopsComboBox(int troops) {
    int num = 3;
    int i = 0;
    String[] r = new String[troops - 3];
    while (num < troops ) {
      r[i] = String.valueOf(num);
      num += 1;
      i++;
    }
    troopsComboBox = new JComboBox<>(r);
    return troopsComboBox;
  }
}
