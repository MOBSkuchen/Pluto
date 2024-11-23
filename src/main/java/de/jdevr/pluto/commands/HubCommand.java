package de.jdevr.pluto.commands;

import com.onarandombox.MultiverseCore.api.MVDestination;
import com.onarandombox.MultiverseCore.destination.DestinationFactory;
import com.onarandombox.MultiverseCore.event.MVTeleportEvent;
import de.jdevr.pluto.Pluto;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HubCommand implements CommandExecutor {
    public static String name = "hub";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Nur Spieler können diesen Befehl verwenden.");
            return false;
        }
        var hubWorld = Pluto.worldManager.getMVWorld("hub");
        DestinationFactory df = Pluto.MultiverseCore.getDestFactory();
        MVDestination d = df.getDestination(hubWorld.getName());
        MVTeleportEvent teleportEvent = new MVTeleportEvent(d, (Player) sender, sender, true);
        Pluto.getInstance().getServer().getPluginManager().callEvent(teleportEvent);
        return true;
    }
}