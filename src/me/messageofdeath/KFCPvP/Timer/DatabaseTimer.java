package me.messageofdeath.KFCPvP.Timer;

import me.messageofdeath.KFCPvP.Database.Database;
import me.messageofdeath.KFCPvP.Database.Configuration.ConfigSettings;

public class DatabaseTimer {
	
	private static int seconds;
	
    public static void run() {
    	if(seconds == 60*Integer.parseInt(String.valueOf(ConfigSettings.DatabaseSaveInterval.getSetting()))) {
    		Database.saveStatDatabase();
    		seconds = -1;
    	}
    	seconds++;
    }
}