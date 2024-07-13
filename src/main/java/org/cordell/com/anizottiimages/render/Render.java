package org.cordell.com.anizottiimages.render;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.NotNull;

public class Render extends MapRenderer {
    private final byte[] mapData;
    private boolean done = false;

    public Render(byte[] mapData) {
        this.mapData = mapData;
    }

    @Override
    public void render(@NotNull MapView map, @NotNull MapCanvas canvas, @NotNull Player player) {
        if (done) return; // Render only once to avoid unnecessary rendering
        for (int x = 0; x < 128; x++) {
            for (int y = 0; y < 128; y++) {
                canvas.setPixel(x, y, mapData[y * 128 + x]);
            }
        }

        done = true;
    }
}
