import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;


public class Demo
{
	static ArrayList<Player> players = new ArrayList<Player>();
	//rock = 0, paper = 1, scissors = 2
	public static void main(String[] args) throws InterruptedException
	{	
		int size = 10000;
		
		long time = System.nanoTime();
		
		//populate arraylist of players
		for(int i = 0; i <size; i++)
			players.add(new Player(i));
		
		boolean foundWinner = false;
		ForkJoinPool pool = new ForkJoinPool();
		
		//keep playing games until a single winner remains
		while(!foundWinner) 
		{
			PlayGames p = new PlayGames(players, players);
			players = pool.invoke(p);
			
			CompareGames c = new CompareGames(players, players);
			players = pool.invoke(c);
			
			foundWinner = pickWinners();
		}
		System.out.println("Winner: Player " + players.get(0).getPlayerNum());
		System.out.println("\nExecution time(ms): " +(System.nanoTime() - time)/1000000);
	}
	
	//if runPlay == true then run play() threads, else run compare() threads
	
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
	
	
	
	public static void printArray()
	{
		for(int i = 0; i < players.size(); i++)
			System.out.print(players.get(i).getPlayerResult() + "\t");
	}
}

