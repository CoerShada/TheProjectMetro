package serb.tp.metro.entities.player.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import serb.tp.metro.Main;
import serb.tp.metro.entities.player.PropertiesRegistry;
import serb.tp.metro.items.ItemMask;

public class RadiationHandler {
	
	public static float getRadiation(EntityPlayer player) 
	{		
		return player.getDataWatcher().getWatchableObjectFloat(PropertiesRegistry.RADIATION);		
	}

	public static void setRadiation(EntityPlayer player, float value) {	
		
		player.getDataWatcher().updateObject(PropertiesRegistry.RADIATION, MathHelper.clamp_float(value, 0.0F, 1000));
	}
	
	public static void decreaseRadiation(EntityPlayer player, float value) {
		
		player.getDataWatcher().updateObject(PropertiesRegistry.RADIATION, MathHelper.clamp_float(getRadiation(player) - value, 0.0F, 1000));
	}
	
	public static void increaseRadiation(EntityPlayer player, float value) {

		player.getDataWatcher().updateObject(PropertiesRegistry.RADIATION, MathHelper.clamp_float(getRadiation(player) + value, 0.0F, 1000));
	}
	

	public static void saveRadiationyToNBT(EntityPlayer player) {
		
		player.getEntityData().setFloat(Main.modid + ":radiation", getRadiation(player));

	}
	
	public static float loadRadiationyToNBT(EntityPlayer player) {
		
		return player.getEntityData().hasKey(Main.modid + ":radiation") ? player.getEntityData().getFloat(Main.modid + ":radiation") : 0;
	}
	
	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
		
		if (event.entity instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) event.entity;	
			
			if (!player.worldObj.isRemote) {
								
				if (getRadiation(player)<1000) {
					
					setRadiation(player, getRadiation(player) + 1.0F);
					setRadiation(player, getRadiation(player) - 1.0F);
				}
			}
		}
	}
	
	
	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event) 
	{
		
		if (event.entityLiving instanceof EntityPlayer) 
		{
			
			EntityPlayer player = (EntityPlayer) event.entityLiving;	
			if (!player.worldObj.isRemote && !player.capabilities.isCreativeMode) {
				
				if (player.posY>=50 && player.ticksExisted % 20 == 0) 
				{
					if (player.inventory.getStackInSlot(15)!=null && player.inventory.getStackInSlot(15).getItem() instanceof ItemMask && player.inventory.getStackInSlot(15).hasTagCompound() && player.inventory.getStackInSlot(15).getTagCompound().getInteger("filterTime")>0) 
						increaseRadiation(player, (float) (3*(1-player.inventory.getStackInSlot(15).getTagCompound().getDouble("radResistance"))));
					
					else
						increaseRadiation(player, 3.0F);//Увеличение радиации игрока на поверхности
				}	
				else if (player.posY<50 && player.ticksExisted % 200 == 0) 
				{	
					//Дописать работу с рад протектером
					decreaseRadiation(player, 1.0F);//Уменьшение радиации игрока в подземеке
				}


			}
			
		}
		
	}
	
	@SubscribeEvent
	public void onPlayerLoad(PlayerEvent.LoadFromFile event) {
		
		
		//Загрузка значения жажды из NBT при входе на сервер (срабатывает для физического и логического серверов).
		setRadiation(event.entityPlayer, loadRadiationyToNBT(event.entityPlayer));
	}
	
	@SubscribeEvent
	public void onPlayerSave(PlayerEvent.SaveToFile event) {
		
		//Сохранение значения жажды в NBT игрока при выходе.
		saveRadiationyToNBT(event.entityPlayer);
	}
	

	
}
