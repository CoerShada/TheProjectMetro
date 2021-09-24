package serb.tp.metro.entities.player.handlers;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import serb.tp.metro.Main;
import serb.tp.metro.entities.player.PropertiesRegistry;


public class WeightHandler {

	public static final double WEIGHT_MAX = 60.0D;
	

    public static float getWeight(EntityPlayer player) {
    	
        return player.getDataWatcher().getWatchableObjectFloat(PropertiesRegistry.WEIGHT);
    }
    

	public static void setWeight(EntityPlayer player, float value) {	
		
		player.getDataWatcher().updateObject(PropertiesRegistry.WEIGHT, value);
	}
	

	public static void addWeight(EntityPlayer player, float value) {
		
		
		player.getDataWatcher().updateObject(PropertiesRegistry.WEIGHT, value);
	}
	

	public static void removeWeight(EntityPlayer player, float value) {
		
		player.getDataWatcher().updateObject(PropertiesRegistry.WEIGHT, getWeight(player) - value);
	}
	

	

	public static void saveWeightToNBT(EntityPlayer player) {
		
		player.getEntityData().setFloat(Main.modid + ":weight", getWeight(player));
	}
	

	public static float loadWeightFromNBT(EntityPlayer player) {
		
		return player.getEntityData().hasKey(Main.modid + ":weight") ? player.getEntityData().getFloat(Main.modid + ":weight") : 0;
	}
	

	
	@SubscribeEvent
	public void onPlayerLoad(PlayerEvent.LoadFromFile event) {
		
		setWeight(event.entityPlayer, loadWeightFromNBT(event.entityPlayer));
	}
	
    
	@SubscribeEvent
	public void onPlayerSave(PlayerEvent.SaveToFile event) {
				
		//Сохранение значения запаса монет в NBT игрока при выходе.
		saveWeightToNBT(event.entityPlayer);
	}
	
}

