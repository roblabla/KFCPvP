package me.messageofdeath.KFCPvP.Utils.WorldManager;

import java.util.ArrayList;

public class WorldManager {

	private static ArrayList<String> worlds;
	
	public static void initWorldManager() {
		worlds = new ArrayList<String>();
	}
	
	public static ArrayList<String> getWorlds() {
		return worlds;
	}
}
