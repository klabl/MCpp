package mod.pwngu.common.module.ic2;

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.api.recipe.Recipes;
import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.main.MCpp;
import mod.pwngu.common.module.MCppModule;
import mod.pwngu.common.module.ic2.recipe.ICRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

import java.util.Iterator;
import java.util.List;

public class MCppModuleIC2 extends MCppModule {

    public MCppModuleIC2(boolean enable) {

        super("IC2", enable, new MCppModule[]{ORE}, "IC2");
    }

    @Override
    public void preRegister() {

        Iterator<IRecipe> it = CraftingManager.getInstance().getRecipeList().iterator();
        while(it.hasNext()) {

            IRecipe recipe = it.next();

            if(recipe.getRecipeOutput() == null) continue;

            if (recipe.getRecipeOutput().isItemEqual(IC2Items.getItem("energiumDust"))) {
                it.remove();
            } else if (recipe.getRecipeOutput().isItemEqual(IC2Items.getItem("advancedCircuit"))) {
                it.remove();
            } else if (recipe.getRecipeOutput().isItemEqual(IC2Items.getItem("lapotronCrystal"))) {
                it.remove();
            }
        }
    }

    @Override
    public void registerItems() {

        GameRegistry.registerItem(MCppItem.BLAZING_GLOWSTONE_DUST, MCppItem.BLAZING_GLOWSTONE_DUST.name.itemName);
        GameRegistry.registerItem(MCppItem.LAPEND_DUST, MCppItem.LAPEND_DUST.name.itemName);
        GameRegistry.registerItem(MCppItem.ENDSTONE_DUST, MCppItem.ENDSTONE_DUST.name.itemName);
    }

    @Override
    public void registerBlocks() {

    }

    @Override
    public void registerCrafting() {

        GameRegistry.addRecipe(IC2Items.getItem("copperIngot"), "xxx", "xxx", "xxx", 'x', new ItemStack(MCppItem.COPPER_NUGGET));
        GameRegistry.addRecipe(IC2Items.getItem("tinIngot"), "xxx", "xxx", "xxx", 'x', new ItemStack(MCppItem.TIN_NUGGET));
        GameRegistry.addRecipe(IC2Items.getItem("leadIngot"), "xxx", "xxx", "xxx", 'x', new ItemStack(MCppItem.LEAD_NUGGET));

        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.COPPER_NUGGET, 9), IC2Items.getItem("copperIngot"));
        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.TIN_NUGGET, 9), IC2Items.getItem("tinIngot"));
        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.TIN_NUGGET, 9), IC2Items.getItem("leadIngot"));


        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.BLAZING_GLOWSTONE_DUST), new ItemStack(Items.glowstone_dust),
                new ItemStack(Items.blaze_powder)/*, new ItemStack(Items.redstone)*/);
        GameRegistry.addShapelessRecipe(new ItemStack(MCppItem.LAPEND_DUST), new ItemStack(MCppItem.ENDSTONE_DUST),
                IC2Items.getItem("lapiDust"));

        ItemStack energium = IC2Items.getItem("energiumDust");
        energium.stackSize = 9;
        GameRegistry.addRecipe(energium, "grg", "rdr", "grg",
                'g', MCppItem.BLAZING_GLOWSTONE_DUST, 'r', Items.redstone, 'd', IC2Items.getItem("diamondDust").getItem());
        GameRegistry.addRecipe(IC2Items.getItem("advancedCircuit"), "lgl", "ece", "lel",
                'e', IC2Items.getItem("energiumDust"), 'g', MCppItem.BLAZING_GLOWSTONE_DUST,
                'l', MCppItem.LAPEND_DUST, 'c', IC2Items.getItem("electronicCircuit"));
        GameRegistry.addRecipe(IC2Items.getItem("lapotronCrystal"), "lal", "lel", "lal",
                'l', MCppItem.LAPEND_DUST, 'e', IC2Items.getItem("energyCrystal"), 'a', IC2Items.getItem("advancedCircuit"));


        GameRegistry.addSmelting(MCppItem.COPPER_PEBBLE, new ItemStack(MCppItem.COPPER_NUGGET), 0.2F);
        GameRegistry.addSmelting(MCppItem.TIN_PEBBLE, new ItemStack(MCppItem.TIN_NUGGET), 0.2F);
        GameRegistry.addSmelting(MCppItem.LEAD_PEBBLE, new ItemStack(MCppItem.LEAD_NUGGET), 0.2F);


        GameRegistry.addSmelting(IC2Items.getItem("smallIronDust"), new ItemStack(MCppItem.IRON_NUGGET), 0.2F);
        GameRegistry.addSmelting(IC2Items.getItem("smallGoldDust"), new ItemStack(Items.gold_nugget), 0.2F);
        GameRegistry.addSmelting(IC2Items.getItem("smallCopperDust"), new ItemStack(MCppItem.COPPER_NUGGET), 0.2F);
        GameRegistry.addSmelting(IC2Items.getItem("smallTinDust"), new ItemStack(MCppItem.TIN_NUGGET), 0.2F);
        GameRegistry.addSmelting(IC2Items.getItem("smallLeadDust"), new ItemStack(MCppItem.LEAD_NUGGET), 0.2F);


        Recipes.macerator.addRecipe(new ICRecipe(new ItemStack(MCppItem.IRON_PEBBLE, 3)), null, IC2Items.getItem("crushedIronOre"));
        Recipes.macerator.addRecipe(new ICRecipe(new ItemStack(MCppItem.GOLD_PEBBLE, 3)), null, IC2Items.getItem("crushedGoldOre"));
        Recipes.macerator.addRecipe(new ICRecipe(new ItemStack(MCppItem.SILVER_PEBBLE, 3)), null, IC2Items.getItem("crushedSilverOre"));
        Recipes.macerator.addRecipe(new ICRecipe(new ItemStack(MCppItem.COPPER_PEBBLE, 3)), null, IC2Items.getItem("crushedCopperOre"));
        Recipes.macerator.addRecipe(new ICRecipe(new ItemStack(MCppItem.TIN_PEBBLE, 3)), null, IC2Items.getItem("crushedTinOre"));
        Recipes.macerator.addRecipe(new ICRecipe(new ItemStack(MCppItem.LEAD_PEBBLE, 3)), null, IC2Items.getItem("crushedLeadOre"));
        Recipes.macerator.addRecipe(new ICRecipe(new ItemStack(MCppItem.URANIUM_PEBBLE, 3)), null, IC2Items.getItem("crushedUraniumOre"));

        Recipes.macerator.addRecipe(new ICRecipe(new ItemStack(MCppItem.ENDSTONE_DUST, 2)), null, new ItemStack(Blocks.end_stone));
    }

    @Override
    public void registerEntities() {

    }

    @Override
    public void registerEventHandler() {

    }

    @Override
    public void registerMisc() {

    }

    @Override
    public void postRegister() {

    }
}
