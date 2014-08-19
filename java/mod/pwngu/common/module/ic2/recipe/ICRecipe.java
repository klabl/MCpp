package mod.pwngu.common.module.ic2.recipe;

import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;

import java.util.*;

public class ICRecipe implements IRecipeInput {

    private ItemStack item;
    private int amount;
    private ArrayList<ItemStack> inputList;

    public ICRecipe(ItemStack item) {

        this.item = item;
        this.amount = item.stackSize;
        this.inputList = new ArrayList<ItemStack>();
        inputList.add(item);
    }

    @Override
    public boolean matches(ItemStack subject) {

        return subject.getItem().equals(item.getItem());
    }

    @Override
    public int getAmount() {

        return amount;
    }

    @Override
    public List<ItemStack> getInputs() {

        return inputList;
    }
}
