public class Player {

	private String name;
	private boolean isPlaying;

	public Player(boolean b, String s){
		this.name = s;
		this.isPlaying = b;
	}
	
	public String getName(){
		return name;
	}

	public boolean isPlaying(){
		if(this.isPlaying == true)
			return true;
		return false;
	}

}

