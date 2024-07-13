package org.cordell.com.anizottiimages;

import org.bukkit.plugin.java.JavaPlugin;
import org.cordell.com.anizottiimages.listener.CommandManager;

import java.util.List;
import java.util.Objects;


public final class AnizottiImages extends JavaPlugin {
    @Override
    public void onEnable() {
        var command_manager = new CommandManager();
        for (var command : List.of("create_image"))
            Objects.requireNonNull(getCommand(command)).setExecutor(command_manager);

        getLogger().info("[AnizottiImages] Plugin enabled!");

    }

    @Override
    public void onDisable() {
        getLogger().info("[AnizottiImages] Plugin disabled!");
    }
}
