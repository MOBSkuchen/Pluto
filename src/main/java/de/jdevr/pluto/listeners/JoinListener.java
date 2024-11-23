package de.jdevr.pluto.listeners;

import de.jdevr.pluto.Pluto;
import de.jdevr.pluto.WorldUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws IOException {
        Player player = event.getPlayer();
        if (Pluto.serverConfigData.hasKey("playerListHeader")) {
            player.setPlayerListHeader(Pluto.serverConfigData.getKeyAsString("playerListHeader"));
        }
        if (Pluto.serverConfigData.hasKey("playerListFooter")) {
            player.setPlayerListFooter(Pluto.serverConfigData.getKeyAsString("playerListFooter"));
        }
        event.setJoinMessage(ChatColor.GOLD.toString() + ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET.toString() + ChatColor.AQUA + " ist dem Server beigetreten!");
        WorldUtils.TeleportPlayerToWorld(player, WorldUtils.hubWorld);
        player.setWalkSpeed(0.2f);
    }

}
