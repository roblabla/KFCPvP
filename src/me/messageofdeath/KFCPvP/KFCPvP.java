package me.messageofdeath.KFCPvP;

import me.messageofdeath.KFCPvP.Database.Database;
import me.messageofdeath.KFCPvP.Listener.signListener;
import me.messageofdeath.KFCPvP.Timer.Timer;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

import org.bukkit.plugin.java.JavaPlugin;

public class KFCPvP extends JavaPlugin {
	
	public static KFCPvP instance;

	@Override
	public void onEnable() {
		instance = this;
		Database.initConfiguration();
		Database.loadConfiguration();
		Database.initDatabase();
        PlayerStats.getPlayerStats("messageofdeath").getStat(StatType.Kills).addAmount(20);
		getServer().getPluginManager().registerEvents(new signListener(), this);
		//Start Global Arena Base Timer
        getServer().getScheduler().runTaskTimer(this, new Timer(), 0L, 20L);
	}
	
	@Override
	public void onDisable() {
		Database.saveDatabase();
	}
}
