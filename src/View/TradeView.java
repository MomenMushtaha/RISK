package View;
import Logic.*;
import Objects.Card;
import Objects.Hand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TradeView extends JFrame {
  // initialize labels
  static JLabel label;
  // initialize buttons
  private JButton tradeButton;
  // initialize combobox
  public JComboBox<String> tradeComboBox;
  //hand
  private String[] options;
  //game
  public Gameplay game;

  public TradeView(Gameplay game, String[] options) {
    this.game = game;
    this.options = options;
    setPreferredSize(new Dimension(300, 300));
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
    add(TradeDialog());
    //to make sure the dialog is appearing at the front
    toFront();
  }

  private JPanel TradeDialog() {
    // Creates the panel, Labels and Layouts
    //initialize panel
    JPanel tradePanel = new JPanel();
    label = new JLabel("please choose what option you want to trade");
    // Sets Layout
    //initialize layouts
    GridLayout tradeLayout = new GridLayout(4, 1);
    tradePanel.setLayout(tradeLayout);
    //ScrollingPanes
    tradeButton = new JButton("Trade!");
    // Setting button commands
    String fortifyString = "Trade!";
    tradeButton.setActionCommand(fortifyString);
    Hand cards = game.getCurrentPlayer().getHand();
    //initialize scroll pane
    tradePanel.add(label);
    tradeComboBox = new JComboBox<>(options);
    tradePanel.add(tradeComboBox);
    tradePanel.add(tradeButton);
    return tradePanel;
  }

  // Action listeners for TradeView
  public void TradeViewActionListeners(ActionListener evt) {
    tradeButton.addActionListener(evt);
    validate();
    repaint();
  }


}

