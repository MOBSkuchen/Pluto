package de.jdevr.pluto;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.Objects;

public class CustomDisplay {
    public static ItemDisplay CreateItemDisplay(Location location, ItemStack itemStack) {
        ItemDisplay itemDisplay = Objects.requireNonNull(location.getWorld()).spawn(location, ItemDisplay.class);
        itemDisplay.setItemStack(itemStack);

        return itemDisplay;
    }

    public static Display ApplyTransform(Display display, double scale, @Nullable Vector3f rotation,
                                         float viewRange, float shadowRadius, float shadowStrength,
                                         @Nullable Display.Billboard rotator, @Nullable Display.Brightness brightness, @Nullable Color glow) {

        Transformation transformation = display.getTransformation();
        if (scale > 0) transformation.getScale().set(scale);

        if (rotation != null) {
            transformation.getLeftRotation().x = rotation.x;
            transformation.getLeftRotation().y = rotation.y;
            transformation.getLeftRotation().z = rotation.z;
        }

        display.setTransformation(transformation);

        if (viewRange > 0) display.setViewRange(viewRange);
        if (shadowRadius > 0) display.setShadowRadius(shadowRadius);
        if (shadowStrength > 0) display.setShadowStrength(shadowStrength);
        if (rotator != null) display.setBillboard(rotator);
        if (brightness != null) display.setBrightness(brightness);

        if (glow != null) {
            display.setGlowColorOverride(glow);
            display.setGlowing(true);
        }

        return display;
    }
}
