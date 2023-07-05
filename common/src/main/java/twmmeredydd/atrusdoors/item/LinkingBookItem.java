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
import twmmeredydd.atrusdoors.block.BookstandBlock;
import twmmeredydd.atrusdoors.block.entity.BookstandBlockEntity;
import twmmeredydd.atrusdoors.entity.AtrusDoorsEntityTypes;
import twmmeredydd.atrusdoors.entity.LinkingBookEntity;
import twmmeredydd.atrusdoors.item.data.LinkingBookData;
import twmmeredydd.atrusdoors.tag.AtrusDoorsTags;

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

        if (!player.isCrouching()) {
            player.awardStat(Stats.ITEM_USED.get(this));

            LinkingBookData data = LinkingBookData.deserializeNBT(book.getOrCreateTag());

            if (!LinkingBookData.isValid(data, level)) {
                player.displayClientMessage(Component.translatable("item.atrusdoors.linking_book.invalid_link"), true);
                return InteractionResultHolder.fail(book);
            }

            if (!player.getAbilities().instabuild) {
                spawnAsEntity(level, new Vec3(player.getX(), player.getY(), player.getZ()), book, player.getYHeadRot());
                book.shrink(1);
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
        BlockPos clickedPos = useOnContext.getClickedPos();
        BlockState clickedBlock = level.getBlockState(clickedPos);
        ItemStack inHand = useOnContext.getItemInHand();

        if (clickedBlock.is(AtrusDoorsTags.BOOKSTANDS)) {
            if (!clickedBlock.getValue(BookstandBlock.HAS_BOOK)) {
                if (!level.isClientSide) {
                    BookstandBlockEntity entity = (BookstandBlockEntity) level.getBlockEntity(clickedPos);
                    entity.setBook(inHand);
                    if (!player.getAbilities().instabuild) inHand.shrink(1);
                    BookstandBlock.resetState(player, level, clickedPos, clickedBlock, true);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        if (player.isCrouching()) {
            if (!level.isClientSide) {
                Direction clickedFace = useOnContext.getClickedFace();

                Vec3 spawnPos = Vec3.atLowerCornerWithOffset(clickedBlock.getCollisionShape(level, clickedPos).isEmpty() ? clickedPos : clickedPos.relative(clickedFace), 0.5, 0 ,0.5);

                spawnAsEntity(level, spawnPos, inHand, player.getYHeadRot());
                inHand.shrink(1);
            }

            return InteractionResult.sidedSuccess(level.isClientSide);
        } else {
            return InteractionResult.PASS;
        }
    }

    public static void spawnAsEntity(Level level, Vec3 pos, ItemStack stack, float yRot) {
        LinkingBookEntity entity = new LinkingBookEntity(AtrusDoorsEntityTypes.LINKING_BOOK, level);
        entity.setPos(pos);
        entity.setBook(stack);
        entity.setYRot(yRot);

        level.gameEvent(entity, GameEvent.ENTITY_PLACE, pos);
        level.addFreshEntity(entity);
    }
}
