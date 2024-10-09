package de.jdevr.pluto;

import de.jdevr.pluto.commands.CreateWorldCommand;
import de.jdevr.pluto.commands.DeleteWorldCommand;
import de.jdevr.pluto.commands.TeleportWorldCommand;
import de.jdevr.pluto.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Pluto extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger logger = Bukkit.getLogger();
        logger.info("Starting up plugin");
        ListenerRegistration();
        CommandRegistration();
        logger.info("Start up done");
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
    }

    private void CommandRegistration() {
        getCommand("createworld").setExecutor(new CreateWorldCommand());
        getCommand("tpworld").setExecutor(new TeleportWorldCommand());
        getCommand("deleteworld").setExecutor(new DeleteWorldCommand());
    }
}
