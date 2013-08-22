package me.messageofdeath.KFCPvP.Database.Configuration;

import java.util.ArrayList;

import me.messageofdeath.Database.YamlDatabase;
import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Database.Types.DatabaseType;

public class Configuration {

	private YamlDatabase config;
	
	public void initConfiguration() {
		config = new YamlDatabase(KFCPvP.getInstance(), "config");
        config.onStartUp();
	}
	
	@SuppressWarnings("unchecked")
	public void loadConfiguration() {
		DatabaseType.loadType.setDataType(DatabaseType.parseString(config.getString("DatabaseType.Load", null)));
		DatabaseType.saveType.setDataType(DatabaseType.parseString(config.getString("DatabaseType.Save", null)));
		
		ConfigSettings.DefaultPointLimit.setSetting(config.getInteger("defaultPointLimit", 
				(Integer)ConfigSettings.DefaultPointLimit.getDefaultSetting()));
		ConfigSettings.DatabaseSaveInterval.setSetting(config.getInteger("DatabaseType.SaveInterval", 
				(Integer)ConfigSettings.DatabaseSaveInterval.getDefaultSetting()));
		ConfigSettings.VotePointAddition.setSetting(config.getInteger("VotesAdditionToPoints", 
				(Integer)ConfigSettings.VotePointAddition.getDefaultSetting()));
		ConfigSettings.Worlds.setSetting(config.getStringArray("Worlds", 
				(ArrayList<String>)ConfigSettings.Worlds.getDefaultSetting()));//Needs the @SuppressWarnings("unchecked")
		
		checkConfiguration();
			
		
		if(DatabaseType.loadType.getDataType() == DatabaseType.MySQL || DatabaseType.saveType.getDataType() == DatabaseType.ALL) {
			for(MySQLSettings settings : MySQLSettings.values())
				settings.setSetting(config.getString("MySQL." + settings.getName(), settings.getDefaultSetting()));
		}
	}
	
	private void checkConfiguration() {
		//*********** Database Settings Check **************
		if(DatabaseType.loadType.getDataType() == null || DatabaseType.saveType.getDataType() == null || DatabaseType.loadType.getDataType() == DatabaseType.ALL) {
			if(DatabaseType.loadType.getDataType() == DatabaseType.ALL) {
				KFCPvP.getInstance().getDatabaseManager().logError("Configuration", "The 'load' database feature cannot be the value of Both! Changing to MySQL!");
				DatabaseType.loadType.setDataType(DatabaseType.MySQL);
			}
			if(DatabaseType.loadType.getDataType() == null) {
				KFCPvP.getInstance().getDatabaseManager().logError("Configuration", "The 'load' Database Type must be set! Changing to YAML!");
				DatabaseType.loadType.setDataType(DatabaseType.YAML);
			}
			if(DatabaseType.saveType.getDataType() == null) {
				KFCPvP.getInstance().getDatabaseManager().logError("Configuration", "The 'save' Database Type must be set! Changing to YAML!");
				DatabaseType.loadType.setDataType(DatabaseType.YAML);
			}
		}
		
		checkInteger(ConfigSettings.DefaultPointLimit);
		
		checkInteger(ConfigSettings.DatabaseSaveInterval);
				
		checkArrays(ConfigSettings.Worlds);
	}
	
	private void checkArrays(ConfigSettings setting) {
		if(!(setting.getSetting() instanceof ArrayList<?>)) {
			KFCPvP.getInstance().getDatabaseManager().logError("Configurtion", "The '"+setting.getName()+"' has to be a List! Changing to default value!");
			setting.setDefaultSetting();
		}
	}
	
	private void checkInteger(ConfigSettings setting) {
		if(!(setting.getSetting() instanceof Integer)) {
			KFCPvP.getInstance().getDatabaseManager().logError("Configurtion", "The '"+setting.getName()+"' has to be a Integer! Changing to default value!");
			setting.setDefaultSetting();
		}
	}
}
