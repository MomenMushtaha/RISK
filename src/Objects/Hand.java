package Objects;

import java.util.* ;
/**
 * Class Hand class represent each player's hand and a list of cards available in the
 * player's hand in addition to a boolean which is set true if player can trade in matching cards
 *
 * @author Momin Mushtaha
 * @version 1
 */
public class Hand
{
  private ArrayList<Card> cards;
  private boolean condition;
  private boolean canTurnInCards;
  private int value;
  /**
   * Constructor for objects of class Hand
   */
  public Hand()
  {
    cards = new ArrayList<>();
    canTurnInCards = false;
  }
  /**
   *add Card to Player's Hand
   *
   *@param newCard to be added
   */
  public void addCard(Card newCard)
  {
    cards.add(newCard);

  }


  public ArrayList<Card> getCards()
  {
    return cards;
  }


  /**
   * Removes the cards at the given indices from the hand
   **/
  public boolean removeCardsFromHand(int index1, int index2, int index3) {

    if (canTurnInCards(index1, index2, index3)) {
      cards.remove(index3);
      cards.remove(index2);
      cards.remove(index1);
      return true;

    } else {
      System.out.println("You must trade in three cards of the same type or one of each type.");
      return false;
    }
  }


  /**
   * returns true if the player can turn in cards
   **/
  public boolean canTurnInCards(int index1, int index2, int index3) {

    condition = false;

    if (cards.size() >= 3) {
      if (cards.get(index1).getType().equals(cards.get(index2).getType()) && cards.get(index1).getType().equals(cards.get(index3).getType())) {
        System.out.println("Extra Troops added");
        //If all three cards have the same type
        condition = true;

      } else if (
        !cards.get(index1).getType().equals(cards.get(index2).getType()) && !cards.get(index1).getType().equals(cards.get(index3).getType()) && !cards.get(index2).getType().equals(cards.get(index3).getType())) {
        System.out.println("Extra Troops added");
        //If all three cards have different types
        condition = true;
      }
    }
    return condition;
  }

  /**
   * Returns true if the player must turn in cards
   **/
  public boolean mustTurnInCards() {

    condition = false;

    if (cards.size() >= 5) {
      condition = true;
    }
    return condition;
}}

