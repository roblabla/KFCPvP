package me.messageofdeath.KFCPvP.Database;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import me.messageofdeath.KFCPvP.Database.Configuration.Configuration;

public class Database {
	
	//******************* Configuration *********************
	
	public static void initConfiguration() {
		Configuration.initConfiguration();
	}
	
	public static void loadConfiguration() {
		Configuration.loadConfiguration();
	}
	
	//******************* Players *********************
	
	public static void initStatDatabase() {
		StatDatabase.initDatabase();
	}
	
	public static void saveStatDatabase() {
		StatDatabase.saveDatabase();
	}
	
	//******************* Items *********************
	
	public static void initItemDatabase() {
		ItemDatabase.initDatabase();
	}
	
	public static void loadItemDatabase() {
		ItemDatabase.loadDatabase();
	}
	
	//******************* Arena *********************
	
	public static void initArenaDatabase() {
		ArenaDatabase.initDatabase();
	}
	
	public static void loadArenaDatabase() {
		ArenaDatabase.loadDatabase();
	}
	
	//******************* World *********************
	
	public static void initWorldDatabase() {
		WorldDatabase.initDatabase();
	}
	
	public static void loadWorldDatabase() {
		WorldDatabase.loadDatabase();
	}
	
	//******************* Logging *********************
	
	public static void logError(String prefix, String msg) {
		Logger log = Bukkit.getLogger();
		for(int i = 20; i > -1; i--)
			log.log(Level.SEVERE, "[KFCPvP {0}] {1}", new Object[]{prefix, msg});
	}
}
