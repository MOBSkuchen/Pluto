package de.jdevr.pluto.listeners;

import de.jdevr.pluto.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET.toString() + ChatColor.AQUA + " ist dem Server beigetreten!");
        WorldUtils.TeleportPlayerToWorld(player, WorldUtils.hubWorld);
        player.setWalkSpeed(0.2f);
    }

}
