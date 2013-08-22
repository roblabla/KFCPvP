package me.messageofdeath.KFCPvP;

import java.util.ArrayList;

import me.messageofdeath.Commands.NewCommandSystem.CommandManager;
import me.messageofdeath.KFCPvP.Commands.infchestCommand;
import me.messageofdeath.KFCPvP.Commands.kfcpvpCommand;
import me.messageofdeath.KFCPvP.Commands.statsCommand;
import me.messageofdeath.KFCPvP.Commands.voteCommand;
import me.messageofdeath.KFCPvP.Database.DatabaseManager;
import me.messageofdeath.KFCPvP.Database.Configuration.Configuration;
import me.messageofdeath.KFCPvP.Database.Databases.ArenaDatabase;
import me.messageofdeath.KFCPvP.Database.Databases.ItemDatabase;
import me.messageofdeath.KFCPvP.Database.Databases.StatDatabase;
import me.messageofdeath.KFCPvP.Database.Databases.WorldDatabase;
import me.messageofdeath.KFCPvP.Database.Types.MySQL;
import me.messageofdeath.KFCPvP.Listener.playerListener;
import me.messageofdeath.KFCPvP.Listener.signListener;
import me.messageofdeath.KFCPvP.Listener.voteListener;
import me.messageofdeath.KFCPvP.Scoreboard.ScoreboardManager;
import me.messageofdeath.KFCPvP.Timer.PluginTimer;
import me.messageofdeath.KFCPvP.Utils.Arenas.Arena;
import me.messageofdeath.KFCPvP.Utils.Arenas.ArenaManager;
import me.messageofdeath.KFCPvP.Utils.Stats.StatsManager;
import me.messageofdeath.KFCPvP.Utils.User.UserManager;
import me.messageofdeath.KFCPvP.Utils.VoteManager.VoteManager;
import me.messageofdeath.KFCPvP.Utils.WorldManager.WorldManager;

import org.bukkit.plugin.java.JavaPlugin;

public class KFCPvP extends JavaPlugin {
	
	private static KFCPvP instance;
	
	//**************** Managers *****************
	
	private StatsManager statManager;
	private DatabaseManager databaseManager;
	private UserManager userManager;
	private VoteManager voteManager;
	private WorldManager worldManager;
	private ArenaManager arenaManager;
	private ScoreboardManager scoreboardManager;
	
	//************** Databases *******************
	
	private StatDatabase statDatabase;
	private ArenaDatabase arenaDatabase;
	private ItemDatabase itemDatabase;
	private WorldDatabase worldDatabase;
	
	//************** Configuration **************
	
	private Configuration configuration;
	
	//************* Database Types **************
	
	private MySQL mysql;
	
	@Override
	public void onEnable() {
		instance = this;
		
		//***************** Initiation *********************
		
		// Managers
		this.statManager = new StatsManager();
		this.databaseManager = new DatabaseManager();
		this.userManager = new UserManager();
		this.voteManager = new VoteManager();
		this.worldManager = new WorldManager();
		this.arenaManager = new ArenaManager();
		this.scoreboardManager = new ScoreboardManager();
		
		// Databases
		this.statDatabase = new StatDatabase();
		this.arenaDatabase = new ArenaDatabase();
		this.itemDatabase = new ItemDatabase();
		this.worldDatabase = new WorldDatabase();
		
		// Database Types
		this.mysql = new MySQL();
		
		//Configuration
		this.configuration = new Configuration();
		
		//***************** Loading ************************
		
		// Configuration
		this.configuration.initConfiguration();
		this.configuration.loadConfiguration();
		// MySQL or Yaml loading
		this.databaseManager.loadDatabaseType();
		// Statistics Database (Does not load entire database to prevent unnessary use of RAM)
		this.statDatabase.initDatabase();
		// Item Database
		this.itemDatabase.initDatabase();
		this.itemDatabase.loadDatabase();
		// Arena Database
		this.arenaDatabase.initDatabase();
		this.arenaDatabase.loadDatabase();
		// World Database
		this.worldDatabase.initDatabase();
		this.worldDatabase.loadDatabase();
		
		// Events
		getServer().getPluginManager().registerEvents(new signListener(), this);
		getServer().getPluginManager().registerEvents(new playerListener(), this);
		if(getServer().getPluginManager().isPluginEnabled("Votifier"))
			getServer().getPluginManager().registerEvents(new voteListener(), this);
		
		// Start Global Arena Base Timer
        getServer().getScheduler().runTaskTimer(this, new PluginTimer(), 0L, 20L);
        
        // Commands
        CommandManager.register(this, new kfcpvpCommand());
        CommandManager.register(this, new voteCommand());
        CommandManager.register(this, new infchestCommand());
        CommandManager.register(this, new statsCommand());
        
        //Testing Arenas
        
        this.arenaManager.register(new Arena("Test", 2, 6));
        this.arenaManager.getArena("Test").addPlayer("messageofdeath");
        this.arenaManager.getArena("Test").addPlayer("michaelv1");
        
        super.getServer().getScheduler().runTaskLater(this, new Runnable() {
        	@Override
        	public void run() {
        		ArrayList<String> winner = new ArrayList<String>();
                winner.add("messageofdeath");
                KFCPvP.getInstance().arenaManager.getArena("Test").queueStopGame(winner, true, false);
        	}
        }, 20L*60L*3);
	}
	
	@Override
	public void onDisable() {
		this.statDatabase.saveDatabase();
	}
	
	//******************* Managers ******************
	
	public StatsManager getStatManager() {
		return this.statManager;
	}
	
	public DatabaseManager getDatabaseManager() {
		return this.databaseManager;
	}
	
	public UserManager getUserManager() {
		return this.userManager;
	}
	
	public VoteManager getVoteManager() {
		return this.voteManager;
	}
	
	public WorldManager getWorldManager() {
		return this.worldManager;
	}
	
	public ArenaManager getArenaManager() {
		return this.arenaManager;
	}
	
	public ScoreboardManager getScoreBoardManager() {
		return this.scoreboardManager;
	}
	
	//******************* Databases ******************
	
	public StatDatabase getStatDatabase() {
		return this.statDatabase;
	}
	
	public ArenaDatabase getArenaDatabase() {
		return this.arenaDatabase;
	}
	
	public ItemDatabase getItemDatabase() {
		return this.itemDatabase;
	}
	
	public WorldDatabase getWorldDatabase() {
		return this.worldDatabase;
	}
	
	//****************** Configuration *********************
	
	public Configuration getConfiguration() {
		return this.configuration;
	}
	
	//*************** Database Types *****************
	
	public MySQL getMySQL() {
		return this.mysql;
	}
	
	public static KFCPvP getInstance() {
		return instance;
	}
}
