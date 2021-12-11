package net.joefoxe.hexerei.tileentity;

import net.joefoxe.hexerei.block.custom.CrystalBall;
import net.joefoxe.hexerei.client.renderer.entity.ModEntityTypes;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.server.ServerLifecycleHooks;


public class CrystalBallTile extends BlockEntity {

//    public final ItemStackHandler itemHandler = createHandler();
//    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
//    protected NonNullList<ItemStack> items = NonNullList.withSize(8, ItemStack.EMPTY);

    public int degreesSpun;
    public int bobAmount;
    public float orbOffset;
    public float smallRingOffset;
    public float largeRingOffset;

    public CrystalBallTile(BlockEntityType<?> tileEntityTypeIn, BlockPos blockPos, BlockState blockState) {
        super(tileEntityTypeIn, blockPos, blockState);

        orbOffset = 0;
        smallRingOffset = 0;
        largeRingOffset = 0;
    }

    public CrystalBallTile(BlockPos blockPos, BlockState blockState) {
        this(ModTileEntities.CRYSTAL_BALL_TILE.get(),blockPos, blockState);
    }

//    public CrystalBallTile() {
//        this(ModTileEntities.CRYSTAL_BALL_TILE.get());
//    }

    public static double getDistanceToEntity(Entity entity, BlockPos pos) {
        double deltaX = entity.position().x - pos.getX();
        double deltaY = entity.position().y - pos.getY();
        double deltaZ = entity.position().z - pos.getZ();

        return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY) + (deltaZ * deltaZ));
    }

    private float moveTo(float input, float movedTo, float speed)
    {
        float distance = movedTo - input;

        if(Math.abs(distance) <= speed)
        {
            return movedTo;
        }

        if(distance > 0)
        {
            input += speed;
        } else {
            input -= speed;
        }

        return input;
    }

    //TODO find where they moved this to
//    @Override
//    public double getMaxRenderDistanceSquared() {
//        return 4096D;
//    }

    @Override
    public AABB getRenderBoundingBox() {
        AABB aabb = super.getRenderBoundingBox().inflate(5, 5, 5);
        return aabb;
    }

//    @Override
    public void tick() {
        if(level.isClientSide) {
            float currentTime = this.getLevel().getGameTime();

            if(largeRingOffset > -7f) {
            if(degreesSpun + 1 < 112)
                degreesSpun += 1;
            else
                degreesSpun = 0;
            }

            if(this.level.getNearestPlayer(this.worldPosition.getX(),this.worldPosition.getY(),this.worldPosition.getZ(), 4D, false) != null) {
                orbOffset = moveTo(orbOffset, (float)Math.sin(Math.PI * (currentTime) / 10) / 4f, 0.25f) ;
                smallRingOffset = moveTo(smallRingOffset, (float)Math.sin(Math.PI * (currentTime + 20) / 15) / 4f, 0.25f);
                largeRingOffset = moveTo(largeRingOffset, (float)Math.sin(Math.PI * (currentTime + 40) / 20) / 4f, 0.35f);

            } else {

                orbOffset = moveTo(orbOffset, -0.5f, 0.1f);
                smallRingOffset = moveTo(smallRingOffset, -4.5f, 0.25f);
                largeRingOffset = moveTo(largeRingOffset, -7f, 0.25f);
            }

            return;
        }

        if(largeRingOffset > -7f) {
            if(degreesSpun + 1 < 112)
                degreesSpun += 1;
            else
                degreesSpun = 0;
        }

        boolean flag = true;
        if(this.level.getNearestPlayer(this.worldPosition.getX(),this.worldPosition.getY(),this.worldPosition.getZ(), 4D, false) != null) {
            ((CrystalBall)this.getBlockState().getBlock()).setPlayerNear(level, worldPosition, this.getBlockState(), true);
            flag = false;
        }

        if(flag)
        {
            ((CrystalBall)this.getBlockState().getBlock()).setPlayerNear(level, worldPosition, this.getBlockState(), false);
        }

    }

}
