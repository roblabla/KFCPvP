package me.messageofdeath.KFCPvP.Utils.WorldManager;

import java.util.ArrayList;

import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Database.Configuration.ConfigSettings;
import me.messageofdeath.KFCPvP.Database.Databases.WorldDatabase;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

public class WorldManager {

	private ArrayList<World> worlds;
	
	public void loadWorld(World world) {
		if(!world.inUse())
			KFCPvP.getInstance().getServer().createWorld(new WorldCreator(world.getWorldName()));
	}
	
	public boolean isLoaded(World world) {
		if(world != null)
			for(org.bukkit.World worlds : Bukkit.getWorlds())
				if(worlds.getName().equalsIgnoreCase(world.getWorldName()))
					return true;
		return false;
	}
	
	public void unloadWorld(World world) {
		if(!world.inUse())
			KFCPvP.getInstance().getServer().unloadWorld(world.getWorldName(), false);
	}
	
	public void initWorldManager() {
		this.worlds = new ArrayList<World>();
		WorldDatabase db = KFCPvP.getInstance().getWorldDatabase();
		for(Object world : (ArrayList<?>)ConfigSettings.Worlds.getSetting()) {
			if(world instanceof String)
				this.worlds.add(new World((String)world, db.getLobby((String)world), db.getSpawns((String)world)
						, db.getChests((String)world), db.getBorder((String)world)));
		}
	}
	
	public ArrayList<World> getWorlds() {
		return worlds;
	}
	
	public World getWorld(String worldName) {
		for(World world : getWorlds())
			if(world.getWorldName().equalsIgnoreCase(worldName))
				return world;
		return null;
	}
}
