import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeployView extends JFrame {
  //initialize panel
  private JPanel deployPanel;
  //initialize layout
  private GridLayout deployLayout;
  //initialize labels
  static JLabel label;
  //initialize buttons
  private JButton deployButton;
  private String deployString = "Deploy Now!";
  //initialize ComboBox
  public JComboBox troopsComboBox;
  private String[] items;
  //initialize Lists
  public JList currentPlayerTerritoryList;
  //initialize scroll panes
  private JScrollPane currentPlayerTerritoryListScrollPane;
  //troops to be added
  public int troops;
  //game
  public Gameplay game;


  public DeployView(Gameplay game) {
    items =new String[troops];
    troops = game.troopsNewTurn;
    this.game = game;
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
    add(DeployDialog());
    //to make sure the dialog is appearing at the front
    toFront();
  }

  private JPanel DeployDialog() {
    // Creates the panel, Labels and Layouts
    deployPanel = new JPanel();
    label = new JLabel("Please choose how many troops and where to place them");
    // Sets Layout
    deployLayout = new GridLayout(4, 1);
    deployPanel.setLayout(deployLayout);
    String[] s = game.listTheTerritories(game.getcurrentPlayer().getTerritories());
    currentPlayerTerritoryList = new JList(s);
    currentPlayerTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    currentPlayerTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    //ScrollingPanes
    deployButton = new JButton("Deploy Now!");
    // Setting button commands
    deployButton.setActionCommand(deployString);
    currentPlayerTerritoryListScrollPane = new JScrollPane(currentPlayerTerritoryList);
    currentPlayerTerritoryList.setVisibleRowCount(s.length);
    deployPanel.add(label);
    deployPanel.add(troopsComboBox(troops));
    deployPanel.add(currentPlayerTerritoryListScrollPane);
    deployPanel.add(deployButton);
    return deployPanel;
  }

  // Action listeners for DeployView
  public void DeployViewActionListeners(ActionListener evt) {
    deployButton.addActionListener(evt);
    validate();
    repaint();
  }

  public int getplayerterritoryIndex() {
    return currentPlayerTerritoryList.getSelectedIndex();
  }

  public JComboBox troopsComboBox(int troops) {
    int num = 0;
    int i = 0;
    String[] r = new String[troops];
    while (num < troops) {
      num += 1;
      r[i] = String.valueOf(num);
      i++;
    }
    troopsComboBox = new JComboBox(r);
    return troopsComboBox;
  }
}



