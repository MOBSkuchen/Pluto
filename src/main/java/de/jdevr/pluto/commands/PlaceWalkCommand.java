package de.jdevr.pluto.commands;

import com.google.gson.JsonElement;
import de.jdevr.pluto.Pluto;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static de.jdevr.pluto.Pluto.gson;

public class PlaceWalkCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl verwenden.");
            return false;
        }
        Player player = (Player) sender;
        try {
            if (Pluto.playerPlaceWalkData.hasKey(player.getName())) {
                var obj = Pluto.playerPlaceWalkData.getAsObject();
                var writer = Pluto.playerPlaceWalkData.NewWriter();
                obj.remove(player.getName());
                gson.toJson(obj, writer);
                writer.flush();
            } else {
                var obj = Pluto.playerPlaceWalkData.getAsObject();
                var writer = Pluto.playerPlaceWalkData.NewWriter();
                obj.add(player.getName(), gson.fromJson("\"" + args[0] + "\"", JsonElement.class));
                gson.toJson(obj, writer);
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}