package Objects;


import java.io.Serializable;
import java.util.ArrayList;


/**
 * Player Class represents each player playing the game and their Hand of cars and a list of the territories owned and
 * continents a player is controlling to manage the amount of bonus troops a player is to get on a new turn
 *
 * @author Peter Tanyous, Momin Mushtaha
 * @version 2
 */
public class Player implements Serializable {
  private final String name;
  private final Hand playerHand;
  private final ArrayList<Territory> territoriesOwned;
  private final ArrayList<Continent> ContinentsOwned;
  public String trade;
  private int tradeTimes;
  private int newTroopers;
  private Boolean isAI;


  /**
   * Constructor for objects of class Player
   */
  public Player(String name) {
    this.name = name;
    territoriesOwned = new ArrayList<>();
    ContinentsOwned = new ArrayList<>();
    this.playerHand = new Hand();
    this.newTroopers = 0;
    this.isAI = false;
    this.trade = "";
  }


  /**
   * returns the name of the Player
   *
   * @return String name of the Player
   */
  public String getName() {
    return name;
  }


  /**
   * @return a territory the player own at index
   */
  public Territory getTerritoyAtIndex(int index) {
    return territoriesOwned.get(index);
  }


  /**
   * returns the list of territories the player own
   *
   * @return linkedlist of territories the player own
   */
  public ArrayList<Territory> getTerritories() {
    return territoriesOwned;
  }


  /**
   * returns the list of Continents the player control
   *
   * @return linkedlist of Continents the player control
   */
  public ArrayList<Continent> getContinents() {
    return ContinentsOwned;
  }


  /**
   * adds continent to the list of continents the player control
   * when a player owns all territories in a certain continent
   *
   * @param continent to be added to the list
   */
  public void addContinents(Continent continent) {
    ContinentsOwned.add(continent);
  }


  /**
   * adds territory to the list of territories the player own
   * when a player wins an attack on a certain territory
   *
   * @param territory to be added to the list
   */
  public void addTerritories(Territory territory) {
    territoriesOwned.add(territory);
  }


  /**
   * removes territory from the list of territories the player own
   * when a player loses a territory in a battle
   *
   * @param removal to be removed from the list of owned territories
   */
  public void removeTerritories(Territory removal) {
    for (int j = 0; j < territoriesOwned.size(); j++) {
      if (territoriesOwned.get(j).getName().equals(removal.getName())) {
        territoriesOwned.remove(j);
      }
    }
  }


  /**
   * Adds a risk card to the players hand
   **/
  public void addCardToPlayer(Card riskCard) {

    playerHand.addCard(riskCard);
  }


  /**
   * adds 1 to tradeTimes everytime it is executed
   */
  public void addToTradeTimes() {
    tradeTimes += 1;
    checkTradeTimes();
  }


  /**
   * Checks the how many card trades have been executed for this player and add newTroopers correspondingly
   */
  public void checkTradeTimes() {
    if (tradeTimes <= 6) {
      if (tradeTimes == 1) {
        newTroopers += 4;
      } else if (tradeTimes == 2) {
        newTroopers += 2;
      } else if (tradeTimes == 3) {
        newTroopers += 2;
      } else if (tradeTimes == 4) {
        newTroopers += 2;
      } else if (tradeTimes == 5) {
        newTroopers += 2;
      } else if (tradeTimes == 6) {
        newTroopers += 3;
      }
    } else {
      newTroopers += 5;
    }
  }


  /**
   * return the hand of the player, indicting the cards he is holding
   *
   * @return playerHand
   */
  public Hand getHand() {
    return playerHand;
  }


  /**
   * returns an Integer of how many troops to be added for this trade cycle
   *
   * @return int newTroopers
   */
  public int getNewTroopers() {
    return newTroopers;
  }


  /**
   * sets this player to an AI
   */
  public void setAI() {
    isAI = true;
  }


  /**
   * retrieves boolean is AI to indicate whether the player is an AI or not
   *
   * @return isAI Boolean value
   */
  public boolean getIsAI() {
    return isAI;
  }


}



