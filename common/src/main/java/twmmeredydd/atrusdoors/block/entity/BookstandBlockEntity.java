package twmmeredydd.atrusdoors.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Clearable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import twmmeredydd.atrusdoors.item.data.LinkingBookData;

public class BookstandBlockEntity extends BlockEntity implements Clearable {
    private LinkingBookData linkData;

    public BookstandBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AtrusDoorsBlockEntityTypes.BOOKSTAND, blockPos, blockState);
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
