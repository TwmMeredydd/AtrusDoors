package twmmeredydd.atrusdoors.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import twmmeredydd.atrusdoors.block.AtrusDoorsBlocks;

public class AtrusDoorsLootTableProvider extends FabricBlockLootTableProvider {
    public AtrusDoorsLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        dropSelf(AtrusDoorsBlocks.OAK_BOOKSTAND.get());
        dropSelf(AtrusDoorsBlocks.SPRUCE_BOOKSTAND.get());
        dropSelf(AtrusDoorsBlocks.BIRCH_BOOKSTAND.get());
        dropSelf(AtrusDoorsBlocks.ACACIA_BOOKSTAND.get());
        dropSelf(AtrusDoorsBlocks.CHERRY_BOOKSTAND.get());
        dropSelf(AtrusDoorsBlocks.JUNGLE_BOOKSTAND.get());
        dropSelf(AtrusDoorsBlocks.DARK_OAK_BOOKSTAND.get());
        dropSelf(AtrusDoorsBlocks.CRIMSON_BOOKSTAND.get());
        dropSelf(AtrusDoorsBlocks.WARPED_BOOKSTAND.get());
        dropSelf(AtrusDoorsBlocks.MANGROVE_BOOKSTAND.get());
    }
}
