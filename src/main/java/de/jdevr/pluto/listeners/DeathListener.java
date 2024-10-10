package de.jdevr.pluto.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(ChatColor.RED + event.getEntity().getDisplayName() + ChatColor.WHITE + " got rekt by " + ChatColor.GOLD + event.getDamageSource().getCausingEntity().getName());
    }
}
