package de.jdevr.pluto.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListWorldsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(ChatColor.UNDERLINE + "Welten:");
        for (World world: Bukkit.getWorlds()){
            sender.sendMessage("- " + ChatColor.GREEN + world.getName());
        }

        return true;
    }
}