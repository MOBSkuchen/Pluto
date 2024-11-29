package de.jdevr.pluto.listeners;

import de.jdevr.pluto.Pluto;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class GuiListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Server server = player.getServer();
        if (player.hasMetadata("opened_menu")) {

            event.setCancelled(true);

            ItemStack clickedOn = event.getCurrentItem();

            if (clickedOn == null) return;

            if (clickedOn.hasItemMeta()) {
                var nsK = Pluto.getNsKey("eT");
                var meta = Objects.requireNonNull(clickedOn.getItemMeta()).getPersistentDataContainer();
                if (meta.has(nsK)) {
                    var cmd = meta.get(nsK, PersistentDataType.STRING);
                    assert cmd != null;
                    server.dispatchCommand(server.getConsoleSender(), cmd.replace("<player>", player.getName()));
                }
            }
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (player.hasMetadata("opened_menu")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.hasMetadata("opened_menu")) {
            player.removeMetadata("opened_menu", Pluto.getInstance());
        }
    }
}
