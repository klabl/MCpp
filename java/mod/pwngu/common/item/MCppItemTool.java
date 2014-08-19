package mod.pwngu.common.item;

import mod.pwngu.common.main.MCpp;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.List;
import java.util.Set;

public class MCppItemTool extends ItemTool {

    public final MCppItem.Names name;

    MCppItemTool(MCppItem.Names name, float damageAddition, ToolMaterial toolMaterial, Set p_i45333_3_) {

        super(damageAddition, toolMaterial, p_i45333_3_);
        this.name = name;

        setCreativeTab(CreativeTabs.tabMisc);
        setUnlocalizedName(name.unlocalizedName);
        setTextureName(MCpp.MODID + ":" + name.textureName);
    }


    public void addInformation(ItemStack stack, EntityPlayer player, List info, boolean par4) {

        info.add("Uses Left: " + (stack.getMaxDamage() - stack.getItemDamage()));
    }
}
