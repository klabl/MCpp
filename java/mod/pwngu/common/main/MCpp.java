package mod.pwngu.common.main;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import mod.pwngu.common.module.MCppModule;
import mod.pwngu.common.util.MCppCreativeTab;
import mod.pwngu.lib.PWNLogger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.Fluid;

import java.util.Set;

@Mod(modid = MCpp.MODID, name = MCpp.NAME, version = MCpp.VERSION)
public class MCpp {

    public static final String MODID = "mcpp";
    public static final String NAME = "MC++";
    public static final String VERSION = "0.1";

    public static final PWNLogger log = PWNLogger.getLogger();

    @Instance(value = MODID)
    public static MCpp INSTANCE;

    @SidedProxy(clientSide = "mod.pwngu.client.ClientProxy", serverSide = "mod.pwngu.network.CommonProxy")
    public static CommonProxy proxy;

    public static Fluid fluidCleanWater = new Fluid("cleanWater").setUnlocalizedName("cleanWater");

    public static final CreativeTabs MCPP_TAB = new MCppCreativeTab();

    public MCpp() {

        INSTANCE = this;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent ev) {

        log.inf("[MCpp] starting pre init");
        long time = System.currentTimeMillis();

        MCppModule.loadAll(MCppModule.Phase.PRE_INIT);

        log.inf("[MCpp] ended pre init in: " + (System.currentTimeMillis() - time) + " ms");
    }

    @EventHandler
    public void load(FMLInitializationEvent ev) {

        log.inf("[MCpp] starting init");
        long time = System.currentTimeMillis();

        proxy.registerRenderers();

        MCppModule.loadAll(MCppModule.Phase.INIT);

        log.inf("[MCpp] ended init in: " + (System.currentTimeMillis() - time) + " ms");
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent ev) {

        log.inf("[MCpp] starting post init");

        long time = System.currentTimeMillis();
        MCppModule.loadAll(MCppModule.Phase.POST_INIT);

        Set<MCppModule> unloadedModules = MCppModule.checkLoading();
        if(!unloadedModules.isEmpty()) {

            for (MCppModule module : unloadedModules) {

                if(module.loadingException != null)
                    log.err("Couldn't load module " + module.name, module.loadingException);
                else
                    log.err("Couldn't load module " + module.name);
            }
        }
        log.inf("[MCpp] ended post init in: " + (System.currentTimeMillis() - time) + " ms");
    }

    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent ev) {

        MCpp.proxy.onServerStopping(ev);
    }
}
