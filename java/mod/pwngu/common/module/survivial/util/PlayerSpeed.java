package mod.pwngu.common.module.survivial.util;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import mod.pwngu.common.main.MCpp;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

import java.util.HashMap;
import java.util.UUID;

public class PlayerSpeed implements IExtendedEntityProperties {

    public static final String PROPERTY_NAME = "PlayerMovementSpeed";
    public static final UUID movementSpeedUUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");


    private HashMap<String, Float> multipliers;

    private final EntityPlayer player;

    private float multiplier;
//    private AttributeModifier lastModifier;

    public static void register(EntityPlayer player) {

        player.registerExtendedProperties(PROPERTY_NAME, new PlayerSpeed(player));
    }

    public static PlayerSpeed get(EntityPlayer player) {

        return (PlayerSpeed) player.getExtendedProperties(PROPERTY_NAME);
    }

    public PlayerSpeed(EntityPlayer player) {

        this.multipliers = new HashMap<String, Float>();
        this.player = player;
        multiplier = 1.0F;
    }

    public void updateMultiplier(String name, float multiplier) {

        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) return;

        multipliers.put(name, multiplier);

        updateMultiplier();

        MCpp.log.inf("###");
        for(String s : multipliers.keySet()) {

            MCpp.log.inf(s + ": " + multipliers.get(s));
        }
    }

    public void removeMultiplier(String name) {

        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) return;

        if(multipliers.remove(name) != null) updateMultiplier();

    }

    private void updateMultiplier() {

        multiplier = 1.0F;
        for(Float mul : multipliers.values()) multiplier *= mul;
    }

    public void onUpdate() {

        //TODO add movement speed multiplier
//        IAttributeInstance attribute = player.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
//
//        if(attribute.getModifier(movementSpeedUUID) != null) {
//
//            attribute.removeModifier(lastModifier);
//        }
//
//        AttributeModifier modifier = new AttributeModifier(movementSpeedUUID, "PlayerSpeed multiplier", multiplier, 2);
//        lastModifier = modifier;
//
//        attribute.applyModifier(modifier);
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
