package Objects;


import java.util.ArrayList;


/**
 * Continents are used to give bonus troops for players
 * who control the continent. Each continent gives different
 * amount of bonus troops for players in control.
 *
 * @author Peter Tanyous
 * @version 1.01 October 24, 2020
 */
public class Continent {
  private final String name;
  private final int bonusArmies;
  private final ArrayList<Territory> territories;


  /**
   * Constructor for Continent
   *
   * @param name        is the name of the Continent
   * @param bonusArmies is the # of bonus armies players will get in their turn for holding the Continent
   * @param territories is the Territories included in this Continent
   */
  public Continent(String name, int bonusArmies, ArrayList<Territory> territories) {
    this.name = name;
    this.bonusArmies = bonusArmies;
    this.territories = territories;
  }


  /**
   * return the Name of the Continent
   */
  public String getName() {
    return name;
  }


  /**
   * returns the number of bonus armies a player gets per round when the player controls this
   * Continent
   **/
  public int getBonusArmies() {
    return bonusArmies;
  }


  /**
   * Returns a list of the country objects that are on this continent
   **/
  public ArrayList<Territory> getMemberTerritories() {
    return territories;
  }


}