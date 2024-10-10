package de.jdevr.pluto.listeners;

import de.jdevr.pluto.WorldUtils;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractionEventListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getState() instanceof Sign) {
                Sign s = (Sign) event.getClickedBlock().getState();
                String message = String.join("\n", s.getLines());
                if (message.startsWith("Change world:\n")) {
                    event.setCancelled(true);
                    WorldUtils.TeleportPlayerToWorld(event.getPlayer(), s.getLines()[1]);
                }
            }
        }
    }
}
