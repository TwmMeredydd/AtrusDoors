package twmmeredydd.atrusdoors.block.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import twmmeredydd.atrusdoors.AtrusDoors;

import java.util.LinkedHashMap;

public class AtrusDoorsBlockEntityTypes {
    public static final LinkedHashMap<ResourceLocation, BlockEntityType<?>> BLOCK_ENTITY_TYPES = new LinkedHashMap<>();

    public static <T extends BlockEntity> BlockEntityType<T> create(String name, BlockEntityType.Builder<T> builder) {
        ResourceLocation id = AtrusDoors.id(name);
        BlockEntityType<T> type = builder.build(null);
        if (BLOCK_ENTITY_TYPES.put(id, type) != null) {
            throw new IllegalArgumentException("Attempted duplicate registration for block entity type " + id);
        }
        return type;
    }
}
