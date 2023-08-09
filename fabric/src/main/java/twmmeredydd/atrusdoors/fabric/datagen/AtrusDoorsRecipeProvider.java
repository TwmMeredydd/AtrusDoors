package twmmeredydd.atrusdoors.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import twmmeredydd.atrusdoors.block.AtrusDoorsBlocks;

import java.util.function.Consumer;

public class AtrusDoorsRecipeProvider extends FabricRecipeProvider {
    public AtrusDoorsRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(Consumer<FinishedRecipe> exporter) {
        bookstand(exporter, AtrusDoorsBlocks.OAK_BOOKSTAND.get(), Blocks.OAK_SLAB);
        bookstand(exporter, AtrusDoorsBlocks.SPRUCE_BOOKSTAND.get(), Blocks.SPRUCE_SLAB);
        bookstand(exporter, AtrusDoorsBlocks.BIRCH_BOOKSTAND.get(), Blocks.BIRCH_SLAB);
        bookstand(exporter, AtrusDoorsBlocks.ACACIA_BOOKSTAND.get(), Blocks.ACACIA_SLAB);
        bookstand(exporter, AtrusDoorsBlocks.CHERRY_BOOKSTAND.get(), Blocks.CHERRY_SLAB);
        bookstand(exporter, AtrusDoorsBlocks.JUNGLE_BOOKSTAND.get(), Blocks.JUNGLE_SLAB);
        bookstand(exporter, AtrusDoorsBlocks.DARK_OAK_BOOKSTAND.get(), Blocks.DARK_OAK_SLAB);
        bookstand(exporter, AtrusDoorsBlocks.CRIMSON_BOOKSTAND.get(), Blocks.CRIMSON_SLAB);
        bookstand(exporter, AtrusDoorsBlocks.WARPED_BOOKSTAND.get(), Blocks.WARPED_SLAB);
        bookstand(exporter, AtrusDoorsBlocks.MANGROVE_BOOKSTAND.get(), Blocks.MANGROVE_SLAB);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AtrusDoorsBlocks.BAMBOO_BOOKSTAND.get(), 2)
                .pattern("#").pattern("/").pattern("#")
                .define('#', Blocks.BAMBOO_SLAB).define('/', Items.BAMBOO)
                .group("bookstand")
                .unlockedBy("has_bamboo_slab", has(Blocks.BAMBOO_SLAB))
                .save(exporter);
    }

    protected static void bookstand(Consumer<FinishedRecipe> exporter, Block bookstand, Block slab) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, bookstand, 2)
                .pattern("#").pattern("/").pattern("#")
                .define('#', slab).define('/', Items.STICK)
                .group("bookstand")
                .unlockedBy(getHasName(slab), has(slab))
                .save(exporter);
    }
}
