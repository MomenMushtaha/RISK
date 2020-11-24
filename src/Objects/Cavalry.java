package Objects;


/**
 * Cavalry class is a type of troop that is worth 5 infantry
 *
 * @author Peter Tanyous
 * @version version 1
 */
public class Cavalry extends Troops {
  private final int worth;


  /**
   * Constructor for objects of class Cavalry
   */
  public Cavalry() {
    worth = 5;
  }


  /**
   * returns the worth of an Cavalry troop
   *
   * @return int the worth of the troop
   */
  public int getWorth() {
    return worth;
  }


}

