package mod.pwngu.common.module.ores.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.main.MCpp;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Random;

public class MCppOreEventHandler {

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent ev) {

        if(ev.drops.isEmpty() || !(ev.drops.get(0).getItem() instanceof ItemBlock) || ev.isSilkTouching) return;

        String name = OreDictionary.getOreName(OreDictionary.getOreID(ev.drops.get(0)));
        MCpp.log.inf(name);

        if(name.equals("oreIron")) {

            fillLootList(ev.drops, MCppItem.IRON_CHUNK, fortuneRandomCount(1, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.equals("oreSilver")) {

            fillLootList(ev.drops, MCppItem.SILVER_CHUNK, fortuneRandomCount(1, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.equals("oreGold")) {

            fillLootList(ev.drops, MCppItem.GOLD_CHUNK, fortuneRandomCount(1, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.equals("orePlatin")) {

            fillLootList(ev.drops, MCppItem.PLATINUM_CHUNK, fortuneRandomCount(1, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.equals("oreCopper")) {

            fillLootList(ev.drops, MCppItem.COPPER_CHUNK, fortuneRandomCount(1, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.equals("oreTin")) {

            fillLootList(ev.drops, MCppItem.TIN_CHUNK, fortuneRandomCount(1, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.equals("oreLead")) {

            fillLootList(ev.drops, MCppItem.LEAD_CHUNK, fortuneRandomCount(1, ev.fortuneLevel, ev.world.rand));
            ev.dropChance = 1.0f;
        } else if(name.equals("oreUranium")) {

            fillLootList(ev.drops, MCppItem.URANIUM_CHUNK, fortuneRandomCount(1, ev.fortuneLevel, ev.world.rand));
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
