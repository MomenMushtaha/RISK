import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Play {
  private static int troopsNewTurn;
  public static Gameplay game;


  /**
   * Starts the game and Initializes the Board and the Players
   * @throws InterruptedException for sleep method
   */
  public static void main(String[] args) throws InterruptedException {
    game = new Gameplay();
    GameControl control =  new GameControl(new GameView(game), game);
    int numPlayers = game.players.size();
    for (int i = 0; i < numPlayers; i++) {
      game.currentPlayer = game.getPlayers(i);
      MILLISECONDS.sleep(300);
      System.out.println("It is " + game.getPlayers(i).getName() + " turn");
      int bonus = 0;
      if (game.currentPlayer.getContinents().size() > 0) {
        for (int j = 0; j < game.currentPlayer.getContinents().size(); j++) {
          bonus = bonus + game.currentPlayer.getContinents().get(j).getBonusArmies();
        }
        MILLISECONDS.sleep(300);
        System.out.println("you received " + bonus + " bonus troops for the continents you are holding");
      }
      troopsNewTurn = (game.currentPlayer.getTerritories().size() / 3) + bonus;
      MILLISECONDS.sleep(300);
      System.out.println(game.getPlayers(i).getName() + " receives " + troopsNewTurn + " troops");
      //Asks the user for a command
      if((i+1) == numPlayers)
      {
        i=-1;
      }
    }
  }

}
