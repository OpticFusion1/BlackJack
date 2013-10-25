import java.util.*;

public class Player {

	private String name;
	private boolean isPlaying;
	private ArrayList<String> cards;
	private int wins;
	private int losses;
	private int ties;

	public Player(boolean b, String s){
		this.name = s;
		this.isPlaying = b;
		this.wins = 0;
		this.losses = 0;
		this.ties = 0;
		this.cards = new ArrayList<String>();
	}

	//Mutator Methods for Player Class
	public void addCard(String c){
		cards.add(c); //Add a card to players hand
	}

	public void addWin(){
		wins++; //Player one a match
	}

	public void addLoss(){
		losses++; //Player lost a match
	}

	public void addTie(){
		ties++; //Player tied a match
	}

	public void clear(){
		cards.clear(); //Clear the players cards
	}

	public void stillPlaying(boolean b){
		isPlaying = b; //changes if the player is done playing for not
	}

	//Accessor Methods for Player Class
	public ArrayList<String> getCards(){
		return cards;
	}

	public String getCard(int n){
		return cards.get(n);
	}

	public int wins(){
		return wins;
	}
	public int losses(){
		return losses;
	}
	public int ties(){
		return ties;
	}
	
	public String getName(){
		return name;
	}
	public int getSize(){
		return cards.size();
	}

	public boolean isPlaying(){
		if(this.isPlaying == true)
			return true;
		return false;
	}

	public String toString(){
		String s = name + ", your current cards are " + cards.get(0);
		for(int i = 1; i < cards.size(); i++)
			s += "," + cards.get(i);
		return s;
	}	

}

