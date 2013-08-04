package me.messageofdeath.KFCPvP.Utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Permissions {
    
	//***************** Signs ********************
    createSign("kfcpvp.create.sign"),
    
    
    
    //**************** Utils *********************
    noPermission("noPerm");
    
    private String perm;
    Permissions(String perm) {
        this.perm = perm;
    }
    
    public String getPermission() {
        return perm;
    }
    
    //************************** Static *****************************
    
    private static String noPermPrefix = 
            ChatColor.BLACK + "[" + ChatColor.DARK_RED + "Error" + ChatColor.BLACK + "] " + ChatColor.DARK_GRAY;
    
    public static String getNoPermPrefix() {
        return noPermPrefix;
    }
    
    public static boolean hasPermission(Player player, Permissions perm) {
        return player.hasPermission(perm.getPermission());
    }
    
    public static boolean hasPermission(CommandSender sender, Permissions perm) {
        return sender.hasPermission(perm.getPermission());
    }
}