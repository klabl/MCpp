package mod.pwngu.common.util;

import net.minecraft.potion.Potion;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class MCppPotion extends Potion {

    static {

        Potion[] potionTypes;

        for (Field field : Potion.class.getDeclaredFields()) {

            field.setAccessible(true);
            try {

                if (field.getName().equals("potionTypes") || field.getName().equals("field_76425_a")) {

                    Field modfield = Field.class.getDeclaredField("modifiers");
                    modfield.setAccessible(true);
                    modfield.setInt(field, field.getModifiers() & ~Modifier.FINAL);

                    potionTypes = (Potion[]) field.get(null);
                    final Potion[] newPotionTypes = new Potion[256];
                    System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                    field.set(null, newPotionTypes);
                }
            } catch (Exception ex) {

                System.err.println("Severe error, during load of MCpp");
                throw new RuntimeException(ex);
            }
        }
    }

    public static final Potion ZOMBIEFICATION = new MCppPotion(32, true, 5797459).setIconIndex(1, 1).setPotionName("potion.zombiefication");
    public static final Potion THIRST = new MCppPotion(33, true, 8171462).setIconIndex(1, 1).setPotionName("potion.thirst");
    public static final Potion WOUNDED = new MCppPotion(34, true, 0).setIconIndex(6, 0).setPotionName("potion.wounded");
//    public static final Potion POISON_IMMUNITY = new MCppPotion(35, false, )

    public MCppPotion(int id, boolean isBadEffect, int potionColor) {

        super(id, isBadEffect, potionColor);
    }
}
