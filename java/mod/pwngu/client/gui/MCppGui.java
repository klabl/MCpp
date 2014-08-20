package mod.pwngu.client.gui;

import mod.pwngu.common.module.MCppModule;
import mod.pwngu.common.module.thirst.util.NeedStats;
import mod.pwngu.common.util.MCppPotion;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import org.lwjgl.opengl.GL11;

public class MCppGui extends GuiIngameForge {

    public static final ResourceLocation MCPP_HUD = new ResourceLocation("mcpp:textures/gui/hud.png");

    public MCppGui(Minecraft mc) {

        super(mc);
    }

    public void renderHealth(int width, int height) {

        this.mc.getTextureManager().bindTexture(icons);
        mc.mcProfiler.startSection("health");
        GL11.glEnable(GL11.GL_BLEND);

        boolean highlight = mc.thePlayer.hurtResistantTime / 3 % 2 == 1;

        if (mc.thePlayer.hurtResistantTime < 10)
            highlight = false;

        IAttributeInstance attrMaxHealth = this.mc.thePlayer.getEntityAttribute(SharedMonsterAttributes.maxHealth);
        int health = MathHelper.ceiling_float_int(mc.thePlayer.getHealth());
        int healthLast = MathHelper.ceiling_float_int(mc.thePlayer.prevHealth);
        float healthMax = (float)attrMaxHealth.getAttributeValue();
        float absorb = this.mc.thePlayer.getAbsorptionAmount();

        int healthRows = MathHelper.ceiling_float_int((healthMax + absorb) / 2.0F / 10.0F);
        int rowHeight = Math.max(10 - (healthRows - 2), 3);

        this.rand.setSeed((long)(updateCounter * 312871));

        int left = width / 2 - 91;
        int top = height - left_height;
        left_height += (healthRows * rowHeight);
        if (rowHeight != 10) left_height += 10 - rowHeight;

        int regen = -1;
        if (mc.thePlayer.isPotionActive(Potion.regeneration))
            regen = updateCounter % 25;

        final int TOP =  9 * (mc.theWorld.getWorldInfo().isHardcoreModeEnabled() ? 5 : 0);
        final int BACKGROUND = (highlight ? 25 : 16);
        int MARGIN = 16;
        if (mc.thePlayer.isPotionActive(Potion.poison))      MARGIN += 36;
        else if (mc.thePlayer.isPotionActive(Potion.wither)) MARGIN += 72;
        float absorbRemaining = absorb;

        for (int i = MathHelper.ceiling_float_int((healthMax + absorb) / 2.0F) - 1; i >= 0; --i)
        {
            //int b0 = (highlight ? 1 : 0);
            int row = MathHelper.ceiling_float_int((float)(i + 1) / 10.0F) - 1;
            int x = left + i % 10 * 8;
            int y = top - row * rowHeight;

            if (health <= 4) y += rand.nextInt(2);
            if (i == regen) y -= 2;

            //Start actual MCpp code
            if(mc.thePlayer.isPotionActive(MCppPotion.WOUNDED) && !mc.thePlayer.isPotionActive(Potion.wither)) {

                this.mc.getTextureManager().bindTexture(MCPP_HUD);
                drawTexturedModalRect(x, y, 0, 36, 9, 9);
                this.mc.getTextureManager().bindTexture(icons);
            } else
                drawTexturedModalRect(x, y, BACKGROUND, TOP, 9, 9);
            //End actual MCpp code

            if (highlight)
            {
                if (i * 2 + 1 < healthLast)
                    drawTexturedModalRect(x, y, MARGIN + 54, TOP, 9, 9); //6
                else if (i * 2 + 1 == healthLast)
                    drawTexturedModalRect(x, y, MARGIN + 63, TOP, 9, 9); //7
            }

            if (absorbRemaining > 0.0F)
            {
                if (absorbRemaining == absorb && absorb % 2.0F == 1.0F)
                    drawTexturedModalRect(x, y, MARGIN + 153, TOP, 9, 9); //17
                else
                    drawTexturedModalRect(x, y, MARGIN + 144, TOP, 9, 9); //16
                absorbRemaining -= 2.0F;
            }
            else
            {
                if (i * 2 + 1 < health)
                    drawTexturedModalRect(x, y, MARGIN + 36, TOP, 9, 9); //4
                else if (i * 2 + 1 == health)
                    drawTexturedModalRect(x, y, MARGIN + 45, TOP, 9, 9); //5
            }
        }

        GL11.glDisable(GL11.GL_BLEND);
        mc.mcProfiler.endSection();
    }

    public void renderFood(int width, int height) {

        this.mc.getTextureManager().bindTexture(MCPP_HUD);

//        if (pre(FOOD)) return;
        mc.mcProfiler.startSection("food");

        GL11.glEnable(GL11.GL_BLEND);
        int left = width / 2 + 91;
        int top = height - right_height;
        right_height += 10;

        FoodStats stats = mc.thePlayer.getFoodStats();
        int level = stats.getFoodLevel();

        for (int i = 0; i < 10; ++i)
        {
            int idx = i * 2 + 1;
            int x = left - i * 8 - 9;
            int y = top;
            int icon = 0;
            int background = 0;

            if(mc.thePlayer.isPotionActive(Potion.hunger.id)) {
                // draw greenish background when poisoned
                background = 9;
                icon = 18;
            } else {

                float fatLevel = NeedStats.get(mc.thePlayer).getFatLevel() - (float) idx;

                if(fatLevel >= 1.0)
                    // draw full fat background
                    background = 18;
                else if(fatLevel >= 0.5)
                    // draw 3/4 fat background
                    background = 27;
                else if(fatLevel >= 0.0F)
                    // draw half fat background
                    background = 36;
                else if(fatLevel > -0.5F)
                    // draw 1/4 fat background
                    background = 45;

            }

//            if (mc.thePlayer.getFoodStats().getSaturationLevel() <= 0.0F && updateCounter % (level * 3 + 1) == 0) {
//
//                y = top + (rand.nextInt(3) - 1);
//            }

            drawTexturedModalRect(x, y, background, 18, 9, 9);

            if (idx < level)
                // draw full shank
                drawTexturedModalRect(x, y, icon, 27, 9, 9);
            else if (idx == level)
                // draw half shank
                drawTexturedModalRect(x, y, icon + 9, 27, 9, 9);
        }
        GL11.glDisable(GL11.GL_BLEND);
        mc.mcProfiler.endSection();
//        post(FOOD);

        if (!mc.thePlayer.isInsideOfMaterial(Material.water) && !this.mc.thePlayer.isRidingHorse() && MCppModule.THIRST.isLoaded)
                renderThirst(width, height);
        else
            this.mc.getTextureManager().bindTexture(icons);
    }

    public void renderThirst(int width, int height) {

        mc.mcProfiler.startSection("thirst");

        GL11.glEnable(GL11.GL_BLEND);

        NeedStats stats = NeedStats.get(mc.thePlayer);

        int level = stats.getThirstLevel();

        int top = height - right_height;
        right_height += 10;
        int left = width / 2 + 91;

        for (int i = 0; i < 10; i++) {

            int idx = i * 2 + 1;

            int x = left - i * 8 - 9;
            int y = top;

            int icon = 0;
            int background = 0;

//            if ((stats.getThirstSaturationLevel() <= 0.0F) && updateCounter % (level * 3 + 1) == 0) {
//
//                y = top + (rand.nextInt(3) - 1);
//            }

            if(mc.thePlayer.isPotionActive(MCppPotion.THIRST)) {
                // draw greenish background when poisoned
                background = 27;
                icon = 18;
            } //else if(idx < stats.getThirstSaturationLevel())
//                // draw full saturated background
//                background = 9;
//            else if(idx == stats.getThirstSaturationLevel())
//                // draw half saturated background
//                background = 18;

            // draw background
            drawTexturedModalRect(x, y, background, 0, 9, 9);

            if (idx < level)
                // draw full drop
                drawTexturedModalRect(x, y, icon, 9, 9, 9);
            else if (idx == level)
                // draw half drop
                drawTexturedModalRect(x, y, icon + 9, 9, 9, 9);
        }

        GL11.glDisable(GL11.GL_BLEND);

        mc.mcProfiler.endSection();

        this.mc.getTextureManager().bindTexture(icons);
    }
}
