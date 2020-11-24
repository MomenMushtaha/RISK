package Objects;


import Logic.Gameplay;
import java.util.Arrays;
import java.util.Random;


/**
 * AI Class Initializes an AI Player and contains its commands for each turn
 *
 * @author Momin Mushtaha
 * @version 1
 */
public class AI {
  private final Gameplay game;
  private final Random r;


  /**
   * constructor for the AI class
   * @param game is the current game playing
   */
  public AI(Gameplay game) {
    this.r = new Random();
    this.game = game;
  }


  /**
   * contains the commands the AI executes for each turn
   */
  public void makeMove() {
    //trade
    for (int i = 0; i < 10; i++) {
      if (game.indexer(i)) {
        i = 10;
      }
    }

    //deploy
    int t = r.nextInt(game.getCurrentPlayer().getTerritories().size() - 1);
    game.getCurrentPlayer().getTerritoyAtIndex(t).addTroops(game.getTroopsNewTurn());
    System.out.println("AI Troops added");

    //attack
    Territory attacking = game.getCurrentPlayer().getTerritoyAtIndex(t);
    System.out.println(Arrays.toString(game.listBorderingTerritories(attacking)));
    Territory target = game.getBorderTerritories().get(0);
    game.attack(target, attacking, attacking.getTroops());
    if (game.getCurrentPlayer().getTerritories().contains(target) && (attacking.getTroops() > 3)) {
      game.MoveAfterAttack(target, attacking, attacking.getTroops() -1);
    }

    //fortify
    if (attacking.getTroops() > 3) {
      game.listPathTerritories(attacking);
      Territory fortifyTo = game.getPathListingAtIndex(0);
      game.fortify(attacking, fortifyTo, 1);
    }
    game.nextPlayerTurn();
  }


}
