package me.messageofdeath.KFCPvP.Database.Databases;

import java.util.ArrayList;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import me.messageofdeath.Database.MySQLDatabase;
import me.messageofdeath.Database.YamlDatabase;
import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Database.Types.DatabaseType;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

public class StatDatabase {
	
	private final String table = "KFCPvP_Stats";
	private YamlDatabase database;
	
	public void initDatabase() {
		if(KFCPvP.getInstance().getDatabaseManager().useMySQL(DatabaseType.loadType) || KFCPvP.getInstance().getDatabaseManager().useMySQL(DatabaseType.saveType)) {
			if(KFCPvP.getInstance().getMySQL().success == true){
				if(!KFCPvP.getInstance().getMySQL().getInstance().doesTableExist(this.table)) {
					String columns = "Player VARCHAR(18),";
	        		for(StatType stats : StatType.values()) {
	        			columns += " " + stats.getName() + " " + stats.getType() + ",";
	        		}
	        		columns = columns.substring(0, columns.length() - 1);
	        		KFCPvP.getInstance().getMySQL().getInstance().createTable(table, columns);
	        		Bukkit.getLogger().log(Level.INFO, "Created the table '{0}'!", table);
				}
			}
        }
		
		if(KFCPvP.getInstance().getDatabaseManager().useYAML(DatabaseType.loadType) || KFCPvP.getInstance().getDatabaseManager().useYAML(DatabaseType.saveType)) {
			database = new YamlDatabase(KFCPvP.getInstance(), "database");
			database.onStartUp();
		}
	}
	
	public void loadPlayer(String name) {
		if(KFCPvP.getInstance().getDatabaseManager().useYAML(DatabaseType.loadType)) {
			String prefix = "PlayerStats." + name + ".";
	    	ArrayList<Integer> stats = new ArrayList<Integer>();
	    	
	    	for(StatType type : StatType.values()) {
				stats.add(this.database.getInteger(prefix + type.getName(), 0));
			}
	    	
	    	KFCPvP.getInstance().getStatManager().createPlayerStats(name, stats);
		}
		if(KFCPvP.getInstance().getDatabaseManager().useMySQL(DatabaseType.loadType)) {
			MySQLDatabase mdatabase = KFCPvP.getInstance().getMySQL().getInstance();
			ArrayList<Integer> stats = new ArrayList<Integer>();
			String where = "Player = '"+name+"'";

			for(StatType type : StatType.values()) {
				stats.add(mdatabase.getInteger(this.table, where, type.getName(), 0));
			}
			
			KFCPvP.getInstance().getStatManager().createPlayerStats(name, stats);
		}
    }
	
	public void saveDatabase() {
		if(KFCPvP.getInstance().getDatabaseManager().useYAML(DatabaseType.saveType)) {
			for(PlayerStats player : KFCPvP.getInstance().getStatManager().getAll()) {
				for(StatType stats : StatType.values())
					this.database.set("PlayerStats." + player.getName() + "." + stats.getName(), player.getStat(stats).getStat());
			}
		}
		if(KFCPvP.getInstance().getDatabaseManager().useMySQL(DatabaseType.saveType)) {
			for(PlayerStats player : KFCPvP.getInstance().getStatManager().getAll()) {
				if(KFCPvP.getInstance().getStatDatabase().hasPlayer(player.getName())) {
					for(StatType stats : StatType.values())
						KFCPvP.getInstance().getMySQL().getInstance()
							.update(this.table, stats.getName() + " = '" + player.getStat(stats).getStat() + "'", "Player = '"+player.getName()+"'");
				}else{
					String columns = "Player,";
					String values = "'" + player.getName() + "',";
					for(StatType stats : StatType.values()) {
						columns += " " + stats.getName() + ",";
						values += " '" + player.getStat(stats).getStat() + "',";
					}
					columns = columns.substring(0, columns.length() - 1);
					values = values.substring(0, values.length() - 1);
					//Insert
					KFCPvP.getInstance().getMySQL().getInstance()
						.insert(this.table, columns, values);
				}
			}
		}
	}
	
	//****************** Methods ********************
	
	public boolean hasPlayer(String name) {
		if(KFCPvP.getInstance().getDatabaseManager().useMySQL(DatabaseType.loadType))
			return KFCPvP.getInstance().getMySQL().getInstance().contains(this.table, "Player = '"+name+"'");
		if(KFCPvP.getInstance().getDatabaseManager().useYAML(DatabaseType.loadType))
			return this.database.contains("PlayerStats." + name);
		return false;
	}
}
