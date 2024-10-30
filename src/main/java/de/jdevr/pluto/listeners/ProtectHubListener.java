package de.jdevr.pluto.listeners;

import de.jdevr.pluto.WorldUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

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
        if (Objects.requireNonNull(event.getPlayer()).isOp()) return;
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
        if (event.getDamager().isOp()) return;
        if (event.getDamager().getWorld().getName().equals(WorldUtils.hubWorld.getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().isOp()) return;
        World hubWorld = WorldUtils.hubWorld;
        Block highestAt = hubWorld.getHighestBlockAt(Objects.requireNonNull(event.getTo()));
        if (event.getPlayer().getWorld().getName().equals(hubWorld.getName()) && highestAt.getType().isAir()) {
            event.setCancelled(true);
            event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
        }
    }
}
