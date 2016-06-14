package net.ddns.gongorg.fancyarmors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import minecraft.spigot.community.michel_0.api.Attribute;
import minecraft.spigot.community.michel_0.api.AttributeModifier;
import minecraft.spigot.community.michel_0.api.ItemAttributes;
import minecraft.spigot.community.michel_0.api.Slot;

/**
 * Handle events for all Player related events
 * 
 * @author You
 */
public class CommandExecutor implements org.bukkit.command.CommandExecutor {
    private final Plugin plugin;

    public CommandExecutor(Plugin instance) {
        plugin = instance;
    }

    /** MUST return boolean */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label,
            String[] args) {
        String basename = cmd.getName();
        if (basename.equalsIgnoreCase("fancy")) {
            if (args.length == 0) {
                printHelp();
                return true;
            }
            String subcmd = args[0];

            return true;
        } else {
            return false;
        }
    }

    private void printHelp() {
        StringBuffer sb = new StringBuffer("FancyArmors usage:\n");
        sb.append("\tfancy [command [args]]\n");
        plugin.log.info(sb.toString());
    }
}
