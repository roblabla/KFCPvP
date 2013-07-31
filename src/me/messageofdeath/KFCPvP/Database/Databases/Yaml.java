package me.messageofdeath.KFCPvP.Database.Databases;

import me.messageofdeath.Database.YamlDatabase;
import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

public class Yaml {
	
	private static YamlDatabase ydatabase;
	
	//******************* OnStartUp Methods *********************

	public static void initDatabase() {
		ydatabase = new YamlDatabase(KFCPvP.instance, "database");
		ydatabase.onStartUp();
	}
	
	//***************** OnShutDown Methods **********************

	public static void saveDatabase() {
		for(PlayerStats player : PlayerStats.getAll()) {
			for(StatType stats : StatType.values())
				ydatabase.set("PlayerStats." + player.getName() + "." + stats.getName(), player.getStat(stats).getAmount());
		}
	}
	
	//******************** Methods ***********************
	
	public static boolean hasPlayer(String name) {
		return ydatabase.contains("PlayerStats." + name);
	}
	
	public static void loadPlayer(String name) {
		String prefix = "PlayerStats." + name + ".";
    	double kills, deaths, wins, loses, playingTime;
    	
    	kills = ydatabase.getInteger(prefix + StatType.Kills.getName(), 0);
    	deaths = ydatabase.getInteger(prefix + StatType.Deaths.getName(), 0);
    	wins = ydatabase.getInteger(prefix + StatType.GamesWon.getName(), 0);
    	loses = ydatabase.getInteger(prefix + StatType.GamesLost.getName(), 0);
    	playingTime = ydatabase.getInteger(prefix + StatType.PlayingTime.getName(), 0);
    	
    	PlayerStats.createPlayerStats(name, kills, deaths, wins, loses, playingTime);
	}
}
