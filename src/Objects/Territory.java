package Objects;


import java.util.ArrayList;


/**
 * Territory Class represents each territory and the amount of troops on it and who is
 * the owner player along with a list of all adjacent territories
 *
 * @author Peter Tanyous, Momin Mushtaha
 * @version 2
 */
public class Territory {
  private final String name;
  private final ArrayList<Territory> borderingTerritories;
  private Player owner;
  private int troops;


  /**
   * Constructor for objects of class Territory
   */
  public Territory(String name) {
    this.name = name;
    borderingTerritories = new ArrayList<>();
  }


  /**
   * returns the name of the territory
   *
   * @return String name of the territory
   */
  public String getName() {
    return name;
  }


  /**
   * returns the owner Player of the territory
   *
   * @return Player owner of the territory
   */
  public Player getPlayer() {
    return owner;
  }


  /**
   * returns the number of troops on the territory
   *
   * @return int troops on the territory
   */
  public int getTroops() {
    return troops;
  }


  /**
   * adds a territory to the bordering territories used by Board class
   * to initialize the map
   *
   * @param adjacent to be added to the boardingTerritories of the list
   */
  public void addBorderTerritories(Territory adjacent) {
    if (adjacent != null) {
      borderingTerritories.add(adjacent);
    }
  }


  /**
   * adds troops to the territory
   *
   * @param troops number of troops to be added to the territory when gamePlay class has a
   *               player in the drafting phase
   */
  public void addTroops(int troops) {
    this.troops = this.troops + troops;
  }


  /**
   * removes troops from the territory
   *
   * @param troops number of troops to be removed from the territory when gamePlay has player in fortifying phase
   */
  public void removeTroops(int troops) {
    this.troops = this.troops - troops;
  }


  /**
   * changes the owner of the territory when  defender loses or attacker loses in the
   * battle
   *
   * @param newOwner new owner of the territory
   */
  public void changeOwner(Player newOwner) {
    owner = newOwner;
  }

  /**
   * retrieves an ArrayList of the bordering Territories
   *
   * @return borderingTerritories ArrayList
   */
  public ArrayList<Territory> getBorderTerritories() {
    return borderingTerritories;
  }
}
