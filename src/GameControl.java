import java.lang.Integer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class GameControl implements ActionListener {

  public Gameplay game;
  private GameView view;

  //Constructor
  public GameControl(GameView view, Gameplay game) {
    this.view = view;
    this.game = game;
    System.out.println("Risk!");
    view.GameViewActionListeners(this::actionPerformed);
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
          int num = Integer.parseInt(selected);
          game.InitializePlayers(num) ;
          game.addInitialTerritories(num);
          game.NumberInitialTroops();
          game.currentPlayer = game.getPlayers(0);
          game.troopsNewTurn = game.get_bonus(game.currentPlayer);
            view.setVisible(false);
            new BoardViewControl(new BoardView(game), game);
            System.out.println("Loading BoardViewControl...");
        }
        default -> {
          System.out.println("Error: " + actionEvent + " actionEvent not found!");
          new GameControl(new GameView(game),game);
        }}}}}