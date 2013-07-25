package me.messageofdeath.KFCPvP.Timer;

import me.messageofdeath.KFCPvP.Utils.Arena;
import me.messageofdeath.KFCPvP.Utils.Arenas;
import org.bukkit.ChatColor;

public class Timer implements Runnable {
    
    @Override
    public void run() {
        for(Arena arena : Arenas.getArenas()) {
            if(arena.isGameInProgress()) {
                int count = arena.getSeconds();
                
                if(count == 60*5)
                    arena.sendMessage(ChatColor.GOLD + "5 minutes left!");
                if(count == 60)
                    arena.sendMessage(ChatColor.GOLD + "1 minute left!");
                if(count == 30)
                    arena.sendMessage(ChatColor.GOLD + "30 seconds left!");
                if(count < 11 && count > 1)
                    arena.sendMessage(ChatColor.GOLD + "" + count + " seconds left!");
                if(count == 1)
                    arena.sendMessage(ChatColor.GOLD + "1 second left!");
                if(count == 0) {
                    //arena.sendPlayersToLobby();
                    arena.setGameInProgress(false);
                    arena.setIsInLobby(true);
                    arena.setSeconds(60*2);
                    //arena.changeMap();
                }
                
                arena.setSeconds(count--);
            }
            else if(arena.isInLobby()) {
                int count = arena.getSeconds();
                
                if(count == 60 *2)
                    arena.sendMessage(ChatColor.GOLD + "2 minutes until game starts!");
                if(count == 60)
                    arena.sendMessage(ChatColor.GOLD + "1 minute until game starts!");
                if(count == 30)
                    arena.sendMessage(ChatColor.GOLD + "30 seconds left!");
                if(count < 11 && count > 1)
                    arena.sendMessage(ChatColor.GOLD + "" + count + " seconds left!");
                if(count == 1)
                    arena.sendMessage(ChatColor.GOLD + "1 second left!");
                if(count == 0) {
                    if(arena.getPlayers().size() >= arena.getMinPlayers()) {
                        arena.sendMessage(ChatColor.GOLD + "The Game has started!");
                        arena.setIsInLobby(false);
                        arena.setGameInProgress(true);
                        arena.setSeconds(60 * 10 + 1);
                    }else{
                        arena.sendMessage(ChatColor.DARK_RED + "Not enough players to start!");
                        arena.setSeconds(60 * 2 + 1);
                    }
                }
                arena.setSeconds(count--);
            }
        }
    }
}