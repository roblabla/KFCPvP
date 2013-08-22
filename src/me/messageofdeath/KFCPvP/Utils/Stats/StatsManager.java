package me.messageofdeath.KFCPvP.Utils.Stats;

import java.util.ArrayList;
import java.util.HashMap;

import me.messageofdeath.KFCPvP.KFCPvP;

public class StatsManager {
	
	public StatsManager() {
		stats = new HashMap<String, PlayerStats>();
	}
	
	protected HashMap<String, PlayerStats> stats;
	
	public void storePlayerStats(PlayerStats statss) {
		stats.put(statss.getName(), statss);
	}
	
	public boolean containsPlayerStats(String name) {
		return stats.containsKey(name);
	}
	
	public void createPlayerStats(String name) {
		if(!containsPlayerStats(name))
			if(KFCPvP.getInstance().getStatDatabase().hasPlayer(name)) {
				KFCPvP.getInstance().getStatDatabase().loadPlayer(name);
			}else{
				ArrayList<Integer> stats = new ArrayList<Integer>();
				for(int i = StatType.values().length; i > -1; i--) {
					stats.add(0);
				}
				createPlayerStats(name, stats);
			}
	}
	
	public void createPlayerStats(String name, ArrayList<Integer> stats) {
		PlayerStats playerStats = new PlayerStats(name);
		int i = 0;
		for(Integer stat : stats) {
			playerStats.getStat(StatType.values()[i]).setStat(stat);
			i++;
		}
    	
    	storePlayerStats(playerStats);
	}
	
	public PlayerStats getPlayerStats(String name) {
		return stats.get(name);
	}
	
	// ALL PLAYER STATS
	
	public ArrayList<String> getAllString() {
		ArrayList<String> stat = new ArrayList<String>();
		for(String sta : stats.keySet())
			stat.add(sta);
		return stat;
	}
	
	public ArrayList<PlayerStats> getAll() {
		ArrayList<PlayerStats> stat = new ArrayList<PlayerStats>();
		for(PlayerStats sta : stats.values())
			stat.add(sta);
		return stat;
	}
}
