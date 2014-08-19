package mod.pwngu.common.main;

import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.world.WorldEvent;

import java.util.HashMap;
import java.util.Map;

public class CommonProxy {

    private final Map<String, NBTTagCompound> playerNeedStatProperties = new HashMap<String, NBTTagCompound>();

    public void registerRenderers() {}

    public void onClientTick(TickEvent.ClientTickEvent ev) {}

    public void onServerTick(TickEvent.ServerTickEvent ev) {

    }

    public void onServerStopping(FMLServerStoppingEvent ev){

    }

    public void onWorldSave(WorldEvent.Save ev) {

    }

    public void onWorldTick(TickEvent.WorldTickEvent ev) {

    }

    public void storeEntityData(String name, NBTTagCompound compound) {

        playerNeedStatProperties.put(name, compound);
    }

    public NBTTagCompound retrieveEntityData(String name) {

        return playerNeedStatProperties.remove(name);
    }
}
