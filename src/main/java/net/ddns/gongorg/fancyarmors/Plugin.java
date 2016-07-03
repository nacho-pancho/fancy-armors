package net.ddns.gongorg.fancyarmors;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import minecraft.spigot.community.michel_0.api.*;

public class Plugin extends org.bukkit.plugin.java.JavaPlugin {

    /**
     * Logging component.
     */
    public Logger log;
    private String pluginName;
    private String pluginVersion;
    private static final String permissionNode = "fancyarmors.";
    ResourceBundle messages;
    private List<Recipe> fancyRecipes = new ArrayList<Recipe>();

    public void onLoad() {
        org.bukkit.plugin.PluginDescriptionFile pdfFile = this.getDescription();
        pluginName = pdfFile.getName();
        pluginVersion = pdfFile.getVersion();
    }

    void configurePlugin() {
        FileConfiguration conf = getConfig();
        boolean debugMode = conf.getBoolean("debug", false);
        log = new Logger(pluginName + " v" + pluginVersion, debugMode);
        log.info("Debugging is set to " + debugMode);
        conf.get("locale_country", "UY");
        final String language = conf.getString("locale_language", "es");
        final String country = conf.getString("locale_country", "");
        log.info("Locale set to " + language + " " + country);
        final Locale locale = new Locale(language, country);
        messages = ResourceBundle.getBundle("Messages", locale);
    }

    public void onEnable() {
        configurePlugin();
        org.bukkit.plugin.PluginManager pm = getServer().getPluginManager();
        getCommand("fancy").setExecutor(new CommandExecutor(this));
	pm.registerEvents(new CraftListener(this), this);	
	pm.registerEvents(new DamageListener(this), this);	
	addRecipes();
    }

    public void onDisable() {
    }

    
    boolean hasPermission(Player player, String permission) {
        return player.hasPermission(permissionNode + permission);
    }

    void addRecipes() {
	log.debug("Adding recipes.");
	//
	// ARMOR BONUSES
	//
	//         GOLD  IRON  CHAIN  DIAMOND
	// HELMET    2     2     2       3
        // CHEST     5     5     6       8
        // LEGS      3     4     5       6
        // BOOTS     1     1     2       3
	//
	ItemStack item,item2;
	ShapelessRecipe recipe;
	//
	// main class from Item Attributes API
	//
	AttributeModifier armor,thoughness;
	ItemAttributes attr;
	//
	// GOLD + LEATHER parts
	//
	// modify attributes so that leather parts have same bonuses as
	// golden ones
	//
	log.debug("Adding gold stuff.");
	addRecipe(Material.LEATHER_HELMET,Material.GOLD_HELMET,             Slot.HEAD ,2.0, 0.0);
	addRecipe(Material.LEATHER_CHESTPLATE,Material.GOLD_CHESTPLATE,     Slot.CHEST,5.0, 0.0);
	addRecipe(Material.LEATHER_LEGGINGS,Material.GOLD_LEGGINGS,         Slot.LEGS ,3.0, 0.0);
	addRecipe(Material.LEATHER_BOOTS,Material.GOLD_BOOTS,               Slot.FEET ,1.0, 0.0);

	addRecipe(Material.LEATHER_HELMET,Material.CHAINMAIL_HELMET,        Slot.HEAD ,2.0, 0.0);
	addRecipe(Material.LEATHER_CHESTPLATE,Material.CHAINMAIL_CHESTPLATE,Slot.CHEST,5.0, 0.0);
	addRecipe(Material.LEATHER_LEGGINGS,Material.CHAINMAIL_LEGGINGS,    Slot.LEGS ,4.0, 0.0);
	addRecipe(Material.LEATHER_BOOTS,Material.CHAINMAIL_BOOTS,          Slot.FEET ,1.0, 0.0);

	addRecipe(Material.LEATHER_HELMET,Material.IRON_HELMET,             Slot.HEAD ,2.0, 0.0);
	addRecipe(Material.LEATHER_CHESTPLATE,Material.IRON_CHESTPLATE,     Slot.CHEST,6.0, 0.0);
	addRecipe(Material.LEATHER_LEGGINGS,Material.IRON_LEGGINGS,         Slot.LEGS ,5.0, 0.0);
	addRecipe(Material.LEATHER_BOOTS,Material.IRON_BOOTS,               Slot.FEET ,2.0, 0.0);

	addRecipe(Material.LEATHER_HELMET,Material.DIAMOND_HELMET,          Slot.HEAD ,3.0, 2.0);
	addRecipe(Material.LEATHER_CHESTPLATE,Material.DIAMOND_CHESTPLATE,  Slot.CHEST,8.0, 2.0);
	addRecipe(Material.LEATHER_LEGGINGS,Material.DIAMOND_LEGGINGS,      Slot.LEGS ,6.0, 2.0);
	addRecipe(Material.LEATHER_BOOTS,Material.DIAMOND_BOOTS,            Slot.FEET ,3.0, 2.0);

    }

    private void addRecipe(Material targetMaterial, Material sourceMaterial, Slot slot, double armor, double though) {
	ItemStack item = new ItemStack(targetMaterial);
	ItemAttributes attr  = new ItemAttributes();
	AttributeModifier aux = new AttributeModifier(Attribute.ARMOR, "generic.armor", slot, 0, armor, UUID.randomUUID());
	attr.addModifier(aux);
	aux = new AttributeModifier(Attribute.ARMOR_THOUGHNESS, "generic.armorThoughness", slot, 0, though, UUID.randomUUID());
	attr.addModifier(aux);
	item = attr.apply(item);
	ShapelessRecipe recipe = new ShapelessRecipe(item);
	recipe.addIngredient(targetMaterial);
	recipe.addIngredient(sourceMaterial);
	getServer().addRecipe(recipe);
	fancyRecipes.add(recipe);
    }

    boolean isFancyRecipe(Recipe r) {
	if (!(r instanceof ShapelessRecipe)) return false;
	List<ItemStack> ingredients = ((ShapelessRecipe)r).getIngredientList();
	for (Recipe cand: fancyRecipes) {
	    List<ItemStack> aux = ((ShapelessRecipe)cand).getIngredientList();
	    if (aux.containsAll(ingredients) && ingredients.containsAll(aux))
		return true;
	}
	return false;
    }
}
