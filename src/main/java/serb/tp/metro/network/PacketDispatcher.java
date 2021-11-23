package serb.tp.metro.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import serb.tp.metro.Main;
import serb.tp.metro.network.client.SoundsToPlayersMessage;
import serb.tp.metro.network.client.SyncCraftTimeMessage;
import serb.tp.metro.network.client.SyncEquipBackpuckMessage;
import serb.tp.metro.network.client.SyncEquipGunMessage;
import serb.tp.metro.network.client.SyncEquipMaskMessage;
import serb.tp.metro.network.client.SyncEquipOuterwearMessage;
import serb.tp.metro.network.client.SyncEquipPantsMessage;
import serb.tp.metro.network.client.SyncGuiContainerSpawnerMaxQuantityLootMessage;
import serb.tp.metro.network.client.SyncGuiContainerSpawnerMessage;
import serb.tp.metro.network.client.SyncGuiContainerSpawnerSpawnTimeMessage;
import serb.tp.metro.network.server.AddClanMessage;
import serb.tp.metro.network.server.ChangeAmmoMessage;
import serb.tp.metro.network.server.ChangeFilterMessage;
import serb.tp.metro.network.server.ChangeFireModMessage;
import serb.tp.metro.network.server.ChangeItemWeight;
import serb.tp.metro.network.server.ChangeSafetyModMessage;
import serb.tp.metro.network.server.ChangeSpawnProbabilityMessage;
import serb.tp.metro.network.server.ChangeSpawnTimeMessage;
import serb.tp.metro.network.server.ChangeVisorMessage;
import serb.tp.metro.network.server.ChengeMaxQuantityLootMessage;
import serb.tp.metro.network.server.EditCustomizationMessage;
import serb.tp.metro.network.server.LoadAmmoMessage;
import serb.tp.metro.network.server.LoadGunMagMessage;
import serb.tp.metro.network.server.NewCraftMessage;
import serb.tp.metro.network.server.OnItemLeftClickMessage;
import serb.tp.metro.network.server.OpenGuiMessage;
import serb.tp.metro.network.server.PickupMessage;
import serb.tp.metro.network.server.RemovingFilterMessage;
import serb.tp.metro.network.server.ResperatingMessage;
import serb.tp.metro.network.server.SaveContainerServerMessage;
import serb.tp.metro.network.server.ShootMessage;
import serb.tp.metro.network.server.UnloadAmmoMessage;
import serb.tp.metro.network.server.UnloadGunMagMessage;

public class PacketDispatcher {

	private static byte packetId = 0;

	private static final SimpleNetworkWrapper dispatcher = NetworkRegistry.INSTANCE.newSimpleChannel(Main.modid);

	public static final void registerPackets() {
		
		
		registerMessage(SyncEquipMaskMessage.class);
		registerMessage(SyncEquipOuterwearMessage.class);
		registerMessage(SyncEquipPantsMessage.class);
		registerMessage(SyncEquipBackpuckMessage.class);
		registerMessage(SyncEquipGunMessage.class);
		registerMessage(SyncGuiContainerSpawnerMessage.class);
		registerMessage(ChengeMaxQuantityLootMessage.class);
		registerMessage(SyncGuiContainerSpawnerSpawnTimeMessage.class);
		registerMessage(SoundsToPlayersMessage.class);
		registerMessage(SyncCraftTimeMessage.class);
		

		registerMessage(ShootMessage.class);
		registerMessage(ChangeSafetyModMessage.class);
		registerMessage(ChangeFireModMessage.class);
		registerMessage(OpenGuiMessage.class);
		registerMessage(PickupMessage.class);
		registerMessage(LoadAmmoMessage.class);
		registerMessage(UnloadAmmoMessage.class);
		registerMessage(ChangeAmmoMessage.class);
		registerMessage(LoadGunMagMessage.class);
		registerMessage(UnloadGunMagMessage.class);
		registerMessage(ChangeVisorMessage.class);
		registerMessage(ResperatingMessage.class);
		registerMessage(ChangeFilterMessage.class);
		registerMessage(ChangeItemWeight.class);
		registerMessage(RemovingFilterMessage.class);
		registerMessage(ChangeSpawnProbabilityMessage.class);
		registerMessage(SaveContainerServerMessage.class);
		registerMessage(SyncGuiContainerSpawnerMaxQuantityLootMessage.class);
		registerMessage(ChangeSpawnTimeMessage.class);
		registerMessage(NewCraftMessage.class);
		registerMessage(OnItemLeftClickMessage.class);
		registerMessage(EditCustomizationMessage.class);
		registerMessage(AddClanMessage.class);
	}

	private static final <T extends AbstractMessage<T> & IMessageHandler<T, IMessage>> void registerMessage(Class<T> clazz) {
		if (AbstractMessage.AbstractClientMessage.class.isAssignableFrom(clazz)) 
		{
			PacketDispatcher.dispatcher.registerMessage(clazz, clazz, packetId++, Side.CLIENT);
		} 
		else if (AbstractMessage.AbstractServerMessage.class.isAssignableFrom(clazz)) 
		{
			PacketDispatcher.dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		} 
		else 
		{
			PacketDispatcher.dispatcher.registerMessage(clazz, clazz, packetId, Side.CLIENT);
			PacketDispatcher.dispatcher.registerMessage(clazz, clazz, packetId++, Side.SERVER);
		}
	}

	public static final void sendTo(IMessage message, EntityPlayerMP player) 
	{
		PacketDispatcher.dispatcher.sendTo(message, player);
	}

	public static void sendToAll(IMessage message) 
	{
		PacketDispatcher.dispatcher.sendToAll(message);
	}

	public static final void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point) 
	{
		PacketDispatcher.dispatcher.sendToAllAround(message, point);
	}

	public static final void sendToAllAround(IMessage message, int dimension, double x, double y, double z, double range) 
	{
		PacketDispatcher.sendToAllAround(message, new NetworkRegistry.TargetPoint(dimension, x, y, z, range));
	}

	public static final void sendToAllAround(IMessage message, EntityPlayer player, double range) 
	{
		PacketDispatcher.sendToAllAround(message, player.worldObj.provider.dimensionId, player.posX, player.posY, player.posZ, range);
	}

	public static final void sendToDimension(IMessage message, int dimensionId) 
	{
		PacketDispatcher.dispatcher.sendToDimension(message, dimensionId);
	}

	public static final void sendToServer(IMessage message) 
	{
		PacketDispatcher.dispatcher.sendToServer(message);
	}

}