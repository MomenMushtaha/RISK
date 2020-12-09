package Control;

import Logic.Gameplay;
import Logic.Serialization;
import View.BoardView;
import View.DeployView;
import View.TerritoryView;
import View.TradeView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class BoardViewControl implements ActionListener {

  public final Gameplay game;
  public BoardView view;
  private Serialization serialization;
  public String passed;
  public String[] options;

  public BoardViewControl(BoardView view, Gameplay game) {
    this.game = game;
    this.view = view;
    serialization = new Serialization();
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
    if ("tradeBtn".equals(actionEvent)) {
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
    } else if ("deployBtn".equals(actionEvent)) {
      if (game.getTrade().equals("must Trade")) {
        System.out.println("Since you have 5 cards, you must trade before doing anything");
      } else {
        if (game.getState().equals("deploying")) {
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
    } else if ("attackBtn".equals(actionEvent)) {
      if (game.getTrade().equals("Must Trade")) {
        System.out.println("Since you have 5 cards, you must trade before doing anything");
      } else {
        if (game.getState().equals("deploying") || game.getState().equals("")) {
          System.out.println("You can only attack if you finished your deploying phase and did not start the fortifying phase");
        } else {
          passed = "attackBtn";
          new TerritoryControl(game, new TerritoryView(game), passed);
        }
      }
    } else if ("fortifyBtn".equals(actionEvent)) {
      if (game.getTrade().equals("Must Trade")) {
        System.out.println("Since you have 5 cards, you must trade before doing anything");
      } else {
        if (game.getState().equals("fortifying") || game.getState().equals("attacking")) {
          passed = "fortifyBtn";
          new TerritoryControl(game, new TerritoryView(game), passed);
        } else {
          System.out.println("You can only fortify if you did not fortify yet and finish your deploying phase,");
          System.out.println(" Note: if you start the fortify phase, you will not be able to attack anymore!");
        }
      }
    } else if ("passBtn".equals(actionEvent)) {
      if (game.getTrade().equals("Must Trade")) {
        System.out.println("Since you have 5 cards, you must trade before doing anything");
      } else {
        game.nextPlayerTurn();
      }
    } else if ("saveBtn".equals(actionEvent)) {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setAcceptAllFileFilterUsed(false);
      fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.sv", "sv"));
      if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        try {
         ObjectOutputStream objectWriter = serialization.save(fileChooser);
          objectWriter.writeObject(game);
          System.out.println("Game Saved Successfully");
          objectWriter.close();
        } catch (ClassNotFoundException | IOException e) {
          e.printStackTrace();
        }
      }
    } else if ("quitBtn".equals(actionEvent)) {
      game.quit();
    } else if ("helpBtn".equals(actionEvent)) {
      game.printHelp();
    } else if ("printBtn".equals(actionEvent)) {
      game.getGameStatus();
    } else {
      System.out.println("actionEvent not found: " + actionEvent);
    }
  }



}