package me.messageofdeath.KFCPvP.Listener;

import me.messageofdeath.KFCPvP.Events.GameDeathEvent;
import me.messageofdeath.KFCPvP.Utils.Arenas.Arena;
import me.messageofdeath.KFCPvP.Utils.Arenas.Arenas;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerListener implements Listener {

	@EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	if(!PlayerStats.containsPlayerStats(event.getPlayer().getName())) {
    		PlayerStats player = new PlayerStats(event.getPlayer().getName());
    		PlayerStats.storePlayerStats(player);
    	}
    }
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		for(Arena arenas : Arenas.getArenas())
			if(arenas.hasPlayer(event.getPlayer().getName())) {
				arenas.removePlayer(event.getPlayer().getName());
				break;
			}
	}
	
	@EventHandler
	public void onGameDeath(GameDeathEvent event) {
		event.getArena().sendToLobby(event.getPlayer().getName());
		
		//TODO Add Death Note
	}
	
	@EventHandler
	public void onHealthChange(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player victim = (Player)event.getEntity();
			Arena arena = null;
			for(Arena a : Arenas.getArenas())
				if(a.hasPlayer(victim.getName())) {
					arena = a;
					break;
				}
			if(victim != null) {
				if(victim.getHealth() - event.getDamage() <= 0) {
					Bukkit.getPluginManager().callEvent(new GameDeathEvent(victim, arena));
					event.setCancelled(true);
				}
			}
		}
	}
}
