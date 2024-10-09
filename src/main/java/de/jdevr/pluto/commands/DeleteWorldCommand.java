package de.jdevr.pluto.commands;

import de.jdevr.pluto.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class DeleteWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Verwendung: /deleteworld <name>");
            return false;
        }

        String worldName = args[0];

        if (!WorldUtils.WorldExists(worldName)) {
            sender.sendMessage(ChatColor.DARK_RED + "Ungültiger Welt name! Diese Welt existiert nicht!");
            return false;
        }

        WorldUtils.DeleteWorld(worldName);

        if (WorldUtils.WorldExists(worldName)) {
            sender.sendMessage(ChatColor.RED + "Das hat aus irgendeinem Grund nicht funktioniert!");
        } else {
            sender.sendMessage(ChatColor.GREEN + "Fertig!");
        }

        return true;
    }
}
