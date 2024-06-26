package Objects;


import java.io.Serializable;

/**
 * is a type of troop that is worth 10 infantry
 *
 * @author Peter Tanyous
 * @version version 1
 */
public class artillery extends Troops {
  private final int worth;


  /**
   * Constructor for objects of class artillery
   */
  public artillery() {
    worth = 10;
  }


  /**
   * returns the worth of an artillery troop
   *
   * @return int the worth of the troop
   */
  public int getWorth() {
    return worth;
  }


}
