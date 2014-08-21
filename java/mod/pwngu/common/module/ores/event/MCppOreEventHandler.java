package mod.pwngu.common.module.ores.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.main.MCpp;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import java.util.ArrayList;
import java.util.Random;

public class MCppOreEventHandler {

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent ev) {

        if(ev.drops.isEmpty() || !(ev.drops.get(0).getItem() instanceof ItemBlock) || ev.isSilkTouching) return;

        String name = ev.block.getUnlocalizedName().toLowerCase();
        MCpp.log.inf(name);

        if(name.endsWith("oreiron")) {

            fillLootList(ev.drops, MCppItem.IRON_PEBBLE, fortuneRandomCount(6, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.endsWith("oregold")) {

            fillLootList(ev.drops, MCppItem.GOLD_PEBBLE, fortuneRandomCount(6, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.endsWith("orecopper")) {

            fillLootList(ev.drops, MCppItem.COPPER_PEBBLE, fortuneRandomCount(6, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.endsWith("oretin")) {

            fillLootList(ev.drops, MCppItem.TIN_PEBBLE, fortuneRandomCount(6, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.endsWith("orelead")) {

            fillLootList(ev.drops, MCppItem.LEAD_PEBBLE, fortuneRandomCount(6, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.endsWith("oreuran")) {

            fillLootList(ev.drops, MCppItem.URANIUM_PEBBLE, fortuneRandomCount(6, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.endsWith("oresilver")) {

            fillLootList(ev.drops, MCppItem.SILVER_PEBBLE, fortuneRandomCount(6, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        }
    }

    private void fillLootList(ArrayList<ItemStack> list, Item item, int count) {

        list.clear();
        for(int i = 0; i < count; ++i) {

            ItemStack drop = new ItemStack(item, 1);
            list.add(drop);
        }
    }

    private int fortuneRandomCount(int base, int fortune, Random rand) {

        if (fortune <= 0) return base;

        return base * Math.max(rand.nextInt(fortune + 2), 1);

        /*
        int j = fortune.nextInt(fortune + 2) - 1;

        if (j < 0)
        {
            j = 0;
        }

        return base * (j + 1);
         */
    }
}
