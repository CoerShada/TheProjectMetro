package serb.tp.metro.client.handlers;

import java.util.Date;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.weapons.FireMod;
import serb.tp.metro.items.weapons.ItemWeapon;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.LoadAmmoMessage;
import serb.tp.metro.network.server.UnloadAmmoMessage;

public class MouseHandler {
	boolean canShoot = true;
	int nextRound = 0;
	//long canDo;
	int time=0;
	Minecraft mc;
	Item currentItem;
	ItemStack is;
	public MouseHandler(Minecraft mc) {
		this.mc = mc;
	}


	//Поменять на ClientTickEvent
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void mouseEvents(PlayerTickEvent e) 
	{
		if (e.side==Side.SERVER) return;

		
		if (e.phase==Phase.START)
			return;

		ItemStack itemStack = mc.thePlayer.inventory.getCurrentItem();
		isAiming(itemStack);
		onItemChanged(itemStack);
	    if (itemStack == null) return;
	   
	    
		//shoot(itemStack, mc.thePlayer, mc.theWorld);
		workWithMag(itemStack, mc.thePlayer, mc.theWorld);
	    
	    
	}
	
	@SideOnly(Side.CLIENT)
	private void isAiming(ItemStack itemStack) {
		if(Mouse.isButtonDown(1) && itemStack!=null && itemStack.getItem() instanceof ItemWeapon) {
			ClientProxy.isAiming = true;
		}
		else {
			ClientProxy.isAiming = false;
		}
	}
	
	@SideOnly(Side.CLIENT)
	private void shoot(ItemStack itemStack, EntityPlayer player, World world) {
		
		if (!(itemStack.getItem() instanceof ItemWeapon)) return;
		
		if(Mouse.isButtonDown(0) && mc.currentScreen==null)
	    {
			
	    	if(canShoot && itemStack.hasTagCompound()  && !itemStack.getTagCompound().getBoolean("safetyMod") && nextRound==0
	    		) {
	    		
	    		switch(FireMod.valueOf(itemStack.getTagCompound().getString("fireMod")))
	    		{
	    			case SEMI:
	    				nextRound = 1;
	    				canShoot = false;
	    				break;
	    			case RD2:
	    				nextRound = 2;
	    				canShoot = false;
	    				break;
	    			case RD3:
	    				nextRound = 3;
	    				canShoot = false;
	    				break;
	    			case FULLAUTO:
	    				
	    				nextRound = 1;
	    				break;
	    			default:
	    				break;
	    		}
	    		//this.currentItem = itemStack.getItem();
	    	}
	    }

	    if(mc.getMinecraft().thePlayer.inventory.getCurrentItem()==null || mc.getMinecraft().thePlayer.inventory.getCurrentItem().getItem()!=this.currentItem) {
	    	
	    	nextRound=0;
	    	canShoot = true;
	    	return;
	    }
	    
	    if (nextRound>0) {
	    	System.out.println(world.getTotalWorldTime());
    		if(mc.theWorld.isRemote && itemStack!=null && itemStack.getItem() instanceof ItemWeapon && itemStack.getTagCompound().getLong("notfire")+(60/itemStack.getTagCompound().getFloat("rateOfFire")*100)<=world.getTotalWorldTime()) {

    			//((ItemWeapon) itemStack.getItem()).shoot(itemStack, mc.thePlayer);
    			nextRound--;
    		}
    		
	    }
	    else if (!Mouse.isButtonDown(0) || mc.currentScreen!=null){
	    	canShoot = true;
	    }
	}
	
	@SideOnly(Side.CLIENT)
	private void onItemChanged(ItemStack itemStack) {
		if (itemStack==null) {
			is = null;
			currentItem = null;
			canShoot = true;
		}
		else if (is==null) {
			is = itemStack;
			canShoot = true;
		}
		else if (currentItem!=itemStack.getItem()) {
			currentItem = itemStack.getItem();
			is = itemStack;
			canShoot = true;
		}
	}

	@SideOnly(Side.CLIENT)
	private void workWithMag(ItemStack itemStack, EntityPlayer player, World world) {
		if (mc.currentScreen==null && itemStack!=null && itemStack.hasTagCompound() &&  itemStack.getItem() instanceof ItemMag) {
			ItemMag mag = (ItemMag) currentItem;
			
			if (Mouse.isButtonDown(0) && Mouse.isButtonDown(1)) {
				//canDo = new Date().getTime();
				mag.chengeAmmo(itemStack, world, player);
				this.currentItem = itemStack.getItem();
			}
			else if (Mouse.isButtonDown(0)) {
				mag.loadAmmo(itemStack, player);
				this.currentItem = itemStack.getItem();
			}
			else if (Mouse.isButtonDown(1)) {
				
				if (is.stackTagCompound.getLong("counter")+mag.cooldownUnloading <= world.getTotalWorldTime() && is.hasTagCompound() && is.getTagCompound().getIntArray("bullets").length>0) {
					
					PacketDispatcher.sendToServer(new UnloadAmmoMessage());
					is = mag.unloadAmmo(player, world);
					
				}
				this.currentItem = itemStack.getItem();
			}
		}

	}
}
