package serb.tp.metro.items.guns;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.items.guns.modules.ItemMag;
import serb.tp.metro.items.guns.modules.LoadMags;
import serb.tp.metro.items.guns.render.RenderFirearmMagGun;

public class LoadBullets{
	
	public static final ItemBullet
		bullet_9x18mm_ShellFactory = (ItemBullet) new ItemBullet("bullet_9x18mm_ShellFactory", 10, 22, 90, 0.1, 7, 0.15, 1),
		bullet_9x18mm_ExpansiveFactory = (ItemBullet) new ItemBullet("bullet_9x18mm_ExpansiveFactory", 10, 40, 30, 1, 6, 1, 1),
		bullet_9x18mm_ArmorpiercingFactory = (ItemBullet) new ItemBullet("bullet_9x18mm_ArmorpiercingFactory", 10, 30, 110, 1, 12, 0.01, 1);

    public static final ItemBullet[]
    		bullets9x18 = new ItemBullet[]{bullet_9x18mm_ShellFactory, bullet_9x18mm_ExpansiveFactory, bullet_9x18mm_ArmorpiercingFactory};

	
	public static void renderItems() {
		//MinecraftForgeClient.registerItemRenderer(bullet_9x18mm_ShellFactory, new RenderFirearmMagGun(new ResourceLocation(Main.modid, "models/weapons/pistols/pm.obj"), (ItemFirearmMagGun) pm));
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_left), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/TonnelTehPR.obj"), 0.5, -0.5, 0.5, 0.5));
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_right), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/TonnelTeh.obj"), 0.5, -0.5, 0.5, 0.5));
	}
}
