package Objects;

/**
 * three Dice used when attacker is taking turn
 * if attacker decides to use three.
 *
 * @author Peter Tanyous
 * @version 1.01 October 24, 2020
/**
 * two Dice used when attacker is taking turn
 * if attacker decides to use two dice.
 * Also used by defender when being attacked
 *
 * @author Peter Tanyous
 * @version 1.01 October 24, 2020
 */
public class twoDice {

  private final Die first;
  private final Die second;
  private int x ;
  private int y ;
  public twoDice() {

    first= new Die();
    second= new Die();

  }
  public void roll(){
    x = first.roll();
    y = second.roll();
  }
  public int getHighest(){

    return Math.max(x, y);

  }
  public int getSecondHighest(){
    return Math.min(x, y);
  }
}
