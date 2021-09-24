package serb.tp.metro.client.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import serb.tp.metro.KeybindingRegistry;
import serb.tp.metro.client.Type;
import serb.tp.metro.items.ItemBackpack;

public class RenderItemOnGround {

	static RenderItem itemRenderer = new RenderItem();

	public static void drawItems(RenderGameOverlayEvent e) {
		Minecraft mc = Minecraft.getMinecraft();
		if(e.type == ElementType.ALL) {
			if (Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				Vec3 hitVec = mc.objectMouseOver.hitVec;
				EntityClientPlayerMP player = mc.thePlayer;
				double playerX = player.posX;
				double playerY = player.posY;
				double playerZ = player.posZ;
				WorldClient world = mc.theWorld;
				double segLen = 0.25;
				double segLend2 = 0.125;
				double dx = hitVec.xCoord - playerX;
				double dy = hitVec.yCoord - playerY;
				double dz = hitVec.zCoord - playerZ;
				double lineLen = Math.sqrt(Math.pow(dx, 2.0) + Math.pow(dy, 2.0) + Math.pow(dz, 2.0));
				double segNumDouble = lineLen / segLen;
				int segNum = (int)segNumDouble;
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

					String itemName = closeItem.getEntityItem().getDisplayName();
					String keyName = Keyboard.getKeyName(KeybindingRegistry.KEY_PICKUP.getKeyCode());
					if(closeItem.getEntityItem().getItem() instanceof ItemBackpack){
						if(player.inventory.getStackInSlot(18)==null) {
							String str = Type.getTranslate("event.pickup") + " " +itemName + " [" + keyName + "]";
							Minecraft.getMinecraft().fontRenderer.drawString(str, (e.resolution.getScaledWidth()) / 2 - Minecraft.getMinecraft().fontRenderer.getStringWidth(keyName) - (int)(str.length()*1.5), e.resolution.getScaledHeight() / 2 + 22, 0xFFFFFF);
						}
						else 
						{
							Minecraft.getMinecraft().fontRenderer.drawString(Type.getTranslate("event.pickup.nospaceforabackpack"), e.resolution.getScaledWidth() / 2 - 100, e.resolution.getScaledHeight() / 2 + 22, 0xFFFFFF);
						}
					}
					else 
					{
						for (int i = 3; i<16;i++) 
						{
	
							if(i==15) {
								Minecraft.getMinecraft().fontRenderer.drawString(Type.getTranslate("event.pickup.nospace"), e.resolution.getScaledWidth() / 2 - 100, e.resolution.getScaledHeight() / 2 + 22, 0xFFFFFF);
							}
							else if (player.inventory.getStackInSlot(i)==null || (player.inventory.getStackInSlot(i).getItem().equals(closeItem.getEntityItem().getItem()) && player.inventory.getStackInSlot(i).getItem().getItemStackLimit()>=player.inventory.getStackInSlot(i).stackSize + closeItem.getEntityItem().stackSize)) {
								String str = Type.getTranslate("event.pickup") + " " +itemName + " [" + keyName + "]";
								Minecraft.getMinecraft().fontRenderer.drawString(str, (e.resolution.getScaledWidth()) / 2 - Minecraft.getMinecraft().fontRenderer.getStringWidth(keyName) - (int)(str.length()*1.5), e.resolution.getScaledHeight() / 2 + 22, 0xFFFFFF);
								return;
							}
						}
					}
									
				}
			}
		}
	}
	
	@SubscribeEvent(receiveCanceled = true,	priority = EventPriority.HIGHEST)
	public void onInGameUI(RenderGameOverlayEvent e) {
		if(e.type == ElementType.ALL) {	  
			RenderHelper.disableStandardItemLighting();
			drawItems(e);
			RenderHelper.disableStandardItemLighting();
		}
	}

}
