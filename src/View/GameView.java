package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * GameView of the GUI of Risk!.
 *
 * @author Momin Mushtaha
 **/
public class GameView extends JFrame {
  public static final String RISK_STARTS = "Risk Starts";
  //initialize labels
  static JLabel label;
  private final String[] players = {"2", "3", "4", "5", "6"};
  //initialize comboBox
  public JComboBox<String> playerComboBox;
  //initialize buttons
  private JButton newGameButton;


  /**
   * constructor for GameView
   *
   */
  public GameView() {
    setTitle(RISK_STARTS);
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(startDialog());
    pack();
    setVisible(true);
    //to make sure the dialog is appearing at the front
    toFront();
  }

  /**
   * Builds the start Dialog that will be shown on the panel
   *
   * @return startPanel
   */
  private JPanel startDialog() {
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

  /**
   * Action listeners for GameView
   *
   * @param evt Action listener
   */
  public void GameViewActionListeners(ActionListener evt) {
    newGameButton.addActionListener(evt);
  }
}