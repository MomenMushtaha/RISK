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
    new GameControl(new GameView(game), game);

      //Asks the user for a command
     /* if((i+1) == numPlayers)
      {
        i=-1;
      }*/
    }
  }

