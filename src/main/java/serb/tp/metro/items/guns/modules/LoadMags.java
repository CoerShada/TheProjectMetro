package serb.tp.metro.items.guns.modules;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.items.guns.ItemBullet;
import serb.tp.metro.items.guns.LoadBullets;
import serb.tp.metro.items.guns.render.RenderFirearmMagGun;

public class LoadMags {
	
	public static final ItemMag 
		PmMag = (ItemMag) new ItemMag("PmMag", 150,8, LoadBullets.bullets9x18, 1300, 700).setCreativeTab(CreativeTabs.tabCombat),
		//AK74Mag = (ItemMag) new ItemMag("AK74Mag", 230, 30, bullets9x39, 1100, 600).setCreativeTab(CreativeTabs.tabCombat),
		Ppsh71Mag = (ItemMag) new ItemMag("Ppsh71Mag", 1150, 71, LoadBullets.bullets9x18, 1700, 1100).setCreativeTab(CreativeTabs.tabCombat), 
		Ppsh35Mag = (ItemMag) new ItemMag("Ppsh35Mag", 330, 35, LoadBullets.bullets9x18, 900, 500).setCreativeTab(CreativeTabs.tabCombat);
		

    

	
	public static void renderItems() {
		//MinecraftForgeClient.registerItemRenderer(pm, new RenderFirearmMagGun(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1line.obj"), (ItemFirearmMagGun) pm));
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_left), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/TonnelTehPR.obj"), 0.5, -0.5, 0.5, 0.5));
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_right), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/TonnelTeh.obj"), 0.5, -0.5, 0.5, 0.5));
	}
}
