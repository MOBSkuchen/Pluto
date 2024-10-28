package de.jdevr.pluto.listeners;

import de.jdevr.pluto.WorldUtils;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
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
        Player player = event.getPlayer();
        World world = player.getWorld();

        if (world.getName().equals(WorldUtils.hubWorld.getName())) {
            if (!player.isOp()) player.setAllowFlight(false);
            player.setGameMode(GameMode.CREATIVE);
        }

        if (player.getGameMode() == GameMode.CREATIVE) {
            player.playSound(player, Sound.BLOCK_ANVIL_PLACE, 1.0f, 1.0f);
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager().getWorld().getName().equals(WorldUtils.hubWorld.getName())) {
            event.setCancelled(true);
        }
    }
}
