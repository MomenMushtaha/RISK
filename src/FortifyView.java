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
  public JList pathTerritoryList;
  //initialize scroll pane
  private JScrollPane pathTerritoryListScrollPane;
  //troops
  private int troops;
  public String[] pathlist;
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
    label = new JLabel("please choose what territory to add troops to");
    // Sets Layout
    fortifyLayout = new GridLayout(4, 1);
    fortifyPanel.setLayout(fortifyLayout);
    pathlist = game.listPathTerritories(game.getcurrentPlayer().getTerritories().get(fortifyIndex));
    pathTerritoryList = new JList(pathlist);
    pathTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    pathTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    //ScrollingPanes
    fortifyButton = new JButton("Fortify!");
    // Setting button commands
    fortifyButton.setActionCommand(fortifyString);
    pathTerritoryListScrollPane = new JScrollPane(pathTerritoryList);
    pathTerritoryList.setVisibleRowCount(pathlist.length);
    fortifyPanel.add(label);
    fortifyPanel.add(troopsComboBox(troops));
    fortifyPanel.add(pathTerritoryListScrollPane);
    fortifyPanel.add(fortifyButton);
    return fortifyPanel;
  }

  // Action listeners for FortifyView
  public void FortifyViewActionListeners(ActionListener evt) {
    fortifyButton.addActionListener(evt);
    validate();
    repaint();
  }

  public int getPathTerritoryIndex() {
    return pathTerritoryList.getSelectedIndex();
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



