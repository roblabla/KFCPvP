package me.messageofdeath.KFCPvP.Database.Configuration;

import java.util.ArrayList;

public enum ConfigSettings {
	
	//*********** Database **************
	
	DatabaseSaveInterval(),
	
	
	
	DefaultPointLimit(),
	
	//*************** Vote ***************
	
	VotePointAddition(),
	
	//************ Worlds *****************
	
	Worlds();
	
	//********* Getting ***************
    
	private Object setting;
	
    public String getName() {
        return this.toString();
    }
	
	public Object getSetting() {
		return setting;
	}
    
    public void setSetting(Object setting) {
        this.setting = setting;
    }
    
    public Object getDefaultSetting() {
    	if(this.getName().equalsIgnoreCase(ConfigSettings.DatabaseSaveInterval.getName()))
    		return 60*10;
    	if(this.getName().equalsIgnoreCase(ConfigSettings.DefaultPointLimit.getName()))
    		return 1000;
    	if(this.getName().equalsIgnoreCase(ConfigSettings.Worlds.getName()))
    		return new ArrayList<String>();
    	if(this.getName().equalsIgnoreCase(ConfigSettings.VotePointAddition.getName()))
    		return 20;
    	return null;
    }
    
    public void setDefaultSetting() {
    	if(this.getName().equalsIgnoreCase(ConfigSettings.DatabaseSaveInterval.getName()))
    		this.setting = 60*10;
    	if(this.getName().equalsIgnoreCase(ConfigSettings.DefaultPointLimit.getName()))
    		this.setting = 1000;
    	if(this.getName().equalsIgnoreCase(ConfigSettings.Worlds.getName()))
    		this.setting = new ArrayList<String>();
    	if(this.getName().equalsIgnoreCase(ConfigSettings.VotePointAddition.getName()))
    		this.setting = 20;
    }
} 
