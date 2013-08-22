package me.messageofdeath.KFCPvP.Database.Databases;

import java.util.ArrayList;

import me.messageofdeath.Blocks.Cuboid;
import me.messageofdeath.Blocks.Vector;
import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Database.DatabaseManager;
import me.messageofdeath.KFCPvP.Database.Types.DatabaseType;
import me.messageofdeath.KFCPvP.Utils.InfiniteChests.InfiniteChest;

public class WorldDatabase {
	
	private final String table = "KFCPvP_Worlds";
	private DatabaseManager manager;

	public void initDatabase() {
		this.manager = KFCPvP.getInstance().getDatabaseManager();
		
		if(this.manager.useMySQL(DatabaseType.loadType) || this.manager.useMySQL(DatabaseType.saveType)) {
			
		}
		
		if(this.manager.useYAML(DatabaseType.loadType) || this.manager.useYAML(DatabaseType.saveType)) {
			
		}
	}
	
	public void loadDatabase() {
		if(this.manager.useMySQL(DatabaseType.loadType)) {
			
		}
		
		if(this.manager.useMySQL(DatabaseType.saveType)) {
			
		}
	}
	
	public Vector getLobby(String world) {
		return null;//TODO Implement
	}
	
	public ArrayList<Vector> getSpawns(String world) {
		return null;//TODO Implement
	}
	
	public ArrayList<InfiniteChest> getChests(String world) {
		return null;//TODO Implement
	}
	
	public Cuboid getBorder(String world) {
		return null;//TODO Implement
	}
}
