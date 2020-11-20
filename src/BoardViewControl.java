import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BoardViewControl implements ActionListener {

  public Gameplay game;
  public BoardView view;
  public DeployControl deploy;
  public String passed;

  public BoardViewControl(BoardView view, Gameplay game){
    this.game = game;
    this.view = view;
    game.printRules();
    game.printCommands();
    game.printWelcome();
    view.BoardViewActionListeners(this::actionPerformed);
    }
  public void actionPerformed(ActionEvent evt) {
    String actionEvent = evt.getActionCommand();
    if (actionEvent.equals("tradeBtn")) {
    } else if(actionEvent.equals("deployBtn")){
      if (game.troopsNewTurn == 0) {
        System.out.println("no troops to deploy");
        game.getupdateTroopsNewTurn();
      }
      else {
        deploy = new DeployControl(game,new DeployView(game));
    }}else if (actionEvent.equals("attackBtn")) {
      passed = "attackBtn";
      new TerritoryControl(game, new TerritoryView(game), passed);
    } else if (actionEvent.equals("fortifyBtn")) {
      passed = "fortifyBtn";
      new TerritoryControl(game, new TerritoryView(game), passed);
    } else if (actionEvent.equals("passBtn")) {
        game.nextPlayerTurn();
    } else if (actionEvent.equals("quitBtn")) {
      game.quit();
    } else if (actionEvent.equals("helpBtn")) {
      game.printHelp();
    } else if (actionEvent.equals("printBtn")) {
      game.getGameStatus();
    } else {
      System.out.println("actionEvent not found: " + actionEvent);
    }
  }
}