package mod.pwngu.common.item;

import static net.minecraft.init.Items.*;

import mod.pwngu.common.main.MCpp;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;

import java.util.HashMap;

public class MCppItemFood extends ItemFood {

    private static final HashMap<Item, MCppItemFood> vanillaFoods = new HashMap<Item, MCppItemFood>();

    static {

        vanillaFoods.put(apple, new MCppItemFood(2, 0.0F, false));
        vanillaFoods.put(baked_potato, new MCppItemFood(3, 0.2F, false));
        vanillaFoods.put(bread, new MCppItemFood(4, 0.0F, false));
        vanillaFoods.put(cake, new MCppItemFood(1, 1.0F, true));
        vanillaFoods.put(carrot, new MCppItemFood(1, 0.0F, false));
        vanillaFoods.put(cooked_chicken, new MCppItemFood(4, 0.2F, false));
        vanillaFoods.put(cooked_porkchop, new MCppItemFood(5, 0.3F, false));
        vanillaFoods.put(cooked_beef, new MCppItemFood(5, 0.3F, false));
        vanillaFoods.put(cookie, new MCppItemFood(1, 1.0F, true));
        vanillaFoods.put(golden_apple, new MCppItemFood(2, 0.0F, false));
        vanillaFoods.put(golden_carrot, new MCppItemFood(1, 0.0F, false));
        vanillaFoods.put(melon, new MCppItemFood(1, 0.0F, false));
        vanillaFoods.put(mushroom_stew, new MCppItemFood(4, 0.1F, false));
        vanillaFoods.put(poisonous_potato, new MCppItemFood(1, 0.0F, false));
        vanillaFoods.put(potato, new MCppItemFood(1, 0.0F, false).setPotionEffect(Potion.poison.id, 5, 0, 0.3F));
        vanillaFoods.put(pumpkin_pie, new MCppItemFood(3, 1.0F, true));
        vanillaFoods.put(beef, new MCppItemFood(4, 0.2F, false).setPotionEffect(Potion.hunger.id, 30, 0, 0.3F));
        vanillaFoods.put(chicken, new MCppItemFood(3, 0.1F, false).setPotionEffect(Potion.hunger.id, 30, 0, 0.5F));
        vanillaFoods.put(porkchop, new MCppItemFood(4, 0.2F, false).setPotionEffect(Potion.hunger.id, 30, 0, 0.3F));
        vanillaFoods.put(rotten_flesh, new MCppItemFood(2, 0.1F, false));
        vanillaFoods.put(spider_eye, new MCppItemFood(1, 0.0F, false));

        vanillaFoods.put(fish, new MCppItemFishFood(0, 0.0F, false));
        vanillaFoods.put(cooked_fished, new MCppItemFishFood(0, 0.0F, true));

        ((ItemFood)cookie).setAlwaysEdible();
        ((ItemFood)pumpkin_pie).setAlwaysEdible();
    }

    public static MCppItemFood getFood(Item item) {

        if(item instanceof MCppItemFood) return (MCppItemFood) item;
        return vanillaFoods.get(item);
    }

    public final MCppItem.Names name;

    public MCppItemFood(int healAmount, float fatValue, boolean isDesert) {

        this(null, healAmount, fatValue, isDesert, false);
    }

    public MCppItemFood(MCppItem.Names name, int healAmount, float fatValue, boolean isDesert, boolean isWolfsFavoriteMeat) {

        super(healAmount, fatValue, isWolfsFavoriteMeat);

        this.name = name;

        if(isDesert)
            this.setAlwaysEdible();
        this.setMaxStackSize(16);

        if(name != null) {

            setUnlocalizedName(name.unlocalizedName);
            setTextureName(MCpp.MODID + ":" + name.textureName);
        }
    }

    @Override
    public MCppItemFood setPotionEffect(int id, int duration, int amplifier, float probability) {

        super.setPotionEffect(id, duration, amplifier, probability);
        return this;
    }
}
