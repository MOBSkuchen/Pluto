package de.jdevr.pluto.listeners;

import de.jdevr.pluto.Pluto;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServerPingListener implements Listener {
    public static List<String> motds = new ArrayList<>();
    @EventHandler
    public void onPing(ServerListPingEvent event) {
        Random rnd = new Random();
        if (motds.isEmpty()) {
            event.setMotd(Pluto.getInstance().getServer().getName());
        } else {
            event.setMotd(motds.get(rnd.nextInt(0, motds.size())));
        }
        event.setMaxPlayers(event.getNumPlayers()+1);
    }
}
