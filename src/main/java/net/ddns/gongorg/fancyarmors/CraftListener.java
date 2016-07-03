package net.ddns.gongorg.fancyarmors;

import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;

public class CraftListener implements Listener {
    
    Plugin plugin;
    
    public CraftListener(Plugin p) {
	this.plugin = p;
    }

    @EventHandler
    public void onCraftItem(CraftItemEvent event) {
	plugin.log.debug("Item crafted.");
	Recipe r = event.getRecipe();
	boolean fancy = plugin.isFancyRecipe(r);
	plugin.log.debug("Is it a fancy recipe:" + fancy);
	ItemStack result = event.getInventory().getResult();
	if (fancy) {
	    ItemMeta meta = result.getItemMeta();
	    String itemText;
	    switch (result.getType()) {
	    case LEATHER_BOOTS:
		itemText = plugin.messages.getString("fancy_boots");
		break;
	    case LEATHER_LEGGINGS:
		itemText = plugin.messages.getString("fancy_leggings");
		break;
	    case LEATHER_CHESTPLATE:
		itemText = plugin.messages.getString("fancy_chestplate");
		break;
	    case LEATHER_HELMET:
		itemText = plugin.messages.getString("fancy_helmet");
		break;
	    default:
		itemText = "Whaaaaat";
	    }
	    meta.setDisplayName(ChatColor.RED + itemText);
		//String[] lore = new String[2];
		//lore[0] = "Taquetep";
		//lore[1] = "ario!";
		//meta.setLore(java.util.Arrays.asList(lore));
	    result.setItemMeta(meta);
	    if (event.getWhoClicked() instanceof Player) {
		Player player = (Player) event.getWhoClicked();
		String craftText = plugin.messages.getString("crafted_fancy_item");
		player.sendMessage(ChatColor.YELLOW + "*** " + craftText + " " + itemText + " ***");
	    }
	}
    }
}
