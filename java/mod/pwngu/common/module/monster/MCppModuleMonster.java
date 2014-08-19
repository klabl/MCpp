package mod.pwngu.common.module.monster;

import mod.pwngu.common.module.MCppModule;
import mod.pwngu.common.module.monster.event.MCppMonsterEventHandler;
import net.minecraftforge.common.MinecraftForge;

public class MCppModuleMonster extends MCppModule {

    public MCppModuleMonster(boolean enable) {

        super("MONSTER", enable);
    }

    @Override
    public void preRegister() {

    }

    @Override
    public void registerItems() {

    }

    @Override
    public void registerBlocks() {

    }

    @Override
    public void registerCrafting() {

    }

    @Override
    public void registerEntities() {

    }

    @Override
    public void registerEventHandler() {

        MinecraftForge.EVENT_BUS.register(new MCppMonsterEventHandler());
    }

    @Override
    public void registerMisc() {

    }

    @Override
    public void postRegister() {

    }
}
