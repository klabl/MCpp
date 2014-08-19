package mod.pwngu.common.module.thirst;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.main.MCpp;
import mod.pwngu.common.module.MCppModule;
import mod.pwngu.common.module.thirst.event.MCppThirstEventHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import static mod.pwngu.common.item.MCppItem.*;
import static mod.pwngu.common.item.MCppItem.FILTER;
import static mod.pwngu.common.item.MCppItem.HOT_TEA;

public class MCppModuleThirst extends MCppModule {

    public MCppModuleThirst(boolean enable) {

        super("THIRST", enable);
    }

    @Override
    public void preRegister() {

    }

    @Override
    public void registerItems() {

        //Juice

        GameRegistry.registerItem(APPLE_JUICE, APPLE_JUICE.name.itemName);
        GameRegistry.registerItem(CARROT_JUICE, CARROT_JUICE.name.itemName);
        GameRegistry.registerItem(MELON_JUICE, MELON_JUICE.name.itemName);
        GameRegistry.registerItem(CLEAN_WATER_BOTTLE, CLEAN_WATER_BOTTLE.name.itemName);
        GameRegistry.registerItem(COLD_TEA, COLD_TEA.name.itemName);
        GameRegistry.registerItem(HOT_TEA, HOT_TEA.name.itemName);

        //Filter

        GameRegistry.registerItem(FILTER, FILTER.name.itemName);
    }

    @Override
    public void registerBlocks() {

    }

    @Override
    public void registerCrafting() {

        //Drinks

        GameRegistry.addRecipe(new ItemStack(MCppItem.FILTER, 1, 0), "#s#", "#g#", "###",
                '#', new ItemStack(Blocks.wooden_slab, 1, OreDictionary.WILDCARD_VALUE), 's', new ItemStack(Blocks.sand), 'g', new ItemStack(Blocks.gravel));

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.CLEAN_WATER_BOTTLE),
                new ItemStack(Items.glass_bottle), new ItemStack(Items.potionitem, 1, 0), new ItemStack(MCppItem.FILTER, 1, OreDictionary.WILDCARD_VALUE));

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.APPLE_JUICE),
                new ItemStack(Items.glass_bottle), new ItemStack(Items.apple));

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.CARROT_JUICE),
                new ItemStack(Items.glass_bottle), new ItemStack(Items.carrot), new ItemStack(Items.carrot));

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.MELON_JUICE),
                new ItemStack(Items.glass_bottle), new ItemStack(Items.melon), new ItemStack(Items.melon));

        //Tea

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.COLD_TEA),
                new ItemStack(MCppItem.CLEAN_WATER_BOTTLE),
                new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE));

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.COLD_TEA),
                new ItemStack(MCppItem.CLEAN_WATER_BOTTLE),
                new ItemStack(Blocks.yellow_flower, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE));

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.COLD_TEA),
                new ItemStack(MCppItem.CLEAN_WATER_BOTTLE),
                new ItemStack(Blocks.yellow_flower, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Blocks.yellow_flower, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE));

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.COLD_TEA),
                new ItemStack(MCppItem.CLEAN_WATER_BOTTLE),
                new ItemStack(Blocks.yellow_flower, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Blocks.yellow_flower, 1, OreDictionary.WILDCARD_VALUE),
                new ItemStack(Blocks.yellow_flower, 1, OreDictionary.WILDCARD_VALUE));

        GameRegistry.addSmelting(MCppItem.COLD_TEA, new ItemStack(MCppItem.HOT_TEA), 0.5F);
    }

    @Override
    public void registerEntities() {

    }

    @Override
    public void registerEventHandler() {

        MCppThirstEventHandler handler = new MCppThirstEventHandler();
        FMLCommonHandler.instance().bus().register(handler);
        MinecraftForge.EVENT_BUS.register(handler);
    }

    @Override
    public void registerMisc() {

        FluidRegistry.registerFluid(MCpp.fluidCleanWater);

        FluidContainerRegistry.registerFluidContainer(
                FluidRegistry.getFluidStack(MCpp.fluidCleanWater.getName(), FluidContainerRegistry.BUCKET_VOLUME / 4),
                new ItemStack(MCppItem.CLEAN_WATER_BOTTLE), new ItemStack(Items.glass_bottle));
    }

    @Override
    public void postRegister() {

        Items.mushroom_stew.setMaxStackSize(16);
        Items.cooked_porkchop.setMaxStackSize(16);
        Items.porkchop.setMaxStackSize(16);
        Items.cooked_beef.setMaxStackSize(16);
        Items.beef.setMaxStackSize(16);
        Items.apple.setMaxStackSize(16);
        Items.poisonous_potato.setMaxStackSize(16);
        Items.potato.setMaxStackSize(16);
        Items.baked_potato.setMaxStackSize(16);
        Items.bread.setMaxStackSize(16);
        Items.carrot.setMaxStackSize(16);
        Items.cooked_chicken.setMaxStackSize(16);
        Items.chicken.setMaxStackSize(16);
        Items.cooked_fished.setMaxStackSize(16);
        Items.fish.setMaxStackSize(16);
        Items.pumpkin_pie.setMaxStackSize(16);

        Items.potionitem.setMaxStackSize(16);
    }
}
