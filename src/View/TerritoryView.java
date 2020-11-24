package View;

import Logic.Gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TerritoryView extends JFrame {
  //game
  public final Gameplay game;
  //initialize buttons
  private JButton attackButton;
  //initialize lists
  private JList<String> currentPlayerTerritoryList;

  public TerritoryView(Gameplay game) {
    this.game = game;
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
    //to make sure the dialog is appearing at the front
    toFront();
    add(TerritoryDialog());
  }

  private JPanel TerritoryDialog() {
    // Creates the panel, Labels and Layouts
    //initialize panel
    JPanel territoryPanel = new JPanel();
    // labels
    JLabel label = new JLabel("Please select your source Territory");
    // Sets Layout
    //initialize layout
    GridLayout territoryLayout = new GridLayout(3, 1);
    territoryPanel.setLayout(territoryLayout);
    String[] s = game.listTheTerritories(game.getCurrentPlayer().getTerritories());
    currentPlayerTerritoryList = new JList<>(s);
    currentPlayerTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    currentPlayerTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    attackButton = new JButton("Proceed");
    String attackString = "Proceed";
    attackButton.setActionCommand(attackString);
    //ScrollingPanes
    //initializing scrolling Panes
    JScrollPane currentPlayerTerritoryListScrollPane = new JScrollPane(currentPlayerTerritoryList);
    currentPlayerTerritoryList.setVisibleRowCount(s.length);

    territoryPanel.add(label);
    territoryPanel.add(currentPlayerTerritoryListScrollPane);
    territoryPanel.add(attackButton);

    return territoryPanel;
  }

  public void TerritoryViewActionListeners(ActionListener evt) {
    attackButton.addActionListener(evt);
    validate();
    repaint();
  }

  public int getplayerterritoryIndex() {
    return currentPlayerTerritoryList.getSelectedIndex();
  }
}



