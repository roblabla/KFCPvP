package me.messageofdeath.KFCPvP.Utils.WorldManager;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;

import me.messageofdeath.Blocks.Cuboid;
import me.messageofdeath.Blocks.Vector;
import me.messageofdeath.KFCPvP.Utils.Arenas.Arena;
import me.messageofdeath.KFCPvP.Utils.InfiniteChests.InfiniteChest;

public class World {

	private String worldName;
	private Vector lobby;
	private ArrayList<Vector> spawns;
	private ArrayList<InfiniteChest> chests;
	private ArrayList<Arena> using;
	private Cuboid cuboid;
	
	public World(String worldName, Vector lobby, ArrayList<Vector> spawns, ArrayList<InfiniteChest> chests, Cuboid cuboid) {
		this.worldName = worldName;
		this.lobby = lobby;
		this.spawns = spawns;
		this.cuboid = cuboid;
		this.chests = chests;
		this.using = new ArrayList<Arena>();
	}
	
	public String getWorldName() {
		return this.worldName;
	}
	
	public Vector getLobby() {
		return this.lobby;
	}
	
	public ArrayList<InfiniteChest> getChests() {
		return chests;
	}
	
	public void randomSpawn(String name) {
		Bukkit.getPlayer(name).teleport(this.spawns.get(new Random().nextInt(this.spawns.size() - 1)).getLocation());
	}
	
	public Cuboid getCuboid() {
		return this.cuboid;
	}
	
	public boolean inUse() {
		return !this.using.isEmpty();
	}
	
	public void addUse(Arena use) {
		this.using.add(use);
	}
	
	public void removeUse(Arena use) {
		this.using.remove(use);
	}
	
	public void sendToLobby(Arena arena) {
		for(String player : arena.getAllOnline())
			Bukkit.getPlayer(player).teleport(this.lobby.getLocation());
	}
	
	public void sendToLobby(String name) {
		Bukkit.getPlayer(name).teleport(this.lobby.getLocation());
	}
}
