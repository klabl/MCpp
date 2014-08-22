package mod.pwngu.common.util;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class MCppCreativeTab extends CreativeTabs {

    public MCppCreativeTab() {

        super("mcpp");
    }

    @Override
    public Item getTabIconItem() {

        return new ItemBlock(Blocks.grass);
    }
}
