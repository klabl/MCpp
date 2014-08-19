package mod.pwngu.common.util;

import net.minecraft.util.DamageSource;

public class MCppDamageSource extends DamageSource {

    public static final DamageSource THIRST = new MCppDamageSource("mcpp.thirst").setDamageBypassesArmor().setDamageIsAbsolute();
    public static final DamageSource THIRST_AND_STARVE = new MCppDamageSource("mcpp.thirstAndStarve").setDamageBypassesArmor().setDamageIsAbsolute();
    public static final DamageSource ZOMBIEFICATION = new MCppDamageSource("mcpp.zombification").setDamageBypassesArmor().setDamageIsAbsolute();

    public MCppDamageSource(String name) {

        super(name);
    }
}
