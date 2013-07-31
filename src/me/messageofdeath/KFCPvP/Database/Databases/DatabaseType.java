package me.messageofdeath.KFCPvP.Database.Databases;

public enum DatabaseType {

	
	MySQL(),
	
	
	
	
	
	YAML(),
	
	
	
	
	
	
	Both(),
	
	
	
	
	
	loadType(),
	
	
	
	
	
	saveType();
	
	public String getName() {
		return this.toString();
	}
	
	private DatabaseType dataType;
	
	public DatabaseType getDataType() {
		return dataType;
	}
	
	public void setDataType(DatabaseType dataType) {
		this.dataType = dataType;
	}
	
	public static DatabaseType parseString(String parse) {
		if(parse == null)
			return null;
		if(parse.equalsIgnoreCase("mysql") || parse.equalsIgnoreCase("sql"))
			return DatabaseType.MySQL;
		else if(parse.equalsIgnoreCase("yaml") || parse.equalsIgnoreCase("yml"))
			return DatabaseType.YAML;
		else if(parse.equalsIgnoreCase("both") || parse.equalsIgnoreCase("all"))
			return DatabaseType.Both;
		else
			return null;
	}
}
