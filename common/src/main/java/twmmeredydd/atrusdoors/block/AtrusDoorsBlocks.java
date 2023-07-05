package twmmeredydd.atrusdoors.block;

import net.minecraft.resources.ResourceLocation;
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

import java.util.LinkedHashMap;

public class AtrusDoorsBlocks {
    public static final LinkedHashMap<ResourceLocation, Block> BLOCKS = new LinkedHashMap<>();

    public static final BookstandBlock OAK_BOOKSTAND = createBookstand(WoodType.OAK, Blocks.OAK_PLANKS, false);
    public static final BookstandBlock SPRUCE_BOOKSTAND = createBookstand(WoodType.SPRUCE, Blocks.SPRUCE_PLANKS, false);
    public static final BookstandBlock BIRCH_BOOKSTAND = createBookstand(WoodType.BIRCH, Blocks.BIRCH_PLANKS, false);
    public static final BookstandBlock ACACIA_BOOKSTAND = createBookstand(WoodType.ACACIA, Blocks.ACACIA_PLANKS, false);
    public static final BookstandBlock CHERRY_BOOKSTAND = createBookstand(WoodType.CHERRY, Blocks.CHERRY_PLANKS, true);
    public static final BookstandBlock JUNGLE_BOOKSTAND = createBookstand(WoodType.JUNGLE, Blocks.JUNGLE_PLANKS, false);
    public static final BookstandBlock DARK_OAK_BOOKSTAND = createBookstand(WoodType.DARK_OAK, Blocks.DARK_OAK_PLANKS, false);
    public static final BookstandBlock CRIMSON_BOOKSTAND = createBookstand(WoodType.CRIMSON, Blocks.CRIMSON_PLANKS, false);
    public static final BookstandBlock WARPED_BOOKSTAND = createBookstand(WoodType.WARPED, Blocks.WARPED_PLANKS, false);
    public static final BookstandBlock MANGROVE_BOOKSTAND = createBookstand(WoodType.MANGROVE, Blocks.MANGROVE_PLANKS, false);
    public static final BookstandBlock BAMBOO_BOOKSTAND = createBookstand(WoodType.BAMBOO, Blocks.BAMBOO, true);

    public static <T extends Block> T create(String name, T block, boolean createItem) {
        ResourceLocation id = AtrusDoors.id(name);
        if (BLOCKS.put(id, block) != null) {
            throw new IllegalArgumentException("Attempted duplicate registration for block " + id);
        }
        if (createItem) AtrusDoorsItems.create(name, new BlockItem(block, new Item.Properties()), true);
        return block;
    }

    private static BookstandBlock createBookstand(WoodType type, Block planks, boolean is120Exclusive) {
        BlockBehaviour.Properties props = BlockBehaviour.Properties.of(Material.WOOD, planks.defaultMaterialColor()).strength(1).sound(SoundType.WOOD);
        if (is120Exclusive) props.requiredFeatures(FeatureFlags.UPDATE_1_20);
        return create(type.name() + "_bookstand", new BookstandBlock(props), true);
    }
}
