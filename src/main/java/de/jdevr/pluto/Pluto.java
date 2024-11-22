package de.jdevr.pluto;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import de.jdevr.pluto.commands.HubCommand;
import de.jdevr.pluto.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class Pluto extends JavaPlugin {
    public static Gson gson = new Gson();
    private static Pluto plugin;
    public static DataStorageUtil interactData;
    public static DataStorageUtil motdData;

    {
        try {
            interactData = new DataStorageUtil("interactData.json", this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    {
        try {
            motdData = new DataStorageUtil("motdData.json", this);
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
            List<String> motds = new ArrayList<>();
            for (JsonElement jsonElement: motdData.getAsArray()) {
                motds.add(jsonElement.getAsString());
            }
            ServerPingListener.motds = motds;
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    private void RegisterCommand(CommandExecutor executor, String usage) {
        var hubCommand = getCommand("hub");
        assert hubCommand != null;
        hubCommand.setUsage(usage);
        hubCommand.setExecutor(executor);
    }

    private void CommandRegistration() {
        RegisterCommand(new HubCommand(), "/hub");
    }

    public static MultiverseCore MultiverseCore = JavaPlugin.getPlugin(MultiverseCore.class);
    public static MVWorldManager worldManager = MultiverseCore.getMVWorldManager();

    public static Pluto getInstance() {
        return plugin;
    }

    public static Logger myLogger() {
        return plugin.getLogger();
    }
}
