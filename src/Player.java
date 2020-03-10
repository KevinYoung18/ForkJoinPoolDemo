
class Player implements Comparable<Player>
{
	private int playerNum;
	private int playerChoice;
	private int playerResult;
	
	Player(int playerNum)
	{
		this.playerNum = playerNum;
	}
	
	@Override
	public int compareTo(Player o) {
		if(this.playerResult == o.getPlayerResult())
			return 0;
		else if(this.playerResult < o.getPlayerResult())
			return -1;
		else
			return 1;
	}
	
	public void incrementResult() {
		playerResult++;
	}
	public void decrementResult() {
		playerResult--;
	}
	public int getPlayerNum() {
		return playerNum;
	}

	public int getPlayerChoice() {
		return playerChoice;
	}

	public void setPlayerChoice(int playerChoice) {
		this.playerChoice = playerChoice;
	}

	public int getPlayerResult() {
		return playerResult;
	}

	public void setPlayerResult(int playerResult) {
		this.playerResult = playerResult;
	}


	
}
