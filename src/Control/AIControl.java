package Control;

import Logic.Gameplay;
import View.AIView;
import View.MapView;

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
    //Add this class' actionListener to AIView's buttons
  }

  //AIView's controller
  public void actionPerformed(ActionEvent evt) {
    String actionEvent = evt.getActionCommand();
    if (actionEvent.equals("StartBtn")) {
      System.out.println("Initializing game");
      String AI = Objects.requireNonNull(view.AIPlayerComboBox.getSelectedItem()).toString();
      setupAI(AI);
      view.setVisible(false);
      new MapControl(game,new MapView());
      System.out.println("Loading mapControl...");
    }
  }


    private void setupAI(String AIPlayers)
    {
      if ("0".equals(AIPlayers)) {
        System.out.println("No AI players");
      } else if ("1".equals(AIPlayers)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        System.out.println("last player is an AI");
      } else if ("2".equals(AIPlayers)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        game.getPlayers(game.playersSize() - 2).setAI();
        System.out.println("last 2 players are AI");
      } else if ("3".equals(AIPlayers)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        game.getPlayers(game.playersSize() - 2).setAI();
        game.getPlayers(game.playersSize() - 3).setAI();
        System.out.println("last 3 players are AI");
      } else if ("4".equals(AIPlayers)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        game.getPlayers(game.playersSize() - 2).setAI();
        game.getPlayers(game.playersSize() - 3).setAI();
        game.getPlayers(game.playersSize() - 4).setAI();
        System.out.println("last 4 players are AI");
      } else if ("5".equals(AIPlayers)) {
        game.getPlayers(game.playersSize() - 1).setAI();
        game.getPlayers(game.playersSize() - 2).setAI();
        game.getPlayers(game.playersSize() - 3).setAI();
        game.getPlayers(game.playersSize() - 4).setAI();
        game.getPlayers(game.playersSize() - 5).setAI();
        System.out.println("all players except Player 1 are AI");
      }
    }
    }