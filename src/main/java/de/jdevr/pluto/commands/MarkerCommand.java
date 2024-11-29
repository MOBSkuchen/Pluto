package de.jdevr.pluto.commands;

import de.jdevr.pluto.CustomDisplay;
import de.jdevr.pluto.CustomInventory;
import de.jdevr.pluto.Pluto;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MarkerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl verwenden.");
            return false;
        }

        Player player = (Player) sender;

        World world = player.getWorld();

        Location loc = player.getTargetBlock(null, 5).getLocation().add(0, 5, 0);

        ItemDisplay itemDisplay = CustomDisplay.CreateItemDisplay(loc, CustomInventory.CreateItem(Material.TORCH, "TORCH", "", 1));
        itemDisplay = (ItemDisplay) CustomDisplay.ApplyTransform(itemDisplay, 20D,
                null, 2F, 10F, 0F, null,
                null, null);

        ScheduleKill(itemDisplay);

        return true;
    }

    private static void ScheduleKill(final ItemDisplay e) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) Pluto.getInstance(), new Runnable() {
            public void run() {
                e.remove();
            }
        }, 200L);
    }
}