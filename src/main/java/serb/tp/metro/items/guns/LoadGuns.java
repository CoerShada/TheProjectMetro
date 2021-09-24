package serb.tp.metro.items.guns;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.LoadTunnels;
import serb.tp.metro.blocks.render.RenderBlockModelled;
import serb.tp.metro.items.guns.modules.ItemMag;
import serb.tp.metro.items.guns.modules.LoadMags;
import serb.tp.metro.items.guns.render.RenderFirearmMagGun;
import serb.tp.metro.items.guns.render.TestedRenderFirearmMagGun;

public class LoadGuns {
	
	public static final ItemGun
    pm = (ItemFirearmMagGun) new ItemFirearmMagGun("Pm", 700, 100, 1.0, 1.0, 10, 10, 30, 0.7, 15, false, new ItemMag[]{LoadMags.PmMag}),
    //AK74 = new ItemFirearmMagGun("AK74", 3400, 600, 1.0, 1.0, 10, 10, 0.3, 90, 15, true, new ItemMag[]{LoadMags.AK74Mag}),
    Ppsh = new ItemFirearmMagGun("Ppsh", 3600, 1200, 1.25, 0, 7, 5, 7, 5, 5, true, new ItemMag[]{LoadMags.Ppsh35Mag, LoadMags.Ppsh71Mag});

    

	
	public static void renderItems() {
		//MinecraftForgeClient.registerItemRenderer(pm, new TestedRenderFirearmMagGun(new ResourceLocation(Main.modid, "models/weapons/pistols/pm.obj"), (ItemFirearmMagGun) pm));
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_left), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/TonnelTehPR.obj"), 0.5, -0.5, 0.5, 0.5));
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_right), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/TonnelTeh.obj"), 0.5, -0.5, 0.5, 0.5));
	}
}
