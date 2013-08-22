package me.messageofdeath.KFCPvP.Database.Types;

import java.sql.SQLException;

import me.messageofdeath.Database.MySQLDatabase;
import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Database.Configuration.MySQLSettings;

public class MySQL {

	private MySQLDatabase mdatabase;
	public boolean success = false;
	
	//***************** OnStartUp Methods *********************
	
	public void initDatabase() {
    	mdatabase = new MySQLDatabase(KFCPvP.getInstance(),
    			MySQLSettings.Host.getSetting(),
    			MySQLSettings.Port.getSetting(),
    			MySQLSettings.Database.getSetting(),
    			MySQLSettings.Username.getSetting(),
    			MySQLSettings.Password.getSetting());
    	
    	boolean success = true;
    	try{
    		mdatabase.onStartUp();
    	}catch(SQLException e) {
    		KFCPvP.getInstance().getDatabaseManager().logError("MySQL", "Coult not connect to database! Changing to YAML! - " + e.getMessage());
    		if(DatabaseType.loadType.getDataType() == DatabaseType.MySQL)
    			DatabaseType.loadType.setDataType(DatabaseType.YAML);
    		if(DatabaseType.saveType.getDataType() == DatabaseType.MySQL)
    			DatabaseType.saveType.setDataType(DatabaseType.YAML);
    		success = false;
    	}
    	this.success = success;
	}
	
	//****************** Methods ************************

	public MySQLDatabase getInstance() {
		return mdatabase;
	}
}
