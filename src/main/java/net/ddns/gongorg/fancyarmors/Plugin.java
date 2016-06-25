package net.ddns.gongorg.fancyarmors;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;

import minecraft.spigot.community.michel_0.api.*;

//import org.bukkit.Location;
//import org.bukkit.Server;
//import org.bukkit.World;
//import org.bukkit.block.Block;
//import org.bukkit.configuration.file.FileConfiguration;
//import org.bukkit.entity.Player;

public class Plugin extends org.bukkit.plugin.java.JavaPlugin {

    /**
     * Logging component.
     */
    public Logger log;
    private String pluginName;
    private String pluginVersion;
    private static final String permissionNode = "fancyarmors.";
    private ResourceBundle i18nResource;


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
        i18nResource = ResourceBundle.getBundle("Messages", locale);
    }

    public void onEnable() {
        configurePlugin();
        org.bukkit.plugin.PluginManager pm = getServer().getPluginManager();
        getCommand("fancy").setExecutor(new CommandExecutor(this));
	addRecipes();
    }

    public void onDisable() {
    }

    
    boolean hasPermission(Player player, String permission) {
        return player.hasPermission(permissionNode + permission);
    }

    void addRecipes() {
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

	item = new ItemStack(Material.LEATHER_HELMET);
	item2 = new ItemStack(Material.GOLD_HELMET);
	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.HEAD, 0, 2.0, UUID.randomUUID());
	attr.addModifier(armor);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_HELMET);
	recipe.addIngredient(Material.GOLD_HELMET);
	getServer().addRecipe(recipe);

	item = new ItemStack(Material.LEATHER_CHESTPLATE);
	item2 = new ItemStack(Material.GOLD_CHESTPLATE);
	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.CHEST, 0, 5.0, UUID.randomUUID());	
	attr.addModifier(armor);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_CHESTPLATE);
	recipe.addIngredient(Material.GOLD_CHESTPLATE);
	getServer().addRecipe(recipe);

	item = new ItemStack(Material.LEATHER_LEGGINGS);
	item2 = new ItemStack(Material.GOLD_LEGGINGS);
	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.LEGS, 0, 3.0, UUID.randomUUID());	
	attr.addModifier(armor);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_LEGGINGS);
	recipe.addIngredient(Material.GOLD_LEGGINGS);
	getServer().addRecipe(recipe);

	item = new ItemStack(Material.LEATHER_BOOTS);
	item2 = new ItemStack(Material.GOLD_BOOTS);
	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.FEET, 0, 1.0, UUID.randomUUID());	
	attr.addModifier(armor);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_BOOTS);
	recipe.addIngredient(Material.GOLD_BOOTS);
	getServer().addRecipe(recipe);

	//
	// IRON
	//
	item = new ItemStack(Material.LEATHER_HELMET);
	item2 = new ItemStack(Material.IRON_HELMET);
	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.HEAD, 0, 2.0, UUID.randomUUID());
	attr.addModifier(armor);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_HELMET);
	recipe.addIngredient(Material.IRON_HELMET);
	getServer().addRecipe(recipe);

	item = new ItemStack(Material.LEATHER_CHESTPLATE);
	item2 = new ItemStack(Material.IRON_CHESTPLATE);
	item.setDurability(item2.getDurability());
	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.CHEST, 0, 5.0, UUID.randomUUID());	
	attr.addModifier(armor);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_CHESTPLATE);
	recipe.addIngredient(Material.IRON_CHESTPLATE);
	getServer().addRecipe(recipe);

	item = new ItemStack(Material.LEATHER_LEGGINGS);
	item2 = new ItemStack(Material.IRON_LEGGINGS);
	item.setDurability(item2.getDurability());
	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.LEGS, 0, 4.0, UUID.randomUUID());	
	attr.addModifier(armor);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_LEGGINGS);
	recipe.addIngredient(Material.IRON_LEGGINGS);
	getServer().addRecipe(recipe);

	item = new ItemStack(Material.LEATHER_BOOTS);
	item2 = new ItemStack(Material.IRON_BOOTS);
	item.setDurability(item2.getDurability());
	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.FEET, 0, 1.0, UUID.randomUUID());	
	attr.addModifier(armor);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_BOOTS);
	recipe.addIngredient(Material.IRON_BOOTS);
	getServer().addRecipe(recipe);


	//
	// DIAMOND
	//
	item = new ItemStack(Material.LEATHER_HELMET);
	item2 = new ItemStack(Material.DIAMOND_HELMET);
	item.setDurability(item2.getDurability());
	attr  = new ItemAttributes();
	//attr.getFromStack(new ItemStack(Material.DIAMOND_HELMET));
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.HEAD, 0, 3.0, UUID.randomUUID());
	attr.addModifier(armor);
	thoughness = new AttributeModifier(Attribute.ARMOR_THOUGHNESS, "generic.armorThoughness", Slot.HEAD, 0, 2.0, UUID.randomUUID());
	attr.addModifier(thoughness);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_HELMET);
	recipe.addIngredient(Material.DIAMOND_HELMET);
	getServer().addRecipe(recipe);

	item = new ItemStack(Material.LEATHER_CHESTPLATE);
	item2 = new ItemStack(Material.DIAMOND_CHESTPLATE);
	item.setDurability(item2.getDurability());
	attr  = new ItemAttributes();
	//attr.getFromStack(new ItemStack(Material.DIAMOND_CHESTPLATE));
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.CHEST, 0, 8.0, UUID.randomUUID());	
	attr.addModifier(armor);
	thoughness = new AttributeModifier(Attribute.ARMOR_THOUGHNESS, "generic.armorThoughness", Slot.HEAD, 0, 2.0, UUID.randomUUID());
	attr.addModifier(thoughness);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_CHESTPLATE);
	recipe.addIngredient(Material.DIAMOND_CHESTPLATE);
	getServer().addRecipe(recipe);

	item = new ItemStack(Material.LEATHER_LEGGINGS);
	item2 = new ItemStack(Material.DIAMOND_LEGGINGS);
	item.setDurability(item2.getDurability());
	attr  = new ItemAttributes();
	//attr.getFromStack(new ItemStack(Material.DIAMOND_LEGGINGS));
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.LEGS, 0, 6.0, UUID.randomUUID());	
	attr.addModifier(armor);
	thoughness = new AttributeModifier(Attribute.ARMOR_THOUGHNESS, "generic.armorThoughness", Slot.HEAD, 0, 2.0, UUID.randomUUID());
	attr.addModifier(thoughness);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_LEGGINGS);
	recipe.addIngredient(Material.DIAMOND_LEGGINGS);
	getServer().addRecipe(recipe);

	item = new ItemStack(Material.LEATHER_BOOTS);
	item2 = new ItemStack(Material.DIAMOND_BOOTS);
	item.setDurability(item2.getDurability());
	attr  = new ItemAttributes();
	//attr.getFromStack(new ItemStack(Material.DIAMOND_BOOTS));
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.FEET, 0, 3.0, UUID.randomUUID());	
	attr.addModifier(armor);
	thoughness = new AttributeModifier(Attribute.ARMOR_THOUGHNESS, "generic.armorThoughness", Slot.HEAD, 0, 2.0, UUID.randomUUID());
	attr.addModifier(thoughness);
	item = attr.apply(item);
	item.setDurability(item2.getDurability());
	recipe = new ShapelessRecipe(item);
	recipe.addIngredient(Material.LEATHER_BOOTS);
	recipe.addIngredient(Material.DIAMOND_BOOTS);
	getServer().addRecipe(recipe);

    }
}
