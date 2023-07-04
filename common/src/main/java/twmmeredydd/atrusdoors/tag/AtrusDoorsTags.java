package twmmeredydd.atrusdoors.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import twmmeredydd.atrusdoors.AtrusDoors;

public class AtrusDoorsTags {
    public static final TagKey<Block> BOOKSTANDS = TagKey.create(Registries.BLOCK, AtrusDoors.id("bookstands"));
}
