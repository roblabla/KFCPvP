package me.messageofdeath.KFCPvP.Timer;

import me.messageofdeath.KFCPvP.Utils.Arenas.Arena;
import me.messageofdeath.KFCPvP.Utils.Arenas.ArenaStatus;
import me.messageofdeath.KFCPvP.Utils.Arenas.Arenas;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class ArenaTimer {
	
	public static ConsoleCommandSender sender = null;
    
    public static void run() {
    	if(sender == null)
    		sender = Bukkit.getConsoleSender();
        for(Arena arena : Arenas.getArenas()) {
            int seconds = arena.getSeconds();
            if(arena.getGameStatus() == ArenaStatus.inGame) {
            	for(String name : arena.getPlayers())
            		PlayerStats.getPlayerStats(name).getStat(StatType.PlayingTime).addAmount(1);
            	if(seconds == 60)
            		arena.sendMessage("&7(&e1&7) &6minute until game ends!");
            	else if(seconds%60 == 0)
            		arena.sendMessage("&7(&e"+seconds/60+"&7) &6minutes until game ends!");
            	if(seconds == 30)
                    arena.sendMessage(ChatColor.GOLD + "&7(&e30&7) &6seconds until game ends!");
            	if(seconds < 11 && seconds > 5)
                    arena.sendMessage(ChatColor.GOLD + "&7(&e"+seconds+"&7) &6second until game ends!");
            	if(seconds == 5)
            		arena.queueStopGame(arena.getWinner(), false, false);
                else
                    arena.setSeconds(seconds-1);
            }
            else if(arena.getGameStatus() == ArenaStatus.pendingEnd) {
            	for(String name : arena.getPlayers())
            		PlayerStats.getPlayerStats(name).getStat(StatType.PlayingTime).addAmount(1);
            	if(seconds != 1 || seconds != 0)
                    arena.sendMessage(ChatColor.GOLD + "&7(&e"+seconds+"&7) &6seconds until game ends!");
            	if(seconds == 1)
            		arena.sendMessage(ChatColor.GOLD + "&7(&e1&7) &6second until game ends!");
            	if(seconds == 0)
            		arena.stopGame();
            	else
            		arena.setSeconds(seconds-1);
            }
            else if(arena.getGameStatus() == ArenaStatus.inLobby) {
            	if(seconds == 60)
            		arena.sendMessage("&7(&e1&7) &6minute until game starts!");
            	else if(seconds % 60 == 0)
            		arena.sendMessage("&7(&e"+seconds/60+"&7) &6minutes until game starts!");
            	if(seconds == 30)
                    arena.sendMessage(ChatColor.GOLD + "&7(&e30&7) &6seconds until game starts!");
            	if(seconds < 11 && seconds > 1)
                    arena.sendMessage(ChatColor.GOLD + "&7(&e"+seconds+"&7) &6seconds until game starts!");
            	if(seconds == 1)
                    arena.sendMessage(ChatColor.GOLD + "&7(&e1&7) &6second until game starts!");
            	if(seconds == 0)
            		arena.queueStartGame();
                else
                	arena.setSeconds(seconds-1);
            }
        }
    }
}