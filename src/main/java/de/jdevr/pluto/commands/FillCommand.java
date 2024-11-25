package de.jdevr.pluto.commands;

import com.google.gson.JsonElement;
import de.jdevr.pluto.Pluto;
import org.bukkit.Location;
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

import java.io.IOException;
import java.util.Objects;

import static de.jdevr.pluto.Pluto.gson;

public class FillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl verwenden.");
            return false;
        }
        Player player = (Player) sender;
        Inventory playerInventory = player.getInventory();
        Material material = Material.getMaterial(args[0]);
        assert material != null;
        var origin = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        var item = new ItemStack(material, 1);
        while (playerInventory.contains(material)) {
            origin = origin.getRelative(BlockFace.NORTH);
            if (!origin.getType().isSolid()) {
                origin.setType(material);
                playerInventory.removeItem(item);
                DoLine(origin, playerInventory, material, item, BlockFace.EAST);
                DoLine(origin, playerInventory, material, item, BlockFace.WEST);
            } else break;
        }
        return true;
    }

    public void DoLine(Block origin, Inventory playerInventory, Material material, ItemStack item, BlockFace direction) {
        while (playerInventory.contains(material)) {
            origin = origin.getRelative(direction);
            if (!origin.getType().isSolid()) {
                origin.setType(material);
                playerInventory.removeItem(item);
            } else break;
        }
    }
}