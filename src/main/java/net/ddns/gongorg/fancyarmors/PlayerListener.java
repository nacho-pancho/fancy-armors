package net.ddns.gongorg.fancyarmors;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.block.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.bukkit.Material;
import org.bukkit.Chunk;
import java.util.List;

/**
 * Handle events for all Player related events
 * 
 * @author Ignacio Ramirez <nacho@fing.edu.uy>
 */
public class PlayerListener implements Listener {
    private final Plugin plugin;

    /**
     * Constructor.
     * 
     * @param instance
     *            The plugin to attach to.
     */
    public PlayerListener(Plugin instance) {
        plugin = instance;
    }

    /**
     * The elegant way would be to trigger custom events (Doorevent, SignEvent,
     * whatever) and use the Bukkit mechanism, but I'm a little lazy for that.
     */
    @EventHandler
    public void onEvent(PlayerInteractEvent event) {
        if (event.isCancelled()) {
            return;
        }
    }
}
