package View;

import Logic.Gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FortifyView extends JFrame {
  // initialize labels
  static JLabel label;
  //game
  public final Gameplay game;
  //troops
  private final int troops;
  //fortifying territory index
  private final int fortifyIndex;
  // initialize combobox
  public JComboBox<String> troopsComboBox;
  //initialize lists
  public JList<String> pathTerritoryList;
  public String[] pathlist;
  // initialize buttons
  private JButton fortifyButton;

  public FortifyView(Gameplay game, int troops, int fortifyIndex) {
    this.fortifyIndex = fortifyIndex;
    this.troops = troops;
    this.game = game;
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
    add(FortifyDialog());
    //to make sure the dialog is appearing at the front
    toFront();
  }

  private JPanel FortifyDialog() {
    // Creates the panel, Labels and Layouts
    //initialize panel
    JPanel fortifyPanel = new JPanel();
    label = new JLabel("please choose what territory to add troops to");
    // Sets Layout
    //initialize layouts
    GridLayout fortifyLayout = new GridLayout(4, 1);
    fortifyPanel.setLayout(fortifyLayout);
    pathlist = game.listPathTerritories(game.getCurrentPlayer().getTerritories().get(fortifyIndex));
    pathTerritoryList = new JList<>(pathlist);
    pathTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    pathTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    //ScrollingPanes
    fortifyButton = new JButton("Fortify!");
    // Setting button commands
    String fortifyString = "Fortify!";
    fortifyButton.setActionCommand(fortifyString);
    //initialize scroll pane
    JScrollPane pathTerritoryListScrollPane = new JScrollPane(pathTerritoryList);
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

  public int getFortifyingIndex() {
    return fortifyIndex;
  }

  public JComboBox<String> troopsComboBox(int troops) {
    int num = 1;
    int i = 0;
    String[] r = new String[troops - 1];
    while (num < troops) {
      r[i] = String.valueOf(num);
      num += 1;
      i++;
    }
    troopsComboBox = new JComboBox<>(r);
    return troopsComboBox;
  }
}



