import java.util.*;

public class Deck {

	private ArrayList<String> deck;
	private String o1 = "123456789";
	private String o2 = "JQKA";

	//builds a new deck
	public Deck() {
		 deck = new ArrayList<String>();

		 //Adding Hearts to Deck
		 for(int i=0; i<o1.size(); i++)
		 	deck.add(o1.charAt(i)+"H");
		 deck.add("10H");
		 for(int i=0; i<o2.size(); i++)
		 	deck.add(o2.charAt(i)+"H");

		 //Adding Spades to Deck
		 for(int i=0; i<o1.size(); i++)
		 	deck.add(o1.charAt(i)+"S");
		 deck.add("10S");
		 for(int i=0; i<o2.size(); i++)
		 	deck.add(o2.charAt(i)+"S");
		 
		 //Adding Dimonds to Deck
		 for(int i=0; i<o1.size(); i++)
		 	deck.add(o1.charAt(i)+"D");
		 deck.add("10D");
		 for(int i=0; i<o2.size(); i++)
		 	deck.add(o2.charAt(i)+"D");
		 
		 //Adding Clubs to Deck
		 for(int i=0; i<o1.size(); i++)
		 	deck.add(o1.charAt(i)+"C");
		 deck.add("10C");
		 for(int i=0; i<o2.size(); i++)
		 	deck.add(o2.charAt(i)+"C");
		 }

		 //shuffle deck
		 public void shuffle(){
		 	ArrayList<String> temp = new ArrayList<String>();
		 	for(i=deck.size(); i>=0; i--){
		 		Random random = new Random();
		 		int r = random.nextInt(i);
		 		temp.add(deck.get(chatAt(r)));
		 		deck.remove(r);
		 	}
		 	deck = temp;
		 }

		 //acessor method for deck
		public ArrayList<String> newdeck(){
		 	return deck;
		}
	}
}