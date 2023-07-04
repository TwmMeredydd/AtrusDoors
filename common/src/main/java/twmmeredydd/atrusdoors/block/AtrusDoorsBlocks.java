package twmmeredydd.atrusdoors.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;

import java.util.LinkedHashMap;

public class AtrusDoorsBlocks {
    public static final LinkedHashMap<ResourceLocation, Block> BLOCKS = new LinkedHashMap<>();

    public static final BookstandBlock OAK_BOOKSTAND = create("oak_bookstand", new BookstandBlock(BlockBehaviour.Properties.of(Material.WOOD, Blocks.OAK_PLANKS.defaultMaterialColor()).strength(1).sound(SoundType.WOOD)), true);

    public static <T extends Block> T create(String name, T block, boolean createItem) {
        ResourceLocation id = AtrusDoors.id(name);
        if (BLOCKS.put(id, block) != null) {
            throw new IllegalArgumentException("Attempted duplicate registration for block " + id);
        }
        if (createItem) AtrusDoorsItems.create(name, new BlockItem(block, new Item.Properties()), true);
        return block;
    }
}
