package Control;

import Logic.Gameplay;
import View.TradeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TradeControl implements ActionListener {
  public final Gameplay game;
  public final TradeView view;
  private final String[] options;

  //Constructor
  public TradeControl(Gameplay game, TradeView view, String[] options) {
    this.game = game;
    System.out.println("Trade Panel!");
    this.view = view;
    this.options = options;
    //Add this class' actionListener to TradeView's buttons
    view.TradeViewActionListeners(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String actionEvent = e.getActionCommand();
    if (actionEvent.equals("Trade!")) {
      view.setVisible(false);
      if (view.tradeComboBox.getSelectedIndex() == -1) {
        System.out.println("No option was chosen");
        new TradeControl(game, new TradeView(game, options), options);
      } else {
        if (game.indexer(view.tradeComboBox.getSelectedIndex())) {
          game.trade();
          System.out.println("Trade was done Successfully");
        } else {
          new TradeControl(game, new TradeView(game, options), options);
        }
      }
    }
  }
}