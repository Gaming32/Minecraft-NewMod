package net.mcreator.new_mod_test;

import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

import net.minecraftforge.fml.common.FMLCommonHandler;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Container;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiButton;

import java.util.HashMap;

public class MCreatorTeleporterGui extends new_mod_test.ModElement {

	public static int GUIID = 1;
	public static HashMap guiinventory = new HashMap();
	public static IInventory inherited;

	public MCreatorTeleporterGui(new_mod_test instance) {
		super(instance);
	}

	public static class GuiContainerMod extends Container {

		World world;
		EntityPlayer entity;
		int x, y, z;

		public GuiContainerMod(World world, int x, int y, int z, EntityPlayer player) {
			this.world = world;
			this.entity = player;
			this.x = x;
			this.y = y;
			this.z = z;
			TileEntity ent = world.getTileEntity(new BlockPos(x, y, z));
			if (ent instanceof MCreatorTeleporter.TileEntityCustom)
				inherited = (IInventory) ent;
			else
				inherited = new InventoryBasic("", true, 9);
			int si;
			int sj;
			for (si = 0; si < 3; ++si)
				for (sj = 0; sj < 9; ++sj)
					this.addSlotToContainer(new Slot(player.inventory, sj + (si + 1) * 9, 12 + 8 + sj * 18, 0 + 84 + si * 18));
			for (si = 0; si < 9; ++si)
				this.addSlotToContainer(new Slot(player.inventory, si, 12 + 8 + si * 18, 0 + 142));
		}

		@Override
		public boolean canInteractWith(EntityPlayer player) {
			return true;
		}

		@Override
		public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = (Slot) this.inventorySlots.get(index);
			if (slot != null && slot.getHasStack()) {
				ItemStack itemstack1 = slot.getStack();
				itemstack = itemstack1.copy();
				if (index < 0) {
					if (!this.mergeItemStack(itemstack1, 0, this.inventorySlots.size(), true)) {
						return ItemStack.EMPTY;
					}
				} else if (!this.mergeItemStack(itemstack1, 0, 0, false)) {
					if (index < 0 + 27) {
						if (!this.mergeItemStack(itemstack1, 0 + 27, this.inventorySlots.size(), true)) {
							return ItemStack.EMPTY;
						}
					} else {
						if (!this.mergeItemStack(itemstack1, 0, 0 + 27, false)) {
							return ItemStack.EMPTY;
						}
					}
					return ItemStack.EMPTY;
				}
				if (itemstack1.getCount() == 0) {
					slot.putStack(ItemStack.EMPTY);
				} else {
					slot.onSlotChanged();
				}
				if (itemstack1.getCount() == itemstack.getCount()) {
					return ItemStack.EMPTY;
				}
				slot.onTake(playerIn, itemstack1);
			}
			return itemstack;
		}

		@Override
		public boolean mergeItemStack(ItemStack itemstack, int from, int to, boolean reverse) {
			boolean mergeResult = false;
			int i = reverse ? to - 1 : from;
			int order = reverse ? -1 : 1;
			if (itemstack.isStackable()) {
				while ((!reverse && i < to || reverse && i >= from) && itemstack.getCount() > 0) {
					Slot slot = this.inventorySlots.get(i);
					ItemStack currstack = slot.getStack();
					if (!currstack.isEmpty()) {
						int maxsize = Math.min(slot.getSlotStackLimit(), itemstack.getMaxStackSize());
						int amount = Math.min(maxsize, itemstack.getCount());
						ItemStack reducedstack = itemstack.copy();
						reducedstack.setCount(amount);
						if (slot.isItemValid(reducedstack) && currstack.getItem().equals(itemstack.getItem())
								&& (!itemstack.getHasSubtypes() || itemstack.getItemDamage() == currstack.getItemDamage())
								&& ItemStack.areItemStackTagsEqual(itemstack, currstack)) {
							int currsize = currstack.getCount() + itemstack.getCount();
							if (currsize <= maxsize) {
								itemstack.setCount(0);
								currstack.setCount(currsize);
								slot.putStack(currstack);
								mergeResult = true;
							} else if (currstack.getCount() < maxsize) {
								itemstack.shrink(maxsize - currstack.getCount());
								currstack.setCount(maxsize);
								slot.putStack(currstack);
								mergeResult = true;
							}
						}
					}
					i += order;
				}
			}
			if (itemstack.getCount() > 0) {
				i = reverse ? to - 1 : from;
				while ((!reverse && i < to || reverse && i >= from) && itemstack.getCount() > 0) {
					Slot slot = this.inventorySlots.get(i);
					ItemStack currstack = slot.getStack();
					if (currstack.isEmpty()) {
						int maxsize = Math.min(slot.getSlotStackLimit(), itemstack.getMaxStackSize());
						int amount = Math.min(maxsize, itemstack.getCount());
						ItemStack reducedstack = itemstack.copy();
						reducedstack.setCount(amount);
						if (slot.isItemValid(reducedstack)) {
							currstack = itemstack.splitStack(amount);
							slot.putStack(currstack);
							mergeResult = true;
						}
					}
					i += order;
				}
			}
			return mergeResult;
		}

		public void onContainerClosed(EntityPlayer playerIn) {
			super.onContainerClosed(playerIn);
		}
	}

	public static class GuiWindow extends GuiContainer {

		World world;
		int x, y, z;
		EntityPlayer entity;
		GuiTextField Destination;

		public GuiWindow(World world, int x, int y, int z, EntityPlayer entity) {
			super(new GuiContainerMod(world, x, y, z, entity));
			this.world = world;
			this.x = x;
			this.y = y;
			this.z = z;
			this.entity = entity;
			this.xSize = 200;
			this.ySize = 166;
		}

		private static final ResourceLocation texture = new ResourceLocation("new_mod_test:textures/teleportergui.png");

		@Override
		public void drawScreen(int mouseX, int mouseY, float partialTicks) {
			this.drawDefaultBackground();
			super.drawScreen(mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.mc.renderEngine.bindTexture(texture);
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
			zLevel = 100.0F;
		}

		@Override
		protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
			try {
				super.mouseClicked(mouseX, mouseY, mouseButton);
				Destination.mouseClicked(mouseX - guiLeft, mouseY - guiTop, mouseButton);
			} catch (Exception ignored) {
			}
		}

		@Override
		public void updateScreen() {
			super.updateScreen();
			Destination.updateCursorCounter();
		}

		@Override
		protected void keyTyped(char typedChar, int keyCode) {
			try {
				super.keyTyped(typedChar, keyCode);
				Destination.textboxKeyTyped(typedChar, keyCode);
			} catch (Exception ignored) {
			}
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int par1, int par2) {
			this.fontRenderer.drawString("ID: ", 16, 11, -1);
			this.fontRenderer.drawString("TeleporterID", 37, 11, -1);
			this.fontRenderer.drawString("Destination:", 9, 31, -1);
			Destination.drawTextBox();
		}

		@Override
		public void onGuiClosed() {
			super.onGuiClosed();
			Keyboard.enableRepeatEvents(false);
		}

		@Override
		public void initGui() {
			super.initGui();
			this.guiLeft = (this.width - 200) / 2;
			this.guiTop = (this.height - 166) / 2;
			Keyboard.enableRepeatEvents(true);
			this.buttonList.clear();
			this.buttonList.add(new GuiButton(0, this.guiLeft + 28, this.guiTop + 65, 144, 20, "Set Destination"));
			Destination = new GuiTextField(0, this.fontRenderer, 71, 25, 120, 20);
			guiinventory.put("text:Destination", Destination);
			Destination.setMaxStringLength(32767);
			Destination.setText("0");
		}

		@Override
		protected void actionPerformed(GuiButton button) {
			MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
			World world = server.getWorld(entity.dimension);
			if (button.id == 0) {
				{
					java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("guiinventory", guiinventory);
					$_dependencies.put("world", world);
					MCreatorTeleporterGuiFinish.executeProcedure($_dependencies);
				}
			}
		}

		@Override
		public boolean doesGuiPauseGame() {
			return false;
		}
	}
}
