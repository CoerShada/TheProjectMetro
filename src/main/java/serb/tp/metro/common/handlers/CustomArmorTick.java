package serb.tp.metro.common.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import serb.tp.metro.Main;
import serb.tp.metro.items.ItemMask;
import serb.tp.metro.items.armor.CustomItemArmor;

public class CustomArmorTick {

	private static ItemStack mask = (ItemStack)null;
	private static ItemStack gun = (ItemStack)null;
	private static ItemStack outerwear = (ItemStack)null;
	private static ItemStack pants = (ItemStack)null;
	private static ItemStack backpuck = (ItemStack)null;
	private static ItemStack chestrig = (ItemStack)null;

	@SubscribeEvent
	public void onPlayerUpdateEvent(LivingUpdateEvent event) {
		if(!(event.entityLiving instanceof EntityPlayer)) {
			return;
		}
		

		EntityPlayer player = (EntityPlayer) event.entityLiving;
		

		if(player.inventory.getStackInSlot(15) != null && player.inventory.getStackInSlot(15).getItem() instanceof ItemMask && player.inventory.getStackInSlot(15).hasTagCompound()) 
		{
		    
			mask = player.inventory.getStackInSlot(15);
			CustomItemArmor.onCustomArmorTick(player.worldObj, player, mask);
			int time = mask.getTagCompound().getInteger("filterTime");
			mask.getTagCompound().setInteger("filterTime", time - 1);
			if (time % 100==0)
			    player.playSound(Main.modid +":resperating_mask", 10, 1);
			player.inventoryContainer.detectAndSendChanges();
		} 
		else 
		{
			mask = (ItemStack)null;
		}




	}

}