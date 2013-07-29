package me.messageofdeath.KFCPvP.Listener;

import me.messageofdeath.KFCPvP.Utils.Permissions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class signListener implements Listener {
    
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if(event.getLine(0).equalsIgnoreCase("[KFCPvP]")) {
            if(Permissions.hasPermission(event.getPlayer(), Permissions.createSign)) {
                event.setLine(0, "[KFCPvP]");
                
            }else{
                event.getPlayer().sendMessage(Permissions.getNoPermPrefix() + "You do not have permission to make a sign!");
                for(int i = 0; i < 4; i++)
                    event.setLine(i, "No Permission");
            }
        }
    }
}