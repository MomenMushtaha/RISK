import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TerritoryView extends JFrame{
  //initialize panel
  private JPanel territoryPanel;
  //initialize layout
  private GridLayout territoryLayout;
  // labels
  private JLabel label;
  //initialize buttons
  private JButton attackButton;
  private String attackString = "Proceed to choose where to attack";
  //initialize lists
  private JList currentPlayerTerritoryList;
  //initializing scrolling Panes
  private JScrollPane currentPlayerTerritoryListScrollPane;
  //game
  public Gameplay game;

  public TerritoryView(Gameplay game)
  {
    this.game = game;
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
    //to make sure the dialog is appearing at the front
    toFront();
    add(TerritoryDialog());
  }

  private JPanel TerritoryDialog()
  {
    // Creates the panel, Labels and Layouts
    territoryPanel = new JPanel();
    label = new JLabel("Please select the Territory to attack from");
    // Sets Layout
    territoryLayout = new GridLayout(3,1);
    territoryPanel.setLayout(territoryLayout);
    String[] s = game.listTheTerritories(game.getcurrentPlayer().getTerritories());
    currentPlayerTerritoryList = new JList(s);
    currentPlayerTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    currentPlayerTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    attackButton = new JButton("Proceed to choose where to attack");
    attackButton.setActionCommand(attackString);
    //ScrollingPanes
    currentPlayerTerritoryListScrollPane = new JScrollPane(currentPlayerTerritoryList);
    currentPlayerTerritoryList.setVisibleRowCount(s.length);

    territoryPanel.add(label);
    territoryPanel.add(currentPlayerTerritoryListScrollPane);
    territoryPanel.add(attackButton);

    return territoryPanel;}

  public void TerritoryViewActionListeners(ActionListener evt) {
    attackButton.addActionListener(evt);
    validate();
    repaint();
  }

  public int getplayerterritoryIndex() {
    return currentPlayerTerritoryList.getSelectedIndex();
  }
}



