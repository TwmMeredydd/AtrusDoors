package twmmeredydd.atrusdoors.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import twmmeredydd.atrusdoors.AtrusDoors;

import java.util.LinkedHashMap;
import java.util.function.BiConsumer;

public class AtrusDoorsEntityTypes {
    public static final LinkedHashMap<ResourceLocation, EntityType<?>> ENTITY_TYPES = new LinkedHashMap<>();

    public static final EntityType<LinkingBookEntity> LINKING_BOOK = create("linking_book",
            EntityType.Builder.of(LinkingBookEntity::new, MobCategory.MISC)
                    .sized(0.75f, 0.125f)
    );

    private static <T extends Entity> EntityType<T> create(String name, EntityType.Builder<T> builder) {
        ResourceLocation id = AtrusDoors.id(name);
        EntityType<T> type = builder.build(id.toString());

        if (ENTITY_TYPES.put(id, type) != null) {
            throw new IllegalArgumentException("Attempted duplicate entity type registration for " + id);
        }

        return type;
    }

    public static void register(BiConsumer<ResourceLocation, ? super EntityType<?>> consumer) {
        ENTITY_TYPES.forEach(consumer);
    }
}
