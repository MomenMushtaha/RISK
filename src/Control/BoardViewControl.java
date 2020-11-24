package Control;

import Logic.Gameplay;
import View.BoardView;
import View.DeployView;
import View.TerritoryView;
import View.TradeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BoardViewControl implements ActionListener {

  public final Gameplay game;
  public String passed;
  public String[] options;

  public BoardViewControl(BoardView view, Gameplay game) {
    this.game = game;
    options = new String[]{"first Card, second Card , third Card", "first Card, second Card , fourth Card", "first Card, second Card, fifth Card",
      "first Card, third Card, fourth Card", "first Card, third Card, fifth Card", "first Card, fourth Card, fifth Card", "second Card, third Card, fourth Card"
      , "second Card, third Card, fifth Card", "second Card, fourth Card, fifth Card"};
    game.printRules();
    game.printCommands();
    game.printWelcome();
    view.BoardViewActionListeners(this);
  }

  public void actionPerformed(ActionEvent evt) {
    String actionEvent = evt.getActionCommand();
    //this could be considered code smell, however it makes it easier ot read than if statements
    switch (actionEvent) {
      case "tradeBtn":
        game.listTheCards();
        if (game.getTrade().equals("")) {
          System.out.println("you can't trade cards right now");
        } else {
          if (game.getCurrentPlayer().getHand().getCards().size() < 3) {
            System.out.println("you have less than 3 cards");
          } else {
            new TradeControl(game, new TradeView(game, options), options);
          }
        }
        break;
      case "deployBtn":
        if (game.getTrade().equals("must Trade")) {
          System.out.println("Since you have 5 cards, you must trade before doing anything");
        } else {
          if (game.getState().equals("")) {
            if (game.getTroopsNewTurn() == 0) {
              System.out.println("no troops to deploy");
              game.updateTroopsNewTurn(game.getTroopsNewTurn());
            } else {
              new DeployControl(game, new DeployView(game));
            }
          } else {
            System.out.println("Deploying phase is already done");
          }
        }
        break;
      case "attackBtn":
        if (game.getTrade().equals("Must Trade")) {
          System.out.println("Since you have 5 cards, you must trade before doing anything");
        } else {
          if (game.getState().equals("") || game.getState().equals("fortified")) {
            System.out.println("You can only attack if you finished your deploying phase and did not start the fortifying phase");
          } else {
            passed = "attackBtn";
            new TerritoryControl(game, new TerritoryView(game), passed);
          }
        }
        break;
      case "fortifyBtn":
        if (game.getTrade().equals("Must Trade")) {
          System.out.println("Since you have 5 cards, you must trade before doing anything");
        } else {
          if (game.getState().equals("")) {
            System.out.println("You can only fortify once you finish your deploying phase");
            System.out.println(" Note: if you start the fortify phase, you will not be able to attack anymore!");
          } else {
            passed = "fortifyBtn";
            new TerritoryControl(game, new TerritoryView(game), passed);
          }
        }
        break;
      case "passBtn":
        if (game.getTrade().equals("Must Trade")) {
          System.out.println("Since you have 5 cards, you must trade before doing anything");
        } else {
          game.nextPlayerTurn();
        }
        break;
      case "quitBtn":
        game.quit();
        break;
      case "helpBtn":
        game.printHelp();
        break;
      case "printBtn":
        game.getGameStatus();
        break;
      default:
        System.out.println("actionEvent not found: " + actionEvent);
        break;
    }
  }
}