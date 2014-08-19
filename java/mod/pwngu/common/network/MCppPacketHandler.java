package mod.pwngu.common.network;

import mod.pwngu.common.main.MCpp;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class MCppPacketHandler {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(MCpp.MODID);

    public static void init() {


    }
}
