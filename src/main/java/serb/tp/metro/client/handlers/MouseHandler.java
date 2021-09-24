package serb.tp.metro.client.handlers;

import java.util.Date;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import serb.tp.metro.client.ClientProxy;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.weapons.FireMod;
import serb.tp.metro.items.weapons.ItemWeapon;

public class MouseHandler {
	boolean canShoot = true;
	int nextRound = 0;
	//long canDo;
	int time=0;
	Minecraft mc;
	Item currentItem;
	public MouseHandler(Minecraft mc) {
		this.mc = mc;
	}

	//Поменять на ClientTickEvent
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void mouseEvents(ClientTickEvent e) 
	{
		if (mc.theWorld==null)
			return;
	
		if (e.phase==Phase.START)
			return;

		ItemStack itemStack = mc.thePlayer.inventory.getCurrentItem();
		isAiming(itemStack);
	    if (itemStack == null) return;
	    
		shoot(itemStack);
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
	private void shoot(ItemStack itemStack) {
		if(Mouse.isButtonDown(0) && mc.currentScreen==null)
	    {
	    	
	    	if (!(itemStack.getItem() instanceof ItemWeapon)) return;

	    	
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
	    		this.currentItem = itemStack.getItem();
	    	}
	    }

	    if(mc.getMinecraft().thePlayer.inventory.getCurrentItem()==null || mc.getMinecraft().thePlayer.inventory.getCurrentItem().getItem()!=this.currentItem) {
	    	
	    	nextRound=0;
	    	canShoot = true;
	    	return;
	    }
	    
	    if (nextRound>0) {
	    	
    		if(mc.theWorld.isRemote && itemStack!=null && itemStack.getItem() instanceof ItemWeapon) {

    			((ItemWeapon) itemStack.getItem()).shoot(itemStack, mc.thePlayer);
    			nextRound--;
    		}
    		
	    }
	    else if (!Mouse.isButtonDown(0) || mc.currentScreen!=null){
	    	canShoot = true;
	    }
	}

	@SideOnly(Side.CLIENT)
	private void workWithMag(ItemStack itemStack, EntityPlayer player, World world) {
		if (mc.currentScreen==null && itemStack!=null && itemStack.hasTagCompound() &&  itemStack.getItem() instanceof ItemMag) {
			if (Mouse.isButtonDown(0) && Mouse.isButtonDown(1)) {
				//canDo = new Date().getTime();
				((ItemMag) itemStack.getItem()).chengeAmmo(itemStack, world, player);
				this.currentItem = itemStack.getItem();
			}
			else if (Mouse.isButtonDown(0)) {
				((ItemMag) itemStack.getItem()).loadAmmo(itemStack, player);
				this.currentItem = itemStack.getItem();
			}
			else if (Mouse.isButtonDown(1)) {
				//canDo = new Date().getTime();
				((ItemMag) itemStack.getItem()).unloadAmmo(itemStack, player);
				this.currentItem = itemStack.getItem();
			}
		}

	}
}
