package org.cordell.com.anizottiimages.common;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.cordell.com.anizottiimages.render.Render;

import java.awt.image.BufferedImage;


public class MapManager {
    public static ItemStack[] createMaps(BufferedImage[] images, World world) {
        var mapItems = new ItemStack[images.length];

        for (int i = 0; i < images.length; i++) {
            var mapData = ImageLoader.convertImageToMap(images[i]);

            var mapView = Bukkit.createMap(world);
            mapView.getRenderers().clear();
            mapView.addRenderer(new Render(mapData));

            var mapItem = new ItemStack(Material.FILLED_MAP);
            var mapMeta = (MapMeta) mapItem.getItemMeta();
            mapMeta.setMapView(mapView);
            mapItem.setItemMeta(mapMeta);

            mapItems[i] = mapItem;
        }

        return mapItems;
    }
}
