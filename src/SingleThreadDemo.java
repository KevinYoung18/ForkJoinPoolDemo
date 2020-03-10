import java.util.ArrayList;
import java.util.Random;

public class SingleThreadDemo
{
	static ArrayList<Player> players = new ArrayList<Player>();
	
	//rock = 0, paper = 1, scissors = 2
	public static void main(String[] args) throws InterruptedException
	{	
		int size = 10000;
		
		long time = System.nanoTime();
		
		boolean foundWinner = false;
		
		//initialize playerList
		for(int i = 0; i < size; i++)
		{
			players.add(new Player(i));
		}
		
		//keep playing games until a single winner remains
		while(!foundWinner) 
		{
			play();
			compare();
			foundWinner = pickWinners();
		}
		System.out.println("Winner: Player " + players.get(0).getPlayerNum());
		System.out.println("\nExecution time(ms): " +(System.nanoTime() - time)/1000000);
	}
	

	
	//eliminates lowest player results returns true if only one remains
	public static boolean pickWinners()
	{
		players.sort(null);
		
		int lowestVal = players.get(0).getPlayerResult();
		
		
		while(players.size() > 1)
		{
			if(players.get(0).getPlayerResult() == lowestVal)
				players.remove(0);
			else
				break;
			
		}
		
		if(players.size() == 1)
			return true;
		return false;
	}
	
	
	
	// sets playerChoice to a random int from 0-3 exclusive representing: rock = 0, paper = 1, scissors = 2
	public  static void play() 
	{
		Random rand = new Random();
		
		for(int i = 0; i < players.size(); i++)
		{
			players.get(i).setPlayerChoice(rand.nextInt(3));
			players.get(i).setPlayerResult(0);
		}
	}
	
	//compares playerChoices against all other players and increments or decrements playerResult based
	//		on a win or loss, respectively
	public static synchronized void compare()
	{
		for(int i = 0; i < players.size(); i++)
		{
			for(int j = 0; j < players.size(); j++)
			{
				if(i != j) 
				{
					switch(players.get(i).getPlayerChoice()) 
					{
						case(0):
							if(players.get(j).getPlayerChoice() == 2)
								players.get(i).incrementResult();
							else if(players.get(j).getPlayerChoice() == 1)
								players.get(i).decrementResult();
						break;
						case(1):
							if(players.get(j).getPlayerChoice() == 0)
								players.get(i).incrementResult();
							else if(players.get(j).getPlayerChoice() == 2)
								players.get(i).decrementResult();
						break;
						case(2):
							if(players.get(j).getPlayerChoice() == 1)
								players.get(i).incrementResult();
							else if(players.get(j).getPlayerChoice() == 0)
								players.get(i).decrementResult();
						break;
					}
				}
			}
		}
	}
}

