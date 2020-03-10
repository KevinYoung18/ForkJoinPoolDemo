import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

//compares playerChoices against all other players and increments or decrements playerResult based
//		on a win or loss, respectively
public class CompareGames extends RecursiveTask<ArrayList<Player>>
{
	
	private static final long serialVersionUID = 1376815006696466886L;
	private List<Player> subList;
	private int maxSize = 50;
	private ArrayList<Player> playerList;
	
	CompareGames(ArrayList<Player> playerList, List<Player> subList)
	{
		this.playerList = playerList;
		this.subList = subList;
	}
	
	@Override
	protected ArrayList<Player> compute() 
	{
		if(subList.size() < maxSize)
		{
			//compares each player in the sublist to each player in the full playerlist
			subList.forEach((player) ->
			{
				for(int i = 0; i < playerList.size(); i++)
				{
					switch(player.getPlayerChoice()) 
					{
						case(0):
							if(playerList.get(i).getPlayerChoice() == 2)
								player.incrementResult();
							else if(playerList.get(i).getPlayerChoice() == 1)
								player.decrementResult();
						break;
						case(1):
							if(playerList.get(i).getPlayerChoice() == 0)
								player.incrementResult();
							else if(playerList.get(i).getPlayerChoice() == 2)
								player.decrementResult();
						break;
						case(2):
							if(playerList.get(i).getPlayerChoice() == 1)
								player.incrementResult();
							else if(playerList.get(i).getPlayerChoice() == 0)
								player.decrementResult();
						break;
					}
					
				}
			});
			
			ArrayList<Player> result = new ArrayList<Player>();
			result.addAll(subList);
			return result;
		}
		//splits list in half and forks each to a new recursiveTask
		else
		{
			int mid = subList.size()/2;
			int hi = subList.size();
			CompareGames t1 = new CompareGames(playerList, subList.subList(0, mid));
			CompareGames t2 = new CompareGames(playerList, subList.subList(mid, hi));
			t1.fork();
			t2.fork();
			ArrayList<Player> finalList = t1.join();
			finalList.addAll(t2.join());
			
			return finalList;
		}
		
	}
	
	
}

