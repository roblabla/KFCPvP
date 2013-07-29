package me.messageofdeath.KFCPvP.Database;

public enum MySQLSettings {

	Host(),
	
	
	
	Port(),
	
	
	
	Database(),
	
	
	
    Username(),
	
	
	
	Password();
    
    public String getName() {
        return this.toString();
    }
	
	public String getInformation() {
		return information;
	}
    
    private String information;
    
    public void setInformation(String info) {
        this.information = info;
    }
}
