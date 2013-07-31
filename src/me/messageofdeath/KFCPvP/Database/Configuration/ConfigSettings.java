package me.messageofdeath.KFCPvP.Database.Configuration;

public enum ConfigSettings {
	
	//*********** Database Save Interval **************
	
	DatabaseSaveInterval(),
	
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
} 
