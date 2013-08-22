package me.messageofdeath.KFCPvP.Utils.Stats;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerStats {

	private String name;
	private HashMap<String, Stat> statistics;
	
	public PlayerStats(String name) {
		this.name = name;
		this.statistics = new HashMap<String, Stat>();
		for(StatType stats : StatType.values())
			this.statistics.put(stats.getName(), new Stat());
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
	
	public Stat getStat(StatType statType) {
		return statistics.get(statType.getName());
	}
}
