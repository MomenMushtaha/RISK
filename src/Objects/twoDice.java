package Objects;


/**
 * two Dice used when attacker is taking turn
 * if attacker decides to use two dice.
 * Also used by defender when being attacked
 *
 * @author Momin Mushtaha, Peter tanyous
 * @version 2
 */
public class twoDice {
  private final Die first;
  private final Die second;
  private int x;
  private int y;


  /**
   * constructor for twoDice
   */
  public twoDice() {
    first = new Die();
    second = new Die();
  }

  /**
   * rolls the dice
   */
  public void roll() {
    x = first.roll();
    y = second.roll();
  }


  /**
   * gets the highest integer value in twoDice
   *
   * @return max value
   */
  public int getHighest() {
    return Math.max(x, y);
  }


  /**
   * gets the second highest integer value in twoDice
   *
   * @return second max value
   */
  public int getSecondHighest() {
    return Math.min(x, y);
  }
}
