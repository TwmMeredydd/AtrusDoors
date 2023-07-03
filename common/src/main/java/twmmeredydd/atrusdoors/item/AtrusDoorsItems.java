package twmmeredydd.atrusdoors.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import twmmeredydd.atrusdoors.AtrusDoors;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class AtrusDoorsItems {
    public static final LinkedHashMap<ResourceLocation, Item> ITEMS = new LinkedHashMap<>();
    public static final ArrayList<Item> TAB_CONTENTS = new ArrayList<>();

    public static final Item LINKING_PANEL = create("linking_panel", new Item(new Item.Properties()), true);
    public static final UnlinkedLinkingBookItem UNLINKED_LINKING_BOOK = create("unlinked_linking_book", new UnlinkedLinkingBookItem(new Item.Properties()), true);
    public static final LinkingBookItem LINKING_BOOK = create("linking_book", new LinkingBookItem(new Item.Properties().stacksTo(1)), false);

    public static <T extends Item> T create(String name, T item, boolean inTab) {
        if (ITEMS.put(AtrusDoors.id(name), item) != null) {
            throw new IllegalArgumentException("Attempted duplicate registry for item " + AtrusDoors.id(name));
        }
        if (inTab) {
            TAB_CONTENTS.add(item);
        }
        return item;
    }
}
