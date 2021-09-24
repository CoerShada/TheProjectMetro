package serb.tp.metro.common.handlers;



import java.util.ArrayList;
import java.util.Date;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.InputEvent.MouseInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.EntityEvent.CanUpdate;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import serb.tp.metro.Main;
import serb.tp.metro.KeybindingRegistry;
import serb.tp.metro.client.render.RenderItemOnGround;
import serb.tp.metro.handlers.GuiHandler;
import serb.tp.metro.items.ItemMask;
import serb.tp.metro.items.armor.ItemHelmet;
import serb.tp.metro.items.guns.ItemGun;
import serb.tp.metro.items.guns.modules.ItemMag;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.PickupMessage;
import serb.tp.metro.network.server.RemovingFilterMessage;


public class KeyBindingHandler {

	public Minecraft mc;
	RenderItem itemRenderer = new RenderItem();



	public KeyBindingHandler(Minecraft mc) 
	{
	    this.mc = mc;

	}    
	
	
	@SubscribeEvent(priority = EventPriority.HIGHEST,receiveCanceled = true)
	public void onKeyInput(KeyInputEvent e) 
	{
	    EntityClientPlayerMP player = mc.thePlayer;
		if(KeybindingRegistry.KEY_PICKUP.isPressed()) {
			if (mc.theWorld.isRemote && mc.objectMouseOver != null && mc.objectMouseOver.hitVec != null) {
				Vec3 hitVec = mc.objectMouseOver.hitVec;
				
				double playerX = player.posX;
				double playerY = player.posY;
				double segLen = 0.25;
				double segLend2 = 0.125;
				double playerZ = player.posZ;
				double dx = hitVec.xCoord - playerX;
				double dy = hitVec.yCoord - playerY;
				double dz = hitVec.zCoord - playerZ;
				double lineLen = Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0) + Math.pow(dz, 2.0));
				double segNumDouble = lineLen / segLen;
				int segNum = (int)segNumDouble;
				World world = player.getEntityWorld();
				ArrayList<EntityItem> items = null;
				int index = 0;

				while (++index <= segNum) {
					double cenX = playerX + dx / segNumDouble * (double)index;
					double cenY = playerY + dy / segNumDouble * (double)index;
					double cenZ = playerZ + dz / segNumDouble * (double)index;
					AxisAlignedBB curAABB = AxisAlignedBB.getBoundingBox((double)(cenX - segLend2), (double)(cenY - segLend2), (double)(cenZ - segLend2), (double)(cenX + segLend2), (double)(cenY + segLend2), (double)(cenZ + segLend2));
					items = (ArrayList)world.getEntitiesWithinAABB(EntityItem.class, curAABB);
					if (items == null || items.isEmpty()) continue;
					index = segNum + 1;
				}

				if (items != null && !items.isEmpty()) {
					EntityItem closeItem = (EntityItem)items.get(0);
					double closeDist = 100.0;
					for (EntityItem item : items) {
						double itemDist = Math.pow(item.posX - playerX, 2.0) + Math.pow(item.posY - playerY, 2.0) + Math.pow(item.posZ - playerZ, 2.0);
						if (itemDist >= closeDist) continue;
						closeDist = itemDist;
						closeItem = item;
					}
					int nullSlot = -1;
					
					for (int i = 3; i<15; i++) 
					{
						if (player.inventory.getStackInSlot(i)==null) 
							nullSlot = i;
						else if ((player.inventory.getStackInSlot(i).getItem().equals(closeItem.getEntityItem().getItem()) && player.inventory.getStackInSlot(i).getItem().getItemStackLimit()>=player.inventory.getStackInSlot(i).stackSize + closeItem.getEntityItem().stackSize)) {
							PacketDispatcher.sendToServer(new PickupMessage(closeItem.getEntityId(), i, true));
							return;
						}
						
					}
					if (nullSlot!=-1)
						PacketDispatcher.sendToServer(new PickupMessage(closeItem.getEntityId(), nullSlot, false));
				}
			}
		}
		
		if(KeybindingRegistry.KEY_FACTIONS.isPressed()) 
		{
		    //GuiHandler.openGui(player, Main.GUI_FACTIONS);
		}
		
		if(KeybindingRegistry.KEY_FMTSM.isPressed()) 
		{
			if(mc.theWorld.isRemote) {
		    	//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    		ItemStack itemStack = player.inventory.getCurrentItem();
	    		if(itemStack!=null && itemStack.getItem() instanceof ItemGun && itemStack.hasTagCompound()) 
	    		{
	    		    ItemGun gun = (ItemGun) itemStack.getItem();
	    		    gun.fireModChange(itemStack);
	    		}
			}
	    		
		}
		if (KeybindingRegistry.KEY_SAFETY.isPressed()) 
		{
			if(mc.theWorld.isRemote) {
		    	//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    		ItemStack itemStack = player.inventory.getCurrentItem();
	    		if(itemStack!=null && itemStack.getItem() instanceof ItemGun && itemStack.hasTagCompound()) 
	    		{
	    		    ItemGun gun = (ItemGun) itemStack.getItem();
	    		    gun.safetyModChange();
	    		}
			}
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LMENU) && KeybindingRegistry.KEY_FILTER.isPressed()) 
		{
			if(mc.theWorld.isRemote) {
	    		//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    		ItemStack itemStack = player.inventory.getStackInSlot(15);;
	    		if (itemStack!=null && itemStack.hasTagCompound()) 
	    		{
	    		    PacketDispatcher.sendToServer(new RemovingFilterMessage());
	    		}
			}
		}
		
		if (KeybindingRegistry.KEY_FILTER.isPressed()) 
		{
			if(mc.theWorld.isRemote) {
	    		//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    		ItemStack itemStack = player.inventory.getStackInSlot(15);
	    		if (itemStack!=null && itemStack.hasTagCompound()) {
	    		    ((ItemMask) itemStack.getItem()).ChengeFilter(player);;
	    		}
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && KeybindingRegistry.KEY_RELOAD.isPressed()) 
		{
			if(mc.theWorld.isRemote) {
	    		//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    		World world = Minecraft.getMinecraft().theWorld;
	    		ItemStack itemStack = Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem();
	    		if(itemStack!=null && itemStack.hasTagCompound() && itemStack.getItem() instanceof ItemMag) 
	    		{
	    			itemStack = ((ItemMag) itemStack.getItem()).chengeAmmo(itemStack, world, player);
	    		}
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LMENU) && KeybindingRegistry.KEY_RELOAD.isPressed()) 
		{
			if(mc.theWorld.isRemote) {
	    		//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    		World world = Minecraft.getMinecraft().theWorld;
	    		ItemStack itemStack = Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem();
	    		if (itemStack!=null && itemStack.hasTagCompound() && itemStack.getItem() instanceof ItemMag) 
	    		{
	    		    ((ItemMag) itemStack.getItem()).unloadAmmo(itemStack, world, player);
	    		}
			}
		}
		
		if (KeybindingRegistry.KEY_RELOAD.isPressed()) 
		{
			if(mc.theWorld.isRemote) {
	    		//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    		World world = Minecraft.getMinecraft().theWorld;
	    		ItemStack itemStack = Minecraft.getMinecraft().thePlayer.inventory.getCurrentItem();
	    		if (itemStack!=null && itemStack.hasTagCompound() && itemStack.getItem() instanceof ItemGun && itemStack.stackTagCompound.getInteger("mag")==-1) 
	    		    ((ItemGun) itemStack.getItem()).load(itemStack, player);
	    		else if (itemStack!=null && itemStack.hasTagCompound() && itemStack.getItem() instanceof ItemMag)
	    		    ((ItemMag) itemStack.getItem()).loadAmmo(itemStack, world, player);
	    		else if(itemStack!=null && itemStack.hasTagCompound() && itemStack.getItem() instanceof ItemGun && itemStack.stackTagCompound.getInteger("mag")!=-1) 
	    		    ((ItemGun) itemStack.getItem()).unload(itemStack, player);
			}
		}
		
		if (KeybindingRegistry.KEY_VISOR.isPressed()) 
		{
			if(mc.theWorld.isRemote) {
	    		//EntityPlayer player = Minecraft.getMinecraft().thePlayer;
	    		World world = Minecraft.getMinecraft().theWorld;
	    		ItemStack itemStack = Minecraft.getMinecraft().thePlayer.inventory.armorItemInSlot(3);
	    		if (itemStack!=null && itemStack.hasTagCompound()) 
	    		{
	    		    itemStack = ((ItemHelmet) itemStack.getItem()).openVisor(itemStack, player);
	    		}
			}
		}
		
		if (KeybindingRegistry.KEY_BACKPACK.isPressed()) 
		{
			//EntityPlayer playerServer =  (EntityPlayer) MinecraftServer.getServer().getEntityWorld().getEntityByID(Minecraft.getMinecraft().thePlayer.getEntityId());
			if(mc.theWorld.isRemote) {
	    		World world = Minecraft.getMinecraft().theWorld;
	    		EntityPlayer playerServer = (EntityPlayer) world.getEntityByID(Minecraft.getMinecraft().thePlayer.getEntityId());
	    		if (player.inventory.getStackInSlot(18)!=null) 
	    		{
	    		    GuiHandler.openGui(playerServer, Main.GUI_BACPACK);
	    		}
			}
		}
			
	}


	@SubscribeEvent(receiveCanceled = true,	priority = EventPriority.HIGHEST)
	public void onInGameUI(RenderGameOverlayEvent e) {
		if(e.type == ElementType.ALL) {	  
			RenderHelper.disableStandardItemLighting();
			RenderItemOnGround.drawItems(e);
			RenderHelper.disableStandardItemLighting();
		}
	}


}
