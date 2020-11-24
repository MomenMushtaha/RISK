package Control;

import Logic.Gameplay;
import View.AIView;
import View.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class GameControl implements ActionListener {

  public final Gameplay game;
  private final GameView view;

  //Constructor
  public GameControl(GameView view, Gameplay game) {
    this.view = view;
    this.game = game;
    System.out.println("Start Panel");
    view.GameViewActionListeners(this);
    //Add this class' actionListener to GameView's buttons
  }

  /**
   * Starts the game and Initializes the Board and the Players
   */
  public static void main(String[] args) {
    Logic.Gameplay game = new Logic.Gameplay();
    new GameControl(new GameView(), game);

  }

  //GameView's controller
  public void actionPerformed(ActionEvent evt) {
    String actionEvent = evt.getActionCommand();
    if (actionEvent.equals("newGameBtn")) {
      System.out.println("Initializing game");
        String s = Objects.requireNonNull(view.playerComboBox.getSelectedItem()).toString();
        if ("2".equals(s) || "3".equals(s) || "4".equals(s) || "5".equals(s) || "6".equals(s)) {
            String selected = view.playerComboBox.getSelectedItem().toString();
            int numPlayers = Integer.parseInt(selected);
            game.startGame(numPlayers);
            view.setVisible(false);
            new AIControl(game, new AIView(numPlayers));
            System.out.println("Loading AIControl...");
        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
            new GameControl(new GameView(), game);
        }
    }
  }
}