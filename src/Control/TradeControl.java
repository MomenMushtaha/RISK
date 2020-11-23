package Control;
import Logic.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TradeControl implements ActionListener {
  public final Gameplay game;
  public final TradeView view;
  private String[] options;
  public boolean trade = false;

  //Constructor
  public TradeControl(Gameplay game, TradeView view, String[] options) {
    this.game = game;
    System.out.println("Trade Panel!");
    this.view = view;
    if(game.getCurrentPlayer().getIsAI())
    {
      view.setVisible(false);
    }
    else {
    this.options = options;
    //Add this class' actionListener to TradeView's buttons
    view.TradeViewActionListeners(this);
  }}
  @Override
  public void actionPerformed(ActionEvent e) {
      String actionEvent = e.getActionCommand();
      if (actionEvent.equals("Deploy Now!")) {
        view.setVisible(false);
        indexer(view.tradeComboBox.getSelectedIndex());
      }}

public void indexer(int index)
{
  boolean x = false;
  //easier to read when the if statement is just one line
  if (index == 0) { x = game.getCurrentPlayer().getHand().removeCards(1,2,3); }
  if (index == 1) { x = game.getCurrentPlayer().getHand().removeCards(1,2,4); }
  if (index == 2) { x = game.getCurrentPlayer().getHand().removeCards(1,2,5); }
  if (index == 3) { x = game.getCurrentPlayer().getHand().removeCards(1,3,4); }
  if (index == 4) { x = game.getCurrentPlayer().getHand().removeCards(1,3,5); }
  if (index == 5) { x = game.getCurrentPlayer().getHand().removeCards(1,4,5); }
  if (index == 6) { x = game.getCurrentPlayer().getHand().removeCards(2,3,4); }
  if (index == 7) { x = game.getCurrentPlayer().getHand().removeCards(2,3,5); }
  if (index == 8) { x = game.getCurrentPlayer().getHand().removeCards(2,4,5); }
  if (index == 9) { x = game.getCurrentPlayer().getHand().removeCards(3,4,5); }
    if (x) {
      game.trade();
      trade = true;
    } else {
      if (game.getCurrentPlayer().getIsAI()) {
        //do nothing
      } else {
        new TradeControl(game, new TradeView(game, options), options);
      }
    }
  }
}