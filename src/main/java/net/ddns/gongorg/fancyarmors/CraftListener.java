package net.ddns.gongorg.fancyarmors;

import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;

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
	if (result != null) {
	    plugin.log.debug("Result durability:" + result.getDurability());
	}
    }
}
