package optic_fusion1.blackjack.component;

import java.util.ArrayList;
import java.util.List;
import optic_fusion1.engine.component.Component;

public class Player extends Component {

  private boolean playing = true;
  private List<String> cards = new ArrayList<>();
  private int wins = 0, losses = 0, ties = 0;

  public Player(String name) {
    super(name);
  }

  //Mutator Methdos for Player Class
  public void addCard(String card) {
    cards.add(card); //Add a card to players hand
  }

  public void addWin() {
    wins++; //Player won a match
  }

  public void addLoss() {
    losses++; //Player lost a match
  }

  public void addTie() {
    ties++; //Player tied a match
  }

  public void clearCards() {
    cards.clear(); //Clear the players cards
  }

  public void setPlaying(boolean isPlaying) {
    playing = isPlaying; //Changes if the player is done playing or not
  }

  //Accessor Methods for Player Class
  public List<String> getCards() {
    return cards;
  }

  public String getCard(int index) {
    return cards.get(index);
  }

  public int getWins() {
    return wins;
  }

  public int getLosses() {
    return losses;
  }

  public int getTies() {
    return ties;
  }

  public int getAmountOfCards() {
    return cards.size();
  }

  public boolean isPlaying() {
    return playing;
  }

  @Override
  public String toString() {
    String message = getName() + ", your current cards are " + cards.get(0);
    for (int i = 0; i < cards.size(); i++) {
      message += "," + cards.get(i);
    }
    return message;
  }

}
