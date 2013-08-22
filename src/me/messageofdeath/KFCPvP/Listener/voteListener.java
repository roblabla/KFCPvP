package me.messageofdeath.KFCPvP.Listener;

import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Utils.User.User;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.vexsoftware.votifier.model.VotifierEvent;

public class voteListener implements Listener {
	
	@EventHandler
	public void onVote(VotifierEvent event) {
		User user = KFCPvP.getInstance().getUserManager().getUser(event.getVote().getUsername());
		KFCPvP.getInstance().getStatManager().createPlayerStats(event.getVote().getUsername());
		/*PlayerStats.getPlayerStats(event.getVote().getUsername()).getStat(StatType.ExtraPoints)
			.addAmount((Integer)ConfigSettings.VotePointAddition.getSetting());*/
		//TODO Add Extra Points to this nigga
		KFCPvP.getInstance().getStatDatabase().saveDatabase();
	}
}
