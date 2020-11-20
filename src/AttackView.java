import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AttackView extends JFrame {
 //initialize panel
  private JPanel attackPanel;
  //initialize layouts
  private GridLayout attackLayout;
  // initialize labels
  static JLabel label;
  // initialize buttons
  private JButton attackButton;
  private String attackString = "Attack Now!";
  // initialize combobox
  public JComboBox troopsComboBox;
  private String[] items;
  //initialize lists
  public JList boarderingTerritoryList;
  //initialize scroll pane
  private JScrollPane boarderingTerritoryListScrollPane;
  //troops
  private int troops;
  //attacking territory index
  private int attackingIndex;
  //game
  public Gameplay game;

  public AttackView(Gameplay game, int troops, int attackingIndex) {
    this.attackingIndex = attackingIndex;
    items =new String[troops];
    this.troops = troops;
    this.game = game;
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
    add(AttackDialog(items));
    //to make sure the dialog is appearing at the front
    toFront();
  }

  private JPanel AttackDialog(String[] r) {
    // Creates the panel, Labels and Layouts
    attackPanel = new JPanel();
    label = new JLabel("please choose what territory you want to attack");
    // Sets Layout
    attackLayout = new GridLayout(4, 1);
    attackPanel.setLayout(attackLayout);
    String[] s = game.listBorderingTerritories(game.getcurrentPlayer().getTerritories().get(attackingIndex));
    boarderingTerritoryList = new JList(s);
    boarderingTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    boarderingTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    //ScrollingPanes
    attackButton = new JButton("Attack Now!");
    // Setting button commands
    attackButton.setActionCommand(attackString);
    boarderingTerritoryListScrollPane = new JScrollPane(boarderingTerritoryList);
    boarderingTerritoryList.setVisibleRowCount(s.length);
    attackPanel.add(label);
    attackPanel.add(troopsComboBox(troops));
    attackPanel.add(boarderingTerritoryListScrollPane);
    attackPanel.add(attackButton);
    return attackPanel;
  }

  // Action listeners for AttackView
  public void AttackViewActionListeners(ActionListener evt) {
    attackButton.addActionListener(evt);
    validate();
    repaint();
  }

  public int getBoarderingTerritoryIndex() {
    return boarderingTerritoryList.getSelectedIndex();
  }
  public int getAttackingIndex()
  {
    return attackingIndex;
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
    troopsComboBox = new JComboBox( r);
    return troopsComboBox;
  }
}



