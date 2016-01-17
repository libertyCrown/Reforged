package org.silvercatcher.reforged.items.others;

import org.silvercatcher.reforged.items.ItemReforged;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemArrowBundle extends ItemReforged {

	
	public ItemArrowBundle() {
		
		super("arrow_bundle");
		setMaxStackSize(16);
	}

	@Override
	public void registerRecipes() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(this),
			new ItemStack(Items.arrow, 8), new ItemStack(Items.string));
	}

	@Override
	public float getHitDamage() {
		return 0f;
	}
}
