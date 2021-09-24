package serb.tp.metro.blocks;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelLine;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelLineExitLeft;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelLineExitRight;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelTurnLeft;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelTurnRight;
import serb.tp.metro.client.render.blocks.RenderBlockModelled;
import serb.tp.metro.client.render.tiles.RenderTileTunnelLine1;
import serb.tp.metro.client.render.tiles.RenderTileTunnelTurnLeft;
import serb.tp.metro.client.render.tiles.RenderTileTunnelTurnRight;

public class LoadTunnels {
	
	public static final Block 
	tunnel1line = new BlockTunnelLine(Material.rock, "tunnel1line", TileEntityTunnelLine.class),
	tunnel1line_exit_right = new BlockTunnelLineExitRight(Material.rock, "tunnel1line_exit_right", TileEntityTunnelLineExitRight.class), 
	//tunnel1line_exit_left = new BlockTunnelLineExitLeft(Material.rock, "tunnel1line_exit_left", TileEntityTunnelLineExitLeft.class), 
	tunnel1turn_right = new BlockTunnelTurnRight(Material.rock, "tunnel1turn_right", TileEntityTunnelTurnRight.class),
	tunnel1turn_left = new BlockTunnelTurnLeft(Material.rock, "tunnel1turn_left", TileEntityTunnelTurnLeft.class);

	@SideOnly(Side.CLIENT)
	public static void renderBlocks() {
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1line.obj"), 0.9, -0.5, 0.5, 0.5));
		//MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_left), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/TonnelTehPR.obj"), 0.5, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1line_exit_right), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1Teh.obj"), 0.9, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1turn_right), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1TurnRight.obj"), 0.5, -0.5, 0.5, 0.5));
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(LoadTunnels.tunnel1turn_left), new RenderBlockModelled(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1TurnLeft.obj"), 0.5, -0.5, 0.5, 0.5));

		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTunnelLine.class, new RenderTileTunnelLine1(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1line.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTunnelLineExitRight.class, new RenderTileTunnelLine1(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1Teh.obj"), -0.001F, 0.016F, 0.005F));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTunnelTurnRight.class, new RenderTileTunnelTurnRight(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1TurnRight.obj")));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTunnelTurnLeft.class, new RenderTileTunnelTurnLeft(new ResourceLocation(Main.modid, "models/static/buildings/tunnels/tunnel1TurnLeft.obj")));

	}
}
