package Objects;


import java.io.Serializable;

/**
 * Class that all troops types will
 * be extending
 *
 * @author Peter
 * @version Tanyous
 */
public class Troops implements Serializable {
  private final int worth;


  /**
   * constructor for Troops
   */
  public Troops() {
    worth = 0;
  }


  /**
   * gets the worth of the troop depending on its type
   *
   * @return the worth of the troop depending on its type
   */
  public int getWorth() {
    return worth;
  }


}

