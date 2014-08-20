package mod.pwngu.common.module.survivial.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import mod.pwngu.common.module.thirst.util.NeedStats;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;

public class MCppBlockBreakHandler {

    @SubscribeEvent
    public void onPlayerBlockBreakWithSpeed(PlayerEvent.BreakSpeed ev) {

        ev.newSpeed = ev.originalSpeed * 0.5F;

        if(ev.entityPlayer.getHeldItem() == null ||
                !ev.entityPlayer.getHeldItem().getItem().canHarvestBlock(ev.block, ev.entityPlayer.getHeldItem())) {

            ev.newSpeed *= 0.1F;
        }

        NeedStats stats = NeedStats.get(ev.entityPlayer);
        if(stats.getThirstLevel() <= 8 && stats.getThirstLevel() > 6) {

            ev.newSpeed *= 0.75F;
        } else if(stats.getThirstLevel() <= 4) {

            ev.newSpeed *= 0.5F;
        }
    }

    @SubscribeEvent
    public void onHarvestDrop(BlockEvent.HarvestDropsEvent ev) {

        if(ev.block.equals(Blocks.leaves) || ev.block.equals(Blocks.leaves2)) {

            if(ev.world.rand.nextFloat() <= 0.2F) ev.drops.add(new ItemStack(Items.stick, 1));
        }
    }
}
