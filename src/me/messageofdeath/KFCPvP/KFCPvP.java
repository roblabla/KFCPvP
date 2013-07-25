package me.messageofdeath.KFCPvP;

import me.messageofdeath.KFCPvP.Timer.Timer;
import org.bukkit.plugin.java.JavaPlugin;

public class KFCPvP extends JavaPlugin {
	
	public static KFCPvP instance;

	@Override
	public void onEnable() {
		instance = this;
        getServer().getScheduler().runTaskTimer(this, new Timer(), 0L, 20L);
	}
}
