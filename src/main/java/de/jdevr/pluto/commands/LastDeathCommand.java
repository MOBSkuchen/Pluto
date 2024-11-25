package de.jdevr.pluto.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class LastDeathCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl verwenden.");
            return false;
        }
        Player player = (Player) sender;
        var p2 = player.getServer().getOfflinePlayer(args[0]);
        var loc = p2.getLastDeathLocation();
        assert loc != null;
        player.sendMessage("Letzter Tod bei " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
        return true;
    }
}