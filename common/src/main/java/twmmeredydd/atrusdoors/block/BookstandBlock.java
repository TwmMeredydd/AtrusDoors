package twmmeredydd.atrusdoors.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import twmmeredydd.atrusdoors.block.entity.AtrusDoorsBlockEntityTypes;
import twmmeredydd.atrusdoors.block.entity.BookstandBlockEntity;
import twmmeredydd.atrusdoors.item.data.LinkingBookData;

public class BookstandBlock extends BaseEntityBlock {
    public static final IntegerProperty ROTATION = BlockStateProperties.ROTATION_16;
    public static final BooleanProperty HAS_BOOK = BlockStateProperties.HAS_BOOK;

    public static final VoxelShape BASE = Block.box(4.0, 0.0, 4.0, 12.0, 1.0, 12.0);
    public static final VoxelShape COLUMN = Block.box(7.0,1.0, 7.0, 9.0, 7.0, 9.0);
    public static final VoxelShape HOLDER = Block.box(3.5, 3.5, 3.5, 12.5, 11, 12.5);

    public BookstandBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(ROTATION, 0)
                .setValue(HAS_BOOK, false)
        );
    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return Shapes.or(BASE, COLUMN, HOLDER);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return this.defaultBlockState().setValue(ROTATION, RotationSegment.convertToSegment(blockPlaceContext.getRotation()));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, HAS_BOOK);
    }

    @Override
    public RenderShape getRenderShape(BlockState blockState) {
        return RenderShape.INVISIBLE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BookstandBlockEntity(blockPos, blockState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return level.isClientSide ? createTickerHelper(blockEntityType, AtrusDoorsBlockEntityTypes.BOOKSTAND, BookstandBlockEntity::animationTick) : null;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        BookstandBlockEntity entity = (BookstandBlockEntity) level.getBlockEntity(blockPos);

        if (blockState.getValue(HAS_BOOK)) {
            if (player.isCrouching()) {
                if (!level.isClientSide) {
                    ItemStack newBook = entity.getBook().copy();
                    if (!player.getInventory().add(newBook)) {
                        player.drop(newBook, false);
                    }
                    entity.clearContent();
                    resetState(player, level, blockPos, blockState, false);
                }

                return InteractionResult.sidedSuccess(level.isClientSide);
            }

            if (!level.isClientSide) {
                if (!LinkingBookData.isValid(entity.getLinkData(), level)) {
                    player.displayClientMessage(Component.translatable("item.atrusdoors.linking_book.invalid_link"), true);
                    return InteractionResult.FAIL;
                }

                entity.getLinkData().linkEntity(player);
                return InteractionResult.SUCCESS;
            }

        }

        return InteractionResult.PASS;
    }

    public static void resetState(Entity entity, Level level, BlockPos blockPos, BlockState state, boolean hasBook) {
        BlockState state2 = state.setValue(HAS_BOOK, hasBook);
        level.setBlock(blockPos, state2, 3);
        level.gameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Context.of(entity));
        level.updateNeighborsAt(blockPos.below(), state2.getBlock());
    }
}
