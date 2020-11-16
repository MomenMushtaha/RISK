import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseListener;

public class TerritoryView extends JFrame{
  private JPanel TerritoryPanel;
  private GridLayout TerritoryLayout;
  public Gameplay game;
  // labels
  static JLabel l;
  private GridBagConstraints cons;
  static JLabel playerTerritoryLabel;
  static JLabel targetingLabel;
  //ScrollingPanes
  private JScrollPane currentPlayerTerritoryListScrollPane;
  private JScrollPane targetingScrollPane;
  private JList currentPlayerTerritoryList;
  private JList targetingList;

  //comboBox

  public TerritoryView(Gameplay game)
  {
    setTitle("Risk Starts");
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(TerritoryDialog());
    pack();
    setVisible(true);
    //to make sure the dialog is appearing at the front
    toFront();
  }

  private JPanel TerritoryDialog()
  {



    // Creates the panel, Labels and Layouts
    TerritoryPanel = new JPanel();
    l = new JLabel("Please select The First and Second Territory");
    playerTerritoryLabel = new JLabel("Owned Territories:");
    targetingLabel = new JLabel("Bordering Territories:");
    // Sets Layout
    TerritoryLayout = new GridLayout();
    String[] listt = game.listTheTerritories(game.currentPlayer.getTerritories());
    currentPlayerTerritoryList = new JList(listt);
    currentPlayerTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    currentPlayerTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    currentPlayerTerritoryList.setVisibleRowCount(42);
    currentPlayerTerritoryListScrollPane = new JScrollPane(currentPlayerTerritoryList);

    targetingList = new JList();
    currentPlayerTerritoryList.setVisibleRowCount(42);
    targetingScrollPane = new JScrollPane(targetingList);


    cons = new GridBagConstraints();

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 4;
    TerritoryPanel.add(playerTerritoryLabel, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 10;
    cons.gridx = 0;
    cons.gridy = 5;
    TerritoryPanel.add(currentPlayerTerritoryListScrollPane, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 10;
    cons.gridx = 0;
    cons.gridy = 6;
    TerritoryPanel.add(targetingScrollPane, cons);

    cons.fill = GridBagConstraints.BOTH;
    cons.insets = new Insets(5, 5, 5, 5);
    cons.weightx = 0.5;
    cons.weighty = 0.5;
    cons.gridx = 0;
    cons.gridy = 7;
    TerritoryPanel.add(targetingLabel, cons);
    return TerritoryPanel;
  }

  // Action listeners for GameView
  public void TerritoryViewMouseListeners(MouseListener evt)
  {
    currentPlayerTerritoryList.addMouseListener(evt);
  }

  public int getplayerterritoryIndex() {
    return currentPlayerTerritoryList.getSelectedIndex();
  }


  public String getplayerterritory() {
    return currentPlayerTerritoryList.getSelectedValue().toString();
  }


  public JList getTargeting() {
    return targetingList;
  }
}



