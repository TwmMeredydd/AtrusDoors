package twmmeredydd.atrusdoors.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.Clearable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import twmmeredydd.atrusdoors.block.BookstandBlock;
import twmmeredydd.atrusdoors.item.data.LinkingBookData;

import static twmmeredydd.atrusdoors.entity.LinkingBookEntity.ANIM_STEP;

public class BookstandBlockEntity extends BlockEntity implements Clearable {
    private ItemStack book = ItemStack.EMPTY;
    public float animProgress;
    public float lastTickAnimProgress;

    public BookstandBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(AtrusDoorsBlockEntityTypes.BOOKSTAND.get(), blockPos, blockState);
    }

    public static void animationTick(Level level, BlockPos pos, BlockState state, BookstandBlockEntity blockEntity) {
        if (!state.getValue(BookstandBlock.HAS_BOOK)) {
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
        this.book = compoundTag.contains("Book", 10) ? ItemStack.of(compoundTag.getCompound("Book")) : ItemStack.EMPTY;
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.book.isEmpty()){
            compoundTag.put("Book", this.book.save(new CompoundTag()));
        }
    }

    @Override
    public void clearContent() {
        this.book = ItemStack.EMPTY;
    }

    public LinkingBookData getLinkData() {
        return LinkingBookData.deserializeNBT(this.book.getOrCreateTag());
    }

    public void setBook(ItemStack newBook) {
        this.book = newBook.copy();
    }

    public ItemStack getBook() {
        return this.book;
    }
}
