package serb.tp.metro.entities.player.handlers;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import serb.tp.metro.Main;
import serb.tp.metro.PotionsRegistry;
import serb.tp.metro.client.gui.AdtributeBarRenderer;
import serb.tp.metro.entities.player.PropertiesRegistry;

public class ThirstyHandler {
	
	double prevPosX;
	double prevPosY; 
	double prevPosZ; 
	double speed;
	
	//Возвращает макс. значение жажды.
	public static float getThirstyMax(EntityPlayer player) {		
		
		return (float) player.getEntityAttribute(PropertiesRegistry.THIRSTY_MAX).getAttributeValue();				
	}
	
	//Возвращает текущее значение жажды.
    public static float getThirsty(EntityPlayer player) {
    	    	
        return player.getDataWatcher().getWatchableObjectFloat(PropertiesRegistry.THIRSTY);
    }
	
    //Устанавливает текущее значение жажды.
	public static void setThirsty(EntityPlayer player, float value) {	
		
		player.getDataWatcher().updateObject(PropertiesRegistry.THIRSTY, MathHelper.clamp_float(value, 0.0F, getThirstyMax(player)));
	}
	
	//Сбрасывает значение жажды.
	public static void refillThirsty(EntityPlayer player) {	
		
		player.getDataWatcher().updateObject(PropertiesRegistry.THIRSTY, getThirstyMax(player));
	}
	
	//Уменьшает жажду на указанную величину.
	public static void decreaseThirsty(EntityPlayer player, float value) {
		
		player.getDataWatcher().updateObject(PropertiesRegistry.THIRSTY, MathHelper.clamp_float(getThirsty(player) + value, 0.0F, getThirstyMax(player)));	
	}
	
	//Увеличивает жажду на указанную величину.
	public static void increaseThirsty(EntityPlayer player, float value) {

		player.getDataWatcher().updateObject(PropertiesRegistry.THIRSTY, MathHelper.clamp_float(getThirsty(player) - value, 0.0F, getThirstyMax(player)));
	}
	
	//Сохранение значения жажды в NBT игрока.
	public static void saveThirstyToNBT(EntityPlayer player) {
		
		player.getEntityData().setFloat(Main.modid + ":thirsty", getThirsty(player));
	}
	
	//Загрузка жажды из NBT.
	public static float loadThirstyFromNBT(EntityPlayer player) {
		
		return player.getEntityData().hasKey(Main.modid + ":thirsty") ? player.getEntityData().getFloat(Main.modid + ":thirsty") : getThirstyMax(player);
	}
	
	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event) {
		
		if (event.entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer) event.entity;
			if (!player.worldObj.isRemote) {
								
				//Симуляция изменения значения для принудительной 
				//синхронизации текущего параметра жажды с клиентом
				//при переходе между мирами.
				if (getThirsty(player) < getThirstyMax(player)) {
					
					setThirsty(player, getThirsty(player) + 1.0F);
					setThirsty(player, getThirsty(player) - 1.0F);
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
				
			
				if (player.isSprinting()) {													
					increaseThirsty(player, 0.01F);//Увеличение жажды при беге каждые 5 сек. на 1 ед.	
				}
				else if(speed!=0 && !player.isSprinting()) {
					increaseThirsty(player, 0.004F);//Увеличение жажды на 1 при ходьбе каждые 12,5 сек. на 1 ед.
					
				}
				else 
				{
					increaseThirsty(player, 0.0025F);//Увеличение жажды в присяди каждые 20 сек. на 1 ед.
					
				}
				if(getThirsty(player)<=0 && player.ticksExisted % 40 == 0) 
				{
				    player.setHealth(player.getHealth()-1);
				}

			}
		}	
	}
	
	@SubscribeEvent
	public void onPlayerLoad(PlayerEvent.LoadFromFile event) {
		
		
		//Загрузка значения жажды из NBT при входе на сервер (срабатывает для физического и логического серверов).
		setThirsty(event.entityPlayer, loadThirstyFromNBT(event.entityPlayer));
	}
	
	@SubscribeEvent
	public void onPlayerSave(PlayerEvent.SaveToFile event) {
		
		//Сохранение значения жажды в NBT игрока при выходе.
		saveThirstyToNBT(event.entityPlayer);
	}
	
	@SubscribeEvent
	public void onPlayerEat(PlayerUseItemEvent.Tick event) {
		
		
			
			EntityPlayer player = event.entityPlayer;
			if (event.duration==1) {
						
				if (event.item.getItem() == Items.potionitem) {
					
					decreaseThirsty(player, 35.0F);//Уменьшение жажды при выпивании зелья на 3.5 ед.
				}
			}
				
			
		
		
	}
	

	

	
}
