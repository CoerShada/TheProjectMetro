package serb.tp.metro.client.render.entity.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import serb.tp.metro.items.weapons.ItemWeapon;

public class CustomRenderPlayer {
	
	
    @SubscribeEvent
    public void preRenderPlayer(RenderLivingEvent.Pre event)
    {
        if (!(event.entity instanceof EntityPlayer)) return;
        
        EntityPlayer player = (EntityPlayer) event.entity;
        if (player.inventory.getCurrentItem()==null || !(player.inventory.getCurrentItem().getItem() instanceof ItemWeapon)) return;
        RenderPlayer renderPlayer = (RenderPlayer) event.renderer;
        if (renderPlayer.modelArmor != null)
            renderPlayer.modelArmor.aimedBow = true;
        if (renderPlayer.modelArmorChestplate != null)
            renderPlayer.modelArmorChestplate.aimedBow = true;
        if (renderPlayer.modelBipedMain != null)
            renderPlayer.modelBipedMain.aimedBow = true;
        //event.setCanceled(true);


           
    }
}
