package twmmeredydd.atrusdoors.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import twmmeredydd.atrusdoors.AtrusDoors;

import java.util.LinkedHashMap;

public class AtrusDoorsItems {
    public static final LinkedHashMap<ResourceLocation, Item> ITEMS = new LinkedHashMap<>();

    public static <T extends Item> T create(String name, T item) {
        if (ITEMS.put(AtrusDoors.id(name), item) != null) {
            throw new IllegalArgumentException("Attempted duplicate registry for item " + AtrusDoors.id(name));
        }
        return item;
    }
}
