package de.jdevr.pluto.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        String cause;
        if (event.getDamageSource().getCausingEntity() != null) {
            cause = event.getDamageSource().getCausingEntity().getName();
        } else {
            cause = "skill issues";
        }
        event.setDeathMessage(ChatColor.RED + event.getEntity().getDisplayName() + ChatColor.WHITE + " leckt eier wegen " + ChatColor.GOLD + cause);
    }
}
