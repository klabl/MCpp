package mod.pwngu.client;

import mod.pwngu.client.gui.MCppRenderEventHandler;
import mod.pwngu.common.main.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.world.WorldEvent;

import java.util.Iterator;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    private Minecraft mc;

    public ClientProxy() {

        super();
        mc = Minecraft.getMinecraft();
    }

    @Override
    public void registerRenderers() {

        MCppRenderEventHandler handler = new MCppRenderEventHandler(mc);
        FMLCommonHandler.instance().bus().register(handler);
    }

    public void onClientTick(TickEvent.ClientTickEvent ev) {

    }

    public void onWorldSave(WorldEvent.Save ev) {

    }
}
