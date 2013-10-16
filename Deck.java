import java.util.*;

public class Deck {

	private ArrayList<String> deck;
	private String o1 = "23456789";
	private String o2 = "JQKA";

	//builds init new deck
	public Deck() {
		 deck = new ArrayList<String>();
		 newDeck();
	}
	
	//fills array deck with 52 cards
	public void newDeck(){
		
		//Adding Hearts to Deck
		for(int i=0; i<o1.length(); i++)
		 	deck.add(o1.charAt(i)+"H");
		 deck.add("10H");
		for(int i=0; i<o2.length(); i++)
		 	deck.add(o2.charAt(i)+"H");

		//Adding Spades to Deck
		for(int i=0; i<o1.length(); i++)
		 	deck.add(o1.charAt(i)+"S");
		deck.add("10S");
		for(int i=0; i<o2.length(); i++)
		 	deck.add(o2.charAt(i)+"S");
		 
		//Adding Dimonds to Deck
		for(int i=0; i<o1.length(); i++)
		 	deck.add(o1.charAt(i)+"D");
		deck.add("10D");
		for(int i=0; i<o2.length(); i++)
		 	deck.add(o2.charAt(i)+"D");
		 
		//Adding Clubs to Deck
		for(int i=0; i<o1.length(); i++)
		 	deck.add(o1.charAt(i)+"C");
		deck.add("10C");
		for(int i=0; i<o2.length(); i++)
		 	deck.add(o2.charAt(i)+"C");
		
		//Shuffle Deck after cards are added
		Collections.shuffle(deck);
	}
		
	//Returns: Random card in the deck
	//If the deck is empty, a new deck is constructed
	public String randomCard(){
		//System.out.println(deck.size());
		Random rand = new Random();
		int r = rand.nextInt(deck.size());
		while(deck.get(r) == null){
			if (deck.isEmpty())
				newDeck();
			else
				r = rand.nextInt(deck.size());
		}
		String s = deck.get(r);
		deck.remove(r);
		return s;
	}

	public int deckSize(){
		return deck.size();
	}

}