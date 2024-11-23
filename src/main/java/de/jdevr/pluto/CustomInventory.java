package de.jdevr.pluto;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;

public class CustomInventory {
    Player player;
    Inventory inventory;
    String id;

    public CustomInventory(Player player, int rows, String title, String id) {
        this.player = player;
        this.inventory = Bukkit.createInventory(player, rows * 9, title);
        this.id = id;
    }

    public void Show() {
        this.player.setMetadata("opened_menu", new FixedMetadataValue(Pluto.getInstance(), this.id));
        this.player.openInventory(this.inventory);
    }

    public void SetItem(ItemStack itemStack, int index) {
        this.inventory.setItem(index, itemStack);
    }

    public void SetItem(ItemStack itemStack, int row, int col) {
        this.inventory.setItem((row - 1) * 9 + col, itemStack);
    }

    public static ItemStack CreateItem(Material material, String displayName, String executeCmd, int amount) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        itemMeta.getPersistentDataContainer().set(Pluto.getNsKey("eT"), PersistentDataType.STRING, executeCmd);
        itemStack.setItemMeta(itemMeta);
        itemStack.setAmount(amount);
        return itemStack;
    }

    public static CustomInventory CreateMenu(Player player) throws IOException {
        var config = Pluto.guiData.getKeyAsRaw("menu1").getAsJsonObject();

        CustomInventory customInventory = new CustomInventory(player, config.get("rows").getAsInt(), config.get("title").getAsString(), "menu1");

        var members = config.getAsJsonArray("members");

        ItemStack itemStack;
        JsonObject member;

        for (JsonElement memCfgO : members) {
            member = memCfgO.getAsJsonObject();
            itemStack = CustomInventory.CreateItem(Material.getMaterial(member.get("mat").getAsString()),
                    member.get("name").getAsString(), member.get("cmd").getAsString(), member.get("amount").getAsInt());
            customInventory.SetItem(itemStack, member.get("pRow").getAsInt(), member.get("pCol").getAsInt());
        }

        return customInventory;
    }
}
