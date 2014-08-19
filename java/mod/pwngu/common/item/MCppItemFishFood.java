package mod.pwngu.common.item;

import net.minecraft.item.ItemStack;

public class MCppItemFishFood extends MCppItemFood {

    public static enum FishType {

        COD(0, "cod", 2, 0.1F, 3, 0.2F),
        SALMON(1, "salmon", 3, 0.1F, 4, 0.2F),
        CLOWNFISH(2, "clownfish", 1, 0.1F),
        PUFFERFISH(3, "pufferfish", 1, 0.1F);

        public final int id;
        public final String name;

        public final int rawHealAmount;
        public final float rawFatValue;

        public final int cookedHealAmount;
        public final float cookedFatValue;

        private FishType(int id, String name, int rawHealAmount, float rawFatValue) {

            this.id = id;
            this.name = name;
            this.rawHealAmount = rawHealAmount;
            this.rawFatValue = rawFatValue;
            this.cookedHealAmount = 0;
            this.cookedFatValue = 0.0F;
        }

        private FishType(int id, String name, int rawHealAmount, float rawFatValue, int cookedHealAmount, float cookedFatValue) {

            this.id = id;
            this.name = name;
            this.rawHealAmount = rawHealAmount;
            this.rawFatValue = rawFatValue;
            this.cookedHealAmount = cookedHealAmount;
            this.cookedFatValue = cookedFatValue;
        }

        public static FishType fromDamageValue(int dmg) {

            for(FishType type : values()) {

                if(type.id == dmg) return type;
            }
            return null;
        }
    }

    private boolean cooked;

    public MCppItemFishFood(int healAmount, float fatValue, boolean cooked) {

        super(healAmount, fatValue, false);
        this.cooked = cooked;
    }

    public int func_150905_g(ItemStack stack) {

        return cooked ? FishType.fromDamageValue(stack.getItemDamage()).cookedHealAmount :
                FishType.fromDamageValue(stack.getItemDamage()).rawHealAmount;
    }

    public float func_150906_h(ItemStack stack) {

        return cooked ? FishType.fromDamageValue(stack.getItemDamage()).cookedFatValue :
                FishType.fromDamageValue(stack.getItemDamage()).rawFatValue;
    }
}
