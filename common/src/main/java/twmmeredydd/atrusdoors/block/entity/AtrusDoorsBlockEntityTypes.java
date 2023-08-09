package twmmeredydd.atrusdoors.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import twmmeredydd.atrusdoors.AtrusDoors;
import twmmeredydd.atrusdoors.block.AtrusDoorsBlocks;
import twmmeredydd.atrusdoors.registry.Register;
import twmmeredydd.atrusdoors.services.Services;

import java.util.function.Supplier;

public class AtrusDoorsBlockEntityTypes {
    public static final Register<BlockEntityType<?>> BLOCK_ENTITY_TYPES = Services.REGISTER_PROVIDER.create(Registries.BLOCK_ENTITY_TYPE, AtrusDoors.MOD_ID);

    public static final Supplier<BlockEntityType<BookstandBlockEntity>> BOOKSTAND = create("bookstand", () -> BlockEntityType.Builder.of(BookstandBlockEntity::new, AtrusDoorsBlocks.OAK_BOOKSTAND.get(), AtrusDoorsBlocks.SPRUCE_BOOKSTAND.get(), AtrusDoorsBlocks.BIRCH_BOOKSTAND.get(), AtrusDoorsBlocks.ACACIA_BOOKSTAND.get(), AtrusDoorsBlocks.CHERRY_BOOKSTAND.get(), AtrusDoorsBlocks.JUNGLE_BOOKSTAND.get(), AtrusDoorsBlocks.DARK_OAK_BOOKSTAND.get(), AtrusDoorsBlocks.CRIMSON_BOOKSTAND.get(), AtrusDoorsBlocks.WARPED_BOOKSTAND.get(), AtrusDoorsBlocks.MANGROVE_BOOKSTAND.get(), AtrusDoorsBlocks.BAMBOO_BOOKSTAND.get()));

    public static <T extends BlockEntity> Supplier<BlockEntityType<T>> create(String name, Supplier<BlockEntityType.Builder<T>> builder) {
        return BLOCK_ENTITY_TYPES.register(name, () -> builder.get().build(null));
    }
}
