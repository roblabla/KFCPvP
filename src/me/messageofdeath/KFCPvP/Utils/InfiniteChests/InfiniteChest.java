package me.messageofdeath.KFCPvP.Utils.InfiniteChests;

import me.messageofdeath.Blocks.Vector;
import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Utils.WorldManager.World;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class InfiniteChest {
	
	//Private
	private ItemStack itemStack;
	private World world;
	private Vector loc;

	public InfiniteChest(String material, World world, Vector loc) {
		if(loc.getBlock().getType() != Material.CHEST  && loc.getBlock().getType() != Material.LOCKED_CHEST 
				&& loc.getBlock().getType() != Material.TRAPPED_CHEST) {
			throw new IllegalArgumentException("The location must be a chest!");
		}
		
		this.itemStack = KFCPvP.getInstance().getItemDatabase().getMaterial(material);
		this.world = world;
		this.loc = loc;
	}
	
	public ItemStack getMaterial() {
		return itemStack;
	}
	
	public World getWorld() {
		return world;
	}
	
	public Vector getVector() {
		return loc;
	}
}
