package org.cordell.com.anizottiimages.listener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cordell.com.anizottiimages.common.ImageLoader;
import org.cordell.com.anizottiimages.common.MapManager;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

import static org.j1sk1ss.itemmanager.manager.Manager.giveItems;


public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        var player = (Player) commandSender;
        if (strings.length < 1) {
            player.sendMessage("Usage: /create_image <image_url>");
            return false;
        }

        try {
            var image    = ImageLoader.loadImageFromURL(strings[0]);
            var images   = ImageLoader.splitImage(Objects.requireNonNull(image));
            var mapItems = Arrays.stream(MapManager.createMaps(images, player.getWorld())).toList();

            giveItems(mapItems, player);
        } catch (Exception e) {
            player.sendMessage("Failed to display image: " + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }
}
