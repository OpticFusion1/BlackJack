import java.util.*;

public class BlackJack {

	private int start = 0;
	private int numberOfPlayers = 0;
	private ArrayList<Players> peoplePlaying;

	public BlackJack(){
		if(start == 0)
			intro();



	}

	private void intro(){
		System.out.println("Welcome to BlackJack!");
		System.out.print("How many players are playing? ");
		String p = sc.nextInt();
		while!(p <= 4 && p > 0){
			System.out.println(p + " is an invalid number of player!");
			System.out.println("How many players are playing? ");
			p = sc.nextInt();
		}
	start = 1;
	numberOfPlayers = p;
	}

}