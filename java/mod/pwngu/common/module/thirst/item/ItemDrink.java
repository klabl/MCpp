package mod.pwngu.common.module.thirst.item;

import mod.pwngu.common.item.MCppItem;
import mod.pwngu.common.item.MCppItemFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ItemDrink extends MCppItemFood {

    public static final Map<Item, ItemDrink> vanillaDrinks = new HashMap<Item, ItemDrink>();

    public static final Set<Item> foodDrinks = new HashSet<Item>();

    static {

        vanillaDrinks.put(Items.melon, new ItemDrink(1));
        vanillaDrinks.put(Items.potionitem, new ItemDrink(4));
        vanillaDrinks.put(Items.milk_bucket, new ItemDrink(8));
        vanillaDrinks.put(Items.apple, new ItemDrink(2));
        vanillaDrinks.put(Items.mushroom_stew, new ItemDrink(4));

        foodDrinks.add(Items.melon);
        foodDrinks.add(Items.apple);
        foodDrinks.add(Items.mushroom_stew);
    }

    public static boolean isDrink(Item item) {

        return item instanceof ItemDrink || vanillaDrinks.containsKey(item);
    }

    public static boolean isFoodAndDrink(Item item) {

        return foodDrinks.contains(item);
    }

    public static ItemDrink getDrink(Item item) {

        if(item instanceof ItemDrink) return (ItemDrink) item;
        return vanillaDrinks.get(item);
    }

    private int thirstHealAmount;

    public ItemDrink(int thirstHealAmount) {

        this(null, thirstHealAmount);
    }

    public ItemDrink(MCppItem.Names name, int thirstHealAmount) {

        super(name, 0, 0.0F, false, false);

        this.thirstHealAmount = thirstHealAmount;
    }

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {

        stack.stackSize--;
        player.getFoodStats().func_151686_a(this, stack);
//        world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

        if (stack.stackSize <= 0) {

            return new ItemStack(Items.glass_bottle);
        }
        player.inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));

        this.onFoodEaten(stack, world, player);
        return stack;
    }

    public EnumAction getItemUseAction(ItemStack p_77661_1_) {

        return EnumAction.drink;
    }

    public int getThirstHealAmount(ItemStack stack) {

        return thirstHealAmount;
    }
}
