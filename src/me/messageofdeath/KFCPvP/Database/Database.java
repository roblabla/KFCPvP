package me.messageofdeath.KFCPvP.Database;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import me.messageofdeath.KFCPvP.Database.Configuration.Configuration;
import me.messageofdeath.KFCPvP.Database.Databases.DatabaseType;
import me.messageofdeath.KFCPvP.Database.Databases.MySQL;
import me.messageofdeath.KFCPvP.Database.Databases.Yaml;

public class Database {
	
    public static DatabaseType loadDBType, saveDBType;
    
	public static void initDatabase() {
		if(loadDBType == DatabaseType.MySQL || saveDBType == DatabaseType.MySQL || saveDBType == DatabaseType.Both) {
			MySQL.initDatabase();
        }
		if(loadDBType == DatabaseType.YAML || saveDBType == DatabaseType.YAML || saveDBType == DatabaseType.Both) {
        	Yaml.initDatabase();
        }
	}
	
	public static boolean hasPlayer(String name) {
		if(loadDBType == DatabaseType.MySQL)
			return MySQL.hasPlayer(name);
		else if(loadDBType == DatabaseType.YAML)
			return Yaml.hasPlayer(name);
		return false;
	}
	
	public static void loadPlayer(String name) {
		if(loadDBType == DatabaseType.YAML) {
        	Yaml.loadPlayer(name);
		}
		if(loadDBType == DatabaseType.MySQL) {
			MySQL.loadPlayer(name);
		}
    }
	
	public static void saveDatabase() {
		if(saveDBType == DatabaseType.YAML || saveDBType == DatabaseType.Both) {
			Yaml.saveDatabase();
		}
		if(saveDBType == DatabaseType.MySQL || saveDBType == DatabaseType.Both) {
			MySQL.saveDatabase();
		}
	}
	
	public static void initConfiguration() {
		Configuration.initConfiguration();
	}
	
	public static void loadConfiguration() {
		Configuration.loadConfiguration();
	}
	
	public static void logError(String prefix, String msg) {
		Logger log = Bukkit.getLogger();
		for(int i = 20; i > -1; i--)
			log.log(Level.SEVERE, "[KFCPvP {0}] {1}", new Object[]{prefix, msg});
	}
}
