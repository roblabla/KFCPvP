package me.messageofdeath.KFCPvP.Utils.WorldManager;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import me.messageofdeath.Blocks.Cuboid;

public class World {

	private String worldName;
	private ArrayList<Location> spawns;
	private Cuboid cuboid;
	
	public World(String worldName, ArrayList<Location> spawns, Cuboid cuboid) {
		this.worldName = worldName;
		this.spawns = spawns;
		this.cuboid = cuboid;
	}
	
	public String getWorldName() {
		return this.worldName;
	}
	
	public void randomSpawn(String name) {
		Random rand = new Random();
		rand.nextInt(spawns.size() - 1);
		Bukkit.getPlayer(name).teleport(spawns.get(rand.nextInt(spawns.size())));
	}
	
	public Cuboid getCuboid() {
		return this.cuboid;
	}
}
