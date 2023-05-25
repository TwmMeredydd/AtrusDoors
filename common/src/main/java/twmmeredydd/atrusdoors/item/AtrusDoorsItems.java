package twmmeredydd.atrusdoors.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.item.data.LinkingBookItem;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class AtrusDoorsItems {
    public static final LinkedHashMap<ResourceLocation, Item> ITEMS = new LinkedHashMap<>();

    public static final Item LINKING_PANEL = create("linking_panel", new AtrusDoorsItem(new Item.Properties(), true));
    public static final UnlinkedLinkingBookItem UNLINKED_LINKING_BOOK = create("unlinked_linking_book", new UnlinkedLinkingBookItem(new Item.Properties(), true));
    public static final LinkingBookItem LINKING_BOOK = create("linking_book", new LinkingBookItem(new Item.Properties().stacksTo(1), false));

    public static <T extends Item> T create(String name, T item) {
        if (ITEMS.put(AtrusDoors.id(name), item) != null) {
            throw new IllegalArgumentException("Attempted duplicate registry for item " + AtrusDoors.id(name));
        }
        return item;
    }

    public static void consume(BiConsumer<ResourceLocation, ? super Item> consumer) {
        ITEMS.forEach(consumer);
    }
}
