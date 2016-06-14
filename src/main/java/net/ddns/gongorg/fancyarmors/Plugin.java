package net.ddns.gongorg.fancyarmors;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

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
    }

    public void onDisable() {
    }

    
    boolean hasPermission(Player player, String permission) {
        return player.hasPermission(permissionNode + permission);
    }

}
