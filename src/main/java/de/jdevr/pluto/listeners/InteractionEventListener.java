package de.jdevr.pluto.listeners;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.jdevr.pluto.Pluto;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.IOException;
import java.util.Objects;

public class InteractionEventListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) throws IOException {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Objects.requireNonNull(event.getClickedBlock()).getState() instanceof Sign) {
                JsonObject jsonObject;
                Pluto.myLogger().info(event.getClickedBlock().getX() + " " + event.getClickedBlock().getY() + " " + event.getClickedBlock().getZ());
                for (JsonElement jsonElement : Pluto.interactData.getAsArray().asList()) {
                    jsonObject = jsonElement.getAsJsonObject();
                    if ((event.getClickedBlock().getX() == jsonObject.get("x").getAsInt())
                            && (event.getClickedBlock().getY() == jsonObject.get("y").getAsInt())
                            && (event.getClickedBlock().getZ() == jsonObject.get("z").getAsInt())
                    ) {
                        event.setCancelled(true);
                        event.getPlayer().performCommand(jsonObject.get("cmd").getAsString());
                    }
                }
            }
        }
    }
}
