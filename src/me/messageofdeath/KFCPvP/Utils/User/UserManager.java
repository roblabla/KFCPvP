package me.messageofdeath.KFCPvP.Utils.User;

import java.util.HashMap;

import org.bukkit.Bukkit;

public class UserManager {
	
	public UserManager() {
		users = new HashMap<String, User>();
	}

	protected HashMap<String, User> users;
	
	public void storePlayerStats(User user) {
		users.put(user.getName(), user);
	}
	
	public boolean containsUser(String name) {
		return users.containsKey(name);
	}
	
	public User getUser(String name) {
		return users.get(name);
	}
	
	public void createUser(String name) {
		try {
			users.put(name, new User(Bukkit.getPlayer(name)));
		} catch (NullPointerException e) {
			Bukkit.getLogger().severe("Player does not exist exception!");
		}
	}
}
