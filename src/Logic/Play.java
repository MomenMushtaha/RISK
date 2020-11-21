package Logic;

import Control.GameControl;
import View.GameView;

public class Play {


  /**
   * Starts the game and Initializes the Board and the Players
   */
  public static void main(String[] args) {
    Logic.Gameplay game = new Logic.Gameplay();
    new GameControl(new GameView(game), game);

    }
  }

