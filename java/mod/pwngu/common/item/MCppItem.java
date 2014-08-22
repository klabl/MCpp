package mod.pwngu.common.item;

import mod.pwngu.common.main.MCpp;
import mod.pwngu.common.module.survivial.item.ItemBandage;
import mod.pwngu.common.module.thirst.item.ItemDrink;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MCppItem extends Item {

    public static final MCppItem IRON_PEBBLE = new MCppItem(Names.IRON_PEBBLE);
    public static final MCppItem GOLD_PEBBLE = new MCppItem(Names.GOLD_PEBBLE);
    public static final MCppItem SILVER_PEBBLE = new MCppItem(Names.SILVER_PEBBLE);
    public static final MCppItem PLATINUM_PEBBLE = new MCppItem(Names.PLATINUM_PEBBLE);
    public static final MCppItem COPPER_PEBBLE = new MCppItem(Names.COPPER_PEBBLE);
    public static final MCppItem TIN_PEBBLE = new MCppItem(Names.TIN_PEBBLE);
    public static final MCppItem LEAD_PEBBLE = new MCppItem(Names.LEAD_PEBBLE);
    public static final MCppItem URANIUM_PEBBLE = new MCppItem(Names.URANIUM_PEBBLE);

    public static final MCppItem IRON_CHUNK = new MCppItem(Names.IRON_CHUNK);
    public static final MCppItem GOLD_CHUNK = new MCppItem(Names.GOLD_CHUNK);
    public static final MCppItem SILVER_CHUNK = new MCppItem(Names.SILVER_CHUNK);
    public static final MCppItem PLATINUM_CHUNK = new MCppItem(Names.PLATINUM_CHUNK);
    public static final MCppItem COPPER_CHUNK = new MCppItem(Names.COPPER_CHUNK);
    public static final MCppItem TIN_CHUNK = new MCppItem(Names.TIN_CHUNK);
    public static final MCppItem LEAD_CHUNK = new MCppItem(Names.LEAD_CHUNK);
    public static final MCppItem URANIUM_CHUNK = new MCppItem(Names.URANIUM_CHUNK);

    public static final MCppItem IRON_NUGGET = new MCppItem(Names.IRON_NUGGET);
    public static final MCppItem SILVER_NUGGET = new MCppItem(Names.SILVER_NUGGET);
    public static final MCppItem PLATINUM_NUGGET = new MCppItem(Names.PLATINUM_NUGGET);
    public static final MCppItem COPPER_NUGGET = new MCppItem(Names.COPPER_NUGGET);
    public static final MCppItem TIN_NUGGET = new MCppItem(Names.TIN_NUGGET);
    public static final MCppItem LEAD_NUGGET = new MCppItem(Names.LEAD_NUGGET);

    public static final MCppItem BLAZING_GLOWSTONE_DUST = new MCppItem(Names.BLAZING_GLOWSTONE_DUST);
    public static final MCppItem ENDSTONE_DUST = new MCppItem(Names.ENDSTONE_DUST);
    public static final MCppItem LAPEND_DUST = new MCppItem(Names.LAPEND_DUST);

    public static final ItemDrink MELON_JUICE = new ItemDrink(Names.MELON_JUICE, 6);
    public static final ItemDrink APPLE_JUICE = new ItemDrink(Names.APPLE_JUICE, 6);
    public static final ItemDrink CARROT_JUICE = new ItemDrink(Names.CARROT_JUICE, 6);
    public static final ItemDrink COLD_TEA = new ItemDrink(Names.COLD_TEA, 5);
    public static final ItemDrink HOT_TEA = new ItemDrink(Names.HOT_TEA, 8);
    public static final ItemDrink CLEAN_WATER_BOTTLE = new ItemDrink(Names.CLEAN_WATER_BOTTLE, 5);

    public static final ItemBandage BANDAGE = new ItemBandage();

    public static final MCppItemTool FILTER = (MCppItemTool) new MCppItemTool(Names.FILTER, 0.0F, ToolMaterial.WOOD, null).setMaxDamage(30);
    public static final MCppItem FILTER_NET = new MCppItem(Names.FILTER_NET);

    public static enum Names {

        IRON_PEBBLE("ironPebble", "pebbles.iron", "pebble_iron"),
        GOLD_PEBBLE("goldPebble", "pebbles.gold", "pebble_gold"),
        SILVER_PEBBLE("silverPebble", "pebbles.silver", "pebble_silver"),
        PLATINUM_PEBBLE("platinumPebble", "pebbles.platinum", "pebble_platinum"),
        COPPER_PEBBLE("copperPebble", "pebbles.copper", "pebble_copper"),
        TIN_PEBBLE("tinPebble", "pebbles.tin", "pebble_tin"),
        LEAD_PEBBLE("leadPebble", "pebbles.lead", "pebble_lead"),
        URANIUM_PEBBLE("uraniumPebble", "pebbles.uranium", "pebble_uranium"),

        IRON_CHUNK("ironChunk", "chunks.iron", "chunk_iron"),
        GOLD_CHUNK("goldChunk", "chunks.gold", "chunk_gold"),
        SILVER_CHUNK("silverChunk", "chunks.silver", "chunk_silver"),
        PLATINUM_CHUNK("platinumChunk", "chunks.platinum", "chunk_platinum"),
        COPPER_CHUNK("copperChunk", "chunks.copper", "chunk_copper"),
        TIN_CHUNK("tinChunk", "chunks.tin", "chunk_tin"),
        LEAD_CHUNK("leadChunk", "chunks.lead", "chunk_lead"),
        URANIUM_CHUNK("uraniumChunk", "chunks.uranium", "chunk_uranium"),

        IRON_NUGGET("ironNugget", "nuggets.iron", "nugget_iron"),
        SILVER_NUGGET("silverNugget", "nuggets.silver", "nugget_silver"),
        PLATINUM_NUGGET("platinumNugget", "nuggets.platinum", "nugget_platinum"),
        COPPER_NUGGET("copperNugget", "nuggets.copper", "nugget_copper"),
        TIN_NUGGET("tinNugget", "nuggets.tin", "nugget_tin"),
        LEAD_NUGGET("leadNugget", "nuggets.lead", "nugget_lead"),

        //TODO silver and platinum stuff

        BLAZING_GLOWSTONE_DUST("blazingGlowstoneDust", "dusts.blazingGlowstone", "blazing_glowstone_dust"),
        ENDSTONE_DUST("endstoneDust", "dusts.endstone", "endstone_dust"),
        LAPEND_DUST("lapendDust", "dusts.lapend", "lapend_dust"),

        MELON_JUICE("melonJuice", "bottles.juice.melon", "juice_melon"),
        APPLE_JUICE("appleJuice", "bottles.juice.apple", "juice_apple"),
        CARROT_JUICE("carrotJuice", "bottles.juice.carrot", "juice_carrot"),

        COLD_TEA("coldTea", "bottles.juice.coldTea", "bottle_cold_tea"),
        HOT_TEA("hotTea", "bottles.juice.hotTea", "bottle_hot_tea"),
        CLEAN_WATER_BOTTLE("cleanWaterBottle", "bottles.cleanWater", "bottle_clean_water"),

        FILTER("filter","tools.filter","filter"),
        FILTER_NET("filterNet", "filterNet", "filterNet"),

        CANTEEN("canteen", "tools.canteen", "canteen"),

        BANDAGE("bandage", "bandage", "bandage");

        public final String itemName;
        public final String unlocalizedName;
        public final String textureName;

        private Names(String itemName, String unlocalizedName, String textureName) {

            this.itemName = itemName;
            this.unlocalizedName = unlocalizedName;
            this.textureName = textureName;
        }
    }

    public final Names name;

    private MCppItem(Names name, int maxStackSize) {

        this.name = name;
        setCreativeTab(MCpp.MCPP_TAB);
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(maxStackSize);
        setUnlocalizedName(name.unlocalizedName);
        setTextureName(MCpp.MODID + ":" + name.textureName);
    }

    private MCppItem(Names name) {

        this(name, 64);
    }
}
