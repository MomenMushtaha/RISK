package View;

import Logic.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

/**
 * GameView of the GUI of Risk!.
 * @author Momin Mushtaha
 **/
public class GameView extends JFrame {
  //initialize labels
  static JLabel label;
  //initialize buttons
  private JButton newGameButton;
  //initialize comboBox
  public JComboBox<String> playerComboBox;
  private final String[] players = { "2", "3" , "4" , "5" , "6" };
  //game
  public Gameplay game;

  public GameView(Gameplay game)
  {
    this.game = game;
    setTitle("Risk Starts");
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(startDialog());
    pack();
    setVisible(true);
    //to make sure the dialog is appearing at the front
    toFront();
  }


  private JPanel startDialog()
  {
    // Creates the panel, Labels and Layouts
    //initialize panel
    JPanel startPanel = new JPanel();
    label = new JLabel("Please select how many players are playing ");
    // Sets Layout
    //initialize layout
    GridLayout startLayout = new GridLayout(3, 1, 5, 5);
    startPanel.setLayout(startLayout);
    //new Combobox for players size
    playerComboBox = new JComboBox<>(players);
    // Creating buttons
    newGameButton = new JButton("New Game");
    // Setting button commands
    String newGameString = "newGameBtn";
    newGameButton.setActionCommand(newGameString);
    //buttons on startPanel
    startPanel.add(label);
    startPanel.add(playerComboBox);
    startPanel.add(newGameButton);
    return startPanel;
  }

  // Action listeners for GameView
  public void GameViewActionListeners(ActionListener evt)
  {
    newGameButton.addActionListener(evt);
  }
}