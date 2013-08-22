package me.messageofdeath.KFCPvP.Listener;

import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Events.GameDeathEvent;
import me.messageofdeath.KFCPvP.Utils.Arenas.Arena;
import me.messageofdeath.KFCPvP.Utils.Arenas.ArenaStatus;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerListener implements Listener {

	@EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	KFCPvP.getInstance().getStatManager().createPlayerStats(event.getPlayer().getName());
    }
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		for(Arena arenas : KFCPvP.getInstance().getArenaManager().getArenas())
			if(arenas.hasPlayer(event.getPlayer().getName())) {
				arenas.sendMessage(event.getPlayer().getName() + " has left the action!");
				if(arenas.getAllOnline().size() == 1) {
					arenas.queueStopGame(arenas.getWinner(), false, true);
				}
				break;
			}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		//TODO Implement Infinite Chests
	}
	
	//Points work by amountOfDamageDone*5 to get percentage
	@EventHandler
	public void onGameDeath(GameDeathEvent event) {
		//event.getArena().getWorld().sendToLobby(event.getVictim().getName());
		KFCPvP.getInstance().getStatManager().getPlayerStats(event.getVictim().getName()).getStat(StatType.Deaths).addAmount(1);
		if(event.getAttacker() != null) {
			if(event.getAttacker() instanceof Player) {
				Player attacker = (Player)event.getAttacker();
				KFCPvP.getInstance().getStatManager().getPlayerStats(attacker.getName()).getStat(StatType.Kills).addAmount(1);
				event.getArena().sendMessage(event.getVictim().getName() + " died by " + attacker.getName());
			}else{
				event.getArena().sendMessage(event.getVictim().getName() + " died by " + event.getAttacker().getType().getName().toLowerCase());
			}
		}
	}
	
	@EventHandler
	public void onHealthChange(EntityDamageByEntityEvent event) {
		if(event.getEntity() instanceof Player) {
			Player victim = (Player)event.getEntity();
			Arena arena = null;
			for(Arena a : KFCPvP.getInstance().getArenaManager().getArenas())
				if(a.hasPlayer(victim.getName())) {
					arena = a;
					break;
				}
			if(arena != null) {
				if(arena.getGameStatus() != ArenaStatus.inLobby) {
					if(victim.getHealth() - event.getDamage() <= 0) {
						Bukkit.getPluginManager().callEvent(new GameDeathEvent(victim, event.getDamager(), arena));
						event.setCancelled(true);
						victim.setFoodLevel(20);
						victim.setHealth(victim.getMaxHealth());
					}
				}else
					event.setCancelled(true);
			}
		}
	}
}
