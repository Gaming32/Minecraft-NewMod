package net.mcreator.new_mod_test;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.client.gui.GuiTextField;

import java.util.HashMap;

public class MCreatorTeleporterGuiFinish extends new_mod_test.ModElement {

	public MCreatorTeleporterGuiFinish(new_mod_test instance) {
		super(instance);
	}

	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MCreatorTeleporterGuiFinish!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MCreatorTeleporterGuiFinish!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MCreatorTeleporterGuiFinish!");
			return;
		}
		if (dependencies.get("guiinventory") == null) {
			System.err.println("Failed to load dependency guiinventory for procedure MCreatorTeleporterGuiFinish!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MCreatorTeleporterGuiFinish!");
			return;
		}
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		HashMap guiinventory = (HashMap) dependencies.get("guiinventory");
		World world = (World) dependencies.get("world");
		{
			TileEntity tileEntity = world.getTileEntity(new BlockPos((int) x, (int) y, (int) z));
			if (tileEntity != null)
				tileEntity.getTileData().setDouble("Destination", new Object() {

					int convert(String s) {
						try {
							return Integer.parseInt(s.trim());
						} catch (Exception e) {
						}
						return 0;
					}
				}.convert((new Object() {

					public String getText() {
						GuiTextField textField = (GuiTextField) guiinventory.get("text:Destination");
						if (textField != null) {
							return textField.getText();
						}
						return "";
					}
				}.getText())));
		}
	}
}
