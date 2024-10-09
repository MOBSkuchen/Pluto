package de.jdevr.pluto.commands;

import de.jdevr.pluto.WorldUtils;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CreateWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(ChatColor.RED + "Verwendung: /createworld <name> <typ> <genStrukt>");
            return false;
        }
        String worldName = args[0];

        if (WorldUtils.WorldExists(worldName)) {
            sender.sendMessage(ChatColor.DARK_RED + "Ungültiger Welt name! Diese Welt existiert bereits!");
            return false;
        }

        WorldType worldType;
        switch (args[1].toLowerCase()) {
            case "flat": worldType = WorldType.FLAT;
            case "amplified": worldType = WorldType.AMPLIFIED;
            case "normal": worldType = WorldType.NORMAL;
            case "large": worldType = WorldType.LARGE_BIOMES;
            default: {
                sender.sendMessage(ChatColor.DARK_RED + "Ungültiger Wert! Falle auf 'normal' zurück..");
                worldType = WorldType.NORMAL;
            }
        }

        boolean generateStructures;
        switch (args[2].toLowerCase()) {
            case "true": generateStructures = true;
            case "false": generateStructures = false;
            default: {
                sender.sendMessage(ChatColor.DARK_RED + "Ungültiger Wert! Muss entweder 'true' oder 'false' sein. Falle auf 'true' zurück..");
                generateStructures = true;
            }
        }

        World world = WorldUtils.CreateWorld(worldName, generateStructures, worldType);
        WorldUtils.TeleportPlayerToWorld((Player) sender, world);

        return true;
    }
}
