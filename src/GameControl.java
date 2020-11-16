import java.io.IOException;
import java.lang.Integer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class GameControl implements ActionListener {

  public Gameplay game;
  public GameView view;
  public BoardView bv;

  //Constructor
  public GameControl(GameView view, Gameplay game) {
    this.view = view;
    this.game = game;
    System.out.println("Risk!");
    view.GameViewActionListeners(this::actionPerformed);
    //Add this class' actionListener to riskView's buttons
  }

  //RiskView's controller
  public void actionPerformed(ActionEvent evt) {

    String actionEvent = evt.getActionCommand();

    if (actionEvent.equals("newGameBtn")) {
      System.out.println("Initializing game");
      //Opens the playerCountDialog

      switch (Objects.requireNonNull(view.playerComboBox.getSelectedItem()).toString()) {
        case "2", "3", "4", "5", "6" -> {
          String selected = view.playerComboBox.getSelectedItem().toString();
          int num = Integer.parseInt(selected);
          System.out.println(num);
          game.InitializePlayers(num) ;
          game.addInitialTerritories(num);
          game.NumberInitialTroops();
          try {
            new BoardViewControl(new BoardView(game), game);
            System.out.println("Loading BoardViewControl...");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }


        }
        default -> System.out.println("Error: " + actionEvent + " actionEvent not found!");
      } } }


  }