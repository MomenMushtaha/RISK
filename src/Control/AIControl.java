package Control;

import Logic.Gameplay;
import View.AIView;
import View.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class AIControl implements ActionListener {

  public final Gameplay game;
  private final AIView view;

  //Constructor
  public AIControl(Gameplay game, AIView view) {
    this.view = view;
    this.game = game;
    System.out.println("Start Panel");
    view.AIViewActionListeners(this);
    //Add this class' actionListener to GameView's buttons
  }

  //GameView's controller
  public void actionPerformed(ActionEvent evt) {
    String actionEvent = evt.getActionCommand();
    if (actionEvent.equals("StartBtn")) {
      System.out.println("Initializing game");
      String s = Objects.requireNonNull(view.AIPlayerComboBox.getSelectedItem()).toString();
      if ("0".equals(s)) {
        System.out.println("No AI players");
        view.setVisible(false);
        new BoardViewControl(new BoardView(game), game);
        System.out.println("Loading BoardViewControl...");
      } else if ("1".equals(s)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        System.out.println("last player is an AI");
        view.setVisible(false);
        new BoardViewControl(new BoardView(game), game);
        System.out.println("Loading BoardViewControl...");
      } else if ("2".equals(s)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        game.getPlayers(game.playersSize() - 2).setAI();
        System.out.println("last 2 players are AI");
        view.setVisible(false);
        new BoardViewControl(new BoardView(game), game);
        System.out.println("Loading BoardViewControl...");
      } else if ("3".equals(s)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        game.getPlayers(game.playersSize() - 2).setAI();
        game.getPlayers(game.playersSize() - 3).setAI();
        System.out.println("last 3 players are AI");
        view.setVisible(false);
        new BoardViewControl(new BoardView(game), game);
        System.out.println("Loading BoardViewControl...");
      } else if ("4".equals(s)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        game.getPlayers(game.playersSize() - 2).setAI();
        game.getPlayers(game.playersSize() - 3).setAI();
        game.getPlayers(game.playersSize() - 4).setAI();
        System.out.println("last 4 players are AI");
        view.setVisible(false);
        new BoardViewControl(new BoardView(game), game);
        System.out.println("Loading BoardViewControl...");
      } else if ("5".equals(s)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        game.getPlayers(game.playersSize() - 2).setAI();
        game.getPlayers(game.playersSize() - 3).setAI();
        game.getPlayers(game.playersSize() - 4).setAI();
        game.getPlayers(game.playersSize() - 5).setAI();
        System.out.println("all players except Player 1 are AI");
        view.setVisible(false);
        new BoardViewControl(new BoardView(game), game);
        System.out.println("Loading BoardViewControl...");
      }
    }
  }
}