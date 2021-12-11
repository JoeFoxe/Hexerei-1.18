//package net.joefoxe.hexerei.client.renderer.entity.custom;
//
//import com.google.common.base.Predicate;
//import net.joefoxe.hexerei.client.renderer.entity.ModEntityTypes;
//import net.joefoxe.hexerei.client.renderer.entity.custom.ai.CreatureAITargetItems;
//import net.joefoxe.hexerei.client.renderer.entity.custom.ai.DirectPathNavigator;
//import net.joefoxe.hexerei.client.renderer.entity.custom.ai.ITargetsDroppedItems;
//import net.joefoxe.hexerei.util.HexereiTags;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.entity.AgeableEntity;
//import net.minecraft.entity.CreatureEntity;
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.entity.ai.RandomPositionGenerator;
//import net.minecraft.entity.ai.attributes.AttributeSupplier;
//import net.minecraft.entity.ai.attributes.Attributes;
//import net.minecraft.entity.ai.controller.MovementController;
//import net.minecraft.entity.ai.goal.*;
//import net.minecraft.world.entity.item.ItemEntity;
//import net.minecraft.entity.monster.MonsterEntity;
//import net.minecraft.entity.passive.Animal;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.BlockItem;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.crafting.Ingredient;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.datasync.EntityDataAccessor;
//import net.minecraft.network.datasync.EntityDataSerializers;
//import net.minecraft.network.datasync.SynchedEntityData;
//import net.minecraft.core.particles.BlockParticleOption;
//import net.minecraft.particles.ParticleOptions;
//import net.minecraft.particles.ItemParticleData;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.pathfinding.GroundPathNavigator;
//import net.minecraft.pathfinding.PathNodeType;
//import net.minecraft.tags.EntityTypeTags;
//import net.minecraft.tags.ITag;
//import net.minecraft.util.*;
//import net.minecraft.util.math.*;
//import net.minecraft.world.phys.Vec3;
//import net.minecraft.util.text.TextFormatting;
//import net.minecraft.world.level.Level;
//import net.minecraft.server.level.ServerLevel;
//
//import javax.annotation.Nullable;
//import java.util.*;
//
//public class CrowEntity extends Animal implements ITargetsDroppedItems {
//    private static final EntityDataAccessor<Boolean> FLYING = SynchedEntityData.defineId(CrowEntity.class, EntityDataSerializers.BOOLEAN);
//    private static final EntityDataAccessor<Float> FLIGHT_LOOK_YAW = SynchedEntityData.defineId(CrowEntity.class, EntityDataSerializers.FLOAT);
//    private static final EntityDataAccessor<Integer> ATTACK_TICK = SynchedEntityData.defineId(CrowEntity.class, EntityDataSerializers.INT);
//    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(CrowEntity.class, EntityDataSerializers.BOOLEAN);
//    public boolean aiItemFlag = false;
//    private boolean isLandNavigator;
//    private int timeFlying;
//    private BlockPos orbitPos = null;
//    private double orbitDist = 5D;
//    private boolean orbitClockwise = false;
//    private boolean fallFlag = false;
//    private int flightLookCooldown = 0;
//    private float targetFlightLookYaw;
//    private int heldItemTime = 0;
//    public int treasureSitTime;
//    public UUID feederUUID = null;
//
//
//    public CrowEntity(EntityType<? extends Animal> type, Level worldIn) {
//        super(type, worldIn);
//        this.setPathPriority(PathNodeType.DANGER_FIRE, -1.0F);
//        this.setPathPriority(PathNodeType.WATER, -1.0F);
//        this.setPathPriority(PathNodeType.WATER_BORDER, 16.0F);
//        this.setPathPriority(PathNodeType.COCOA, -1.0F);
//        this.setPathPriority(PathNodeType.FENCE, -1.0F);
//        switchNavigator(false);
//    }
//
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.ENTITY_CHICKEN_AMBIENT;
//    }
//
//    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
//        return SoundEvents.ENTITY_CHICKEN_HURT;
//    }
//
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.ENTITY_CHICKEN_DEATH;
//    }
//
//    public static AttributeSupplier.MutableAttribute bakeAttributes() {
//        return MonsterEntity.func_234295_eP_().add(Attributes.MAX_HEALTH, 8.0D).add(Attributes.ATTACK_DAMAGE, 1.0D).add(Attributes.MOVEMENT_SPEED, 0.2F);
//    }
//
//    public void writeAdditional(CompoundTag compound) {
//        super.writeAdditional(compound);
//        compound.putBoolean("Flying", this.isFlying());
//        compound.putBoolean("Sitting", this.isSitting());
//        if(feederUUID != null){
//            compound.putUniqueId("FeederUUID", feederUUID);
//        }
//    }
//
//    public void readAdditional(CompoundTag compound) {
//        super.readAdditional(compound);
//        this.setFlying(compound.getBoolean("Flying"));
//        this.setSitting(compound.getBoolean("Sitting"));
//        if(compound.hasUniqueId("FeederUUID")){
//            this.feederUUID = compound.getUniqueId("FeederUUID");
//        }
//    }
//
//    protected void registerGoals() {
//        super.registerGoals();
//        this.goalSelector.addGoal(0, new SwimGoal(this));
//        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
//        this.goalSelector.addGoal(4, new TemptGoal(this, 1.0D, Ingredient.fromItems(Items.PUMPKIN_SEEDS,Items.MELON_SEEDS,Items.BEETROOT_SEEDS,Items.WHEAT_SEEDS), false){
//            public boolean shouldExecute(){
//                return !CrowEntity.this.aiItemFlag && super.shouldExecute();
//            }
//        });
//        this.goalSelector.addGoal(5, new AIWanderIdle());
//        this.goalSelector.addGoal(6, new LookAtGoal(this, Player.class, 6.0F));
//        this.goalSelector.addGoal(7, new LookAtGoal(this, CreatureEntity.class, 6.0F));
//        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
////        this.goalSelector.addGoal(9, new AIScatter());
//        this.targetSelector.addGoal(1, new AITargetItems(this, false, false, 15, 16));
//    }
//
//    public boolean isBreedingItem(ItemStack stack) {
//        Item item = stack.getItem();
//        return item == Items.PUMPKIN_SEEDS;
//    }
//
//    public boolean causeFallDamage(float distance, float damageMultiplier) {
//        return false;
//    }
//
//    protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
//    }
//
//    private void switchNavigator(boolean onLand) {
//        if (onLand) {
//            this.moveController = new MovementController(this);
//            this.navigator = new GroundPathNavigator(this, world);
//            this.isLandNavigator = true;
//        } else {
//            this.moveController = new MoveHelper(this);
//            this.navigator = new DirectPathNavigator(this, world);
//            this.isLandNavigator = false;
//        }
//    }
//
//    @Override
//    protected void registerData() {
//        super.registerData();
//        this.entityData.register(FLYING, false);
//        this.entityData.register(SITTING, false);
//        this.entityData.register(ATTACK_TICK, 0);
//        this.entityData.register(FLIGHT_LOOK_YAW, 0F);
//    }
//
//    public boolean isSitting() {
//        return this.entityData.get(SITTING);
//    }
//
//    public void setSitting(boolean sitting) {
//        this.entityData.set(SITTING, sitting);
//    }
//
//    public float getFlightLookYaw() {
//        return entityData.get(FLIGHT_LOOK_YAW);
//    }
//
//    public void setFlightLookYaw(float yaw) {
//        entityData.set(FLIGHT_LOOK_YAW, yaw);
//    }
//
//    public boolean isFlying() {
//        return this.entityData.get(FLYING);
//    }
//
//    public void setFlying(boolean flying) {
//        if (flying && this.isChild()) {
//            flying = false;
//        }
//        this.entityData.set(FLYING, flying);
//    }
//
//    public void tick() {
//        super.tick();
//        float yMot = (float) -((float) this.getDeltaMovement().y * (double) (180F / (float) Math.PI));
//        float absYaw = Math.abs(this.rotationYaw - this.prevRotationYaw);
//
//        if (!level.isClientSide) {
//            if (isFlying()) {
//                float lookYawDist = Math.abs(this.getFlightLookYaw() - targetFlightLookYaw);
//                if (flightLookCooldown > 0) {
//                    flightLookCooldown--;
//                }
//                if (flightLookCooldown == 0 && this.rand.nextInt(4) == 0 && lookYawDist < 0.5F) {
//                    targetFlightLookYaw = Mth.clamp(rand.nextFloat() * 120F - 60, -60, 60);
//                    flightLookCooldown = 3 + rand.nextInt(15);
//                }
//                if (this.getFlightLookYaw() < this.targetFlightLookYaw && lookYawDist > 0.5F) {
//                    this.setFlightLookYaw(this.getFlightLookYaw() + Math.min(lookYawDist, 4F));
//                }
//                if (this.getFlightLookYaw() > this.targetFlightLookYaw && lookYawDist > 0.5F) {
//                    this.setFlightLookYaw(this.getFlightLookYaw() - Math.min(lookYawDist, 4F));
//                }
//                if (this.onGround && !this.isInWaterOrBubbleColumn() && this.timeFlying > 30) {
//                    this.setFlying(false);
//                }
//                timeFlying++;
//                this.setNoGravity(true);
//                if (this.hasPassenger() || this.isInLove()) {
//                    this.setFlying(false);
//                }
//            } else {
//                fallFlag = false;
//                timeFlying = 0;
//                this.setNoGravity(false);
//            }
//            if (isFlying() && this.isLandNavigator) {
//                switchNavigator(false);
//            }
//            if (!isFlying() && !this.isLandNavigator) {
//                switchNavigator(true);
//            }
//        }
//        if (!this.getHeldItemMainhand().isEmpty()) {
//            heldItemTime++;
//            if (heldItemTime > 200 && canTargetItem(this.getHeldItemMainhand())) {
//                heldItemTime = 0;
//                this.heal(4);
//                this.playSound(SoundEvents.ENTITY_GENERIC_EAT, this.getSoundVolume(), this.getSoundPitch());
//                if (this.getHeldItemMainhand().hasContainerItem()) {
//                    this.spawnAtLocation(this.getHeldItemMainhand().getContainerItem());
//                }
//                eatItemEffect(this.getHeldItemMainhand());
//                this.getHeldItemMainhand().shrink(1);
//            }
//        } else {
//            heldItemTime = 0;
//        }
//        if(treasureSitTime > 0){
//            treasureSitTime--;
//        }
//        if(this.isSitting() && this.isInWaterOrBubbleColumn()){
//            this.setDeltaMovement(this.getDeltaMovement().add(0, 0.02F, 0));
//        }
//    }
//
//    public void eatItem(){
//        heldItemTime = 200;
//    }
//
//    private void eatItemEffect(ItemStack heldItemMainhand) {
//        for (int i = 0; i < 2 + rand.nextInt(2); i++) {
//            double d2 = this.rand.nextGaussian() * 0.02D;
//            double d0 = this.rand.nextGaussian() * 0.02D;
//            double d1 = this.rand.nextGaussian() * 0.02D;
//            float radius = this.getWidth() * 0.65F;
//            float angle = (0.01745329251F * this.renderYawOffset);
//            double extraX = radius * Mth.sin((float) (Math.PI + angle));
//            double extraZ = radius * Mth.cos(angle);
//            ParticleOptions data = new ItemParticleData(ParticleTypes.ITEM, heldItemMainhand);
//            if (heldItemMainhand.getItem() instanceof BlockItem) {
//                data = new BlockParticleOption(ParticleTypes.BLOCK, ((BlockItem) heldItemMainhand.getItem()).getBlock().defaultBlockState());
//            }
//            this.level.addParticle(data, this.getPosX() + extraX, this.getPosY() + this.getHeight() * 0.6F, this.getPosZ() + extraZ, d0, d1, d2);
//        }
//    }
//
//    public Vec3 getBlockInViewAway(Vec3 fleePos, float radiusAdd) {
//        float radius = 5 + radiusAdd + this.getRNG().nextInt(5);
//        float neg = this.getRNG().nextBoolean() ? 1 : -1;
//        float renderYawOffset = this.renderYawOffset;
//        float angle = (0.01745329251F * renderYawOffset) + 3.15F + (this.getRNG().nextFloat() * neg);
//        double extraX = radius * Mth.sin((float) (Math.PI + angle));
//        double extraZ = radius * Mth.cos(angle);
//        BlockPos radialPos = new BlockPos(fleePos.getX() + extraX, 0, fleePos.getZ() + extraZ);
//        BlockPos ground = getCrowGround(radialPos);
//        int distFromGround = (int) this.getPosY() - ground.getY();
//        int flightHeight = 8 + this.getRNG().nextInt(4);
//        BlockPos newPos = ground.up(distFromGround > 3 ? flightHeight : this.getRNG().nextInt(4) + 8);
//        if (!this.isTargetBlocked(Vec3.copyCentered(newPos)) && this.getDistanceSq(Vec3.copyCentered(newPos)) > 1) {
//            return Vec3.copyCentered(newPos);
//        }
//        return null;
//    }
//
//    public Vec3 getBlockGrounding(Vec3 fleePos) {
//        float radius = 10 + this.getRNG().nextInt(15);
//        float neg = this.getRNG().nextBoolean() ? 1 : -1;
//        float renderYawOffset = this.renderYawOffset;
//        float angle = (0.01745329251F * renderYawOffset) + 3.15F + (this.getRNG().nextFloat() * neg);
//        double extraX = radius * Mth.sin((float) (Math.PI + angle));
//        double extraZ = radius * Mth.cos(angle);
//        BlockPos radialPos = new BlockPos(fleePos.getX() + extraX, getPosY(), fleePos.getZ() + extraZ);
//        BlockPos ground = this.getCrowGround(radialPos);
//        if (ground.getY() == 0) {
//            return this.getPositionVec();
//        } else {
//            ground = this.getPosition();
//            while (ground.getY() > 2 && world.isEmptyBlock(ground)) {
//                ground = ground.below();
//            }
//        }
//        if (!this.isTargetBlocked(Vec3.copyCentered(ground.up()))) {
//            return Vec3.copyCentered(ground);
//        }
//        return null;
//    }
//
//    private boolean isOverWaterOrVoid() {
//        BlockPos position = this.getPosition();
//        while (position.getY() > 0 && world.isEmptyBlock(position)) {
//            position = position.below();
//        }
//        return !world.getFluidState(position).isEmpty() || position.getY() <= 0;
//    }
//
//    public InteractionResult getEntityInteractionResult(Player player, InteractionHand hand) {
//        ItemStack itemstack = player.getItemInHand(hand);
//        Item item = itemstack.getItem();
//        InteractionResult type = super.getEntityInteractionResult(player, hand);
//        if (!this.getHeldItemMainhand().isEmpty() && type != InteractionResult.SUCCESS) {
//            this.spawnAtLocation(this.getHeldItemMainhand().copy());
//            this.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
//            return InteractionResult.SUCCESS;
//        } else {
//            return type;
//        }
//    }
//
//    public boolean isTargetBlocked(Vec3 target) {
//        Vec3 Vec3 = new Vec3(this.getPosX(), this.getPosYEye(), this.getPosZ());
//
//        return this.level.clip(new ClipContext(Vec3, target, ClipContext.BlockMode.COLLIDER, ClipContext.Fluid.NONE, this)).getType() != HitResult.Type.MISS;
//    }
//
//    private Vec3 getOrbitVec(Vec3 vector3d, float gatheringCircleDist) {
//        float angle = (0.01745329251F * (float) this.orbitDist * (orbitClockwise ? -ticksExisted : ticksExisted));
//        double extraX = gatheringCircleDist * Mth.sin((angle));
//        double extraZ = gatheringCircleDist * Mth.cos(angle);
//        if (this.orbitPos != null) {
//            Vec3 pos = new Vec3(orbitPos.getX() + extraX, orbitPos.getY() + rand.nextInt(2), orbitPos.getZ() + extraZ);
//            if (this.level.isEmptyBlock(new BlockPos(pos))) {
//                return pos;
//            }
//        }
//        return null;
//    }
//
//    public BlockPos getCrowGround(BlockPos in) {
//        BlockPos position = new BlockPos(in.getX(), this.getPosY(), in.getZ());
//        while (position.getY() < 256 && !world.getFluidState(position).isEmpty()) {
//            position = position.up();
//        }
//        while (position.getY() > 2 && world.isEmptyBlock(position)) {
//            position = position.below();
//        }
//        return position;
//    }
//
//
//    public void travel(Vec3 vec3d) {
//        if (this.isSitting()) {
//            if (this.getNavigator().getPath() != null) {
//                this.getNavigator().clearPath();
//            }
//            vec3d = Vec3.ZERO;
//        }
//        super.travel(vec3d);
//    }
//
//    @Override
//    public void onGetItem(ItemEntity e) {
//        ItemStack duplicate = e.getItem().copy();
//        duplicate.setCount(1);
//        if (!this.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() && !this.level.isClientSide) {
//            this.spawnAtLocation(this.getItemInHand(InteractionHand.MAIN_HAND), 0.0F);
//        }
//        if(e.getThrowerId() != null && (e.getItem().getItem() == Items.WHEAT_SEEDS || e.getItem().getItem() == Items.PUMPKIN_SEEDS || e.getItem().getItem() == Items.MELON_SEEDS || e.getItem().getItem() == Items.BEETROOT_SEEDS)){
//            Player player = world.getPlayerByUuid(e.getThrowerId());
//            if(player != null){
//                feederUUID = e.getThrowerId();
//            }
//        }
//        this.setFlying(true);
//        this.setItemInHand(InteractionHand.MAIN_HAND, duplicate);
//    }
//
//    @Override
//    public boolean canTargetItem(ItemStack stack) {
//        return stack.getItem().equals(Items.WHEAT_SEEDS) && !this.isSitting();
//    }
//
//    @Nullable
//    @Override
//    public AgeableEntity createChild(ServerLevel world, AgeableEntity mate) {
//        return ModEntityTypes.CROW.get().create(world);
//    }
//
//
////    private class AIScatter extends Goal {
////        protected final CrowEntity.AIScatter.Sorter theNearestAttackableTargetSorter;
////        protected final Predicate<? super Entity> targetEntitySelector;
////        protected int executionChance = 8;
////        protected boolean mustUpdate;
////        private Entity targetEntity;
////        private Vec3 flightTarget = null;
////        private int cooldown = 0;
////        private ITag tag;
////
////        AIScatter() {
////            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
////            tag = EntityTypeTags.getCollection().get(HexereiTags.CrowScatter);
////            this.theNearestAttackableTargetSorter = new CrowEntity.AIScatter.Sorter(CrowEntity.this);
////            this.targetEntitySelector = new Predicate<Entity>() {
////                @Override
////                public boolean apply(@Nullable Entity e) {
////                    return e.isAlive() && e.getType().isContained(tag) || e instanceof Player && !((Player) e).isCreative();
////                }
////            };
////        }
////
////        @Override
////        public boolean shouldExecute() {
////            if (CrowEntity.this.hasPassenger() || CrowEntity.this.isSitting() || CrowEntity.this.aiItemFlag || CrowEntity.this.isVehicle()) {
////                return false;
////            }
////            if (!this.mustUpdate) {
////                long worldTime = CrowEntity.this.level.getGameTime() % 10;
////                if (CrowEntity.this.getIdleTime() >= 100 && worldTime != 0) {
////                    return false;
////                }
////                if (CrowEntity.this.getRNG().nextInt(this.executionChance) != 0 && worldTime != 0) {
////                    return false;
////                }
////            }
////            List<Entity> list = CrowEntity.this.level.getEntitiesWithinAABB(Entity.class, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);
////            if (list.isEmpty()) {
////                return false;
////            } else {
////                Collections.sort(list, this.theNearestAttackableTargetSorter);
////                this.targetEntity = list.get(0);
////                this.mustUpdate = false;
////                return true;
////            }
////        }
////
////        @Override
////        public boolean shouldContinueExecuting() {
////            return targetEntity != null;
////        }
////
////        public void resetTask() {
////            flightTarget = null;
////            this.targetEntity = null;
////        }
////
////        @Override
////        public void tick() {
////            if (cooldown > 0) {
////                cooldown--;
////            }
////            if (flightTarget != null) {
////                CrowEntity.this.setFlying(true);
////                CrowEntity.this.getMoveHelper().setMoveTo(flightTarget.x, flightTarget.y, flightTarget.z, 1F);
////                if (cooldown == 0 && CrowEntity.this.isTargetBlocked(flightTarget)) {
////                    cooldown = 30;
////                    flightTarget = null;
////                }
////            }
////
////            if (targetEntity != null) {
////                if (CrowEntity.this.onGround || flightTarget == null || flightTarget != null && CrowEntity.this.getDistanceSq(flightTarget) < 3) {
////                    Vec3 vec = CrowEntity.this.getBlockInViewAway(targetEntity.getPositionVec(), 0);
////                    if (vec != null && vec.getY() > CrowEntity.this.getPosY()) {
////                        flightTarget = vec;
////                    }
////                }
////                if (CrowEntity.this.getDistance(targetEntity) > 20.0F) {
////                    this.resetTask();
////                }
////            }
////        }
////
////        protected double getTargetDistance() {
////            return 4D;
////        }
////
////        protected AABB getTargetableArea(double targetDistance) {
////            Vec3 renderCenter = new Vec3(CrowEntity.this.getPosX(), CrowEntity.this.getPosY() + 0.5, CrowEntity.this.getPosZ());
////            AABB aabb = new AABB(-2, -2, -2, 2, 2, 2);
////            return aabb.offset(renderCenter);
////        }
////
////
////        public class Sorter implements Comparator<Entity> {
////            private final Entity theEntity;
////
////            public Sorter(Entity theEntityIn) {
////                this.theEntity = theEntityIn;
////            }
////
////            public int compare(Entity p_compare_1_, Entity p_compare_2_) {
////                double d0 = this.theEntity.getDistanceSq(p_compare_1_);
////                double d1 = this.theEntity.getDistanceSq(p_compare_2_);
////                return d0 < d1 ? -1 : (d0 > d1 ? 1 : 0);
////            }
////        }
////    }
//
//
//    private class AIWanderIdle extends Goal {
//        protected final CrowEntity crow;
//        protected double x;
//        protected double y;
//        protected double z;
//        private boolean flightTarget = false;
//        private int orbitResetCooldown = 0;
//        private int maxOrbitTime = 360;
//        private int orbitTime = 0;
//
//        public AIWanderIdle() {
//            super();
//            this.setMutexFlags(EnumSet.of(Flag.MOVE));
//            this.crow = CrowEntity.this;
//        }
//
//        @Override
//        public boolean shouldExecute() {
//            if (orbitResetCooldown < 0) {
//                orbitResetCooldown++;
//            }
//            if ((crow.getAttackTarget() != null && crow.getAttackTarget().isAlive() && !this.crow.isVehicle()) || crow.isSitting() || this.crow.hasPassenger()) {
//                return false;
//            } else {
//                if (this.crow.getRNG().nextInt(20) != 0 && !crow.isFlying() || crow.aiItemFlag) {
//                    return false;
//                }
//                if (this.crow.isChild()) {
//                    this.flightTarget = false;
//                } else if (this.crow.isInWaterOrBubbleColumn()) {
//                    this.flightTarget = true;
//                } else if (this.crow.isOnGround()) {
//                    this.flightTarget = rand.nextInt(10) == 0;
//                } else {
//                    if (orbitResetCooldown == 0 && rand.nextInt(6) == 0) {
//                        orbitResetCooldown = 100 + rand.nextInt(300);
//                        crow.orbitPos = crow.getPosition();
//                        crow.orbitDist = 4 + rand.nextInt(5);
//                        crow.orbitClockwise = rand.nextBoolean();
//                        orbitTime = 0;
//                        maxOrbitTime = (int) (180 + 360 * rand.nextFloat());
//                    }
//                    this.flightTarget = rand.nextInt(5) != 0 && crow.timeFlying < 400;
//                }
//                Vec3 lvt_1_1_ = this.getPosition();
//                if (lvt_1_1_ == null) {
//                    return false;
//                } else {
//                    this.x = lvt_1_1_.x;
//                    this.y = lvt_1_1_.y;
//                    this.z = lvt_1_1_.z;
//                    return true;
//                }
//            }
//        }
//
//        public void tick() {
//            if (orbitResetCooldown > 0) {
//                orbitResetCooldown--;
//            }
//            if (orbitResetCooldown < 0) {
//                orbitResetCooldown++;
//            }
//            if (orbitResetCooldown > 0 && crow.orbitPos != null) {
//                if (orbitTime < maxOrbitTime && !crow.isInWaterOrBubbleColumn()) {
//                    orbitTime++;
//                } else {
//                    orbitTime = 0;
//                    crow.orbitPos = null;
//                    orbitResetCooldown = -400 - rand.nextInt(400);
//                }
//            }
//            if (crow.collidedHorizontally && !crow.onGround) {
//                resetTask();
//            }
//            if (flightTarget) {
//                crow.getMoveHelper().setMoveTo(x, y, z, 1F);
//            } else {
//                if (crow.isFlying() && !crow.onGround) {
//                    if (!crow.isInWaterOrBubbleColumn()) {
//                        //  crow.setDeltaMovement(crow.getDeltaMovement().mul(1F, 0.6F, 1F));
//                    }
//                } else {
//                    this.crow.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, 1F);
//                }
//            }
//            if (!flightTarget && isFlying()) {
//                crow.fallFlag = true;
//                if (crow.onGround) {
//                    crow.setFlying(false);
//                    orbitTime = 0;
//                    crow.orbitPos = null;
//                    orbitResetCooldown = -400 - rand.nextInt(400);
//                }
//            }
//            if (isFlying() && (!world.isEmptyBlock(crow.getPositionUnderneath()) || crow.onGround) && !crow.isInWaterOrBubbleColumn() && crow.timeFlying > 30) {
//                crow.setFlying(false);
//                orbitTime = 0;
//                crow.orbitPos = null;
//                orbitResetCooldown = -400 - rand.nextInt(400);
//            }
//        }
//
//        @Nullable
//        protected Vec3 getPosition() {
//            Vec3 vector3d = crow.getPositionVec();
//            if (orbitResetCooldown > 0 && crow.orbitPos != null) {
//                return crow.getOrbitVec(vector3d, 4 + rand.nextInt(4));
//            }
//            if (crow.isVehicle() || crow.isOverWaterOrVoid()) {
//                flightTarget = true;
//            }
//            if (flightTarget) {
//                if (crow.timeFlying < 340 || crow.isVehicle() || crow.isOverWaterOrVoid()) {
//                    return crow.getBlockInViewAway(vector3d, 0);
//                } else {
//                    return crow.getBlockGrounding(vector3d);
//                }
//            } else {
//                return RandomPositionGenerator.findRandomTarget(this.crow, 10, 7);
//            }
//        }
//
//        public boolean shouldContinueExecuting() {
//            if (flightTarget) {
//                return crow.isFlying() && crow.getDistanceSq(x, y, z) > 4F;
//            } else {
//                return (!this.crow.getNavigator().noPath()) && !this.crow.isVehicle();
//            }
//        }
//
//        public void startExecuting() {
//            if (flightTarget) {
//                crow.setFlying(true);
//                crow.getMoveHelper().setMoveTo(x, y, z, 1F);
//            } else {
//                this.crow.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, 1F);
//            }
//        }
//
//        public void resetTask() {
//            this.crow.getNavigator().clearPath();
//            super.resetTask();
//        }
//    }
//
//
//    class MoveHelper extends MovementController {
//        private final CrowEntity parentEntity;
//
//        public MoveHelper(CrowEntity bird) {
//            super(bird);
//            this.parentEntity = bird;
//        }
//
//        public void tick() {
//            if (this.action == MovementController.Action.MOVE_TO) {
//                Vec3 vector3d = new Vec3(this.posX - parentEntity.getPosX(), this.posY - parentEntity.getPosY(), this.posZ - parentEntity.getPosZ());
//                double d5 = vector3d.length();
//                if (d5 < 0.3) {
//                    this.action = MovementController.Action.WAIT;
//                    parentEntity.setDeltaMovement(parentEntity.getDeltaMovement().scale(0.5D));
//                } else {
//                    double d1 = this.posY - this.parentEntity.getPosY();
//                    float yScale = d1 > 0 || fallFlag ? 1F : 0.7F;
//                    parentEntity.setDeltaMovement(parentEntity.getDeltaMovement().add(vector3d.scale(speed * 0.03D / d5)));
//                    Vec3 vector3d1 = parentEntity.getDeltaMovement();
//                    parentEntity.rotationYaw = -((float) Mth.atan2(vector3d1.x, vector3d1.z)) * (180F / (float) Math.PI);
//                    parentEntity.renderYawOffset = parentEntity.rotationYaw;
//
//                }
//
//            }
//        }
//    }
//
//    private class AITargetItems extends CreatureAITargetItems {
//
//        public AITargetItems(CreatureEntity creature, boolean checkSight, boolean onlyNearby, int tickThreshold, int radius) {
//            super(creature, checkSight, onlyNearby, tickThreshold, radius);
//            this.executionChance = 1;
//        }
//
//        public void resetTask() {
//            super.resetTask();
//            ((CrowEntity) goalOwner).aiItemFlag = false;
//        }
//
//        public boolean shouldExecute() {
//            return super.shouldExecute() && !((CrowEntity) goalOwner).isSitting() && (goalOwner.getAttackTarget() == null || !goalOwner.getAttackTarget().isAlive());
//        }
//
//        public boolean shouldContinueExecuting() {
//            return super.shouldContinueExecuting() && !((CrowEntity) goalOwner).isSitting() && (goalOwner.getAttackTarget() == null || !goalOwner.getAttackTarget().isAlive());
//        }
//
//        @Override
//        protected void moveTo() {
//            CrowEntity crow = (CrowEntity) goalOwner;
//            if (this.targetEntity != null) {
//                crow.aiItemFlag = true;
//                if (this.goalOwner.getDistance(targetEntity) < 2) {
//                    crow.getMoveHelper().setMoveTo(this.targetEntity.getPosX(), targetEntity.getPosY(), this.targetEntity.getPosZ(), 1.5F);
//                }
//                if (this.goalOwner.getDistance(this.targetEntity) > 8 || crow.isFlying()) {
//                    crow.setFlying(true);
//                    float f = (float) (crow.getPosX() - targetEntity.getPosX());
//                    float f1 = 1.8F;
//                    float f2 = (float) (crow.getPosZ() - targetEntity.getPosZ());
//                    float xzDist = Mth.sqrt(f * f + f2 * f2);
//
//                    if (!crow.canEntityBeSeen(targetEntity)) {
//                        crow.getMoveHelper().setMoveTo(this.targetEntity.getPosX(), 1 + crow.getPosY(), this.targetEntity.getPosZ(), 1.5F);
//                    } else {
//                        if (xzDist < 5) {
//                            f1 = 0;
//                        }
//                        crow.getMoveHelper().setMoveTo(this.targetEntity.getPosX(), f1 + this.targetEntity.getPosY(), this.targetEntity.getPosZ(), 1.5F);
//                    }
//                } else {
//                    this.goalOwner.getNavigator().tryMoveToXYZ(this.targetEntity.getPosX(), this.targetEntity.getPosY(), this.targetEntity.getPosZ(), 1.5F);
//                }
//            }
//        }
//
//        @Override
//        public void tick() {
//            super.tick();
//            moveTo();
//        }
//    }
//
//}
