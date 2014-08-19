package mod.pwngu.common.module.ores;

import cpw.mods.fml.common.registry.GameRegistry;
import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.module.MCppModule;
import mod.pwngu.common.module.ores.event.MCppOreEventHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import static mod.pwngu.common.item.MCppItem.*;
import static mod.pwngu.common.item.MCppItem.LEAD_NUGGET;

/**
 * Created by klaus on 12.08.14.
 */
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

        GameRegistry.registerItem(GOLD_PEBBLE, GOLD_PEBBLE.name.itemName);
        OreDictionary.registerOre(GOLD_PEBBLE.name.itemName, GOLD_PEBBLE);

        GameRegistry.registerItem(SILVER_PEBBLE, SILVER_PEBBLE.name.itemName);
        OreDictionary.registerOre(SILVER_PEBBLE.name.itemName, SILVER_PEBBLE);

        GameRegistry.registerItem(COPPER_PEBBLE, COPPER_PEBBLE.name.itemName);
        OreDictionary.registerOre(COPPER_PEBBLE.name.itemName, COPPER_PEBBLE);

        GameRegistry.registerItem(TIN_PEBBLE, TIN_PEBBLE.name.itemName);
        OreDictionary.registerOre(TIN_PEBBLE.name.itemName, TIN_PEBBLE);

        GameRegistry.registerItem(LEAD_PEBBLE, LEAD_PEBBLE.name.itemName);
        OreDictionary.registerOre(LEAD_PEBBLE.name.itemName, LEAD_PEBBLE);

        GameRegistry.registerItem(URANIUM_PEBBLE, URANIUM_PEBBLE.name.itemName);
        OreDictionary.registerOre(URANIUM_PEBBLE.name.itemName, URANIUM_PEBBLE);


        // Nuggets

        GameRegistry.registerItem(IRON_NUGGET, IRON_NUGGET.name.itemName);
        OreDictionary.registerOre(IRON_NUGGET.name.itemName, IRON_NUGGET);

        GameRegistry.registerItem(SILVER_NUGGET, SILVER_NUGGET.name.itemName);
        OreDictionary.registerOre(SILVER_NUGGET.name.itemName, SILVER_NUGGET);

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

        // Nuggets

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.IRON_NUGGET, 9), new ItemStack(Items.iron_ingot));
        GameRegistry.addRecipe(new ItemStack(Items.iron_ingot), "xxx", "xxx", "xxx", 'x', new ItemStack(MCppItem.IRON_NUGGET));

        GameRegistry.addSmelting(MCppItem.IRON_PEBBLE, new ItemStack(MCppItem.IRON_NUGGET), 0.2F);
        GameRegistry.addSmelting(MCppItem.GOLD_PEBBLE, new ItemStack(Items.gold_nugget), 0.2F);
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
