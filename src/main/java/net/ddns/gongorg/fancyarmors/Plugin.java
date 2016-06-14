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
        pm.registerEvents(new PlayerListener(this), this);
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
	ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
	ItemStack chestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
	ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
	ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
	
	//
	// GOLD + LEATHER parts
	//
	// modify attributes so that leather parts have same bonuses as
	// golden ones
	//
	ItemAttributes attr  = new ItemAttributes();
	//attr.getFromStack(helmet);
	
	AttributeModifier armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.HEAD, 0, 2.0, UUID.randomUUID());
	attr.addModifier(armor);
	helmet = attr.apply(helmet);
	
	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.CHEST, 0, 5.0, UUID.randomUUID());	
	attr.addModifier(armor);
	chestPlate = attr.apply(chestPlate);

	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.LEGS, 0, 5.0, UUID.randomUUID());	
	attr.addModifier(armor);
	leggings = attr.apply(leggings);

	attr  = new ItemAttributes();
	armor = new AttributeModifier(Attribute.ARMOR, "generic.armor", Slot.FEET, 0, 5.0, UUID.randomUUID());	
	attr.addModifier(armor);
	boots = attr.apply(boots);
	

	ShapedRecipe helmetRecipe = new ShapedRecipe(helmet);
	ShapedRecipe chestPlateRecipe = new ShapedRecipe(chestPlate);
	ShapedRecipe leggingsRecipe = new ShapedRecipe(leggings);
	ShapedRecipe bootsRecipe = new ShapedRecipe(boots);

	// all share the same shape	
	String r1 = " ab";
	helmetRecipe.shape(r1);
	chestPlateRecipe.shape(r1);
	leggingsRecipe.shape(r1);
	bootsRecipe.shape(r1);

	helmetRecipe.setIngredient('a',Material.GOLD_HELMET);
	helmetRecipe.setIngredient('b',Material.LEATHER_HELMET);
	getServer().addRecipe(helmetRecipe);

	chestPlateRecipe.setIngredient('a',Material.GOLD_CHESTPLATE);
	chestPlateRecipe.setIngredient('b',Material.LEATHER_CHESTPLATE);
	getServer().addRecipe(chestPlateRecipe);

	leggingsRecipe.setIngredient('a',Material.GOLD_LEGGINGS);
	leggingsRecipe.setIngredient('b',Material.LEATHER_LEGGINGS);
	getServer().addRecipe(leggingsRecipe);

	bootsRecipe.setIngredient('a',Material.GOLD_BOOTS);
	bootsRecipe.setIngredient('b',Material.LEATHER_BOOTS);
	getServer().addRecipe(bootsRecipe);

    }
}
