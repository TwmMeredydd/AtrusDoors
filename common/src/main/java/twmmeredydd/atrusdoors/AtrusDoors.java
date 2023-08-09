package twmmeredydd.atrusdoors;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import twmmeredydd.atrusdoors.block.AtrusDoorsBlocks;
import twmmeredydd.atrusdoors.block.entity.AtrusDoorsBlockEntityTypes;
import twmmeredydd.atrusdoors.entity.AtrusDoorsEntityTypes;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;

public class AtrusDoors {
    public static final String MOD_ID = "atrusdoors";

    public static void init() {
        AtrusDoorsBlocks.REGISTER.registerAll();
        AtrusDoorsItems.REGISTER.registerAll();
        AtrusDoorsEntityTypes.ENTITY_TYPES.registerAll();
        AtrusDoorsBlockEntityTypes.BLOCK_ENTITY_TYPES.registerAll();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static <T extends CreativeModeTab.Builder> CreativeModeTab.Builder buildItemGroup(T builder) {
        return builder
                .title(Component.translatable("itemGroup." + MOD_ID + ".main"))
                .icon(() -> new ItemStack(AtrusDoorsItems.LINKING_BOOK.get()))
                .displayItems((itemDisplayParameters, output) -> AtrusDoorsItems.TAB_CONTENTS.forEach(item -> output.accept(item.get())));
    }

}
