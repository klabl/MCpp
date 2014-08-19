package mod.pwngu.common.item;

import mod.pwngu.common.main.MCpp;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class MCppItemBucket extends ItemBucket {

    public final MCppItem.Names name;

    public MCppItemBucket(MCppItem.Names name, Block fluid) {

        super(fluid);
        this.name = name;
//        setCreativeTab(MCpp.mcpptab);
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
        setUnlocalizedName(name.unlocalizedName);
        setTextureName(MCpp.MODID + ":" + name.textureName);
        setContainerItem(Items.bucket);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister register) {

        register.registerIcon(MCpp.MODID + ":" + name.textureName);
    }
}
