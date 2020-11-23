package Objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Allows the creation of the Risk deck containing the 42 cards.
 **/
public class Deck {

  private final ArrayList<Card> deck;

  /**
   * Creates all 42 cards, one for each territory. Each card has either
   * a type of Infantry, Cavalry, or Artillery. Ensure that the number of
   * Infantry, Cavalry, and Artillery are the same
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
      System.out.println("Added new card to deck: " + deck.get(i).getTerritoryName());
    }
    Collections.shuffle(deck);
  }

  /**
   * Removes a card from the deck and return it
   **/
  public Card draw() {

    Card drawCard = deck.get(0);
    deck.remove(0);
    return drawCard;
  }

}

