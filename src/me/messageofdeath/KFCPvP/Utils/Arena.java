package me.messageofdeath.KFCPvP.Utils;

import java.util.ArrayList;
import me.messageofdeath.Blocks.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * 
 * @author messageofDEATH
 */

public class Arena {
    
    private final String name;
    private int maxPlayers, minPlayers, seconds;
    private boolean gameInProgress, isInLobby;
    private Location lobby;
    private ArrayList<Location> spawns;
    private ArrayList<String> players;
    private Cuboid cuboid;
    
    public Arena(String name, Location lobby, ArrayList<Location> spawns, Cuboid cuboid, int maxPlayers, int minPlayers) {
        this.name = name;
        this.lobby = lobby;
        this.spawns = spawns;
        this.cuboid = cuboid;
        this.maxPlayers = maxPlayers;
        this.minPlayers = minPlayers;
        this.seconds = 60*2;
        this.isInLobby = true;
        this.gameInProgress = false;
        this.players = new ArrayList<String>();
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
        if(!hasPlayer(name))
            this.players.add(name);
    }
    
    public boolean hasPlayer(String name) {
        return this.players.contains(name);
    }
    
    public void removePlayer(String name) {
        this.players.remove(name);
    }
    
    public ArrayList<String> getPlayers() {
        return this.players;
    }
    
    public void sendMessage(String msg) {
        for(String player : this.players) {
            if(isOnline(player))
                Bukkit.getPlayer(player).sendMessage(msg);
            else
                removePlayer(player);
        }
    }
    
    private boolean isOnline(String name) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
    
    //****************** Spawns *********************
    
    //Where they get the infinite chests
    public Location getLobby() {
        return this.lobby;
    }
    
    public ArrayList<Location> getSpawns() {
        return this.spawns;
    }
    
    public void addSpawn(Location loc) {
        this.spawns.add(loc);
    }
    
    //****************** Boundaries *******************
   
    public Cuboid getCuboid() {
        return this.cuboid;
    }
    
    //****************** Game ***********************
    
    public boolean isGameInProgress() {
        return this.gameInProgress;
    }
    
    public void setGameInProgress(boolean set) {
        this.gameInProgress = set;
    }
    
    public boolean isInLobby() {
        return this.isInLobby;
    }
    
    public void setIsInLobby(boolean set) {
        this.isInLobby = set;
    }
    
    public int getSeconds() {
        return this.seconds;
    }
    
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}