package de.jdevr.pluto;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.File;

public class WorldUtils {
    public static World hubWorld = getHubWorld();

    public static World getHubWorld() {
        if (WorldExists("hub")) return Bukkit.getWorld("hub");
        return CreateWorld("hub", false, WorldType.FLAT);
    }

    public static void TeleportPlayerToWorld(Player player, World world) {
        String title = world.getName();
        title = title.substring(0, 1).toUpperCase() + title.substring(1);;
        player.teleport(world.getSpawnLocation());
        player.sendTitle(title, null, 8, 40, 8);
        world.getPlayers().forEach(
                p_ -> p_.sendMessage(ChatColor.GOLD.toString() +
                        ChatColor.BOLD + p_.getDisplayName() + ChatColor.RESET.toString() + ChatColor.GREEN + " ist der Welt beigreten!"));
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
