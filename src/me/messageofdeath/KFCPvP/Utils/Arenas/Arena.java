package me.messageofdeath.KFCPvP.Utils.Arenas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Database.Database;
import me.messageofdeath.KFCPvP.Timer.ArenaTimer;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;
import me.messageofdeath.KFCPvP.Utils.WorldManager.World;
import me.messageofdeath.KFCPvP.Utils.WorldManager.WorldManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * 
 * @author messageofDEATH
 */

public class Arena {
    
    private final String name, prefix;
    private int maxPlayers, minPlayers, seconds;
    private ArenaStatus arenaStatus;
    private Location lobby;
    private World world;
    private ArrayList<String> players, winner;
    
    public Arena(String name, Location lobby, int minPlayers, int maxPlayers) {
        this.name = name;
        this.lobby = lobby;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.seconds = 60*2;//Start Lobby Time
        this.prefix = ChatColor.translateAlternateColorCodes('&', "&8[&4K&eF&4C&c&f-&4P&fv&4P&8]&6");
        this.arenaStatus = ArenaStatus.inLobby;
        this.players = new ArrayList<String>();
        this.world = null;
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
        if(!hasPlayer(name)) {
            this.players.add(name);
            if(!PlayerStats.containsPlayerStats(name))
            	PlayerStats.createPlayerStats(name);
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
    	for(String name : getPlayers())
    		stat.add(PlayerStats.getPlayerStats(name));
        return stat;
    }
    
    public void sendMessage(String msg) {
        for(String player : this.getPlayers()) {
        	if(this.isOnline(player))
        		KFCPvP.instance.getServer().getPlayer(player).sendMessage(prefix + " " + ChatColor.translateAlternateColorCodes('&',msg));
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
    
    public void sendToLobby() {
    	for(String player : getPlayers()) {
    		if(isOnline(player))
    			Bukkit.getPlayer(player).teleport(this.lobby);
    	}
    }
    
    public void sendToLobby(String name) {
    	if(isOnline(name))
    		Bukkit.getPlayer(name).teleport(this.lobby);
    }
    
    //****************** World *******************
   
    public World getWorld() {
    	return this.world;
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
    
    public void changeMap(String map) {//TODO Implement world manager
    	WorldManager.unloadWorld(getWorld());
    	WorldManager.loadWorld(WorldManager.getWorld(map));
    	this.world = WorldManager.getWorld(map);
    }
    
    public ArrayList<String> getWinner() {
    	ArrayList<PlayerStats> stat = getPlayersStats();
    	ArrayList<String> newStats = new ArrayList<String>();
    	Collections.sort(stat, new Comparator<PlayerStats>() {
    		
			@Override
			public int compare(PlayerStats stats1, PlayerStats stats2) {
				return (int) (stats1.getStat(StatType.Kills).getAmountChanged() - stats2.getStat(StatType.Kills).getAmountChanged());
			}    		
    	});
    	int lastInt = (int) stat.get(0).getStat(StatType.Kills).getAmountChanged();
    	
    	for(int i = 0; i < stat.size(); i++) {
    		if(lastInt == stat.get(i).getStat(StatType.Kills).getAmountChanged())
    			newStats.add(stat.get(i).getName());
    		else
    			break;
    	}
    		
		return newStats;
    }
    
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
    	
    	this.changeMap(/*TODO Implement world manager and voting for worlds*/"");
    	
    	for(String name : this.getPlayers()) {
    		Player player = Bukkit.getPlayer(name);
    		player.setGameMode(GameMode.ADVENTURE);
    		getWorld().randomSpawn(name);
    	}
    }
    
    public void queueStopGame(ArrayList<String> winner, boolean force, boolean notEnoughPlayers) {
    	this.winner = winner;
    	this.setSeconds(5);
    	if(this.winner != null) {
    		if(!this.winner.isEmpty()) {
    			String win = winner.get(0) + ",";
    			if(winner.size() > 1) {
    				for(int i = 1; i < winner.size(); i++)
    					win += " " + winner.get(i) + ",";
    				win = win.substring(0, win.length() - 1);
    				win += " all won the game";
    			}else
    				win = winner.get(0) + " has won the game!";
    			for(int i = 6; i > -1; i--)
    				this.sendMessage(win);
    		}
    	}
    	if(notEnoughPlayers)
    		this.sendMessage("This arena does not contain enough players to continue.");
    	if(force)
    		this.sendMessage("This arena was forced closed!");
    }
    
    public void stopGame() {
    	if(this.winner != null)
    		for(int i = 6; i > -1; i--)
    			this.sendMessage(winner + " has won the game!");
    	
    	this.setGameStatus(ArenaStatus.inLobby);
        this.setSeconds(60*2);
        Database.saveDatabase();
        this.sendToLobby();
    }
}