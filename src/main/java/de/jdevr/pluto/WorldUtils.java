package de.jdevr.pluto;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;

public class WorldUtils {
    public static World hubWorld = Bukkit.getWorld("hub");
    public static void TeleportPlayerToWorld(Player player, World world) {
        player.teleport(world.getSpawnLocation());
    }

    public static void TeleportPlayerToWorld(Player player, String worldName) {TeleportPlayerToWorld(player, Bukkit.getWorld(worldName));}

    public static World CreateWorld(String name, boolean generateStructures, WorldType worldType) {
        WorldCreator worldCreator = new WorldCreator(name);
        worldCreator.generateStructures(generateStructures);
        worldCreator.type(worldType);
        return worldCreator.createWorld();
    }

    public static boolean WorldExists(String worldName) {
        return Bukkit.getWorld(worldName) != null;
    }

    public static void KickWorld(Player player) {
        player.sendMessage(
                ChatColor.RED + "Du wurdest aus der Welt " + ChatColor.BOLD +
                player.getWorld().getName() + ChatColor.RESET + ChatColor.RED +
                " gekickt und auf die Hub welt teleportiert. Vielleicht wurde die vorherige Welt gelöscht.");
        player.teleport(hubWorld.getSpawnLocation());
    }

    public static void DeleteWorld(String worldName) {
        World world = Bukkit.getWorld(worldName);

        for (Player player: world.getPlayers()) {
            KickWorld(player);
        }

        if (Bukkit.getWorld(worldName) == null) return;

        File folder = Bukkit.getWorld(worldName).getWorldFolder();
        Bukkit.unloadWorld(Bukkit.getWorld(worldName), false);

        folder.delete();
    }
}
