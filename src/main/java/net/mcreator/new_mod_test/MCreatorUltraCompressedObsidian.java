package net.mcreator.new_mod_test;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.event.ModelRegistryEvent;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

public class MCreatorUltraCompressedObsidian extends new_mod_test.ModElement {

	@GameRegistry.ObjectHolder("new_mod_test:ultra_compressed_obsidian")
	public static final Block block = null;

	public MCreatorUltraCompressedObsidian(new_mod_test instance) {
		super(instance);
		instance.blocks.add(() -> new BlockCustom());
		instance.items.add(() -> new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels(ModelRegistryEvent event) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(
				"new_mod_test:ultra_compressed_obsidian", "inventory"));
	}

	public static class BlockCustom extends Block {

		public BlockCustom() {
			super(Material.ROCK);
			setRegistryName("ultra_compressed_obsidian");
			setUnlocalizedName("ultra_compressed_obsidian");
			setSoundType(SoundType.STONE);
			setHarvestLevel("pickaxe", 3);
			setHardness(400F);
			setResistance(48000F);
			setLightLevel(0F);
			setLightOpacity(255);
			setCreativeTab(MCreatorNewMod.tab);
		}

		@SideOnly(Side.CLIENT)
		@Override
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.SOLID;
		}
	}
}
