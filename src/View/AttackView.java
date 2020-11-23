package View;
import Logic.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AttackView extends JFrame {
  // initialize labels
  static JLabel label;
  // initialize buttons
  private JButton attackButton;
  // initialize combobox
  public JComboBox<String> troopsComboBox;
  //initialize lists
  public JList<String> boarderingTerritoryList;
  //troops
  private final int troops;
  //attacking territory index
  private final int attackingIndex;
  //game
  public final Gameplay game;

  public AttackView(Gameplay game, int troops, int attackingIndex) {
    this.attackingIndex = attackingIndex;
    this.troops = troops;
    this.game = game;
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
    add(AttackDialog());
    //to make sure the dialog is appearing at the front
    toFront();
  }

  private JPanel AttackDialog() {
    // Creates the panel, Labels and Layouts
    //initialize panel
    JPanel attackPanel = new JPanel();
    label = new JLabel("please choose what territory you want to attack");
    // Sets Layout
    //initialize layouts
    GridLayout attackLayout = new GridLayout(4, 1);
    attackPanel.setLayout(attackLayout);
    String[] s = game.listBorderingTerritories(game.getCurrentPlayer().getTerritories().get(attackingIndex));
    boarderingTerritoryList = new JList<>(s);
    boarderingTerritoryList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    boarderingTerritoryList.setLayoutOrientation(JList.VERTICAL_WRAP);
    //ScrollingPanes
    attackButton = new JButton("Attack Now!");
    // Setting button commands
    String attackString = "Attack Now!";
    attackButton.setActionCommand(attackString);
    //initialize scroll pane
    JScrollPane boarderingTerritoryListScrollPane = new JScrollPane(boarderingTerritoryList);
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


  public JComboBox<String> troopsComboBox(int troops) {
    int num = 0;
    int i = 0;
    String[] r = new String[troops];
    while (num < troops) {
      num += 1;
      r[i] = String.valueOf(num);
      i++;
    }
    troopsComboBox = new JComboBox<>( r);
    return troopsComboBox;
  }
}



