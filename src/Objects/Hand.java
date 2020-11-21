package Objects;

import java.util.* ;
/**
 * Class Hand class represent each player's hand and a list of cards available in the
 * player's hand in addition to a boolean which is set true if player can trade in matching cards
 *
 * @author Peter Tanyous
 * @version 1
 */
public class Hand
{
  private ArrayList<Card> cards;
  private boolean canTurnInCards;
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
  /**
   *empty cards player have when player trades in
   */
  public void removeCards(){
    checkCardSet();
    if(canTurnInCards = true){
      cards.clear();
    }
  }
  /**
   *Checks the cards player have in hands and alter the canTurnInCards boolean attribute
   *
   */
  public boolean checkCardSet(){
    int i = 0;
    int c = 0;
    int a = 0;
    int w = 0;
    if(cards.size() == 5){
      canTurnInCards = true;
    }
    else{
      for (Card card : cards) {
        if (card.getTypeWorth() == 1) {
          i = i + 1;
        }
        if (card.getTypeWorth() == 5) {
          c = c + 1;
        }
        if (card.getTypeWorth() == 10) {
          a = a + 1;
        }
        if (card.getTypeWorth() == 0) {
          w = w + 1;
        }
      }
      if(((i + w )>= 3) || ((c + w )>= 3) || ((a + w )>= 3)){
        canTurnInCards = true;
      }
    }
return canTurnInCards;
  }

  public ArrayList<Card> getCards()
  {
    return cards;
  }


  /*public String[] generateOptions()
  {

  }*/
}

