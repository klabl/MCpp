package mod.pwngu.common.module.survivial.item;

import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.main.MCpp;
import mod.pwngu.common.util.MCppPotion;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBandage extends ItemFood {

    public final MCppItem.Names name;

    public ItemBandage() {

        super(0, 0.0F, false);

        this.name = MCppItem.Names.BANDAGE;
        this.setMaxStackSize(4);

        setUnlocalizedName(name.unlocalizedName);
        setTextureName(MCpp.MODID + ":" + name.textureName);
    }

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {

        stack.stackSize--;

        player.removePotionEffect(MCppPotion.WOUNDED.id);

        return stack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {

        return 256;
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_) {

        return EnumAction.eat;
    }
}
