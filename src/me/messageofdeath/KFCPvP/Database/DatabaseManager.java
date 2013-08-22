package me.messageofdeath.KFCPvP.Database;

import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Database.Types.DatabaseType;

public class DatabaseManager {

	//******************* Methods ************************
	
	public void loadDatabaseType() {
		if(this.useMySQL(DatabaseType.loadType) || this.useMySQL(DatabaseType.saveType))
			KFCPvP.getInstance().getMySQL().initDatabase();
	}
	
	public boolean useMySQL(DatabaseType type) {
		return type.getDataType() == DatabaseType.MySQL || type.getDataType() == DatabaseType.ALL;
	}
	
	public boolean useYAML(DatabaseType type) {
		return type.getDataType() == DatabaseType.YAML || type.getDataType() == DatabaseType.ALL;
	}
	
	//******************* Logging *********************
	
	public void logError(String prefix, String msg) {
		//TODO Fix
	}
}
