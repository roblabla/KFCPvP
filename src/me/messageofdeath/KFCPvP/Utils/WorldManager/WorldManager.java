package me.messageofdeath.KFCPvP.Utils.WorldManager;

import java.util.ArrayList;

public class WorldManager {

	private static ArrayList<World> worlds;
	
	public static void initWorldManager() {
		worlds = new ArrayList<World>();
	}
	
	public static ArrayList<World> getWorlds() {
		return worlds;
	}
}
