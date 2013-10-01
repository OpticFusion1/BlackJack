import java.util.*;
import java.util.concurrent.TimeUnit;

public class BlackJack {

	private int start = 0;
	private int numberOfPlayers = 0;
	private Player [] peoplePlaying;
	private Deck deck;
	private int gamesPlayed = 0;


	public BlackJack(){
		if(start == 0)
			intro();
		//else
			//game();
	}

	private void intro(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to BlackJack!");
		System.out.print("How many players are playing? ");
		int p = sc.nextInt();
		//write a test for string input...
		while(p > 4 || p <= 0){
			System.out.println(p + " is an invalid number of player!");
			System.out.print("How many players are playing? ");
			p = sc.nextInt();
		}
		numberOfPlayers = p;
		peoplePlaying = new Player [numberOfPlayers];
		System.out.println("Congrats! " + numberOfPlayers + " are selected to play!");
		int count = 0;
		while(count < numberOfPlayers){
			System.out.print("Player " + (count+1) + ", Enter a name to play with: ");
			String name = sc.next(); //Prompting Player for name
			peoplePlaying[count] = new Player(true, name);
			System.out.println("Player " + (count+1) + ", You've sucessfully named yourself " + name);
			count++;
		}
		for(int i=0; i<numberOfPlayers; i++){
			if(i == numberOfPlayers-1)
				System.out.print("and " + peoplePlaying[i].getName() + " get ready!!!");
			else
				System.out.print(peoplePlaying[i].getName() + " ");
		}
		System.out.println("Game Commencing in :");
		int time = 5;
		while(time > 0){
			System.out.print("\n" + time + ".. ");
			try{
				TimeUnit.SECONDS.sleep(1);
			}catch (Exception e){}
			time--;
		}
		start = 1;
		System.out.println("\nYes we're going into a game!");
	}
/*
	public void game(){
		deck = new Deck();
		

		while!(isGameOver()){
			
		}

	}
	public boolean isGameOver(){
		
	}
*/
}