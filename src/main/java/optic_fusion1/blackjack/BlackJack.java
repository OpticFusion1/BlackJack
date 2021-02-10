package optic_fusion1.blackjack;

import java.util.List;
import java.util.concurrent.TimeUnit;
import optic_fusion1.blackjack.component.Deck;
import optic_fusion1.blackjack.component.Player;
import optic_fusion1.engine.game.SimpleGame;

/*
  Original Author: Croller
  Date: 10/2013
  Updated By: Optic_Fusion1
  Date: 11/11/2020
  View README for more Info!
 */
public class BlackJack extends SimpleGame {

  private int numberOfPlayers = 0; //How many players are playing (between 1-4 inclusive)
  private Player[] peoplePlaying; //Array of Player objects; stores player information
  private Player dealer = new Player("Dealer", this); //creates Player Dealer for dealer cards
  private Deck deck = new Deck(this); //Deck object to construct a deck of cards and return a random card & creates a new deck with 52 cards (eventually to be more once betting is implemented)
  private int gamesPlayed = 0; //Keeps track of how many games have been played

  /*
    Overview: Method that plays the game of BlackJack
      1)Deals init cards to dealer and players
      2)loops through players moves
      3)loops through dealers moves (AI)
      4)evaluates who won and who lost
      5)finds out who wants to play again and who doesn't
      6)once player leave a game, statsics for games played are given
   */
  @Override
  public void tick() { //Runs until game#isRunning returns false (in this case once all players are done playing)
    LOGGER.info("\nRound " + (gamesPlayed + 1) + "\n"); //Tells the current round
    //Dealing init cards to players / dealer
    for (int i = 0; i < numberOfPlayers; i++) {
      peoplePlaying[i].addCard(deck.randomCard());
    }
    dealer.addCard(deck.randomCard());
    for (int i = 0; i < numberOfPlayers; i++) {
      peoplePlaying[i].addCard(deck.randomCard());
    }
    dealer.addCard(deck.randomCard());
    //First Default deal is out, First card is Shown, Second card is Hidden
    LOGGER.info("Dealers Hand : " + dealer.getCard(0) + " X");
    //Players turn...
    int userOption = 0; //Users Option...
    for (int i = 0; i < numberOfPlayers; i++) {
      userOption = correctInput();
      switch (userOption) {
        case 1:
          peoplePlaying[i].addCard(deck.randomCard());
          LOGGER.info("You got a : " + peoplePlaying[i].getCard(peoplePlaying[i].getAmountOfCards() - 1));
          if (bust(i)) { //if player busts then move to next player..
            continue;
          } else { //otherwise if player doesn't bust then ask player what they want to do
            i--;
            continue;
          }
        case 2:
          //Does nothing
          continue;
        case 3:
          //Double the bet but does nothing yet
          LOGGER.info("Hey were given option double, which doesn't do anything yet");
          i--;
          break;
        case 4:
          LOGGER.info("Hey were given option split, which doesn't do anything yet");
          i--;
          break;
        default:
          break;
      }
    }
    //Dealers turn...
    int cardSum = 0;
    int aces = 0;
    boolean done = false;
    while (!done) {
      LOGGER.info(dealer.toString());
      cardSum = 0;
      for (int i = 0; i < dealer.getAmountOfCards(); i++) {
        if (dealer.getCard(i).contains("A")) {
          aces++;
        } else {
          cardSum += cardValue(dealer.getCard(i));
        }
      }
      while (aces != 0) {
        cardSum += cardValueA(cardSum);
        aces--;
      }
      if (cardSum <= 16) {
        dealer.addCard(deck.randomCard());
      } else if (cardSum > 16 && cardSum < 22) {
        done = true;
      } else if (cardSum > 21) {
        System.out.println("DEALER BUSTED!!! with " + cardSum);
        done = true;
      }
    }
    eval(cardSum); //evaluates who won, who lost, who tied
    if (!afterGame()) { //if there are no players playing anymore.. then exit while loop and game
      endGame();
      LOGGER.info("\nThank You for playing BlackJack - by Croller (Updated by Optic_Fusion1)\n");
    } else { //keep playing
      dealer.clearCards();//clears the dealers deck;
    }
  }

  @Override
  public void startGame() {
    init();
    setRunning(true);
    if (gamesPlayed == 0) {
      intro();
      //File stats = new File("Stats.txt");
    }
  }

  /*
    OverView: Introduction of the game: to consult the user for number of players and put you into a game!
    Pre-Conditions: if gamesPlayed = 0, this method will consult the user for information to play
    Post-Conditions: Game On!
   */
  private void intro() {
    LOGGER.info("Welcome to BlackJack!");
    LOGGER.info("How many players are playing? (1-5)");
    String input = getScanner().nextLine();
    while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5")) {
      LOGGER.warn(input + " is not a valid number of players!");
      LOGGER.info("How many players are playing (1-5)");
      input = getScanner().nextLine();
    }
    numberOfPlayers = Integer.parseInt(input);
    peoplePlaying = new Player[numberOfPlayers];
    LOGGER.info("Congrats! " + numberOfPlayers + " are selected to play!");
    int count = 0;
    while (count < numberOfPlayers) {
      LOGGER.info("Player " + (count + 1) + ", Enter a name to play with: ");
      String name = getScanner().nextLine(); //Prompting Player for name
      peoplePlaying[count] = new Player(name, this);
      LOGGER.info("Player " + (count + 1) + ", You've sucessfully named yourself " + name);
      count++;
    }
    for (int i = 0; i < numberOfPlayers; i++) {
      if (i == numberOfPlayers - 1) {
        LOGGER.info("and " + peoplePlaying[i].getName() + " get ready!!!");
      } else {
        LOGGER.info(peoplePlaying[i].getName() + " ");
      }
    }
    LOGGER.info("Game Commencing in :");
    int time = 5;
    while (time > 0) {
      LOGGER.info(time + "..\n");
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      time--;
    }
    run();
  }

  /*
    Overview: Method that ensures that input from user is correct 
    Pre-Conditions: A Scanner asking for input
    Post-Conditions: A guarentee that the correct input was given from the user
   */
  private int correctInput() {
    LOGGER.info("What would you like to do? (1-Hit, 2-Stay, 3-Double, 4-Split) : ");
    String input = getScanner().nextLine();
    while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
      LOGGER.warn(input + " is not an integer between 1-4");
      LOGGER.info("What would you like to do? (1-Hit, 2-Stay, 3-Double, 4-Split) : ");
      input = getScanner().nextLine();
    }
    return Integer.parseInt(input);
  }

  /*
      Overview: After each round, this method asks players if they want to keep playing or not
      If they do, the method will return true, Otherwise false
      Pre-Conditions: A game must complete
      Post-Conditions: A new game or exit the program
   */
  private boolean afterGame() {
    gamesPlayed++; //one game has players... so increment gamesPlayed
    for (int i = 0; i < numberOfPlayers; i++) {
      LOGGER.info(peoplePlaying[i].getName() + ", do you want to play again? (Y/N) :");
      String input = getScanner().nextLine().toLowerCase();
      while (!input.equals("y") && !input.equals("n")) {
        LOGGER.warn("This is not Y/N... please try again... :");
        input = getScanner().nextLine().toLowerCase();
      }
      if (input.equals("n")) {
        peoplePlaying[i].setPlaying(false);
        statistics(i); //Will give player results of their game or games
        i--;
      } else {
        peoplePlaying[i].clearCards(); //We know player is playing.. so clear his current cards;
      }
    }
    return numberOfPlayers != 0;
  }

  //Overview: removes player at index "index" from peoplePlaying[]
  private void remove(int index) {
    for (int i = index; i < numberOfPlayers - 1; i++) {
      peoplePlaying[i] = peoplePlaying[i + 1];
    }
    numberOfPlayers--;
  }

  /*
    Overview: Evaluates the outcome of all players vs dealer
    Pre-Conditions:
    Post-Conditions:
   */
  private void eval(int dsum) {
    LOGGER.info("\n..........Evaluation..........");
    LOGGER.info(dealer.toString());
    for (int i = 0; i < numberOfPlayers; i++) {
      if (bust(i)) {
        peoplePlaying[i].addLoss();
        continue;
      } else {
        int psum = 0;
        int ace = 0;
        for (int j = 0; j < peoplePlaying[i].getAmountOfCards(); j++) {
          if (peoplePlaying[i].getCard(j).contains("A")) {
            ace++;
          } else {
            psum += cardValue(peoplePlaying[i].getCard(j));
          }
        }
        while (ace != 0) {
          psum += cardValueA(psum);
          ace--;
        }
        if (dsum > 21) {
          peoplePlaying[i].addWin();
          LOGGER.info(peoplePlaying[i].getName() + " wins with a sum of " + psum + " dealer busted...");
        } else if (dsum < psum) {
          peoplePlaying[i].addWin();
          LOGGER.info(peoplePlaying[i].getName() + " wins with a sum of " + psum + " vs " + dsum);
        } else if (dsum == psum) {
          peoplePlaying[i].addTie();
          LOGGER.info(peoplePlaying[i].getName() + " Tie... " + psum + " vs " + dsum);
        } else {
          peoplePlaying[i].addLoss();
          LOGGER.info(peoplePlaying[i].getName() + " lost to the dealer ....." + psum + " vs" + dsum);
        }
      }
    }
  }

  /**
   * Returns if a player busts or not
   *
   * @param playerIndex index of player
   * @return True if player busts otherwise false
   */
  private boolean bust(int playerIndex) {
    int sum = 0;
    int ace = 0;
    List<String> temp = peoplePlaying[playerIndex].getCards(); //Temp List to tally up card values
    for (int i = 0; i < temp.size(); i++) {
      if (temp.get(i).contains("A")) { //Tallys aces to compare to the whole sum of other cards
        ace++;
      } else {
        sum += cardValue(temp.get(i)); //Tallys up every other card value into sum
      }
    }
    while (ace != 0) { //decides what an ace or aces will be counted as
      sum += cardValueA(sum);
      ace--;
    }
    if (sum > 21) { //Player busted...
      LOGGER.info(peoplePlaying[playerIndex].getName() + " BUSTED!!!" + sum + "..."); //Busted...
      return true;
    } else if (sum < 21) { //Still alive!
      LOGGER.info(peoplePlaying[playerIndex].getName() + " Didn't Bust! " + sum); //Didn't Bust
      return false;
    }
    //BLACKJACK!
    LOGGER.info("BLACKJACK!!!! " + sum + " Baby!!!"); //BlackJack
    return false;
  }

  //Overview: Returns card values for all cards but Aces
  private int cardValue(String card) {
    if (card.contains("J") || card.contains("Q") || card.contains("K") || card.contains("1")) {
      return 10;
    }
    return Integer.parseInt(card.substring(0, 1));
  }

  //Overview: Returns card value for Ace dependeing on the sum of the other cards in play
  private int cardValueA(int sum) {
    return sum <= 10 ? 11 : 1;
  }

  //Overview: Shows statistics for player that is leaving...
  private void statistics(int playerIndex) {
    LOGGER.info("\nStatistics Page for " + peoplePlaying[playerIndex].getName() + "\n");
    LOGGER.info(peoplePlaying[playerIndex].getName() + ", you played a total of " + gamesPlayed + " games");
    LOGGER.info("Wins : " + peoplePlaying[playerIndex].getWins());
    LOGGER.info("Ties : " + peoplePlaying[playerIndex].getTies());
    LOGGER.info("Losses : " + peoplePlaying[playerIndex].getLosses());
    LOGGER.info("\nThanks for playing " + peoplePlaying[playerIndex].getName());
    remove(playerIndex);
  }

}
