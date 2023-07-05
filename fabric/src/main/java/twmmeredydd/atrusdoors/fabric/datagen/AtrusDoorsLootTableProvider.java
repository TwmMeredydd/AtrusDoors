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
        dropSelf(AtrusDoorsBlocks.OAK_BOOKSTAND);
        dropSelf(AtrusDoorsBlocks.SPRUCE_BOOKSTAND);
        dropSelf(AtrusDoorsBlocks.BIRCH_BOOKSTAND);
        dropSelf(AtrusDoorsBlocks.ACACIA_BOOKSTAND);
        dropSelf(AtrusDoorsBlocks.CHERRY_BOOKSTAND);
        dropSelf(AtrusDoorsBlocks.JUNGLE_BOOKSTAND);
        dropSelf(AtrusDoorsBlocks.DARK_OAK_BOOKSTAND);
        dropSelf(AtrusDoorsBlocks.CRIMSON_BOOKSTAND);
        dropSelf(AtrusDoorsBlocks.WARPED_BOOKSTAND);
        dropSelf(AtrusDoorsBlocks.MANGROVE_BOOKSTAND);
    }
}
