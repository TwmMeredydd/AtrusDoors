package twmmeredydd.atrusdoors.block.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.block.AtrusDoorsBlocks;

import java.util.LinkedHashMap;

public class AtrusDoorsBlockEntityTypes {
    public static final LinkedHashMap<ResourceLocation, BlockEntityType<?>> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();

    public static final BlockEntityType<BookstandBlockEntity> BOOKSTAND = create("bookstand", BlockEntityType.Builder.of(BookstandBlockEntity::new, AtrusDoorsBlocks.OAK_BOOKSTAND.get(), AtrusDoorsBlocks.SPRUCE_BOOKSTAND.get(), AtrusDoorsBlocks.BIRCH_BOOKSTAND.get(), AtrusDoorsBlocks.ACACIA_BOOKSTAND.get(), AtrusDoorsBlocks.CHERRY_BOOKSTAND.get(), AtrusDoorsBlocks.JUNGLE_BOOKSTAND.get(), AtrusDoorsBlocks.DARK_OAK_BOOKSTAND.get(), AtrusDoorsBlocks.CRIMSON_BOOKSTAND.get(), AtrusDoorsBlocks.WARPED_BOOKSTAND.get(), AtrusDoorsBlocks.MANGROVE_BOOKSTAND.get(), AtrusDoorsBlocks.BAMBOO_BOOKSTAND.get()));

    public static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType.Builder<T> builder) {
        ResourceLocation id = AtrusDoors.id(name);
        BlockEntityType<T> type = builder.build(null);
        if (BLOCK_ENTITY_TYPES.put(id, type) != null) {
            throw new IllegalArgumentException("Attempted duplicate registration for block entity type " + id);
        }
        return type;
    }
}
