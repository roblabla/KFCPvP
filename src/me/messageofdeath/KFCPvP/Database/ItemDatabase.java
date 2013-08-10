package me.messageofdeath.KFCPvP.Database;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.messageofdeath.Database.YamlDatabase;
import me.messageofdeath.KFCPvP.KFCPvP;

public class ItemDatabase {

	private static HashMap<String, ItemStack> itemNames;
	
	private static YamlDatabase items;
	
	protected static void initItemDatabase() {
		items = new YamlDatabase(KFCPvP.instance, "items");
		items.onStartUp();
		itemNames = new HashMap<String, ItemStack>();
	}
	
	protected static void loadItemDatabase() {
		for(String key : items.getSection("")) {
			String[] value = items.getString(key, "0,0").split(",");
			if(!itemNames.containsKey(key))
				itemNames.put(key, new ItemStack(Material.getMaterial(value[0]), 1, Short.parseShort(value[1])));
		}
	}
	
	public static ItemStack getMaterial(String input) {
		if(input != null) {
			input = input.toUpperCase();
			Material material = null;
			if(!StringUtils.isNumeric(input)) {
				String mat = String.valueOf(input);
				if(mat.contains(":")) {
					String[] split = mat.split(":");
					if(!StringUtils.isNumeric(split[1])) {
						return null;
					}
					if(!StringUtils.isNumeric(split[0])) {
						// diamondpick:1   STRING
						if(itemNames.containsKey(split[0]))
							return new ItemStack(itemNames.get(split[0]).getType(), 1, Short.parseShort(split[1]));
						else
							material = Material.getMaterial(split[0]);
						return new ItemStack(material, 1, Short.parseShort(split[1]));
					}else{
						// 1:1 INT
						material = Material.getMaterial(Integer.parseInt(split[0]));
						return new ItemStack(material, 1, Short.parseShort(split[1]));
					}
					
				}else{
					if(itemNames.containsKey(input))
						return new ItemStack(itemNames.get(input).getType(), 1, itemNames.get(input).getData().getData());
					else
						material = Material.getMaterial(input);
				}
			}else{
				material = Material.getMaterial(Integer.parseInt(String.valueOf(input)));
			}
			if(material != null)
				return new ItemStack(material, 1);
		}
		return null;
	}
}
