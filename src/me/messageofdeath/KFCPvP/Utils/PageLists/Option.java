package me.messageofdeath.KFCPvP.Utils.PageLists;

import org.bukkit.Bukkit;

public class Option {
	
	private String text, perm;

	public Option(String text, String perm) {
		this.text = text;
		this.perm = perm;
	}
	
	public boolean hasPermission(String name) {
		return Bukkit.getPlayer(name).hasPermission(this.perm) || this.perm.equalsIgnoreCase("noPerm") 
				|| this.perm.equalsIgnoreCase("") || this.perm.equalsIgnoreCase("noPermission");
	}
	
	public String getText() {
		return this.text;
	}
}
