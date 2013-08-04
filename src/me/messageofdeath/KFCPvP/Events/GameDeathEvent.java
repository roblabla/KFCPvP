package me.messageofdeath.KFCPvP.Events;

import me.messageofdeath.KFCPvP.Utils.Arenas.Arena;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameDeathEvent extends Event {
	
	private Player victim;
	private Entity attacker;
	private Arena arena;

	private static final HandlerList handlers = new HandlerList();

	public GameDeathEvent(Player victim, Entity attacker, Arena arena) {
		this.victim = victim;
		this.attacker = attacker;
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
	
	public Player getVictim() {
		return victim;
	}
	
	public Entity getAttacker() {
		return attacker;
	}
}
