package me.messageofdeath.KFCPvP.Utils.Stats;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerStats {

	private String name;
	private HashMap<String, Stats> statistics;
	
	public PlayerStats(String name) {
		this.name = name;
		this.statistics = new HashMap<String, Stats>();
		for(StatType stats : StatType.values())
			this.statistics.put(stats.getName(), new Stats());
	}
	
	//****************** Player ********************
	
	public String getName() {
		return name;
	}
	
	public Player getPlayer() {
		return (Player)Bukkit.getPlayer(name);
	}
	
	public OfflinePlayer getOfflinePlayer() {
		return Bukkit.getOfflinePlayer(name);
	}
	
	//***************** Stats ******************
	
	public Stats getStat(StatType statType) {
		return statistics.get(statType.getName());
	}
	
	//****************** Static *******************
	
	private static HashMap<String, PlayerStats> stats;
	
	public static void storePlayerStats(PlayerStats statss) {
		if(stats == null)
			stats = new HashMap<String, PlayerStats>();
		stats.put(statss.getName(), statss);
	}
	
	public static boolean containsPlayerStats(String name) {
		if(stats == null)
			stats = new HashMap<String, PlayerStats>();
		return stats.containsKey(name);
	}
	
	public static void createPlayerStats(String name) {
		storePlayerStats(new PlayerStats(name));
	}
	
	public static PlayerStats getPlayerStats(String name) {
		return stats.get(name);
	}
	
	// ALL PLAYER STATS
	
	public static ArrayList<String> getAllString() {
		if(stats == null)
			stats = new HashMap<String, PlayerStats>();
		ArrayList<String> stat = new ArrayList<String>();
		for(String sta : stats.keySet())
			stat.add(sta);
		return stat;
	}
	
	public static ArrayList<PlayerStats> getAll() {
		if(stats == null)
			stats = new HashMap<String, PlayerStats>();
		ArrayList<PlayerStats> stat = new ArrayList<PlayerStats>();
		for(PlayerStats sta : stats.values())
			stat.add(sta);
		return stat;
	}
}
