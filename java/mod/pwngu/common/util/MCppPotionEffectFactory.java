package mod.pwngu.common.util;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class MCppPotionEffectFactory {

    private MCppPotionEffectFactory() {}

    public static PotionEffect createPotionEffectWounded() {

        PotionEffect effect = new PotionEffect(MCppPotion.WOUNDED.id, 36000, 0);
        effect.getCurativeItems().clear();
        return effect;
    }

    public static PotionEffect createPotionEffectZombiefication(int duration, int amplifier) {

        PotionEffect effect = new PotionEffect(MCppPotion.ZOMBIEFICATION.id, duration, amplifier);
        effect.getCurativeItems().clear();
        return effect;
    }

    public static PotionEffect createPotionEffectConfusion(int duration) {

        PotionEffect effect = new PotionEffect(Potion.confusion.id, duration, 0);
        effect.getCurativeItems().clear();

        return effect;
    }
}
