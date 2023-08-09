package twmmeredydd.atrusdoors.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.registry.Register;
import twmmeredydd.atrusdoors.services.Services;

import java.util.function.Supplier;

public class AtrusDoorsEntityTypes {
    public static final Register<EntityType<?>> ENTITY_TYPES = Services.PLATFORM.createRegister(Registries.ENTITY_TYPE);

    public static final Supplier<EntityType<LinkingBookEntity>> LINKING_BOOK = create("linking_book",
            EntityType.Builder.of(LinkingBookEntity::new, MobCategory.MISC)
                    .sized(0.75f, 0.125f)
    );

    private static <T extends Entity> Supplier<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITY_TYPES.register(name, () -> builder.build(AtrusDoors.id(name).toString()));
    }
}
