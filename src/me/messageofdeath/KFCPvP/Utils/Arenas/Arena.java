package me.messageofdeath.KFCPvP.Utils.Arenas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Timer.ArenaTimer;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;
import me.messageofdeath.KFCPvP.Utils.WorldManager.World;
import me.messageofdeath.KFCPvP.Utils.WorldManager.WorldManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * 
 * @author messageofDEATH
 */

public class Arena {
    
    private final String name, prefix;
    private String win;
    private boolean forceEnd, notEnoughPlayers;
    private int maxPlayers, minPlayers, seconds;
    private ArenaStatus arenaStatus;
    private World world;
    private ArrayList<String> players, winner;
    
    public Arena(String name, int minPlayers, int maxPlayers) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.seconds = 60*2;//Start Lobby Time
        this.prefix = ChatColor.translateAlternateColorCodes('&', "&8[&4K&eF&4C&c&f-&4P&fv&4P&8]&6");
        this.arenaStatus = ArenaStatus.inLobby;
        this.players = new ArrayList<String>();
        this.world = null;
        this.forceEnd = false;
        this.notEnoughPlayers = false;
    }
    
    public String getName() {
        return this.name;
    }
    
    //***************** Players ***********************
    
    public int getMaxPlayers() {
        return this.maxPlayers;
    }
    
    public int getMinPlayers() {
        return this.minPlayers;
    }
    
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    
    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }
    
    public void addPlayer(String name) {
        if(!this.hasPlayer(name)) {
            this.players.add(name);
            if(!KFCPvP.getInstance().getStatManager().containsPlayerStats(name))
            	KFCPvP.getInstance().getStatManager().createPlayerStats(name);
        }
    }
    
    public boolean hasPlayer(String name) {
        return this.players.contains(name);
    }
    
    public ArrayList<String> getPlayers() {
        return this.players;
    }
    
    public ArrayList<PlayerStats> getPlayersStats() {
    	ArrayList<PlayerStats> stat = new ArrayList<PlayerStats>();
    	for(String name : this.getPlayers())
    		stat.add(KFCPvP.getInstance().getStatManager().getPlayerStats(name));
        return stat;
    }
    
    public void sendMessage(String msg) {
        for(String player : this.getPlayers()) {
        	if(this.isOnline(player))
        		KFCPvP.getInstance().getServer().getPlayer(player).sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&',msg));
        }
        ArenaTimer.sender.sendMessage("[Arena Debug] ("+this.getName()+") " + prefix + " " + ChatColor.translateAlternateColorCodes('&', msg));
    }
    
    private boolean isOnline(String name) {
    	for(Player player : Bukkit.getOnlinePlayers())
    		if(player.getName().equalsIgnoreCase(name))
    			return true;
    	return false;
    }
    
    public ArrayList<String> getAllOnline() {
    	ArrayList<String> players = new ArrayList<String>();
    	for(Player player : Bukkit.getOnlinePlayers())
    		for(String name : this.getPlayers())
    			if(player.getName().equalsIgnoreCase(name))
    				players.add(name);
    	return players;
    }
    
    //****************** World *******************
   
    public World getWorld() {
    	return this.world;
    }
    
    public void setWorld(World world) {
    	this.world = world;
    }
    
    //****************** Game ***********************
    
    public ArenaStatus getGameStatus() {
    	return this.arenaStatus;
    }
    
    public void setGameStatus(ArenaStatus arenaStatus) {
    	this.arenaStatus = arenaStatus;
    }
    
    public int getSeconds() {
        return this.seconds;
    }
    
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    
  //*********************** Map Change ***********************
    
    public void changeMap(String map) {
    	WorldManager worldManager = KFCPvP.getInstance().getWorldManager();
    	if(worldManager.isLoaded(getWorld())) {
    		this.getWorld().removeUse(this);
    		worldManager.unloadWorld(getWorld());
    	}
    	this.setWorld(worldManager.getWorld(map));
    	if(this.getWorld() != null) {
	    	if(!worldManager.isLoaded(getWorld())) {
	    		worldManager.loadWorld(worldManager.getWorld(map));
	    		this.getWorld().addUse(this);
	    	}
    	}else{
    		//Report error that world does not exist
    	}
    }
    
  //*********************** Winner ***********************
    
    public ArrayList<String> getWinner() {//TODO Fix in fashion of Points
    	ArrayList<PlayerStats> stat = getPlayersStats();
    	ArrayList<String> tempStats = new ArrayList<String>(), newStats = new ArrayList<String>();
    	Collections.sort(stat, new Comparator<PlayerStats>() {
    		
			@Override
			public int compare(PlayerStats stats1, PlayerStats stats2) {
				return (int) (stats1.getStat(StatType.Kills).getChanged() - stats2.getStat(StatType.Kills).getChanged());
			}    		
    	});
    	int lastInt = (int) stat.get(0).getStat(StatType.Kills).getChanged();
    	
    	for(int i = 0; i < stat.size(); i++) {
    		if(lastInt == stat.get(i).getStat(StatType.Kills).getChanged())
    			tempStats.add(stat.get(i).getName());
    		else
    			break;
    	}
    		
		return newStats;
    }
    
  //*********************** Start Game ***********************
    
    public void queueStartGame() {
    	 if(this.getPlayers().size() >= this.getMinPlayers()) {
    		 this.sendMessage(ChatColor.GOLD + "The Game has started!");
    		 this.setGameStatus(ArenaStatus.inGame);
             this.startGame();
    		 this.setSeconds(60 * 10);
         }else{
        	 this.sendMessage(ChatColor.DARK_RED + "Not enough players to start!");
             this.setSeconds(60 * 2);
         }
    }
    
    public void startGame() {//TODO Do start game
    	
    	this.changeMap(/*TODO Implement world manager and voting for worlds*/""/*VoteManager.getNextMap(this)*/);
    	
    	for(String name : this.getPlayers()) {
    		Player player = Bukkit.getPlayer(name);
    		player.setGameMode(GameMode.ADVENTURE);
    		//getWorld().randomSpawn(name);
    	}
    }
    
    //*********************** Stop Game ***********************
    
    public void queueStopGame(ArrayList<String> winner, boolean force, boolean notEnoughPlayers) {
    	this.winner = winner;
    	this.setSeconds(5);
    	this.setGameStatus(ArenaStatus.pendingEnd);
    	if(this.winner != null) {
    		if(!this.winner.isEmpty()) {
    			String win = ChatColor.GRAY + "(" + ChatColor.GOLD + winner.get(0) + ChatColor.DARK_GRAY + ",";
    			if(this.winner.size() > 1) {
    				
    				for(int i = 1; i < winner.size(); i++)
    					win += " " + ChatColor.GOLD + winner.get(i) + ChatColor.DARK_GRAY + ",";
    				win = win.substring(0, win.length() - 1);
    				win += ChatColor.GRAY + ")" + ChatColor.GOLD + " all won the game";
    			}else{
    				win = win.substring(0, win.length() - 1);
    				win += ChatColor.GRAY + ")" + ChatColor.GOLD + " has won the game!";
    			}
    			this.win = win;
    			for(int i = 6; i > -1; i--)
    				this.sendMessage(win);
    		}
    	}
    	if(notEnoughPlayers)
    		this.notEnoughPlayers = notEnoughPlayers;
    	if(force)
    		this.forceEnd = force;
    }
    
    public void stopGame() {
    	if(this.win != null)
    		for(int i = 6; i > -1; i--)
    			this.sendMessage(this.win);
    	
    	if(this.notEnoughPlayers)
    		this.sendMessage("This arena does not contain enough players to continue.");
    	if(this.forceEnd)
    		this.sendMessage("This arena was forced closed!");
    	this.setGameStatus(ArenaStatus.inLobby);
        this.setSeconds(60*2);
        KFCPvP.getInstance().getStatDatabase().saveDatabase();
        //this.world.sendToLobby(this);
    }
}