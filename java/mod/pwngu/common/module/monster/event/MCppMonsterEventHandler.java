package mod.pwngu.common.module.monster.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import mod.pwngu.common.module.survivial.util.PlayerSpeed;
import mod.pwngu.common.util.MCppDamageSource;
import mod.pwngu.common.util.MCppPotion;
import mod.pwngu.common.util.MCppPotionEffectFactory;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.WorldEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MCppMonsterEventHandler {

//    private static final List<BiomeGenBase.SpawnListEntry> baseSpawns = new ArrayList<BiomeGenBase.SpawnListEntry>();
    private static final List<BiomeGenBase.SpawnListEntry> surfaceSpawns = new ArrayList<BiomeGenBase.SpawnListEntry>();
    private static final List<BiomeGenBase.SpawnListEntry> undergroundSpawns = new ArrayList<BiomeGenBase.SpawnListEntry>();

    private static final BiomeGenBase.SpawnListEntry skeleton = new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 250, 4, 4);
//    private static final BiomeGenBase.SpawnListEntry spiderBase = new BiomeGenBase.SpawnListEntry(EntitySpider.class, 100, 4, 4);

    static {

//        baseSpawns.add(spiderBase);
//        baseSpawns.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 100, 4, 4));
//        baseSpawns.add(new BiomeGenBase.SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
//        baseSpawns.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 100, 4, 4));
//        baseSpawns.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));
//        baseSpawns.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 10, 1, 4));
//        baseSpawns.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 5, 1, 1));

        surfaceSpawns.add(new BiomeGenBase.SpawnListEntry(EntityZombie.class, 400, 4, 6));
        surfaceSpawns.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 80, 4, 4));
        surfaceSpawns.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 15, 3, 4));
        surfaceSpawns.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 5, 1, 1));

        undergroundSpawns.add(new BiomeGenBase.SpawnListEntry(EntitySpider.class, 300, 4, 6));
        undergroundSpawns.add(new BiomeGenBase.SpawnListEntry(EntityCreeper.class, 100, 1, 2));
        undergroundSpawns.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 100, 4, 4));
        undergroundSpawns.add(new BiomeGenBase.SpawnListEntry(EntityCaveSpider.class, 50, 4, 8));
        undergroundSpawns.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 15, 3, 4));
    }


    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent ev) {

        if (ev.phase == TickEvent.Phase.END && !ev.player.worldObj.isRemote) {

            ItemStack stack = ev.player.getItemInUse();

            if(stack != null && stack.getItem().equals(Items.golden_apple) &&
                    ev.player.getItemInUseDuration() == (stack.getMaxItemUseDuration() - 1)) {

                if(ev.player.isPotionActive(MCppPotion.ZOMBIEFICATION)) {

                    ev.player.removePotionEffect(MCppPotion.ZOMBIEFICATION.id);

                    PotionEffect zombiefication = ev.player.getActivePotionEffect(MCppPotion.ZOMBIEFICATION);

                    if(zombiefication.getAmplifier() - 1 >= 0) {

                        ev.player.addPotionEffect(MCppPotionEffectFactory.createPotionEffectZombiefication(
                                Math.min(zombiefication.getDuration() + 12000, 24000), zombiefication.getAmplifier() - 1));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent ev) {

        if(ev.source.getSourceOfDamage() == null || ev.entityLiving.worldObj.isRemote) return;

        EnumDifficulty difficulty = ev.entity.worldObj.difficultySetting;

        if(ev.source.getSourceOfDamage().getClass() == EntitySpider.class) {

            if(difficulty == EnumDifficulty.NORMAL) {

                ev.entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 100, 0));
            } else if(difficulty == EnumDifficulty.HARD) {

                ev.entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 150, 0));
            }

        } else if(ev.source.getSourceOfDamage().getClass() == EntityCaveSpider.class) {

//            ev.entityLiving.removePotionEffect(Potion.poison.id);
            if(difficulty == EnumDifficulty.EASY) {

                ev.entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 140, 0));
            } else if(difficulty == EnumDifficulty.NORMAL) {

//                ev.entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 140, 1));
                ev.entityLiving.addPotionEffect(new PotionEffect(Potion.blindness.id, 140, 0));
            } else if(difficulty == EnumDifficulty.HARD) {

//                ev.entityLiving.addPotionEffect(new PotionEffect(Potion.poison.id, 300, 1));
                ev.entityLiving.addPotionEffect(new PotionEffect(Potion.blindness.id, 300, 0));
            }
        } else if(ev.source.getSourceOfDamage() instanceof EntityZombie && ev.entityLiving instanceof EntityPlayer) {

            EntityPlayer player = (EntityPlayer) ev.entityLiving;

            PotionEffect zombiefication = null;
            if(ev.entityLiving.isPotionActive(MCppPotion.ZOMBIEFICATION.id))
                zombiefication = player.getActivePotionEffect(MCppPotion.ZOMBIEFICATION);

            int immunity = 0;

            for(int i = 0; i < player.inventory.armorInventory.length; i++) {

                if(player.inventory.armorInventory[i] != null) immunity += 15;
            }

            immunity += (player.getTotalArmorValue() * 2);

            // apply zombiefication
            if(player.getRNG().nextInt(100) > immunity) {

                if (zombiefication == null) { // no zombiefication

                    player.addPotionEffect(MCppPotionEffectFactory.createPotionEffectZombiefication(24000, 0));
                    player.addPotionEffect(MCppPotionEffectFactory.createPotionEffectConfusion(140));
                } else if(zombiefication.getAmplifier() == 2) { // zombiefication is in the final state

                    if(zombiefication.getDuration() <= 1200) {

                        ev.entityLiving.attackEntityFrom(MCppDamageSource.ZOMBIEFICATION, 400.0F);
                        EntityZombie zombie = new EntityZombie(ev.entity.worldObj);
                        zombie.copyLocationAndAnglesFrom(ev.entityLiving);
                        ev.entity.worldObj.spawnEntityInWorld(zombie);
                    } else {

                        player.removePotionEffect(MCppPotion.ZOMBIEFICATION.id);
                        player.addPotionEffect(MCppPotionEffectFactory.createPotionEffectZombiefication(
                                1200, 2));
                    }
                } else {// zombiefication is not in final state

                    player.removePotionEffect(MCppPotion.ZOMBIEFICATION.id);
                    player.addPotionEffect(MCppPotionEffectFactory.createPotionEffectZombiefication(
                            zombiefication.getDuration(), zombiefication.getAmplifier() + 1));
                    player.addPotionEffect(MCppPotionEffectFactory.createPotionEffectConfusion(140));
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent ev) {

        if (!ev.entity.worldObj.isRemote && ev.entityLiving instanceof EntityPlayer) {

            if (ev.entityLiving.isPotionActive(MCppPotion.ZOMBIEFICATION.id)) {

                PotionEffect zombiefication = ev.entityLiving.getActivePotionEffect(MCppPotion.ZOMBIEFICATION);

                if(zombiefication.getAmplifier() == 2) {

                    if (zombiefication.getDuration() <= 10) { // kill player

                        ev.entityLiving.attackEntityFrom(MCppDamageSource.ZOMBIEFICATION, 400.0F);
                        EntityZombie zombie = new EntityZombie(ev.entity.worldObj);
                        zombie.copyLocationAndAnglesFrom(ev.entityLiving);
                        ev.entity.worldObj.spawnEntityInWorld(zombie);
                    } else if(zombiefication.getDuration() <= 1200) { // add confusion

                        ev.entityLiving.addPotionEffect(MCppPotionEffectFactory.createPotionEffectConfusion(160));
                        PlayerSpeed.get((EntityPlayer) ev.entityLiving).updateMultiplier("zombiefication", 0.6F);
                    } else {

                        PlayerSpeed.get((EntityPlayer) ev.entityLiving).updateMultiplier("zombiefication", 0.9F);
                    }
                } else {

                    if(zombiefication.getDuration() <= 10) { // increase zombiefication amplifier

                        ev.entityLiving.removePotionEffect(MCppPotion.ZOMBIEFICATION.id);
                        ev.entityLiving.addPotionEffect(MCppPotionEffectFactory.createPotionEffectZombiefication(
                                24000, zombiefication.getAmplifier() + 1));
                    }
                    PlayerSpeed.get((EntityPlayer) ev.entityLiving).removeMultiplier("zombiefication");
                }
            }
        }
    }

    @SubscribeEvent
    public void onSpawnCheck(WorldEvent.PotentialSpawns ev) {

//        MCpp.log.inf("checking spawn for block (x:" + ev.x + " y:" + ev.y + " z:" + ev.z + ")");
//        MCpp.log.inf("block is: " + ev.world.getBlock(ev.x, ev.y, ev.z));

        if(ev.type != EnumCreatureType.monster || ev.world.provider.dimensionId != 0) return;

        //TODO remove next line...
        ev.list.clear();
        //...

        Set<BiomeGenBase.SpawnListEntry> set = new HashSet<BiomeGenBase.SpawnListEntry>(ev.list);

//        set.removeAll(baseSpawns);

        if(ev.y < 60 && !ev.world.canBlockSeeTheSky(ev.x, ev.y, ev.z)) {

            set.addAll(undergroundSpawns);
            if(ev.world.getBlock(ev.x, ev.y - 1, ev.z).equals(Blocks.stonebrick))
                set.add(skeleton);

        } else if(ev.y >= 60) {

            set.addAll(surfaceSpawns);
        }

//        ev.list.clear();
        ev.list.addAll(set);
//        MCpp.log.inf("Spawning: " + getEntitiesFromSpawnList(ev.list));
    }

//    private String getEntitiesFromSpawnList(List<BiomeGenBase.SpawnListEntry> list) {
//
//        StringBuilder sb = new StringBuilder().append("{");
//        for(BiomeGenBase.SpawnListEntry entry : list) {
//
//            sb.append(entry.entityClass.getSimpleName()).append(":").append(entry.itemWeight).append(", ");
//        }
//        return sb.append("}").toString();
//    }
}
