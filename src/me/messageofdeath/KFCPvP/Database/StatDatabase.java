package me.messageofdeath.KFCPvP.Database;

import me.messageofdeath.KFCPvP.Database.Databases.DatabaseType;
import me.messageofdeath.KFCPvP.Database.Databases.MySQL;
import me.messageofdeath.KFCPvP.Database.Databases.Yaml;

public class StatDatabase {
	
	public static void initDatabase() {
		if(DatabaseType.loadType.getDataType() == DatabaseType.MySQL || DatabaseType.saveType.getDataType() == DatabaseType.MySQL || DatabaseType.saveType.getDataType() == DatabaseType.Both) {
			MySQL.initDatabase();
        }
		if(DatabaseType.loadType.getDataType() == DatabaseType.YAML || DatabaseType.saveType.getDataType() == DatabaseType.YAML || DatabaseType.saveType.getDataType() == DatabaseType.Both) {
			Yaml.initDatabase();
        }
	}
	
	public static void loadPlayer(String name) {
		if(DatabaseType.loadType.getDataType() == DatabaseType.YAML) {
        	Yaml.loadPlayer(name);
		}
		if(DatabaseType.loadType.getDataType() == DatabaseType.MySQL) {
			MySQL.loadPlayer(name);
		}
    }
	
	public static void saveDatabase() {
		if(DatabaseType.saveType.getDataType() == DatabaseType.YAML || DatabaseType.saveType.getDataType() == DatabaseType.Both) {
			Yaml.saveDatabase();
		}
		if(DatabaseType.saveType.getDataType() == DatabaseType.MySQL || DatabaseType.saveType.getDataType() == DatabaseType.Both) {
			MySQL.saveDatabase();
		}
	}
	
	//****************** Methods ********************
	
	public static boolean hasPlayer(String name) {
		if(DatabaseType.loadType.getDataType() == DatabaseType.MySQL)
			return MySQL.hasPlayer(name);
		if(DatabaseType.loadType.getDataType() == DatabaseType.YAML)
			return Yaml.hasPlayer(name);
		return false;
	}
}
