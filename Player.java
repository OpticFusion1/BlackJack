import java.util.*;

public class Player {

	private String name;
	private boolean isPlaying;
	private ArrayList<String> cards;

	public Player(boolean b, String s){
		this.name = s;
		this.isPlaying = b;
		this.cards = new ArrayList<String>();
	}

	public void addCard(String c){
		cards.add(c);
	}
	public ArrayList<String> getCards(){
		return cards;
	}

	public String getCard(int n){
		return cards.get(n);
	}
	
	public String getName(){
		return name;
	}
	public int getSize(){
		return cards.size();
	}

	public void clear(){
		cards.clear();
	}

	public void reset(){
		cards.clear();
	}

	public void looser(boolean b){
		isPlaying = b;
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

