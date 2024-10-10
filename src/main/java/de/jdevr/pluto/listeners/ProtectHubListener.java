package de.jdevr.pluto.listeners;

import de.jdevr.pluto.WorldUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ProtectHubListener implements Listener {

    @EventHandler
    public void onBlock(BlockBreakEvent event) {
        if (event.getPlayer().isOp()) return;
        if (event.getBlock().getWorld().getName().equals(WorldUtils.hubWorld.getName())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Du kannst in dieser Welt nichts zerstören!");
        }
    }

    @EventHandler
    public void onCanBuild(BlockCanBuildEvent event) {
        if (event.getPlayer().isOp()) return;
        if (event.getBlock().getWorld().getName().equals(WorldUtils.hubWorld.getName())) {
            event.setBuildable(false);
            event.getPlayer().sendMessage(ChatColor.RED + "Du kannst in dieser Welt nicht bauen!");
        }
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent event) {
        if (event.getEntity().getWorld().getName().equals(WorldUtils.hubWorld.getName()) && !(event.getEntity() instanceof Player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        if (event.getPlayer().getWorld().getName().equals(WorldUtils.hubWorld.getName())) {
            event.getPlayer().setAllowFlight(false);
            event.getPlayer().setGameMode(GameMode.CREATIVE);
            event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_ANVIL_PLACE, 1.0f, 1.0f);
        }

        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            event.getPlayer().getWorld().strikeLightning(event.getPlayer().getLocation());
        }

        event.getPlayer().getWorld().getPlayers().forEach(
                player -> player.sendMessage(ChatColor.GOLD.toString() +
                ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET.toString() + ChatColor.GREEN + " ist der Welt beigreten!"));
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager().getWorld().getName().equals(WorldUtils.hubWorld.getName())) {
            event.setCancelled(true);
        }
    }
}
