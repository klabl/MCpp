package mod.pwngu.common.module.thirst.event;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.item.MCppItemFood;
import mod.pwngu.common.main.MCpp;
import mod.pwngu.common.module.survivial.util.PlayerSpeed;
import mod.pwngu.common.module.thirst.item.ItemDrink;
import mod.pwngu.common.module.thirst.util.NeedStats;
import mod.pwngu.common.util.MCppPotion;
import mod.pwngu.core.event.MCppPlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import net.minecraftforge.event.world.BlockEvent;

public class MCppThirstEventHandler {

    private int drinkingTickTimer = 0;

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent ev) {

        if(ev.phase != TickEvent.Phase.END) return;
        if(ev.player.worldObj.isRemote) return;

        ev.player.addExhaustion(0.00333F); // about 4 exhaustion per minute

        if(ev.player.isSneaking() && ev.player.isInWater()) {

            drinkingTickTimer++;

            if(drinkingTickTimer >= 20) {

                if(NeedStats.get(ev.player).needToDrink() && !ev.player.isPotionActive(MCppPotion.THIRST)) {

                    NeedStats.get(ev.player).addThirstStats(1);
                    //TODO play drinking sound
                    if (ev.player.getRNG().nextFloat() < 0.33F && !ev.player.isPotionActive(MCppPotion.THIRST)) {

                        ev.player.addPotionEffect(new PotionEffect(MCppPotion.THIRST.id, 300, 1));
                    }
                }
                drinkingTickTimer = 0;
            }
        } else drinkingTickTimer = 0;

        ItemStack stack = ev.player.getItemInUse();

        if(stack != null && ItemDrink.vanillaDrinks.containsKey(stack.getItem()) && ev.player.getItemInUseDuration() == (stack.getMaxItemUseDuration() - 1)) {

            ItemDrink drink = ItemDrink.getDrink(stack.getItem());
            if (drink != null) {

                NeedStats.get(ev.player).addThirstStats(drink.getThirstHealAmount(stack));
                if (stack.getItem() instanceof ItemPotion && stack.getItemDamage() == 0 && ev.player.getRNG().nextFloat() <= 0.5F) {

                    ev.player.addPotionEffect(new PotionEffect(MCppPotion.THIRST.id, 600));
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerStartUsing(PlayerUseItemEvent.Start ev) {

        if(ev.item.getItem().equals(Items.potionitem) && ev.item.getItemDamage() == 0) {

            if(!NeedStats.get(ev.entityPlayer).needToDrink() || ev.entityPlayer.isPotionActive(MCppPotion.THIRST)) ev.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerBlockBreak(BlockEvent.BreakEvent ev) {

        ev.getPlayer().addExhaustion(0.075F);
    }

    @SubscribeEvent
    public void onPlayerRespawn(cpw.mods.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent ev) {

        NeedStats.get(ev.player).assignStandardValues();
    }

    @SubscribeEvent
    public void onCrafting(cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent ev) {

        if(ev.crafting.getItem().equals(MCppItem.CLEAN_WATER_BOTTLE)) {

            for(int i = 0; i < ev.craftMatrix.getSizeInventory(); i++) {

                ItemStack stack = ev.craftMatrix.getStackInSlot(i);

                if(stack != null) {

                    if(stack.getItem().equals(MCppItem.FILTER)) {

                        stack.setItemDamage(stack.getItemDamage() + 1);
                        if(stack.getItemDamage() < stack.getItem().getMaxDamage()) stack.stackSize++;
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityConstruction(EntityEvent.EntityConstructing ev) {

        if(ev.entity instanceof EntityPlayer) {

            NeedStats.register((EntityPlayer) ev.entity);
            PlayerSpeed.register((EntityPlayer) ev.entity);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent ev) {

        if (!ev.entity.worldObj.isRemote && ev.entity instanceof EntityPlayer) {

            NBTTagCompound playerData = MCpp.proxy.retrieveEntityData(((EntityPlayer) ev.entity).getDisplayName());

            if (playerData != null) {

                ev.entity.getExtendedProperties(NeedStats.PROPERTY_NAME).loadNBTData(playerData);
            }
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {

        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {

            NBTTagCompound playerData = new NBTTagCompound();
            event.entity.getExtendedProperties(NeedStats.PROPERTY_NAME).saveNBTData(playerData);
            MCpp.proxy.storeEntityData(((EntityPlayer) event.entity).getDisplayName(), playerData);
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent ev) {

        if (ev.entityLiving.isPotionActive(MCppPotion.THIRST) && ev.entityLiving.getActivePotionEffect(MCppPotion.THIRST).getDuration() == 0)
            ev.entityLiving.removePotionEffect(MCppPotion.THIRST.id);

        if(!ev.entity.worldObj.isRemote && ev.entityLiving instanceof EntityPlayer) {

            if(ev.entityLiving.isPotionActive(MCppPotion.THIRST)) {

                int amp = ev.entityLiving.getActivePotionEffect(MCppPotion.THIRST).getAmplifier();
                for(int i = 0; i < amp + 1; i++)
                    NeedStats.get((EntityPlayer) ev.entityLiving).addThirstExhaustion(0.025F);
            }

            if(ev.entityLiving.isPotionActive(Potion.hunger)) {

                int amp = ev.entityLiving.getActivePotionEffect(Potion.hunger).getAmplifier();
                for(int i = 0; i < amp + 1; i++)
                    NeedStats.get((EntityPlayer) ev.entityLiving).addThirstExhaustion(-0.025F);
            }
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent ev) {

        if(ev.entity.worldObj.isRemote) return;

        if(ev.source.getSourceOfDamage() instanceof EntityPlayer) {

            int foodLevel = ((EntityPlayer) ev.source.getSourceOfDamage()).getFoodStats().getFoodLevel();

            if(foodLevel <= 8 && foodLevel > 4) {

                ev.ammount *= 0.75F;
            } else if(foodLevel <= 4) {

                ev.ammount *= 0.5F;
            }
        }
    }

    @SubscribeEvent
    public void onFoodExhaustion(MCppPlayerEvent.FoodExhaustion ev) {

        NeedStats.get(ev.entityPlayer).addThirstExhaustion(ev.exhaustion);
    }

    @SubscribeEvent
    public void onFoodUpdate(MCppPlayerEvent.FoodUpdate ev) {

        NeedStats.get(ev.entityPlayer).onUpdate();
        ev.setResult(Event.Result.ALLOW);
    }

    @SubscribeEvent
    public void onNeedFoodCheck(MCppPlayerEvent.NeedFood ev) {

        if(ItemDrink.isFoodAndDrink(ev.entityPlayer.getHeldItem().getItem())) {

            ev.needsFood = ev.needsFood && !ev.entityPlayer.isPotionActive(Potion.hunger) ||
                           NeedStats.get(ev.entityPlayer).needToDrink() && !ev.entityPlayer.isPotionActive(MCppPotion.THIRST);

            /*
            ** simplified from: **
                if (ev.needsFood && !ev.entityPlayer.isPotionActive(Potion.hunger)) ev.needsFood = true;
                else if (NeedStats.get(ev.entityPlayer).needToDrink() && !ev.entityPlayer.isPotionActive(MCppPotion.THIRST))
                    ev.needsFood = true;
                else ev.needsFood = false;
             */
        } else {

            boolean isDrink = ItemDrink.isDrink(ev.entityPlayer.getHeldItem().getItem());

            if(!isDrink && !ev.needsFood) return;
            ev.needsFood = !(ev.entityPlayer.isPotionActive(Potion.hunger) && !isDrink && !ev.needsFood) &&
                           !(ev.entityPlayer.isPotionActive(MCppPotion.THIRST) && isDrink) &&
                           !(isDrink && !NeedStats.get(ev.entityPlayer).needToDrink());

            /*
            ** simplified from: **

                if(!isDrink && !ev.needsFood) return;
                if(ev.entityPlayer.isPotionActive(Potion.hunger) && !isDrink && !ev.needsFood)
                    ev.needsFood = false;
                else if(ev.entityPlayer.isPotionActive(MCppPotion.THIRST) && isDrink)
                    ev.needsFood = false;
                else if(isDrink && !NeedStats.get(ev.entityPlayer).needToDrink())
                    ev.needsFood = false;
                else ev.needsFood = true;
            */
        }
    }

    @SubscribeEvent
    public void onPlayerReplenishWithItem(MCppPlayerEvent.FoodReplenishWithItem ev) {

        if (!ev.entityPlayer.worldObj.isRemote) {

            ItemDrink drink = ItemDrink.getDrink(ev.itemFood);
            if (drink != null)
                NeedStats.get(ev.entityPlayer).addThirstStats(drink.getThirstHealAmount(ev.foodStack));

            MCppItemFood food = MCppItemFood.getFood(ev.itemFood);
            if(food != null) {

                NeedStats.get(ev.entityPlayer).addFoodStats(food.func_150905_g(ev.foodStack), food.func_150906_h(ev.foodStack));
            }
        }
    }

    @SubscribeEvent
    public void onPlayerFoodReplenish(MCppPlayerEvent.FoodReplenish ev) {

        ev.setCanceled(true);
    }
}
