package me.messageofdeath.KFCPvP.Utils.WorldManager;

import me.messageofdeath.Blocks.Cuboid;

public class World {

	private String worldName;
	private Cuboid cuboid;
	
	public World(String worldName, Cuboid cuboid) {
		this.worldName = worldName;
		this.cuboid = cuboid;
	}
	
	public String getWorldName() {
		return this.worldName;
	}
	
	public Cuboid getCuboid() {
		return this.cuboid;
	}
}
