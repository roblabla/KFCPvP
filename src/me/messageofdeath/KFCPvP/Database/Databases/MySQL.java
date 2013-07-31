package me.messageofdeath.KFCPvP.Database.Databases;

import java.sql.SQLException;

import me.messageofdeath.Database.MySQLDatabase;
import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Database.Database;
import me.messageofdeath.KFCPvP.Database.Configuration.MySQLSettings;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

import org.bukkit.Bukkit;

public class MySQL {

	private static String table;
	private static MySQLDatabase mdatabase;
	
	//***************** OnStartUp Methods *********************
	
	public static void initDatabase() {
		table = "KFCPvP_Stats";
    	mdatabase = new MySQLDatabase(KFCPvP.instance,
    			MySQLSettings.Host.getSetting(),
    			MySQLSettings.Port.getSetting(),
    			MySQLSettings.Database.getSetting(),
    			MySQLSettings.Username.getSetting(),
    			MySQLSettings.Password.getSetting());
    	
    	boolean success = true;
    	try{
    		mdatabase.onStartUp();
    	}catch(SQLException e) {
    		Database.logError("MySQL", "Coult not connect to database! Changing to YAML! - " + e.getMessage());
    		Database.logError("MySQL", "Coult not connect to database! Changing to YAML! - " + e.getMessage());
    		if(DatabaseType.loadType.getDataType() == DatabaseType.MySQL)
    			DatabaseType.loadType.setDataType(DatabaseType.YAML);
    		if(DatabaseType.saveType.getDataType() == DatabaseType.MySQL)
    			DatabaseType.saveType.setDataType(DatabaseType.YAML);
    		success = false;
    	}
    	if(success == true) {
        	if(!mdatabase.doesTableExist(table)) {
        		mdatabase.createTable(table, 
        				"Player VARCHAR(18), " + 
        				StatType.Kills.getName() + " INT, " + 
        				StatType.Deaths.getName() + " INT, " + 
        				StatType.GamesWon.getName() + " INT, " + 
        				StatType.GamesLost.getName() + " INT, " + 
        				StatType.PlayingTime.getName() + " INT");
        		Bukkit.getLogger().info("Created the table '"+table+"'!");
        	}
    	}
	}
	
	//***************** OnCloseDown Methods *****************
	
	public static void saveDatabase() {
		for(PlayerStats player : PlayerStats.getAll()) {
			if(Database.hasPlayer(player.getName())) {
				for(StatType stats : StatType.values())
					mdatabase.update(table, stats.getName() + " = '" + player.getStat(stats).getAmount() + "'", "Player = '"+player.getName()+"'");
			}else{
				String columns = "Player, ";
				String values = player.getName() + ", ";
				for(StatType stats : StatType.values()) {
					columns += " " + stats.getName() + ",";
					values += " " + player.getStat(stats).getAmount() + ",";
				}
				columns = columns.substring(0, columns.length() - 1);
				values = values.substring(0, values.length() - 1);
				//Insert
				mdatabase.insert(table, columns, values);
			}
		}
	}
	
	//****************** Methods ************************
	
	public static boolean hasPlayer(String name) {
		return mdatabase.contains(table, "Player = '"+name+"'");
	}
	
	public static void loadPlayer(String name) {
		double kills, deaths, wins, loses, playingTime;
		String where = "Player = '"+name+"'";
		
		kills = mdatabase.getInteger(table, where, StatType.Kills.getName(), 0);
		deaths = mdatabase.getInteger(table, where, StatType.Deaths.getName(), 0);
		wins = mdatabase.getInteger(table, where, StatType.GamesWon.getName(), 0);
		loses = mdatabase.getInteger(table, where, StatType.GamesLost.getName(), 0);
		playingTime = mdatabase.getInteger(table, where, StatType.PlayingTime.getName(), 0);
		
		PlayerStats.createPlayerStats(name, kills, deaths, wins, loses, playingTime);
	}
}
