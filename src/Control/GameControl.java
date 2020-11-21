package Control;

import Logic.Gameplay;
import View.BoardView;
import View.GameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class GameControl implements ActionListener {

  public Gameplay game;
  private final GameView view;

  //Constructor
  public GameControl(GameView view, Gameplay game) {
    this.view = view;
    this.game = game;
    System.out.println("Start Panel");
    view.GameViewActionListeners(this);
    //Add this class' actionListener to GameView's buttons
  }

  //GameView's controller
  public void actionPerformed(ActionEvent evt) {
    String actionEvent = evt.getActionCommand();
    if (actionEvent.equals("newGameBtn")) {
      System.out.println("Initializing game");
      switch (Objects.requireNonNull(view.playerComboBox.getSelectedItem()).toString()) {
        case "2", "3", "4", "5", "6" -> {
          String selected = view.playerComboBox.getSelectedItem().toString();
          int numPlayers = Integer.parseInt(selected);
          game.startGame(numPlayers);
          view.setVisible(false);
            new BoardViewControl(new BoardView(game), game);
            System.out.println("Loading BoardViewControl...");
        }
        default -> {
          System.out.println("Error: " + actionEvent + " actionEvent not found!");
          new GameControl(new GameView(game),game);
        }}}}}