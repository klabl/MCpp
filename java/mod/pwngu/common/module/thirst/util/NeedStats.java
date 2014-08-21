package mod.pwngu.common.module.thirst.util;

import mod.pwngu.common.module.survivial.util.PlayerSpeed;
import mod.pwngu.common.util.MCppDamageSource;
import mod.pwngu.common.util.MCppPotion;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class NeedStats implements IExtendedEntityProperties {

//    private int t = 0;

    public static final String PROPERTY_NAME = "NeedStats";

    private static final int THIRST_LEVEL_WATCHER = 20;
    private static final int FAT_LEVEL_WATCHER = 21;

    public static int regTimer = 80;

    public static void register(EntityPlayer player) {

        player.registerExtendedProperties(NeedStats.PROPERTY_NAME, new NeedStats(player));
    }

    public static NeedStats get(EntityPlayer player) {

        return (NeedStats) player.getExtendedProperties(PROPERTY_NAME);
    }

    private final EntityPlayer player;

    private int timer;
    private float thirstExhaustionLevel;

    public NeedStats(EntityPlayer player) {

        this.player = player;

        player.getDataWatcher().addObject(THIRST_LEVEL_WATCHER, 20);
        player.getDataWatcher().addObject(FAT_LEVEL_WATCHER, 0.0F);
    }

    public void assignStandardValues() {

        setThirstLevel(20);
        setFatLevel(0.0F);
    }

    public void addThirstStats(int thirstLevel) {

        if(player.isPotionActive(MCppPotion.THIRST)) return;

        this.setThirstLevel(Math.min(thirstLevel + this.getThirstLevel(), 20));
    }

    public void addFoodStats(int foodLevel, float fatLevel) {

        FoodStats stats = player.getFoodStats();
        int finalFoodLevel = stats.getFoodLevel() + foodLevel;
        stats.setFoodLevel(Math.min(finalFoodLevel, 20));

        if(finalFoodLevel >= 20)
            setFatLevel(Math.min(
                    getFatLevel() + ((float) (finalFoodLevel - 20.0) * fatLevel),
                    stats.getFoodLevel()));
        else
            setFatLevel(Math.min(getFatLevel() + fatLevel * 0.1F, stats.getFoodLevel()));
    }

    public void addThirstExhaustion(float exhaustion) {

        exhaustion *= 0.5F;

        this.thirstExhaustionLevel = Math.min(this.thirstExhaustionLevel + (exhaustion * getExhaustionModifier()), 40.0F);
    }

    public void onUpdate() {

//        if(player.capabilities.isCreativeMode) return;

        EnumDifficulty enumdifficulty = player.worldObj.difficultySetting;

        FoodStats foodStats = player.getFoodStats();

//        t++;
//        if(t >= 20) {
//
//            MCpp.log.inf("FoodLevel --------- " + foodStats.getFoodLevel());
//            MCpp.log.inf("ThirstLevel ------- " + getThirstLevel());
//            MCpp.log.inf("+++++++++++++++++++");
//            MCpp.log.inf("FatLevel ---------- " + getFatLevel());
//            MCpp.log.inf("+++++++++++++++++++");
//            MCpp.log.inf("FoodExhaustion ---- " + foodStats.foodExhaustionLevel);
//            MCpp.log.inf("ThirstExhaustion -- " + thirstExhaustionLevel);
//            MCpp.log.inf("###################");
//
//            t -= 20;
//        }

        if (foodStats.foodExhaustionLevel > 4.0F) {

            foodStats.foodExhaustionLevel -= 4.0F;

            if (enumdifficulty != EnumDifficulty.PEACEFUL) {

                if(MathHelper.ceiling_float_int(getFatLevel()) >= foodStats.foodLevel) {

                    setFatLevel(getFatLevel() - 0.25F);
                } else {

                    foodStats.foodLevel = Math.max(foodStats.foodLevel - 1, 0);
                }
            }
        }

        if (this.thirstExhaustionLevel > 4.0F) {

            this.thirstExhaustionLevel -= 4.0F;

            if (enumdifficulty != EnumDifficulty.PEACEFUL) {

                setThirstLevel(Math.max(this.getThirstLevel() - 1, 0));
            }
        }


        int thirstLevel = getThirstLevel();
        if (thirstLevel <= 8 && thirstLevel > 4) {

            PlayerSpeed.get(player).updateMultiplier("thirst", 0.75F);
        } else if (thirstLevel <= 4) {

            PlayerSpeed.get(player).updateMultiplier("thirst", 0.5F);
        } else {

            PlayerSpeed.get(player).removeMultiplier("thirst");
        }

        if(thirstLevel <= 6) {

            if (player.isSprinting()) player.setSprinting(false);
        }

        if(player.shouldHeal() && foodStats.foodLevel >= 12 && this.getThirstLevel() >= 12 &&
                player.worldObj.getGameRules().getGameRuleBooleanValue("naturalRegeneration")) {

            this.timer++;

            if(this.timer >= regTimer) {

                if(!player.isPotionActive(MCppPotion.WOUNDED)) {

                    player.heal(1.0F);
                    player.addExhaustion(3.0F);
                    this.timer = 0;
                } else {

                    this.timer -= 20;
                }
            }
        } else if(foodStats.foodLevel <= 0 && this.getThirstLevel() >= 0) {

            this.timer++;

            if(this.timer >= 80) {

                player.attackEntityFrom(DamageSource.starve, 1.0F);
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 160, 0));

                this.timer = 0;
            }

        } else if(foodStats.foodLevel >= 0 && this.getThirstLevel() <= 0) {

            this.timer++;

            if(this.timer >= 80) {

                player.attackEntityFrom(MCppDamageSource.THIRST, 1.0F);
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 160, 0));

                this.timer = 0;
            }
        } else if(foodStats.foodLevel <= 0 && this.getThirstLevel() <= 0) {

            this.timer++;

            if(this.timer >= 5) {

                player.attackEntityFrom(MCppDamageSource.THIRST_AND_STARVE, 1.0F);
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 160, 0));

                this.timer = 0;
            }
        } else this.timer = 0;
    }

    public int getThirstLevel() {

        return player.getDataWatcher().getWatchableObjectInt(NeedStats.THIRST_LEVEL_WATCHER);
    }

    public float getFatLevel() {

        return player.getDataWatcher().getWatchableObjectFloat(NeedStats.FAT_LEVEL_WATCHER);
    }

    public void setFatLevel(float fat) {

        player.getDataWatcher().updateObject(FAT_LEVEL_WATCHER, fat);
    }

    public boolean needToDrink() {

        return getThirstLevel() < 20;
    }

    private void setThirstLevel(int level) {

        player.getDataWatcher().updateObject(THIRST_LEVEL_WATCHER, level);
    }

    private float getExhaustionModifier() {

        if(player.isInsideOfMaterial(Material.water)) return 0.0F;
        if(!player.worldObj.isDaytime()) return 1.0F;

        float temperature = player.getEntityWorld().getBiomeGenForCoords((int) player.posX, (int) player.posZ).temperature;

        if(player.worldObj.canBlockSeeTheSky((int) player.posX, (int) player.posX, (int) player.posZ))
            return temperature < 0.9F ? 1.0F :(temperature < 1.5F ? 1.3F : 2.0F);
        else
            return temperature < 0.9F ? 1.0F :(temperature < 1.5F ? 1.15F : 1.5F);
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {

        NBTTagCompound properties = new NBTTagCompound();

        properties.setInteger("needTickTimer", this.timer);
        properties.setInteger("thirstLevel", this.getThirstLevel());
        properties.setFloat("thirstExhaustionLevel", this.thirstExhaustionLevel);
        properties.setFloat("fatLevel", getFatLevel());

        compound.setTag(PROPERTY_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

        if(!compound.hasKey(PROPERTY_NAME)) return;

        NBTTagCompound properties = compound.getCompoundTag(PROPERTY_NAME);

        this.timer = properties.getInteger("needTickTimer");
        this.thirstExhaustionLevel = properties.getFloat("thirstExhaustionLevel");
        player.getDataWatcher().updateObject(THIRST_LEVEL_WATCHER, properties.getInteger("thirstLevel"));
        player.getDataWatcher().updateObject(FAT_LEVEL_WATCHER, properties.getFloat("fatLevel"));
    }

    @Override
    public void init(Entity entity, World world) {

    }
}
