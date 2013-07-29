package me.messageofdeath.KFCPvP.Database;

public enum DatabaseType {

	
	MySQL(),
	
	
	
	
	
	YAML();
	
	public String getName() {
		return this.toString();
	}
	
	public static DatabaseType parseString(String parse) {
		if(parse == null)
			return null;
		if(parse.equalsIgnoreCase("mysql") || parse.equalsIgnoreCase("sql"))
			return DatabaseType.MySQL;
		else if(parse.equalsIgnoreCase("yaml") || parse.equalsIgnoreCase("yml"))
			return DatabaseType.YAML;
		else
			return null;
	}
}
