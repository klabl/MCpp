package mod.pwngu.common.module.survivial.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.module.thirst.util.NeedStats;
import mod.pwngu.common.module.survivial.util.PlayerSpeed;
import mod.pwngu.common.util.MCppPotion;
import mod.pwngu.common.util.MCppPotionEffectFactory;
import mod.pwngu.core.event.MCppPlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class MCppSurvivalEventHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void updateMoveSpeed(TickEvent.PlayerTickEvent ev) {

        PlayerSpeed.get(ev.player).onUpdate();
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent ev) {

        if(!ev.entityPlayer.onGround && ev.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) ev.setCanceled(true);
    }

    @SubscribeEvent
    public void onPlayerBlockBreakWithSpeed(PlayerEvent.BreakSpeed ev) {

        if(ev.entityPlayer.getHeldItem() != null &&
                ev.entityPlayer.getHeldItem().getItem().func_150893_a(ev.entityPlayer.getHeldItem(), ev.block) > 1.0F) {

            ev.newSpeed = ev.originalSpeed * 0.5F;
        } else {

            ev.newSpeed = ev.originalSpeed * 0.2F;
        }

        NeedStats stats = NeedStats.get(ev.entityPlayer);
        if(stats.getThirstLevel() <= 8 && stats.getThirstLevel() > 6) {

            ev.newSpeed *= 0.75F;
        } else if(stats.getThirstLevel() <= 4) {

            ev.newSpeed *= 0.5F;
        }
    }

    @SubscribeEvent
    public void onPlayerSleepInBed(PlayerSleepInBedEvent ev) {

        if(!ev.isCanceled()) ev.entityPlayer.removePotionEffect(MCppPotion.WOUNDED.id);
    }

    @SubscribeEvent
    public void onNeedFoodCheck(MCppPlayerEvent.NeedFood ev) {

        if(ev.entityPlayer.getHeldItem().getItem().equals(MCppItem.BANDAGE)) {

            ev.needsFood = ev.entityPlayer.isPotionActive(MCppPotion.WOUNDED);
        }
    }

    @SubscribeEvent
    public void onEntityConstruction(EntityEvent.EntityConstructing ev) {

        if(ev.entity instanceof EntityPlayer) {

            PlayerSpeed.register((EntityPlayer) ev.entity);
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent ev) {

        if(ev.entity.worldObj.isRemote) return;

        if(ev.source.getSourceOfDamage() instanceof EntityPlayer) {

            int foodLevel = ((EntityPlayer) ev.source.getSourceOfDamage()).getFoodStats().getFoodLevel();

            if(foodLevel <= 8 && foodLevel > 6) {

                ev.ammount *= 0.75F;
            } else if(foodLevel <= 4) {

                ev.ammount *= 0.5F;
            }
        }

        if(ev.entityLiving instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) ev.entityLiving;

            if(!ev.entityLiving.isPotionActive(MCppPotion.WOUNDED)) {

                if(ev.ammount >= 3.0 ||
                        ((ev.entityLiving.getHealth() - ev.ammount) <= 6.0F) ||
                        ev.source.equals(DamageSource.inFire) ||
                        ev.source.equals(DamageSource.onFire) ||
                        ev.source.equals(DamageSource.lava)) {

                    ev.entityLiving.addPotionEffect(MCppPotionEffectFactory.createPotionEffectWounded());
                }
            }

            if(player.getItemInUse() != null && player.getItemInUse().getItem().equals(MCppItem.BANDAGE)) {

                player.stopUsingItem();
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent ev) {

        if(ev.entityLiving instanceof EntityPlayer && !ev.entityLiving.worldObj.isRemote) {

            if(ev.entityLiving.isPotionActive(MCppPotion.WOUNDED))
                PlayerSpeed.get((EntityPlayer) ev.entityLiving).updateMultiplier("wounded", 0.8F);
            else
                PlayerSpeed.get((EntityPlayer) ev.entityLiving).updateMultiplier("wounded", 1.0F);
        }
    }
}
