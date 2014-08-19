package mod.pwngu.common.module.survivial.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mod.pwngu.common.main.MCpp;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.HashMap;

public class PlayerSpeed implements IExtendedEntityProperties {

    public static final String PROPERTY_NAME = "PlayerMovementSpeed";
    public static final float BASE_SPEED = 0.1F;

    private static final int MOVESPEED_WATCHER = 25;

    private HashMap<String, Float> multipliers;

    private final EntityPlayer player;

    public static void register(EntityPlayer player) {

        player.registerExtendedProperties(PROPERTY_NAME, new PlayerSpeed(player));
    }

    public static PlayerSpeed get(EntityPlayer player) {

        return (PlayerSpeed) player.getExtendedProperties(PROPERTY_NAME);
    }

    public PlayerSpeed(EntityPlayer player) {

        this.multipliers = new HashMap<String, Float>();
        this.player = player;

        player.getDataWatcher().addObject(MOVESPEED_WATCHER, BASE_SPEED);
    }

    public void updateMultiplier(String name, float multiplier) {

        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) return;

        multipliers.put(name, multiplier);

        float walkspeed = BASE_SPEED;
        for(Float mul : multipliers.values()) walkspeed *= mul;
        player.getDataWatcher().updateObject(MOVESPEED_WATCHER, walkspeed);
    }

    public float getWalkSpeed() {

        return player.getDataWatcher().getWatchableObjectFloat(MOVESPEED_WATCHER);
    }

    @SideOnly(Side.CLIENT)
    public void onUpdate() {

        player.capabilities.setPlayerWalkSpeed(getWalkSpeed());
        MCpp.log.inf("walkspeed: " + player.capabilities.getWalkSpeed());
    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {

        NBTTagCompound properties = new NBTTagCompound();

        for(String name : multipliers.keySet())
            compound.setFloat(name, multipliers.get(name));

        compound.setTag(PROPERTY_NAME, properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

        if(!compound.hasKey(PROPERTY_NAME)) return;

        NBTTagCompound properties = compound.getCompoundTag(PROPERTY_NAME);

        for(Object name : properties.func_150296_c())
            multipliers.put((String) name, properties.getFloat((String) name));
    }

    @Override
    public void init(Entity entity, World world) {

    }
}
