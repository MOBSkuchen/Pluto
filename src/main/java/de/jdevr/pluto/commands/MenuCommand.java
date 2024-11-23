package de.jdevr.pluto.commands;

import de.jdevr.pluto.CustomInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class MenuCommand implements CommandExecutor {
    public static String name = "hub";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl verwenden.");
            return false;
        }

        CustomInventory inv = null;
        try {
            inv = CustomInventory.CreateMenu(player);
            inv.Show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}