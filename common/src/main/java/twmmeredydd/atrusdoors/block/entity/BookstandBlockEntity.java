package twmmeredydd.atrusdoors.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.Clearable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import twmmeredydd.atrusdoors.block.BookStandBlock;
import twmmeredydd.atrusdoors.item.data.LinkingBookData;

import static twmmeredydd.atrusdoors.entity.LinkingBookEntity.ANIM_STEP;

public class BookstandBlockEntity extends BlockEntity implements Clearable {
    private LinkingBookData linkData;
    public float animProgress;
    public float lastTickAnimProgress;

    public BookstandBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AtrusDoorsBlockEntityTypes.BOOKSTAND, blockPos, blockState);
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, BookstandBlockEntity blockEntity) {
        if (!state.getValue(BookStandBlock.HAS_BOOK)) {
            blockEntity.animProgress = 0;
            blockEntity.lastTickAnimProgress = 0;
        } else {
            Player player = level.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 3, false);
            blockEntity.lastTickAnimProgress = blockEntity.animProgress;
            blockEntity.animProgress = Mth.clamp(blockEntity.animProgress + (player != null ? ANIM_STEP : -ANIM_STEP), 0.0F, 1.0F);
        }
    }

    @Override
    public void load(CompoundTag compoundTag) {
        super.load(compoundTag);
        if (compoundTag.contains("LinkDestination")) {
            this.linkData = LinkingBookData.deserializeNBT(compoundTag);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (this.linkData != null) {
            this.linkData.serializeNBT(compoundTag);
        }
    }

    @Override
    public void clearContent() {

    }
}
