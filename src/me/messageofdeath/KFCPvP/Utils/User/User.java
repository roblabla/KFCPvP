package me.messageofdeath.KFCPvP.Utils.User;

import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Utils.Stats.PlayerStats;
import me.messageofdeath.KFCPvP.Utils.Stats.Stat;
import me.messageofdeath.KFCPvP.Utils.Stats.StatType;

import org.bukkit.entity.Player;

public class User {

	private Player player;
	private PlayerStats playerStats;
	private UserStatus status;
	
	public User(Player player) {
		if(player != null) {
			this.player = player;
			this.playerStats = KFCPvP.getInstance().getStatManager().getPlayerStats(this.player.getName());
			this.status = UserStatus.Unavailable;
		}
	}
	
	public String getName() {
		return this.player.getName();
	}
	
	//******************** Status *************************
	
	public UserStatus getUserStatus() {
		return this.status;
	}
	
	public void setUserStatus(UserStatus status) {
		this.status = status;
	}
	
	//******************** Voting ************************
	
	//********************* Player Stats ************************

	public Stat getStat(StatType statType) {
		return this.playerStats.getStat(statType);
	}
}
