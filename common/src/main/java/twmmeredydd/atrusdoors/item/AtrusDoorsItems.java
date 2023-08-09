package twmmeredydd.atrusdoors.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.registry.Register;
import twmmeredydd.atrusdoors.services.Services;

import java.util.ArrayList;
import java.util.function.Supplier;

public class AtrusDoorsItems {
    public static final Register<Item> REGISTER = Services.PLATFORM.createRegister(Registries.ITEM);
    public static final ArrayList<Supplier<? extends Item>> TAB_CONTENTS = new ArrayList<>();

    public static final Supplier<Item> LINKING_PANEL = create("linking_panel", () -> new Item(new Item.Properties()), true);
    public static final Supplier<UnlinkedLinkingBookItem> UNLINKED_LINKING_BOOK = create("unlinked_linking_book", () -> new UnlinkedLinkingBookItem(new Item.Properties()), true);
    public static final Supplier<LinkingBookItem> LINKING_BOOK = create("linking_book", () -> new LinkingBookItem(new Item.Properties().stacksTo(1)), false);

    public static <T extends Item> Supplier<T> create(String name, Supplier<T> item, boolean inTab) {
        Supplier<T> object = REGISTER.register(name, item);
        if (inTab) {
            TAB_CONTENTS.add(object);
        }
        return object;
    }
}
