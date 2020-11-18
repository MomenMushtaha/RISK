import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FortifyView extends JFrame {
  //initialize panel
  private JPanel fortifyPanel;
  //initialize layouts
  private GridLayout fortifyLayout;
  // initialize labels
  static JLabel label;
  // initialize buttons
  private JButton fortifyButton;
  private String fortifyString = "Fortify!";
  // initialize combobox
  public JComboBox troopsComboBox;
  private String[] items;
  //initialize lists
  public JList boarderingTerritoryList;
  //initialize scroll pane
  private JScrollPane boarderingTerritoryListScrollPane;
  //troops
  private int troops;
  //fortifying territory index
  private int fortifyIndex;
  //game
  public Gameplay game;

  public FortifyView(Gameplay game, int troops, int fortifyIndex) {
    this.fortifyIndex = fortifyIndex;
    items =new String[troops];
    this.troops = troops;
    this.game = game;
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
    add(FortifyDialog(items));
    //to make sure the dialog is appearing at the front
    toFront();
  }

  private JPanel FortifyDialog(String[] r) {
    // Creates the panel, Labels and Layouts
    fortifyPanel = new JPanel();
    label = new JLabel("please choose what territory you want to attack");
    // Sets Layout
    fortifyLayout = new GridLayout(4, 1);
    fortifyPanel.setLayout(fortifyLayout);
    String[] s = game.listBorderingTerritories(game.getcurrentPlayer().getTerritories().get(fortifyIndex));
    boarderingTerritoryList = new JList(s);
    boarderingTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    boarderingTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    //ScrollingPanes
    fortifyButton = new JButton("Fortify!");
    // Setting button commands
    fortifyButton.setActionCommand(fortifyString);
    boarderingTerritoryListScrollPane = new JScrollPane(boarderingTerritoryList);
    boarderingTerritoryList.setVisibleRowCount(s.length);
    fortifyPanel.add(troopsComboBox(troops));
    fortifyPanel.add(boarderingTerritoryListScrollPane);
    fortifyPanel.add(fortifyButton);
    return fortifyPanel;
  }

  // Action listeners for FortifyView
  public void FortifyViewActionListeners(ActionListener evt) {
    fortifyButton.addActionListener(evt);
    validate();
    repaint();
  }

  public int getBoarderingTerritoryIndex() {
    return boarderingTerritoryList.getSelectedIndex();
  }
  public int getFortifyingIndex()
  {
    return fortifyIndex;
  }

  public JComboBox troopsComboBox(int troops) {
    int num = 1;
    int i = 0;
    String[] r = new String[troops];
    while (num < troops ) {
      r[i] = String.valueOf(num);
      num += 1;
      i++;
    }
    troopsComboBox = new JComboBox(r);
    return troopsComboBox;
  }
}



