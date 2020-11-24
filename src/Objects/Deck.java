package Objects;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * Class Deck creates a Deck containing the 42 Cards.
 *
 * @author Momin Mushtaha
 * @version 2
 */
public class Deck {
  private final ArrayList<Card> deck;


  /**
   * Creates all 42 cards, one for each territory and every card has type
   * all the types are equivalently distributed
   *
   * @param territories territories List passed
   */
  public Deck(Territory[] territories) {
    Collections.shuffle(Arrays.asList(territories));
    //Types of cards
    Troops[] typesArray = new Troops[]{new Cavalry(), new artillery(), new infantry()};
    deck = new ArrayList<>();
    Random generator = new Random();
    int i;
    for (i = 0; i < territories.length; i++) {
      // Add new cards to deck
      deck.add(new Card(territories[i], typesArray[generator.nextInt(3)]));
      System.out.println("new cards on deck: " + deck.get(i).getTerritoryName());
    }
    Collections.shuffle(deck);
  }


  /**
   * Removes a card and return it
   **/
  public Card draw() {
    Card draw = deck.get(0);
    deck.remove(0);
    return draw;
  }


}

