package de.jdevr.pluto.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Objects;

public class DeathListener implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        String cause;
        if (event.getDamageSource().getCausingEntity() != null) {
            cause = event.getDamageSource().getCausingEntity().getName();
        } else {
            cause = "skill issues";
        }
        event.setDeathMessage(ChatColor.RED + event.getEntity().getDisplayName() + ChatColor.WHITE + " leckt einer wegen " + ChatColor.GOLD + cause);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        event.setRespawnLocation(Objects.requireNonNull(Objects.requireNonNull(event.getPlayer().getLastDeathLocation()).getWorld()).getSpawnLocation());
    }
}
