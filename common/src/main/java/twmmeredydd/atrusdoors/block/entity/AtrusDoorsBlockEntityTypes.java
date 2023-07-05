package twmmeredydd.atrusdoors.block.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.block.AtrusDoorsBlocks;

import java.util.LinkedHashMap;

public class AtrusDoorsBlockEntityTypes {
    public static final LinkedHashMap<ResourceLocation, BlockEntityType<?>> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();

    public static final BlockEntityType<BookstandBlockEntity> BOOKSTAND = create("bookstand", BlockEntityType.Builder.of(BookstandBlockEntity::new, AtrusDoorsBlocks.OAK_BOOKSTAND, AtrusDoorsBlocks.SPRUCE_BOOKSTAND, AtrusDoorsBlocks.BIRCH_BOOKSTAND, AtrusDoorsBlocks.ACACIA_BOOKSTAND, AtrusDoorsBlocks.CHERRY_BOOKSTAND, AtrusDoorsBlocks.JUNGLE_BOOKSTAND, AtrusDoorsBlocks.DARK_OAK_BOOKSTAND, AtrusDoorsBlocks.CRIMSON_BOOKSTAND, AtrusDoorsBlocks.WARPED_BOOKSTAND, AtrusDoorsBlocks.MANGROVE_BOOKSTAND, AtrusDoorsBlocks.BAMBOO_BOOKSTAND));

    public static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType.Builder<T> builder) {
        ResourceLocation id = AtrusDoors.id(name);
        BlockEntityType<T> type = builder.build(null);
        if (BLOCK_ENTITY_TYPES.put(id, type) != null) {
            throw new IllegalArgumentException("Attempted duplicate registration for block entity type " + id);
        }
        return type;
    }
}
