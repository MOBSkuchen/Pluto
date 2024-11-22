package de.jdevr.pluto;

import com.google.gson.Gson;
import de.jdevr.pluto.commands.CreateWorldCommand;
import de.jdevr.pluto.commands.DeleteWorldCommand;
import de.jdevr.pluto.commands.ListWorldsCommand;
import de.jdevr.pluto.commands.TeleportWorldCommand;
import de.jdevr.pluto.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Logger;

public final class Pluto extends JavaPlugin {
    Gson gson = new Gson();
    private static Pluto plugin;
    public static DataStorageUtil interactData;
    public static DataStorageUtil worldData;

    {
        try {
            interactData = new DataStorageUtil("interactData.json", this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    {
        try {
            worldData = new DataStorageUtil("worldData.json", this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onEnable() {
        plugin = this;
        Logger logger = Bukkit.getLogger();
        ListenerRegistration();
        CommandRegistration();
        try {
            WorldUtils.LoadAllWorlds();
            logger.info("Loaded all worlds ");
        } catch (IOException e) {
            logger.info("Failed to load worlds!");
        }
    }

    @Override
    public void onDisable() {
        Logger logger = Bukkit.getLogger();
        logger.info("Disabled plugin");
    }

    private void ListenerRegistration() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new QuitListener(), this);
        pluginManager.registerEvents(new ProtectHubListener(), this);
        pluginManager.registerEvents(new ChatListener(), this);
        pluginManager.registerEvents(new ServerPingListener(), this);
        pluginManager.registerEvents(new DeathListener(), this);
        pluginManager.registerEvents(new InteractionEventListener(), this);
    }

    private void CommandRegistration() {
        getCommand("createworld").setExecutor(new CreateWorldCommand());
        getCommand("tpworld").setExecutor(new TeleportWorldCommand());
        getCommand("deleteworld").setExecutor(new DeleteWorldCommand());
        getCommand("listworlds").setExecutor(new ListWorldsCommand());
    }

    public static Pluto getInstance() {
        return plugin;
    }

    public static Logger myLogger() {
        return plugin.getLogger();
    }
}
