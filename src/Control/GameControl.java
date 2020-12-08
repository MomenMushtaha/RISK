package Control;

import Logic.Gameplay;
import Logic.JdomParser;
import View.AIView;
import View.BoardView;
import View.GameView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    } else if (actionEvent.equals("loadGameBtn")) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            try {
            load(fileChooser);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        }
    }}

    private void load(JFileChooser fileChooser) throws IOException, ClassNotFoundException {
        ObjectInputStream objectReader = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()));
        game = (Gameplay) objectReader.readObject();
        objectReader.close();
        System.out.println("loading saved game file is done successfully");
        new BoardViewControl(new BoardView(game),game);
        game.getGameStatus();
        System.out.println("Its is "  + game.getCurrentPlayer().getName() + " turn !");
        if (game.getState().equals("")) {
            System.out.println(game.getCurrentPlayer().getName() + " finished his/her deploying, attacking, and fortifying states");
        } else {
            System.out.println(game.getCurrentPlayer().getName() + " is in his/her " + game.getState());
        }
    }
}