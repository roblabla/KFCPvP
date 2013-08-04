package me.messageofdeath.KFCPvP.Utils.WorldManager;

import java.util.ArrayList;

import me.messageofdeath.KFCPvP.KFCPvP;

import org.bukkit.WorldCreator;

public class WorldManager  {

	private static ArrayList<World> worlds;
	
	public static void loadWorld(World world) {
		KFCPvP.instance.getServer().createWorld(new WorldCreator(world.getWorldName()));
	}
	
	public static void unloadWorld(World world) {
		KFCPvP.instance.getServer().unloadWorld(world.getWorldName(), false);
	}
	
	public static void initWorldManager() {
		worlds = new ArrayList<World>();
	}
	
	public static ArrayList<World> getWorlds() {
		return worlds;
	}
	
	public static World getWorld(String worldName) {
		for(World world : getWorlds())
			if(world.getWorldName().equalsIgnoreCase(worldName))
				return world;
		return null;
	}
}
