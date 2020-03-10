import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.RecursiveTask;

//sets playerChoice to a random int from 0-2 inlusive representing: rock = 0, paper = 1, scissors = 2
public class PlayGames extends RecursiveTask<ArrayList<Player>>
{
	
	private static final long serialVersionUID = -5523562140509923499L;
	private List<Player> subList;
	private int maxSize = 200;
	private ArrayList<Player> playerList;
	
	PlayGames(ArrayList<Player> playerList, List<Player> subList)
	{
		this.playerList = playerList;
		this.subList = subList;
	}
	
	@Override
	protected ArrayList<Player> compute() 
	{
		if(subList.size() < maxSize)
		{
			Random rand = new Random();
			subList.forEach((player) ->{
				player.setPlayerChoice(rand.nextInt(3));
				player.setPlayerResult(0);
			});
			
			ArrayList<Player> result = new ArrayList<Player>();
			result.addAll(subList);
			return result;
		}
		else
		{
			int mid = subList.size()/2;
			int hi = subList.size();
			PlayGames t1 = new PlayGames(playerList, subList.subList(0, mid));
			PlayGames t2 = new PlayGames(playerList, subList.subList(mid, hi));
			t1.fork();
			t2.fork();
			ArrayList<Player> finalList = t1.join();
			finalList.addAll(t2.join());
			
			return finalList;
		}
		
	}
	
	
}