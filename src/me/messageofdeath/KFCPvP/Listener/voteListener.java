package me.messageofdeath.KFCPvP.Listener;

import me.messageofdeath.KFCPvP.Database.Configuration.ConfigSettings;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.vexsoftware.votifier.model.VotifierEvent;

public class voteListener implements Listener {
	
	@EventHandler
	public void onVote(VotifierEvent event) {
		PlayerStats.createPlayerStats(event.getVote().getUsername());
		PlayerStats.getPlayerStats(event.getVote().getUsername()).getStat(StatType.ExtraPoints)
			.addAmount((Integer)ConfigSettings.VotePointAddition.getSetting());
	}
}
