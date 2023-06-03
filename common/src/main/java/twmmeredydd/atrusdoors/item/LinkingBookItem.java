package twmmeredydd.atrusdoors.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import twmmeredydd.atrusdoors.entity.AtrusDoorsEntityTypes;
import twmmeredydd.atrusdoors.entity.LinkingBookEntity;
import twmmeredydd.atrusdoors.item.data.LinkingBookData;

public class LinkingBookItem extends Item {
    public LinkingBookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack book = player.getItemInHand(interactionHand);
        if (level.isClientSide) {
            return InteractionResultHolder.success(book);
        }

        if (!player.isCrouching()){
            player.awardStat(Stats.ITEM_USED.get(this));

            LinkingBookData data = LinkingBookData.deserializeNBT(book.getOrCreateTag());

            if (!data.isValid(level)) {
                player.displayClientMessage(Component.translatable("item.atrusdoors.linking_book.invalid_link"), true);
                return InteractionResultHolder.fail(book);
            }

            data.linkEntity(player);
            return InteractionResultHolder.success(book);
        }

        return InteractionResultHolder.pass(book);
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        Level level = useOnContext.getLevel();
        Player player = useOnContext.getPlayer();

        if (player.isCrouching()) {
            if (!level.isClientSide) {
                BlockPos clickedPos = useOnContext.getClickedPos();
                BlockState clickedBlock = level.getBlockState(clickedPos);
                Direction clickedFace = useOnContext.getClickedFace();
                ItemStack inHand = useOnContext.getItemInHand();

                Vec3 spawnPos = Vec3.atLowerCornerWithOffset(clickedBlock.getCollisionShape(level, clickedPos).isEmpty() ? clickedPos : clickedPos.relative(clickedFace), 0.5, 0 ,0.5);

                LinkingBookEntity entity = new LinkingBookEntity(AtrusDoorsEntityTypes.LINKING_BOOK, level);
                entity.setPos(spawnPos);
                entity.setLinkData(LinkingBookData.deserializeNBT(inHand.getOrCreateTag()));
                entity.setYRot(player.getYHeadRot());

                level.gameEvent(entity, GameEvent.ENTITY_PLACE, spawnPos);
                level.addFreshEntity(entity);
                inHand.shrink(1);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }
}
