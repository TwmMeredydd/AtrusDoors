package twmmeredydd.atrusdoors.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;
import twmmeredydd.atrusdoors.registry.Register;
import twmmeredydd.atrusdoors.services.Services;

import java.util.function.Supplier;

public class AtrusDoorsBlocks {
    public static final Register<Block> REGISTER = Services.REGISTER_PROVIDER.create(Registries.BLOCK, AtrusDoors.MOD_ID);

    public static final Supplier<BookstandBlock> OAK_BOOKSTAND = createBookstand(WoodType.OAK, Blocks.OAK_PLANKS, false);
    public static final Supplier<BookstandBlock> SPRUCE_BOOKSTAND = createBookstand(WoodType.SPRUCE, Blocks.SPRUCE_PLANKS, false);
    public static final Supplier<BookstandBlock> BIRCH_BOOKSTAND = createBookstand(WoodType.BIRCH, Blocks.BIRCH_PLANKS, false);
    public static final Supplier<BookstandBlock> ACACIA_BOOKSTAND = createBookstand(WoodType.ACACIA, Blocks.ACACIA_PLANKS, false);
    public static final Supplier<BookstandBlock> CHERRY_BOOKSTAND = createBookstand(WoodType.CHERRY, Blocks.CHERRY_PLANKS, true);
    public static final Supplier<BookstandBlock> JUNGLE_BOOKSTAND = createBookstand(WoodType.JUNGLE, Blocks.JUNGLE_PLANKS, false);
    public static final Supplier<BookstandBlock> DARK_OAK_BOOKSTAND = createBookstand(WoodType.DARK_OAK, Blocks.DARK_OAK_PLANKS, false);
    public static final Supplier<BookstandBlock> CRIMSON_BOOKSTAND = createBookstand(WoodType.CRIMSON, Blocks.CRIMSON_PLANKS, false);
    public static final Supplier<BookstandBlock> WARPED_BOOKSTAND = createBookstand(WoodType.WARPED, Blocks.WARPED_PLANKS, false);
    public static final Supplier<BookstandBlock> MANGROVE_BOOKSTAND = createBookstand(WoodType.MANGROVE, Blocks.MANGROVE_PLANKS, false);
    public static final Supplier<BookstandBlock> BAMBOO_BOOKSTAND = createBookstand(WoodType.BAMBOO, Blocks.BAMBOO, true);

    public static <T extends Block> Supplier<T> create(String name, Supplier<T> block, boolean createItem) {
        Supplier<T> registeredBlock = REGISTER.register(name, block);
        if (createItem) AtrusDoorsItems.create(name, () -> new BlockItem(registeredBlock.get(), new Item.Properties()), true);
        return registeredBlock;
    }

    private static Supplier<BookstandBlock> createBookstand(WoodType type, Block planks, boolean is120Exclusive) {
        BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.WOOD, planks.defaultMaterialColor()).strength(1).sound(SoundType.WOOD);
        if (is120Exclusive) props.requiredFeatures(FeatureFlags.UPDATE_1_20);
        return create(type.name() + "_bookstand", () -> new BookstandBlock(props), true);
    }
}
