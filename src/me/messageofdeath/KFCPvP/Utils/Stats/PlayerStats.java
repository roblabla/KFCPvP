package me.messageofdeath.KFCPvP.Utils.Stats;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerStats {

	private String name;
	private Stats stat;
	
	public PlayerStats(String name) {
		this.name = name;
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
		return null;
	}
	
	//****************** Static *******************
	
	private static HashMap<String, PlayerStats> stats;
	
	public static void registerStats(PlayerStats statss) {
		if(stats == null)
			stats = new HashMap<String, PlayerStats>();
		stats.put(statss.getName(), statss);
	}
	
	public static PlayerStats getStats(String name) {
		return stats.get(name);
	}
}
