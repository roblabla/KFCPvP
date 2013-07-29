package me.messageofdeath.KFCPvP.Database;

import java.util.ArrayList;

public enum ConfigSettings {
	
	//************ MySQL ***************

	Host(),
	
	
	
	Port(),
	
	
	
	Database(),
	
	
	
    Username(),
	
	
	
	Password(),
	
	
	Worlds();
	
	//********* Getting ***************
    
    public String getName() {
        return this.toString();
    }
	
	public String getSetting() {
		return setting;
	}
	
	public ArrayList<String> getArraySetting() {
		return arraySetting;
	}
    
    private String setting;
    private ArrayList<String> arraySetting = new ArrayList<String>();
    
    public void setSetting(String setting) {
        this.setting = setting;
    }
    
    public void setArraySetting(ArrayList<String> setting) {
    	this.arraySetting = setting;
    }
} 
