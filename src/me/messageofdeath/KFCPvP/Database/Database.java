package me.messageofdeath.KFCPvP.Database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import me.messageofdeath.Database.MySQLDatabase;
import me.messageofdeath.Database.YamlDatabase;
import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

public class Database {
	
    private static YamlDatabase ydatabase, config;
    private static MySQLDatabase mdatabase;
    private static DatabaseType dbType;
    private static String table;
    
	public static void initDatabase() {
		if(dbType == DatabaseType.YAML) {
        	ydatabase = new YamlDatabase(KFCPvP.instance, "database");
        	ydatabase.onStartUp();
        }
		else if(dbType == DatabaseType.MySQL) {
			table = "KFCPvP_Stats";
        	mdatabase = new MySQLDatabase(KFCPvP.instance,
        			ConfigSettings.Host.getSetting(),
        			ConfigSettings.Port.getSetting(),
        			ConfigSettings.Database.getSetting(),
        			ConfigSettings.Username.getSetting(),
        			ConfigSettings.Password.getSetting());
        	
        	try{
        		mdatabase.onStartUp();
        	}catch(SQLException e) {
        		logError("MySQL", "Coult not connect to database! " + e.getMessage());
        		logError("MySQL", "Coult not connect to database! " + e.getMessage());
        		dbType = null;
        		return;
        	}
        	
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
	
	public static boolean hasPlayer(String name) {
		if(dbType == DatabaseType.MySQL)
			return mdatabase.contains(table, "Player = '"+name+"'");
		else if(dbType == DatabaseType.YAML)
			return ydatabase.contains("PlayerStats." + name);
		return false;
	}
	
	public static void loadPlayer(String name) {
		if(dbType == DatabaseType.YAML) {
        	String prefix = "PlayerStats." + name + ".";
        	double kills, deaths, wins, loses, playingTime;
        	
        	kills = ydatabase.getInteger(prefix + StatType.Kills.getName(), 0);
        	deaths = ydatabase.getInteger(prefix + StatType.Deaths.getName(), 0);
        	wins = ydatabase.getInteger(prefix + StatType.GamesWon.getName(), 0);
        	loses = ydatabase.getInteger(prefix + StatType.GamesLost.getName(), 0);
        	playingTime = ydatabase.getInteger(prefix + StatType.PlayingTime.getName(), 0);
        	
        	createPlayerStats(name, kills, deaths, wins, loses, playingTime);
		}
		else if(dbType == DatabaseType.MySQL) {
			double kills, deaths, wins, loses, playingTime;
			String where = "Player = '"+name+"'";
			
			kills = mdatabase.getInteger(table, where, StatType.Kills.getName(), 0);
			deaths = mdatabase.getInteger(table, where, StatType.Deaths.getName(), 0);
			wins = mdatabase.getInteger(table, where, StatType.GamesWon.getName(), 0);
			loses = mdatabase.getInteger(table, where, StatType.GamesLost.getName(), 0);
			playingTime = mdatabase.getInteger(table, where, StatType.PlayingTime.getName(), 0);
			
			createPlayerStats(name, kills, deaths, wins, loses, playingTime);
		}
    }
	
	public static void saveDatabase() {
		if(dbType == DatabaseType.YAML) {
			for(PlayerStats player : PlayerStats.getAll()) {
				String prefix = "PlayerStats." + player.getName() + ".";
				ydatabase.set(prefix + StatType.Kills.getName(), player.getStat(StatType.Kills).getAmount());
				ydatabase.set(prefix + StatType.Deaths.getName(), player.getStat(StatType.Deaths).getAmount());
				ydatabase.set(prefix + StatType.GamesWon.getName(), player.getStat(StatType.GamesWon).getAmount());
				ydatabase.set(prefix + StatType.GamesLost.getName(), player.getStat(StatType.GamesLost).getAmount());
				ydatabase.set(prefix + StatType.PlayingTime.getName(), player.getStat(StatType.PlayingTime).getAmount());
			}
		}
		else if(dbType == DatabaseType.MySQL) {
			for(PlayerStats player : PlayerStats.getAll()) {
				if(Database.hasPlayer(player.getName())) {
					//Update
					String where = "Player = '"+player.getName()+"'";
					mdatabase.update(table, StatType.Kills.getName() + " = '" + player.getStat(StatType.Kills).getAmount() + "'", where);
					mdatabase.update(table, StatType.Deaths.getName() + " = '" + player.getStat(StatType.Deaths).getAmount() + "'", where);
					mdatabase.update(table, StatType.GamesWon.getName() + " = '" + player.getStat(StatType.GamesWon).getAmount() + "'", where);
					mdatabase.update(table, StatType.GamesLost.getName() + " = '" + player.getStat(StatType.GamesLost).getAmount() + "'", where);
					mdatabase.update(table, StatType.PlayingTime.getName() + " = '" + player.getStat(StatType.PlayingTime).getAmount() + "'", where);
				}else{
					//Insert
					mdatabase.insert(table, 
							"Player, " + 
							StatType.Kills.getName() + ", " +
							StatType.Deaths.getName() + ", " + 
							StatType.GamesWon.getName() + ", " + 
							StatType.GamesLost.getName() + ", " + 
							StatType.PlayingTime.getName()
							
							,
							
							"'" + player.getName() + "', " +
							"'" + player.getStat(StatType.Kills).getAmount() + "', " +
							"'" + player.getStat(StatType.Deaths).getAmount() + "', " +
							"'" + player.getStat(StatType.GamesWon).getAmount() + "', " +
							"'" + player.getStat(StatType.GamesLost).getAmount() + "', " +
							"'" + player.getStat(StatType.PlayingTime).getAmount() + "'");
				}
			}
		}
	}
	
	private static void createPlayerStats(String name, double kills, double deaths, double won, double lost, double playTime) {
		PlayerStats playerStats = new PlayerStats(name);
    	playerStats.getStat(StatType.Kills).setAmount(kills);
    	playerStats.getStat(StatType.Deaths).setAmount(deaths);
    	playerStats.getStat(StatType.GamesWon).setAmount(won);
    	playerStats.getStat(StatType.GamesLost).setAmount(lost);
    	playerStats.getStat(StatType.PlayingTime).setAmount(playTime);
    	
    	PlayerStats.storePlayerStats(playerStats);
	}
	
	public static void initConfiguration() {
		config = new YamlDatabase(KFCPvP.instance, "config");
        config.onStartUp();
	}
	
	public static void loadConfiguration() {
		dbType = DatabaseType.parseString(config.getString("DatabaseType", null));
		if(dbType == DatabaseType.MySQL) {
			//MySQL
			String prefix = "MySQL.";
			ConfigSettings.Host.setSetting(config.getString(prefix + "Host", "localhost"));
			ConfigSettings.Port.setSetting(config.getString(prefix + "Port", "3306"));
			ConfigSettings.Database.setSetting(config.getString(prefix + "Database", "minecraft"));
			ConfigSettings.Username.setSetting(config.getString(prefix + "User", "root"));
			ConfigSettings.Password.setSetting(config.getString(prefix + "Pass", ""));
			//Worlds
			ConfigSettings.Worlds.setArraySetting(config.getStringArray("Worlds", new ArrayList<String>()));
		}
	}
	
	public static void logError(String prefix, String msg) {
		Logger log = Bukkit.getLogger();
		for(int i = 20; i > -1; i--)
			log.log(Level.SEVERE, "[KFCPvP {0}] {1}", new Object[]{prefix, msg});
	}
}
