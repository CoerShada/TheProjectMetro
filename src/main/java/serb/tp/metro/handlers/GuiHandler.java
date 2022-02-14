package serb.tp.metro.handlers;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.storages.TileEntityStorage;
import serb.tp.metro.blocks.tiles.storages.creators.TileEntityCreator;
import serb.tp.metro.blocks.tiles.storages.spawners.TileEntityStorageSpawner;
import serb.tp.metro.client.gui.clan.GuiClanMainWindow;
import serb.tp.metro.client.gui.containers.GuiBackpackStorage;
import serb.tp.metro.client.gui.containers.GuiCustomPlayerInventory;
import serb.tp.metro.client.gui.containers.GuiCustomization;
import serb.tp.metro.client.gui.containers.GuiStorage;
import serb.tp.metro.client.gui.containers.GuiStorageCreator;
import serb.tp.metro.client.gui.containers.GuiStorageSpawner;
import serb.tp.metro.common.CommonProxy;
import serb.tp.metro.containers.ContainerBackpackStorage;
import serb.tp.metro.containers.ContainerCreator;
import serb.tp.metro.containers.ContainerCustomPlayer;
import serb.tp.metro.containers.ContainerCustomization;
import serb.tp.metro.containers.ContainerStorageSpawner;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.containers.StorageContainer;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
		if (guiId == CommonProxy.GUI_CUSTOM_INV) return new ContainerCustomPlayer(player);
		
		if (guiId == CommonProxy.GUI_BACPACK) return new ContainerBackpackStorage(player, new InventoryItemStorage(player.inventory.getStackInSlot(18)));
		
		if (guiId == CommonProxy.GUI_STORAGE) return new StorageContainer(player, (TileEntityStorage) world.getTileEntity(x, y, z), ((TileEntityStorage) world.getTileEntity(x, y, z)).width, ((TileEntityStorage) world.getTileEntity(x, y, z)).height);
		
		if (guiId==CommonProxy.GUI_STORAGE_SPAWNER) return new ContainerStorageSpawner(player, (TileEntityStorageSpawner) world.getTileEntity(x, y, z));
		
		if (guiId==CommonProxy.GUI_CREATOR) return new ContainerCreator(player, (TileEntityCreator) world.getTileEntity(x, y, z), ((TileEntityStorage) world.getTileEntity(x, y, z)).width, ((TileEntityStorage) world.getTileEntity(x, y, z)).height);
		
		if (guiId==CommonProxy.GUI_MODIFY) return new ContainerCustomization(1, player, player.inventory.getCurrentItem(), 0, 0);

		
		return null;
	}

	@Override
	public Object getClientGuiElement(int guiId, EntityPlayer player, World world, int x, int y, int z) {
		if (guiId == CommonProxy.GUI_CUSTOM_INV) return new GuiCustomPlayerInventory(player);
		
		if (guiId == CommonProxy.GUI_BACPACK) return new GuiBackpackStorage(player, new InventoryItemStorage(player.inventory.getStackInSlot(18)));
		
		if (guiId == CommonProxy.GUI_STORAGE) return new GuiStorage(player, (TileEntityStorage) world.getTileEntity(x, y, z));
		
		if (guiId == CommonProxy.GUI_CLAN) return new GuiClanMainWindow(player);
		
		if (guiId == CommonProxy.GUI_STORAGE_SPAWNER) return new GuiStorageSpawner(player, (TileEntityStorageSpawner) world.getTileEntity(x, y, z));
		
		if (guiId == CommonProxy.GUI_CREATOR) return new GuiStorageCreator(player, (TileEntityCreator) world.getTileEntity(x, y, z));

		if (guiId == CommonProxy.GUI_MODIFY) return new GuiCustomization(player, player.inventory.getCurrentItem());
		return null;
	}
	



	public static void openGui(EntityPlayer player, int guiId ,int x, int y, int z) {
		player.openGui(Main.mod, guiId, player.worldObj, x, y, z);
	}

	public static void openGui(EntityPlayer player, int guiId) {
		player.openGui(Main.mod, guiId, player.worldObj, (int)player.posX, (int)player.posY, (int)player.posZ);
	}
	

	


}