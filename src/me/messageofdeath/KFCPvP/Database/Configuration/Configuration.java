package me.messageofdeath.KFCPvP.Database.Configuration;

import java.util.ArrayList;

import me.messageofdeath.Database.YamlDatabase;
import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Database.Database;
import me.messageofdeath.KFCPvP.Database.Databases.DatabaseType;

public class Configuration {

	private static YamlDatabase config;
	
	public static void initConfiguration() {
		config = new YamlDatabase(KFCPvP.instance, "config");
        config.onStartUp();
	}
	
	public static void loadConfiguration() {
		Database.loadDBType = DatabaseType.parseString(config.getString("DatabaseType.Load", null));
		Database.saveDBType = DatabaseType.parseString(config.getString("DatabaseType.Save", null));

		ConfigSettings.DatabaseSaveInterval.setSetting(config.getInteger("DatabaseType.SaveInterval", 60*10));
		ConfigSettings.Worlds.setSetting(config.getStringArray("Worlds", new ArrayList<String>()));
		
		checkConfiguration();
			
		
		if(Database.loadDBType == DatabaseType.MySQL || Database.saveDBType == DatabaseType.Both) {
			for(MySQLSettings settings : MySQLSettings.values())
				settings.setSetting(config.getString("MySQL." + settings.getName(), settings.getDefaultSetting()));
		}
	}
	
	private static void checkConfiguration() {
		//*********** Database Settings Check **************
		if(Database.loadDBType == null || Database.saveDBType == null || Database.loadDBType == DatabaseType.Both) {
			if(Database.loadDBType == DatabaseType.Both) {
				Database.logError("Configuration (Database)", "The 'load' database feature cannot be the value of Both! Changing to MySQL!");
				Database.logError("Configuration (Database)", "The 'load' database feature cannot be the value of Both! Changing to MySQL!");
				Database.loadDBType = DatabaseType.MySQL;
			}
			if(Database.loadDBType == null) {
				Database.logError("Configuration (Database)", "The 'load' Database Type cannot be null! Changing to YAML!");
				Database.logError("Configuration (Database)", "The 'load' Database Type cannot be null! Changing to YAML!");
				Database.loadDBType = DatabaseType.YAML;
			}
			if(Database.saveDBType == null) {
				Database.logError("Configuration (Database)", "The 'save' Database Type cannot be null! Changing to YAML!");
				Database.logError("Configuration (Database)", "The 'save' Database Type cannot be null! Changing to YAML!");
				Database.saveDBType = DatabaseType.YAML;
			}
		}
		
		if(!(ConfigSettings.DatabaseSaveInterval.getSetting() instanceof Integer)) {
			int seconds = 60*10;
			Database.logError("Configuration (Database)", "The Database 'Save Interval' has to be a Integer! Changing to (Seconds)" + seconds 
					+ " or (Minutes)" + seconds/60);
			Database.logError("Configuration (Database)", "The Database 'Save Interval' has to be a Integer! Changing to (Seconds)" + seconds 
					+ " or (Minutes)" + seconds/60);
			ConfigSettings.DatabaseSaveInterval.setSetting(seconds);
		}
		
		//*************** Worlds Settings Check ***************
		
		if(!(ConfigSettings.Worlds.getSetting() instanceof ArrayList<?>)) {
			Database.logError("Configuration (Worlds)", "The save Database Type cannot be null! Changing to YAML!");
			Database.logError("Configuration (Worlds)", "The save Database Type cannot be null! Changing to YAML!");
			ConfigSettings.Worlds.setSetting(new ArrayList<String>());
		}
	}
}
