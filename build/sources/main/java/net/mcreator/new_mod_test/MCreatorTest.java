package net.mcreator.new_mod_test;

import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.biome.Biome;
import net.minecraft.util.math.BlockPos;
import net.minecraft.init.Blocks;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.boss.EntityDragon;

import java.util.Random;

public class MCreatorTest extends new_mod_test.ModElement {

	@GameRegistry.ObjectHolder("new_mod_test:test")
	public static final BiomeGenCustom biome = null;

	public MCreatorTest(new_mod_test instance) {
		super(instance);
		instance.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.FOREST);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(biome, 1));
	}

	static class BiomeGenCustom extends Biome {

		public BiomeGenCustom() {
			super(new Biome.BiomeProperties("Crazy").setRainfall(0.5F).setBaseHeight(0.1F).setWaterColor(-14329397).setHeightVariation(1F)
					.setTemperature(0.5F));
			setRegistryName("test");
			topBlock = Blocks.ICE.getDefaultState();
			fillerBlock = Blocks.PACKED_ICE.getDefaultState();
			decorator.generateFalls = true;
			decorator.treesPerChunk = 1;
			decorator.flowersPerChunk = 1;
			decorator.grassPerChunk = 1;
			decorator.deadBushPerChunk = 1;
			decorator.mushroomsPerChunk = 1;
			decorator.bigMushroomsPerChunk = 1;
			decorator.reedsPerChunk = 1;
			decorator.cactiPerChunk = 1;
			decorator.sandPatchesPerChunk = 1;
			decorator.gravelPatchesPerChunk = 1;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
			this.spawnableCreatureList.add(new SpawnListEntry(EntityDragon.class, 40, 1, 5));
			this.spawnableCreatureList.add(new SpawnListEntry(EntityWither.class, 40, 1, 5));
		}

		@SideOnly(Side.CLIENT)
		@Override
		public int getGrassColorAtPos(BlockPos pos) {
			return -13261999;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public int getFoliageColorAtPos(BlockPos pos) {
			return -13261999;
		}

		@SideOnly(Side.CLIENT)
		@Override
		public int getSkyColorByTemp(float currentTemperature) {
			return -5916161;
		}

		@Override
		public WorldGenAbstractTree getRandomTreeFeature(Random rand) {
			return new WorldGenMegaPineTree(false, false);
		}
	}
}
