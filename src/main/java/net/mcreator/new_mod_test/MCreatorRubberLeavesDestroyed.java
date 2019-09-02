package net.mcreator.new_mod_test;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;

import java.util.HashMap;

public class MCreatorRubberLeavesDestroyed extends new_mod_test.ModElement {

	public MCreatorRubberLeavesDestroyed(new_mod_test instance) {
		super(instance);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MCreatorRubberLeavesDestroyed!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MCreatorRubberLeavesDestroyed!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MCreatorRubberLeavesDestroyed!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MCreatorRubberLeavesDestroyed!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorRubberLeavesDestroyed!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		double DropNum = 0;
		if (((new Object() {

			ItemStack stack() {
				ItemStack stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).copy();
				stack.setCount(1);
				return stack;
			}
		}.stack()).getItem() == new ItemStack(Items.SHEARS, (int) (1)).getItem() && (new Object() {

			ItemStack stack() {
				ItemStack stack = ((entity instanceof EntityLivingBase) ? ((EntityLivingBase) entity).getHeldItemMainhand() : ItemStack.EMPTY).copy();
				stack.setCount(1);
				return stack;
			}
		}.stack()).getMetadata() == new ItemStack(Items.SHEARS, (int) (1)).getMetadata())) {
			if (!world.isRemote) {
				EntityItem entityToSpawn = new EntityItem(world, x, y, z, new ItemStack(MCreatorRubberLeaves.block, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.spawnEntity(entityToSpawn);
			}
		} else {
			DropNum = (double) Math.floor((Math.random() * 6));
			if (((DropNum) == 0)) {
				if (!world.isRemote) {
					EntityItem entityToSpawn = new EntityItem(world, x, y, z, new ItemStack(MCreatorRubberSapling.block, (int) (1)));
					entityToSpawn.setPickupDelay(10);
					world.spawnEntity(entityToSpawn);
				}
			}
		}
	}
}
