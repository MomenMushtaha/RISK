import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

class BoardViewControl implements ActionListener {

  public Gameplay game;
  public BoardView view;
  public TerritoryView tv;
  public int troopsNewTurn;

  public BoardViewControl(BoardView view, Gameplay game) throws InterruptedException {
    this.game = game;
    this.view = view;
    view.BoardViewActionListeners(this);
    game.printWelcome();
    game.printRules();
    game.printCommands();
    int numPlayers = game.players.size();
    MILLISECONDS.sleep(300);
    System.out.println("At the start of each turn each player receives 3 or more troops and" +
      " if you rule a whole continent you will get more bonus troops.");
    MILLISECONDS.sleep(300);
    System.out.println("The game will start with player 1");
  }
  public void actionPerformed(ActionEvent evt) {

    String actionEvent = evt.getActionCommand();
    if (actionEvent.equals("tradeBtn")) {

    } else if(actionEvent.equals("deployBtn")){
      new DeployControl(game,new DeployView(game), troopsNewTurn);
    }else if (actionEvent.equals("attackBtn")) {
      new TerritoryControl(new TerritoryView(game));

    } else if (actionEvent.equals("fortifyBtn")) {
      new TerritoryControl(new TerritoryView(game));

    } else if (actionEvent.equals("passBtn")) {
      try {
        game.nextPlayerTurn();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    } else {
      System.out.println("actionEvent not found: " + actionEvent);
    }
  }
}