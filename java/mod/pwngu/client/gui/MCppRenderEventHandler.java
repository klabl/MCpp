package mod.pwngu.client.gui;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;

public class MCppRenderEventHandler {

    private Minecraft mc;
    private MCppGui gui;

    public MCppRenderEventHandler(Minecraft mc) {

        this.mc = mc;
        gui = new MCppGui(mc);
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent ev) {

        mc.ingameGUI = gui;
    }
}
