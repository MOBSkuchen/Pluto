package de.jdevr.pluto.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Random;

public class ServerPingListener implements Listener {
    String[] motds = {
            "Cooler Server",
            "Hariz leckt eier",
            "Jasper > Hariz",
            "J + J",
            "Leck eier"
    };
    @EventHandler
    public void onPing(ServerListPingEvent event) {
        Random rnd = new Random();
        event.setMotd(motds[rnd.nextInt(0, motds.length-1)]);
        event.setMaxPlayers(event.getNumPlayers()+1);
    }
}
