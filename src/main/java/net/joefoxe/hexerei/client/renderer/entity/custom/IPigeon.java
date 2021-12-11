//package net.joefoxe.hexerei.client.renderer.entity.custom;
//
//import net.joefoxe.hexerei.client.renderer.entity.custom.ai.IPigeonFoodHandler;
//import net.joefoxe.hexerei.client.renderer.entity.custom.ai.Skill;
//import net.joefoxe.hexerei.client.renderer.entity.custom.ai.SkillInstance;
//import net.joefoxe.hexerei.util.DataKey;
//import net.joefoxe.hexerei.util.EnumMode;
//import net.joefoxe.hexerei.util.PigeonLevel;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraftforge.registries.RegistryObject;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Supplier;
//
//public interface IPigeon {
//
//    public AbstractPigeonEntity getPigeon();
//    public void untame();
//    public boolean canInteract(LivingEntity playerIn);
//    public EnumMode getMode();
//    public PigeonLevel getLevel();
//    //    public PigeonStage getStage();
//    public void increaseLevel(PigeonLevel.Type typeIn);
//
//    /**
//     * Convenience method to get the level of a skill
//     * @param skillGetter A getter function, typically a {@link RegistryObject <com.platinumg17.rigoranthusemortisreborn.api.registry.Skill>} would be provided
//     * @return The level of the skill
//     */
//    default int getLevel(Supplier<? extends Skill> skillGetter) {
//        return this.getLevel(skillGetter.get());
//    }
//
//    /**
//     * Returns the level of the given skill
//     * @param skillIn The {@link Skill}
//     * @return The level of the skill
//     */
//    public int getLevel(Skill skillIn);
//
//    default Optional<SkillInstance> getSkill(Supplier<? extends Skill> skillGetter) {
//        return this.getSkill(skillGetter.get());
//    }
//
//    public Optional<SkillInstance> getSkill(Skill skillIn);
//
//    public List<IPigeonFoodHandler> getFoodHandlers();
//
//    @Deprecated
//    public <T> void setData(DataKey<T> key, T value);
//    @Deprecated
//    public <T> void setDataIfEmpty(DataKey<T> key, T value);
//    @Deprecated
//    public <T> T getData(DataKey<T> key);
//    @Deprecated
//    public <T> T getDataOrGet(DataKey<T> key, Supplier<T> other);
//    @Deprecated
//    public <T> T getDataOrDefault(DataKey<T> key, T other);
//    @Deprecated
//    public <T> boolean hasData(DataKey<T> key);
//}