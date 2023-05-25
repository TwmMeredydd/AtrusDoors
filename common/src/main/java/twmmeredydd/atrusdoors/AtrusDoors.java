package twmmeredydd.atrusdoors;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;
import twmmeredydd.atrusdoors.item.CreativeTabMember;

public class AtrusDoors {
    public static final String MOD_ID = "atrusdoors";

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static <T extends CreativeModeTab.Builder> CreativeModeTab.Builder buildItemGroup(T builder) {
        return builder
                .title(Component.translatable("itemGroup." + MOD_ID + ".main"))
                .icon(() -> new ItemStack(AtrusDoorsItems.LINKING_BOOK))
                .displayItems((itemDisplayParameters, output) -> AtrusDoorsItems.ITEMS.values().forEach(t -> {
                    if (((CreativeTabMember)t).isInTab()) {
                        output.accept(t);
                    }
                }));
    }

}
