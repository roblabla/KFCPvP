package me.messageofdeath.KFCPvP.Utils.Stats;

public enum StatType {

	Kills(),
	
	
	
	
	
	Deaths(),
	
	
	
	
	
	GamesWon(),
	
	
	
	
	
	GamesLost(),
	
	
	
	
	
	
	PointLimit(),
	
	
	
	
	
	
	ExtraPoints(),
	
	
	
	
	
	
	
	TimeofVote(),
	
	
	
	
	
	PlayingTime();
	
	public String getName() {
		return this.toString();
	}
	
	public String getType() {
		if(this.equals(Kills) || this.equals(Deaths) || this.equals(GamesWon) 
				|| this.equals(GamesLost) || this.equals(PointLimit) 
				|| this.equals(ExtraPoints) || this.equals(PlayingTime)) {
			return "INT";
		}
		else if(this.equals(TimeofVote))
			return "VARCHAR(100)";
		return "";
	}
}
