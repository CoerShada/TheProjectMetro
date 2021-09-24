package serb.tp.metro.entities.player.handlers;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import serb.tp.metro.Main;
import serb.tp.metro.client.gui.AdtributeBarRenderer;
import serb.tp.metro.entities.player.PropertiesRegistry;

public class StaminaHandler {
	
	double prevPosX;
	double prevPosY; 
	double prevPosZ; 
	double speed;

	//Возвращает макс. значение жажды.
		public static float getStaminaMax(EntityPlayer player) {		
			
			return (float) player.getEntityAttribute(PropertiesRegistry.THIRSTY_MAX).getAttributeValue();			
		}
		
		//Возвращает текущее значение жажды.
	    public static float getStamina(EntityPlayer player) {
	    	    	
	        return player.getDataWatcher().getWatchableObjectFloat(PropertiesRegistry.STAMINA);
	    }
		
	    //Устанавливает текущее значение жажды.
		public static void setStamina(EntityPlayer player, float value) {	
			
			player.getDataWatcher().updateObject(PropertiesRegistry.STAMINA, MathHelper.clamp_float(value, 0.0F, getStaminaMax(player)));
		}
		
		//Сбрасывает значение жажды.
		public static void refillStamina(EntityPlayer player) {	
			
			player.getDataWatcher().updateObject(PropertiesRegistry.STAMINA, getStaminaMax(player));
		}
		
		//Уменьшает жажду на указанную величину.
		public static void decreaseStamina(EntityPlayer player, float value) {
			
			player.getDataWatcher().updateObject(PropertiesRegistry.STAMINA, MathHelper.clamp_float(getStamina(player) - value, 0.0F, getStaminaMax(player)));	
		}
		
		//Увеличивает жажду на указанную величину.
		public static void increaseStamina(EntityPlayer player, float value) {

			player.getDataWatcher().updateObject(PropertiesRegistry.STAMINA, MathHelper.clamp_float(getStamina(player) + value, 0.0F, getStaminaMax(player)));
		}
		
		//Сохранение значения жажды в NBT игрока.
		public static void saveStaminaToNBT(EntityPlayer player) {
			
			player.getEntityData().setFloat(Main.modid + ":stamina", getStamina(player));
		}
		
		//Загрузка жажды из NBT.
		public static float loadStaminaFromNBT(EntityPlayer player) {
			
			return player.getEntityData().hasKey(Main.modid + ":stamina") ? player.getEntityData().getFloat(Main.modid + ":stamina") : getStaminaMax(player);
		}
		
		@SubscribeEvent
		public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
			
			if (event.entity instanceof EntityPlayer) {
				
				EntityPlayer player = (EntityPlayer) event.entity;
				if (!player.worldObj.isRemote) {
									
					//Симуляция изменения значения для принудительной 
					//синхронизации текущего параметра жажды с клиентом
					//при переходе между мирами.
					if (getStamina(player) < getStaminaMax(player)) {
						
						setStamina(player, getStamina(player) + 1.0F);
						setStamina(player, getStamina(player) - 1.0F);
					}
				}
			}
		}
	    
		@SubscribeEvent
		public void onPlayerUpdate(LivingUpdateEvent event) {
			
			
			if (event.entityLiving instanceof EntityPlayer) {
				
				
				EntityPlayer player = (EntityPlayer) event.entityLiving;
				
				
				if (!player.worldObj.isRemote && !player.capabilities.isCreativeMode) {
					
					speed = Math.sqrt(Math.pow(player.posX - prevPosX, 2) + Math.pow(player.posY - prevPosY, 2) + Math.pow(player.posZ - prevPosZ, 2));
					prevPosX = player.posX;
					prevPosY = player.posY;
					prevPosZ = player.posZ;
					if(getStamina(player)<=0) 
					{
					    player.setSprinting(false);
					}
					if (player.isSprinting()) {
						decreaseStamina(player, 0.05F + WeightHandler.getWeight(player)/520000);									
					}
					else if(speed!=0 && !player.isSprinting()) {
						decreaseStamina(player, 0.004F);
					}
					
					else 
					{
						increaseStamina(player, 0.25F);//Увеличение жажды в присяди каждые 20 сек. на 1 ед.
					}
				}
			}	
		}
		
		@SubscribeEvent
		public void onPlayerLoad(PlayerEvent.LoadFromFile event) {
			
			
			//Загрузка значения жажды из NBT при входе на сервер (срабатывает для физического и логического серверов).
			setStamina(event.entityPlayer, loadStaminaFromNBT(event.entityPlayer));
		}
		
		@SubscribeEvent
		public void onPlayerSave(PlayerEvent.SaveToFile event) {
			
			//Сохранение значения жажды в NBT игрока при выходе.
			saveStaminaToNBT(event.entityPlayer);
		}
		
	
}
