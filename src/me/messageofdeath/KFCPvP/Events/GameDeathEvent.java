package me.messageofdeath.KFCPvP.Events;

import me.messageofdeath.KFCPvP.Utils.Arenas.Arena;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameDeathEvent extends Event {
	
	private Player player;
	private Arena arena;

	private static final HandlerList handlers = new HandlerList();

	public GameDeathEvent(Player player, Arena arena) {
		this.player = player;
		this.arena = arena;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	public Arena getArena() {
		return arena;
	}
	
	public Player getPlayer() {
		return player;
	}
}
