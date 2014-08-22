package mod.pwngu.common.module;

import cpw.mods.fml.common.Loader;
import mod.pwngu.common.main.MCpp;
import mod.pwngu.common.module.ic2.MCppModuleIC2;
import mod.pwngu.common.module.monster.MCppModuleMonster;
import mod.pwngu.common.module.ores.MCppModuleOre;
import mod.pwngu.common.module.survivial.MCppModuleSurvival;
import mod.pwngu.common.module.thirst.MCppModuleThirst;

import java.util.HashSet;
import java.util.Set;

public abstract class MCppModule {

    static {

        //TODO Load config
    }

    public static final MCppModule ORE = new MCppModuleOre(true);
    public static final MCppModule THIRST = new MCppModuleThirst(true);
    public static final MCppModule MONSTER = new MCppModuleMonster(true);
    public static final MCppModule SURVIVAL = new MCppModuleSurvival(false);
    public static final MCppModule IC2 = new MCppModuleIC2(true);

    public static enum Phase {

        PRE_INIT,
        INIT,
        POST_INIT
    }

    public static void loadAll(Phase phase) {

        for(MCppModule module : modules()) {

            if(module.modDependant != null && phase != Phase.POST_INIT) continue;
            else module.load();
        }
    }

    public static Set<MCppModule> checkLoading() {

        HashSet<MCppModule> unloadedModules = new HashSet<MCppModule>();

        for(MCppModule module : modules()) if(module.isEnabled && !module.isLoaded) unloadedModules.add(module);

        return unloadedModules;
    }

    public static MCppModule[] modules() {

        return new MCppModule[] {ORE, THIRST, MONSTER, SURVIVAL, IC2};
    }

    public final String name;

    protected boolean isEnabled;
    public boolean isLoaded;

    public Exception loadingException;

    public final String modDependant;
    private final MCppModule[] dependants;

    protected MCppModule(String name, boolean enable) {

        this(name, enable, new MCppModule[]{}, null);
    }

    protected MCppModule(String name, boolean enable, MCppModule[] dependants, String modDependant) {

        this.name = name;
        this.isLoaded = false;
        this.modDependant = modDependant;
        this.loadingException = null;

        this.dependants = dependants;

        if(enable) {

            for (MCppModule module : dependants) {

                if (!module.isEnabled) {

                    isEnabled = false;
                    return;
                }
            }
        }

        isEnabled = enable;
    }

    public void load() {

        //When the module isn't enabled or is already loaded or already failed to load with an exception
        if(!isEnabled || isLoaded || loadingException != null) return;
        if(modDependant != null && !Loader.isModLoaded(modDependant)) return;
        for(MCppModule module : dependants) if(!module.isLoaded) return;

        try {

            preRegister();

            registerItems();
            registerBlocks();
            registerCrafting();
            registerEntities();
            registerMisc();
            registerEventHandler();

            postRegister();

            MCpp.log.inf("Successfully loaded module: " + name);
            isLoaded = true;
        } catch (Exception ex) {

            loadingException = ex;
            throw new RuntimeException(ex);
        }
    }

    public abstract void preRegister();

    public abstract void registerItems();

    public abstract void registerBlocks();

    public abstract void registerCrafting();

    public abstract void registerEntities();

    public abstract void registerEventHandler();

    public abstract void registerMisc();

    public abstract void postRegister();
}
