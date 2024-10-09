package de.jdevr.pluto.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(String.format(ChatColor.GOLD.toString() + ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET.toString() + ChatColor.GREEN + " hat den Server verlassen!"));
    }

}
