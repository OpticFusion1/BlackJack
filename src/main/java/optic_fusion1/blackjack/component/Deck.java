package optic_fusion1.blackjack.component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import optic_fusion1.engine.component.Component;
import optic_fusion1.engine.game.Game;
import static optic_fusion1.engine.game.Game.LOGGER;

public class Deck extends Component {

  private List<String> deck = new ArrayList<>();
  private String o1 = "23456789"; //TODO: Come up with a better variable name
  private String o2 = "JQKA"; //TODO: Come up with a better variable name

  public Deck(Game game) {
    super("Deck", game);
    newDeck();
  }

  private void newDeck() {
    //fills array deck with 52 cards;
    LOGGER.info("A new Deck is being created..."); //testing...
    //Adding Hearts to Deck
    addCards('H');
    //Adding Spades to Deck
    addCards('S');
    //Adding Diamonds to Deck
    addCards('D');
    //Adding Clubs to Deck
    addCards('C');
    Collections.shuffle(deck);
  }

  private void addCards(char cardType) {
    for (int i = 0; i < o1.length(); i++) {
      deck.add(o1.charAt(i) + String.valueOf(cardType));
    }
    deck.add("10" + String.valueOf(cardType));
    for (int i = 0; i < o2.length(); i++) {
      deck.add(o2.charAt(i) + String.valueOf(cardType));
    }
  }

  /**
   * Returns a random card. If the deck is empty, a new deck is constructed
   *
   * @return A random card
   */
  public String randomCard() {
    //LOGGER.info("Deck Size: " + deck.size());
    Random random = new Random();
    if (deck.isEmpty()) {
      newDeck();
    }
    int num = random.nextInt(deck.size());
    while (deck.get(num) == null) {
      num = random.nextInt(deck.size());
    }
    String card = deck.get(num);
    deck.remove(card);
    return card;
  }

  /**
   * Returns the size of the deck
   *
   * @return Deck Size
   */
  public int getDeckSize() {
    return deck.size();
  }

  @Override
  public void tick() {
  }

  @Override
  public void render() {
  }

}
