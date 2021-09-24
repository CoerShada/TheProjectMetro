package serb.tp.metro.client.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import serb.tp.metro.client.render.RenderBackpuck;
import serb.tp.metro.client.render.RenderBody;
import serb.tp.metro.client.render.RenderBoots;
import serb.tp.metro.client.render.RenderGun;
import serb.tp.metro.client.render.RenderHelmet;
import serb.tp.metro.client.render.RenderImages;
import serb.tp.metro.client.render.RenderMask;
import serb.tp.metro.client.render.RenderOuterwear;
import serb.tp.metro.client.render.RenderPants;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.items.ItemMask;
import serb.tp.metro.items.armor.ItemHelmet;

public class PlayerRenderHandler {

	private RenderHelmet renderHelmet = new RenderHelmet();
	private RenderBody renderBody = new RenderBody();
	private RenderImages renderImages = new RenderImages(Minecraft.getMinecraft());
	private RenderBoots renderBoots = new RenderBoots();
	private RenderMask renderMask = new RenderMask();
	private RenderOuterwear renderBracers = new RenderOuterwear();
	private RenderPants renderPants = new RenderPants();
	private RenderBackpuck renderBackpuck = new RenderBackpuck();
	private RenderGun renderGun = new RenderGun();
	
	@SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if(event.type == RenderGameOverlayEvent.ElementType.CROSSHAIRS && Minecraft.getMinecraft().thePlayer.inventory.currentItem>=0 && Minecraft.getMinecraft().thePlayer.inventory.currentItem<=2 && !Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
            event.setCanceled(true);
        }
    }

	@SubscribeEvent
	public void onPlayerRenderTick(RenderPlayerEvent.Specials.Post event) {
		if(event.entityPlayer == null) {
			return;
		}		
		EntityPlayer player = (EntityPlayer) event.entityLiving;
		
		if (player.getCurrentArmor(3) != null) renderHelmet.render(player, event.renderer);
		if (player.getCurrentArmor(2) != null) renderBody.render(player, event.renderer);
		if (player.inventory.getStackInSlot(15)!= null) renderMask.render(player, event.renderer);	
		if (player.inventory.getStackInSlot(18) != null) renderBackpuck.render(player, event.renderer);
		if (player.inventory.getStackInSlot(2) != null) renderGun.render(player, event.renderer);
	}
	
	@SubscribeEvent
	public void onPlayerRenderTickHud(RenderGameOverlayEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if(player == null) {
			return;
		}
		
		if (player.inventory.getStackInSlot(15) != null && player.inventory.getStackInSlot(15).getItem() instanceof ItemMask) 
		{
			
			renderImages.renderGameOverlay(0, false, 0, 0);
		}
		
		if (player.inventory.armorItemInSlot(3)!=null && player.inventory.armorItemInSlot(3).getItem() instanceof ItemHelmet && player.inventory.armorItemInSlot(3).hasTagCompound() && player.inventory.armorItemInSlot(3).getTagCompound().getString("visor").equalsIgnoreCase("close"))
		{
			renderImages.renderGameOverlay(0, false, 0, 0);
		}
		
	}

}