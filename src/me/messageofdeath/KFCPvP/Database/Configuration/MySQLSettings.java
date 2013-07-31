package me.messageofdeath.KFCPvP.Database.Configuration;

public enum MySQLSettings {

	Host(),
	
	
	
	Port(),
	
	
	
	Database(),
	
	
	
    Username(),
	
	
	
	Password();
	
	//********* Getting ***************
    
	private String setting;
	
    public String getName() {
        return this.toString();
    }
	
	public String getSetting() {
		return setting;
	}
    
    public void setSetting(String setting) {
        this.setting = setting;
    }
}
