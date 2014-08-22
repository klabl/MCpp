package mod.pwngu.common.module.ores;

import cpw.mods.fml.common.registry.GameRegistry;
import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.module.MCppModule;
import mod.pwngu.common.module.ores.event.MCppOreEventHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.List;

import static mod.pwngu.common.item.MCppItem.*;

public class MCppModuleOre extends MCppModule {

    public MCppModuleOre(boolean enable) {

        super("ORE", enable);
    }

    @Override
    public void preRegister() {

    }

    @Override
    public void registerItems() {

        // Pebbles

        GameRegistry.registerItem(IRON_PEBBLE, IRON_PEBBLE.name.itemName);
        OreDictionary.registerOre(IRON_PEBBLE.name.itemName, IRON_PEBBLE);

        GameRegistry.registerItem(SILVER_PEBBLE, SILVER_PEBBLE.name.itemName);
        OreDictionary.registerOre(SILVER_PEBBLE.name.itemName, SILVER_PEBBLE);

        GameRegistry.registerItem(GOLD_PEBBLE, GOLD_PEBBLE.name.itemName);
        OreDictionary.registerOre(GOLD_PEBBLE.name.itemName, GOLD_PEBBLE);

        GameRegistry.registerItem(COPPER_PEBBLE, COPPER_PEBBLE.name.itemName);
        OreDictionary.registerOre(COPPER_PEBBLE.name.itemName, COPPER_PEBBLE);

        GameRegistry.registerItem(TIN_PEBBLE, TIN_PEBBLE.name.itemName);
        OreDictionary.registerOre(TIN_PEBBLE.name.itemName, TIN_PEBBLE);

        GameRegistry.registerItem(LEAD_PEBBLE, LEAD_PEBBLE.name.itemName);
        OreDictionary.registerOre(LEAD_PEBBLE.name.itemName, LEAD_PEBBLE);

        GameRegistry.registerItem(URANIUM_PEBBLE, URANIUM_PEBBLE.name.itemName);
        OreDictionary.registerOre(URANIUM_PEBBLE.name.itemName, URANIUM_PEBBLE);


        //Chunks

        GameRegistry.registerItem(IRON_CHUNK, IRON_CHUNK.name.itemName);
        OreDictionary.registerOre("oreIron", IRON_CHUNK);

        GameRegistry.registerItem(SILVER_CHUNK, SILVER_CHUNK.name.itemName);
        OreDictionary.registerOre("oreSilver", SILVER_CHUNK);

        GameRegistry.registerItem(GOLD_CHUNK, GOLD_CHUNK.name.itemName);
        OreDictionary.registerOre("oreGold", GOLD_CHUNK);

        GameRegistry.registerItem(PLATINUM_CHUNK, PLATINUM_CHUNK.name.itemName);
        OreDictionary.registerOre("oreGold", PLATINUM_CHUNK);

        GameRegistry.registerItem(COPPER_CHUNK, COPPER_CHUNK.name.itemName);
        OreDictionary.registerOre("oreCopper", COPPER_CHUNK);

        GameRegistry.registerItem(TIN_CHUNK, TIN_CHUNK.name.itemName);
        OreDictionary.registerOre("oreTin", TIN_CHUNK);

        GameRegistry.registerItem(LEAD_CHUNK, LEAD_CHUNK.name.itemName);
        OreDictionary.registerOre("oreLead", LEAD_CHUNK);

        GameRegistry.registerItem(URANIUM_CHUNK, URANIUM_CHUNK.name.itemName);
        OreDictionary.registerOre("oreUranium", URANIUM_CHUNK);
        OreDictionary.registerOre("oreUran", URANIUM_CHUNK);


        // Nuggets

        GameRegistry.registerItem(IRON_NUGGET, IRON_NUGGET.name.itemName);
        OreDictionary.registerOre(IRON_NUGGET.name.itemName, IRON_NUGGET);

        GameRegistry.registerItem(SILVER_NUGGET, SILVER_NUGGET.name.itemName);
        OreDictionary.registerOre(SILVER_NUGGET.name.itemName, SILVER_NUGGET);

        GameRegistry.registerItem(PLATINUM_NUGGET, PLATINUM_NUGGET.name.itemName);
        OreDictionary.registerOre(PLATINUM_NUGGET.name.itemName, PLATINUM_NUGGET);

        GameRegistry.registerItem(COPPER_NUGGET, COPPER_NUGGET.name.itemName);
        OreDictionary.registerOre(COPPER_NUGGET.name.itemName, COPPER_NUGGET);

        GameRegistry.registerItem(TIN_NUGGET, TIN_NUGGET.name.itemName);
        OreDictionary.registerOre(TIN_NUGGET.name.itemName, TIN_NUGGET);

        GameRegistry.registerItem(LEAD_NUGGET, LEAD_NUGGET.name.itemName);
        OreDictionary.registerOre(LEAD_NUGGET.name.itemName, LEAD_NUGGET);

    }

    @Override
    public void registerBlocks() {

    }

    @Override
    public void registerCrafting() {

        GameRegistry.addSmelting(MCppItem.GOLD_PEBBLE, new ItemStack(Items.gold_nugget), 0.1F);
        GameRegistry.addSmelting(MCppItem.GOLD_CHUNK, new ItemStack(Items.gold_ingot), 0.2F);

        GameRegistry.addSmelting(MCppItem.IRON_PEBBLE, new ItemStack(MCppItem.IRON_NUGGET), 0.1F);
        GameRegistry.addSmelting(MCppItem.IRON_CHUNK, new ItemStack(Items.iron_ingot), 0.2F);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MCppItem.IRON_NUGGET, 9), new ItemStack(Items.iron_ingot)));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.iron_ingot), "xxx", "xxx", "xxx",
                'x', "nuggetIron"));

        List<ItemStack> silver = OreDictionary.getOres("ingotSilver");
        if(!silver.isEmpty()) {

            GameRegistry.addSmelting(MCppItem.SILVER_PEBBLE, new ItemStack(MCppItem.SILVER_NUGGET), 0.1F);
            GameRegistry.addSmelting(MCppItem.SILVER_CHUNK, silver.get(0), 0.2F);
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MCppItem.SILVER_NUGGET, 9), "ingotSilver"));
            GameRegistry.addRecipe(new ShapedOreRecipe(silver.get(0), "xxx", "xxx", "xxx",
                    'x', "nuggetSilver"));
        }

        List<ItemStack> platinum = OreDictionary.getOres("ingotPlatinum");
        if(!platinum.isEmpty()) {

            GameRegistry.addSmelting(MCppItem.PLATINUM_PEBBLE, new ItemStack(MCppItem.PLATINUM_NUGGET), 0.1F);
            GameRegistry.addSmelting(MCppItem.PLATINUM_CHUNK, platinum.get(0), 0.2F);
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MCppItem.PLATINUM_NUGGET, 9), "ingotPlatinum"));
            GameRegistry.addRecipe(new ShapedOreRecipe(platinum.get(0), "xxx", "xxx", "xxx",
                    'x', "nuggetPlatinum"));
        }

        List<ItemStack> copper = OreDictionary.getOres("ingotCopper");
        if(!copper.isEmpty()) {

            GameRegistry.addSmelting(MCppItem.COPPER_PEBBLE, new ItemStack(MCppItem.COPPER_NUGGET), 0.1F);
            GameRegistry.addSmelting(MCppItem.COPPER_CHUNK, copper.get(0), 0.2F);
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MCppItem.COPPER_NUGGET, 9), "ingotCopper"));
            GameRegistry.addRecipe(new ShapedOreRecipe(copper.get(0), "xxx", "xxx", "xxx",
                    'x', "nuggetCopper"));
        }

        List<ItemStack> tin = OreDictionary.getOres("ingotTin");
        if(!tin.isEmpty()) {

            GameRegistry.addSmelting(MCppItem.TIN_PEBBLE, new ItemStack(MCppItem.TIN_NUGGET), 0.1F);
            GameRegistry.addSmelting(MCppItem.TIN_CHUNK, tin.get(0), 0.2F);
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MCppItem.TIN_NUGGET, 9), "ingotTin"));
            GameRegistry.addRecipe(new ShapedOreRecipe(tin.get(0), "xxx", "xxx", "xxx",
                    'x', "nuggetTin"));
        }

        List<ItemStack> lead = OreDictionary.getOres("ingotLead");
        if(!lead.isEmpty()) {

            GameRegistry.addSmelting(MCppItem.LEAD_PEBBLE, new ItemStack(MCppItem.LEAD_NUGGET), 0.1F);
            GameRegistry.addSmelting(MCppItem.LEAD_CHUNK, lead.get(0), 0.2F);
            GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(MCppItem.LEAD_NUGGET, 9), "ingotLead"));
            GameRegistry.addRecipe(new ShapedOreRecipe(lead.get(0), "xxx", "xxx", "xxx",
                    'x', "nuggetLead"));
        }
    }

    @Override
    public void registerEntities() {

    }

    @Override
    public void registerEventHandler() {

        MinecraftForge.EVENT_BUS.register(new MCppOreEventHandler());
    }

    @Override
    public void registerMisc() {

    }

    @Override
    public void postRegister() {

    }
}
