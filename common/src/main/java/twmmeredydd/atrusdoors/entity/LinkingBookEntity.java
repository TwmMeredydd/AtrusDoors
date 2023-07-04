package twmmeredydd.atrusdoors.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import twmmeredydd.atrusdoors.item.AtrusDoorsItems;
import twmmeredydd.atrusdoors.item.data.LinkingBookData;

public class LinkingBookEntity extends Entity {

    private static final EntityDataAccessor<CompoundTag> LINK_ACCESSOR = SynchedEntityData.defineId(LinkingBookEntity.class, EntityDataSerializers.COMPOUND_TAG);
    public static final float ANIM_STEP = 0.1F;

    public float lastTickAnimProgress;
    public float animProgress;

    public LinkingBookEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand interactionHand) {
        if (this.level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        if (player.isCrouching()) {
            ItemStack stack = this.getAsItem();
            if (!player.getInventory().add(stack)) {
                player.drop(stack, false);
            }
            this.remove(RemovalReason.DISCARDED);
            return InteractionResult.SUCCESS;
        }

        LinkingBookData data = this.getLinkData();

        if (!LinkingBookData.isValid(data, player.getLevel())) {
            player.displayClientMessage(Component.translatable("item.atrusdoors.linking_book.invalid_link"), true);
            return InteractionResult.FAIL;
        }

        data.linkEntity(player);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();

        Player player = this.level.getNearestPlayer(this, 3);
        this.lastTickAnimProgress = this.animProgress;
        this.animProgress = Mth.clamp(this.animProgress + (player != null ? ANIM_STEP : -ANIM_STEP), 0.0F, 1.0F);
    }

    @Override
    protected float getEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return 0.0f;
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(LINK_ACCESSOR, new CompoundTag());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        this.setLinkData(LinkingBookData.deserializeNBT(compoundTag));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        if (this.getLinkData() != null) {
            this.getLinkData().serializeNBT(compoundTag);
        }
    }

    public LinkingBookData getLinkData() {
        return LinkingBookData.deserializeNBT(this.entityData.get(LINK_ACCESSOR));
    }

    public void setLinkData(LinkingBookData data) {
        this.entityData.set(LINK_ACCESSOR, data.serializeNBT(new CompoundTag()));
    }

    @Override
    public boolean isPickable() {
        return true;
    }

    @Nullable
    @Override
    public ItemStack getPickResult() {
        return this.getAsItem();
    }

    public ItemStack getAsItem() {
        ItemStack stack = new ItemStack(AtrusDoorsItems.LINKING_BOOK);
        this.getLinkData().serializeNBT(stack.getOrCreateTag());
        return stack;
    }
}
