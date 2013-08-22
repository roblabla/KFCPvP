package me.messageofdeath.KFCPvP.Utils.Stats;

public enum StatType {

	Kills(),
	
	
	
	
	
	Deaths(),
	
	
	
	
	
	GamesWon(),
	
	
	
	
	
	GamesLost(),
	
	
	
	
	
	
	/*PointLimit(),//Not Stat
	
	
	
	
	
	
	ExtraPoints(),//Not Stat
	
	
	
	
	
	
	
	TimeofVote(),//Not Stat*/
	
	
	
	
	
	PlayingTime();
	
	public String getName() {
		return this.toString();
	}
	
	public String getType() {
		return "INT";
	}
}
