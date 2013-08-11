package me.messageofdeath.KFCPvP;

import me.messageofdeath.Commands.NewCommandSystem.CommandManager;
import me.messageofdeath.KFCPvP.Commands.infchestCommand;
import me.messageofdeath.KFCPvP.Commands.kfcpvpCommand;
import me.messageofdeath.KFCPvP.Commands.statsCommand;
import me.messageofdeath.KFCPvP.Commands.voteCommand;
import me.messageofdeath.KFCPvP.Database.Database;
import me.messageofdeath.KFCPvP.Listener.playerListener;
import me.messageofdeath.KFCPvP.Listener.signListener;
import me.messageofdeath.KFCPvP.Listener.voteListener;
import me.messageofdeath.KFCPvP.Timer.PluginTimer;

import org.bukkit.plugin.java.JavaPlugin;

public class KFCPvP extends JavaPlugin {
	
	public static KFCPvP instance;

	@Override
	public void onEnable() {
		instance = this;
		//Configuration
		Database.initConfiguration();
		Database.loadConfiguration();
		//Stats Database (Does not load entire database to prevent overload)
		Database.initStatDatabase();
		//Item Database
		Database.initItemDatabase();
		Database.loadItemDatabase();
		//Arena Database
		Database.initArenaDatabase();
		Database.loadArenaDatabase();
		//World Database
		Database.initWorldDatabase();
		Database.loadWorldDatabase();
		
		//Events
		getServer().getPluginManager().registerEvents(new signListener(), this);
		getServer().getPluginManager().registerEvents(new playerListener(), this);
		if(getServer().getPluginManager().isPluginEnabled("Votifier"))
			getServer().getPluginManager().registerEvents(new voteListener(), this);
		
		//Start Global Arena Base Timer
        getServer().getScheduler().runTaskTimer(this, new PluginTimer(), 0L, 20L);
        
        //Commands
        CommandManager.register(this, new kfcpvpCommand());
        CommandManager.register(this, new voteCommand());
        CommandManager.register(this, new infchestCommand());
        CommandManager.register(this, new statsCommand());        
	}
	
	@Override
	public void onDisable() {
		Database.saveStatDatabase();
	}
}
