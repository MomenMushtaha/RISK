package Control;

import Logic.Gameplay;
import Logic.Serialization;
import View.AIView;
import View.BoardView;
import View.GameView;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;


public class GameControl implements ActionListener {
    public Gameplay game;
    public Serialization serialization;
    private final GameView view;

  //Constructor
  public GameControl(GameView view, Gameplay game) {
    this.view = view;
    this.game = game;
    serialization = new Serialization();
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
            game.setNumPlayers(numPlayers);
            game.InitializePlayers(numPlayers);
            view.setVisible(false);
            new AIControl(game, new AIView(numPlayers));
            System.out.println("Loading AIControl...");
        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
            view.setVisible(false);
            new GameControl(new GameView(), game);
        }
    } else if (actionEvent.equals("loadGameBtn")) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.sv", "sv"));
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                try (ObjectInputStream objectReader = serialization.load(fileChooser)) {
                    game = (Gameplay) objectReader.readObject();
                System.out.println("loading saved game file is done successfully");
                view.setVisible(false);
                new BoardViewControl(new BoardView(game),game);
                game.getGameStatus();
                System.out.println("Its is "  + game.getCurrentPlayer().getName() + "'s turn!");
                if (game.getState().equals("")) {
                    System.out.println(game.getCurrentPlayer().getName() + " finished his/her deploying, attacking, and fortifying states");
                } else {
                    System.out.println(game.getCurrentPlayer().getName() + " is in his/her " + game.getState());
                }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        }
    }}
}