package mod.pwngu.common.module.thirst.item;

import mod.pwngu.common.item.MCppItem;

public class ItemCanteen extends ItemDrink {

    public static final int WATER = 0;
    public static final int APPLE_JUICE = 32;
    public static final int CARROT_JUICE = 64;
    public static final int MELON_JUICE = 96;

    public ItemCanteen() {

        super(MCppItem.Names.CANTEEN, 5);
    }
}
