package mod.pwngu.common.module.survivial;

import cpw.mods.fml.common.registry.GameRegistry;
import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.module.MCppModule;
import mod.pwngu.common.module.survivial.event.MCppBlockBreakHandler;
import mod.pwngu.common.module.survivial.event.MCppSurvivalEventHandler;
import mod.pwngu.common.module.thirst.util.NeedStats;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class MCppModuleSurvival extends MCppModule {

    public MCppModuleSurvival(boolean enable) {

        super("SURVIVAL", enable, new MCppModule[]{THIRST}, null);
    }

    @Override
    public void preRegister() {

    }

    @Override
    public void registerItems() {

        GameRegistry.registerItem(MCppItem.BANDAGE, MCppItem.BANDAGE.name.itemName);
    }

    @Override
    public void registerBlocks() {

    }

    @Override
    public void registerCrafting() {
        GameRegistry.addRecipe(new ItemStack(MCppItem.BANDAGE),
                "sww", "sww", 's', new ItemStack(Items.string),
                'w', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE));
    }

    @Override
    public void registerEntities() {

    }

    @Override
    public void registerEventHandler() {

        MinecraftForge.EVENT_BUS.register(new MCppSurvivalEventHandler());
        MinecraftForge.EVENT_BUS.register(new MCppBlockBreakHandler());
    }

    @Override
    public void registerMisc() {

    }

    @Override
    public void postRegister() {

        NeedStats.regTimer = 300;
    }
}
