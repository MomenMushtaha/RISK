import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DeployView extends JFrame {
  private GridLayout DeployLayout;
  public Gameplay game;
  // labels
  static JLabel l;
  private GridBagConstraints cons;
  static JLabel playerTerritoryLabel;
  //ScrollingPanes
  private JScrollPane currentPlayerTerritoryListScrollPane;
  private JList currentPlayerTerritoryList;

  public DeployView(Gameplay game) {
    this.game = game;
    setTitle("Risk Starts");
    setPreferredSize(new Dimension(500, 500));
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);

    add(DeployDialog());
    //to make sure the dialog is appearing at the front
    toFront();
  }

  private JPanel DeployDialog() {


    // Creates the panel, Labels and Layouts
    JPanel deployPanel = new JPanel();
    l = new JLabel("Please select the Territory to deploy troops into");
    playerTerritoryLabel = new JLabel("Owned Territories:");
    // Sets Layout
    DeployLayout = new GridLayout();
    String[] listt = game.listTheTerritories(game.getPlayers(0).getTerritories());
    System.out.println(listt);
    currentPlayerTerritoryList = new JList(listt);
    currentPlayerTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    currentPlayerTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    currentPlayerTerritoryList.setVisibleRowCount(42);
    currentPlayerTerritoryListScrollPane = new JScrollPane(currentPlayerTerritoryList);
    currentPlayerTerritoryList.setVisibleRowCount(42);
    cons = new GridBagConstraints();

    deployPanel.add(l);
    deployPanel.add(playerTerritoryLabel);
    deployPanel.add(currentPlayerTerritoryListScrollPane);
   return deployPanel;
  }

  // Action listeners for GameView
  public void DeployViewMouseListeners(MouseListener evt) {
    currentPlayerTerritoryList.addMouseListener(evt);
  }

  public int getplayerterritoryIndex() {
    return currentPlayerTerritoryList.getSelectedIndex();
  }


  public String getplayerterritory() {
    return currentPlayerTerritoryList.getSelectedValue().toString();
  }
}



