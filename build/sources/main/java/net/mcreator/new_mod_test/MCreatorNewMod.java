package net.mcreator.new_mod_test;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.creativetab.CreativeTabs;

public class MCreatorNewMod extends new_mod_test.ModElement {

	public MCreatorNewMod(new_mod_test instance) {
		super(instance);
	}

	public static CreativeTabs tab = new CreativeTabs("tabnewmod") {

		@SideOnly(Side.CLIENT)
		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(Items.EXPERIENCE_BOTTLE, (int) (1));
		}

		@SideOnly(Side.CLIENT)
		public boolean hasSearchBar() {
			return false;
		}
	};
}
