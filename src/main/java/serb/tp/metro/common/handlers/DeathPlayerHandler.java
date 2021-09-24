package serb.tp.metro.common.handlers;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import serb.tp.metro.entities.EntityBody;

public class DeathPlayerHandler {


	
	@SubscribeEvent(receiveCanceled = true,	priority = EventPriority.HIGHEST)
	public void dropCrops(LivingDropsEvent event) {
	    EntityLivingBase to = event.entityLiving;
	    World world = event.entityLiving.worldObj;
	    EntityBody body = new EntityBody(world);
	    body.setPosition(to.posX, to.posY, to.posZ);
	    body.setDrops(event.drops);
	    System.out.println(world.isRemote);
	    world.spawnEntityInWorld(body);
	    event.setCanceled(true);
	}
	
	
	/*@SubscribeEvent(receiveCanceled = true,	priority = EventPriority.HIGHEST)
	public void playerDeath(LivingDeathEvent e) {
		e.setCanceled(true);
		
		if (e.entityLiving instanceof EntityPlayer) {
			
			World world = e.entity.worldObj;
			EntityPlayer player = (EntityPlayer) e.entityLiving;
			System.out.println(player.posX);
			EntityBody body = new EntityBody(world, player.inventory.mainInventory, player.inventory.armorInventory);
			world.spawnEntityInWorld(body);
			body.setPosition(player.posX, player.posY, player.posZ);
			
			for (int i = 0; i<player.inventory.getSizeInventory(); i++) {
				//Тут if на недроп
				player.inventory.setInventorySlotContents(i, null);
			}
			world.func_147480_a((int) player.posX, (int) player.posY, (int) player.posZ, false);
			world.spawnEntityInWorld(body);
		}
	}*/
}
