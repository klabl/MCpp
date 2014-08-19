package mod.pwngu.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class MCppBlock extends Block {

    public static enum Names {

        CLEAN_WATER("cleanWater", "fluid.cleanWater", "water_clean");

        public final String blockName;
        public final String unlocalizedName;
        public final String textureName;

        private Names(String blockName, String unlocalizedName, String textureName) {

            this.blockName = blockName;
            this.unlocalizedName = unlocalizedName;
            this.textureName = textureName;
        }
    }

    public final MCppBlock.Names name;

    protected MCppBlock(MCppBlock.Names name, Material blockMaterial) {

        super(blockMaterial);
        this.name = name;
    }
}
