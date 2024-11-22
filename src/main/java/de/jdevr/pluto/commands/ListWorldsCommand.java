package de.jdevr.pluto.commands;

import de.jdevr.pluto.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class ListWorldsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ChatColor.UNDERLINE + "Welten:");
        try {
            for (String world: WorldUtils.ListWorlds()){
                sender.sendMessage("- " + ChatColor.GREEN + world);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}