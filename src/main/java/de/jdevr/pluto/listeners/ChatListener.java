package de.jdevr.pluto.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void OnChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String nameColor = ChatColor.LIGHT_PURPLE.toString();
        String msgColor = ChatColor.GRAY.toString();
        if (player.isOp()) {
            nameColor = ChatColor.DARK_PURPLE.toString();
            msgColor = ChatColor.WHITE.toString();
        }
        event.setFormat(ChatColor.DARK_GREEN + "[" + nameColor + "%1$s" + ChatColor.DARK_GREEN + "] " + msgColor + "%2$s");
    }
}
