import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.SplittableRandom;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

class BoardViewControl implements ActionListener {

  public Gameplay game;
  public int troopsNewTurn;
  public String passed;

  public BoardViewControl(BoardView view, Gameplay game) throws InterruptedException {
    this.game = game;
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
    for (int i = 0; i < numPlayers; i++) {
      game.currentPlayer = game.getPlayers(i);
      System.out.println("this is " + game.currentPlayer.getName());
      MILLISECONDS.sleep(300);
      System.out.println("It is " + game.getPlayers(i).getName() + " turn");
      int bonus = 0;
      if (game.currentPlayer.getContinents().size() > 0) {
        for (int j = 0; j < game.currentPlayer.getContinents().size(); j++) {
          bonus = bonus + game.currentPlayer.getContinents().get(j).getBonusArmies();
        }
        MILLISECONDS.sleep(300);
        System.out.println("you received " + bonus + " bonus troops for the continents you are holding");
      }
      troopsNewTurn = (game.currentPlayer.getTerritories().size() / 3) + bonus;
      MILLISECONDS.sleep(300);
      System.out.println(game.getPlayers(i).getName() + " receives " + troopsNewTurn + " troops");
    }
  }
  public void actionPerformed(ActionEvent evt) {

    String actionEvent = evt.getActionCommand();
    if (actionEvent.equals("tradeBtn")) {

    } else if(actionEvent.equals("deployBtn")){
      if (troopsNewTurn ==0)
      {
        System.out.println("no troops to deploy");
      }
      else {
        new DeployControl(game,new DeployView(game, troopsNewTurn), troopsNewTurn);
      }
    }else if (actionEvent.equals("attackBtn")) {
      passed = "attackBtn";
      new TerritoryControl(game, new TerritoryView(game), passed);
    } else if (actionEvent.equals("fortifyBtn")) {
      passed = "fortifyBtn";
      new TerritoryControl(game, new TerritoryView(game), passed);
    } else if (actionEvent.equals("passBtn")) {
        game.nextPlayerTurn();
    } else if (actionEvent.equals("passBtn")) {
      game.nextPlayerTurn();
    } else if (actionEvent.equals("passBtn")) {
      game.nextPlayerTurn();

    } else {
      System.out.println("actionEvent not found: " + actionEvent);
    }
  }
}