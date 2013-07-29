package me.messageofdeath.KFCPvP.Timer;

import me.messageofdeath.KFCPvP.Utils.Arenas.Arena;
import me.messageofdeath.KFCPvP.Utils.Arenas.Arenas;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

import org.bukkit.ChatColor;

public class Timer implements Runnable {
    
    @Override
    public void run() {
        for(Arena arena : Arenas.getArenas()) {
            int seconds = arena.getSeconds();
            if(arena.isGameInProgress()) {
            	for(String name : arena.getPlayers())
            		PlayerStats.getPlayerStats(name).getStat(StatType.PlayingTime).addAmount(1);
                if(seconds == 60*5)
                    arena.sendMessage(ChatColor.GOLD + "5 minutes left!");
                if(seconds == 60*2)
                    arena.sendMessage(ChatColor.GOLD + "2 minutes left!");
                if(seconds == 60)
                    arena.sendMessage(ChatColor.GOLD + "1 minute left!");
                if(seconds == 30)
                    arena.sendMessage(ChatColor.GOLD + "30 seconds left!");
                if(seconds < 11 && seconds > 1)
                    arena.sendMessage(ChatColor.GOLD + "" + seconds + " seconds left!");
                if(seconds == 1)
                    arena.sendMessage(ChatColor.GOLD + "1 second left!");
                if(seconds == 0) {
                    //arena.sendPlayersToLobby();
                    arena.setGameInProgress(false);
                    arena.setIsInLobby(true);
                    arena.setSeconds(60*2);
                    //arena.changeMap();
                }else
                    arena.setSeconds(seconds-1);
            }
            else if(arena.isInLobby()) {
                if(seconds == 60 *2)
                    arena.sendMessage(ChatColor.GOLD + "2 minutes until game starts!");
                if(seconds == 60)
                    arena.sendMessage(ChatColor.GOLD + "1 minute until game starts!");
                if(seconds == 30)
                    arena.sendMessage(ChatColor.GOLD + "30 seconds left!");
                if(seconds < 11 && seconds > 1)
                    arena.sendMessage(ChatColor.GOLD + "" + seconds + " seconds left!");
                if(seconds == 1)
                    arena.sendMessage(ChatColor.GOLD + "1 second left!");
                if(seconds == 0) {
                    if(arena.getPlayers().size() >= arena.getMinPlayers()) {
                        arena.sendMessage(ChatColor.GOLD + "The Game has started!");
                        arena.setIsInLobby(false);
                        arena.setGameInProgress(true);
                        //arena.startGame();
                        arena.setSeconds(60 * 10);
                    }else{
                        arena.sendMessage(ChatColor.DARK_RED + "Not enough players to start!");
                        arena.setSeconds(60 * 2);
                    }
                }else
                	arena.setSeconds(seconds-1);
            }
        }
    }
}