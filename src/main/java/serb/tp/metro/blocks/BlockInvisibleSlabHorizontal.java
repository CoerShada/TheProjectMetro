package serb.tp.metro.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityPlayersBlock;
import serb.tp.metro.creativetabs.LoadTabs;

public class BlockInvisibleSlabHorizontal extends BlockSlab{


	public BlockInvisibleSlabHorizontal(String name) {
		super(false, Material.clay);
		this.setBlockTextureName(Main.modid+":blocksInvisible/blockInvisible");
		this.setCreativeTab(LoadTabs.blocksInvisible);
		this.setBlockName(name);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
		this.setLightOpacity(0);
		GameRegistry.registerBlock(this, name);
	}
	
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
		if (Minecraft.getMinecraft().thePlayer.capabilities.isCreativeMode) {
			return 1;
		}
        return -1;
    }

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public float getAmbientOcclusionLightValue() {
		return 1.0F;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		
		if(player.inventory.getCurrentItem()!=null && player.inventory.getCurrentItem().getItem().getClass().equals(ItemBlock.class) && Block.getBlockFromItem(player.inventory.getCurrentItem().getItem()).getClass().equals(PlayersBlock.class)) {
			TileEntityPlayersBlock te = new TileEntityPlayersBlock(world.getBlock(x, y, z), world.getBlockMetadata(x, y, z));
			world.removeTileEntity(x, y, z);
			world.setBlock(x, y, z, Block.getBlockFromItem(player.inventory.getCurrentItem().getItem()));
			world.setTileEntity(x, y, z, te);
			if (!player.capabilities.isCreativeMode)
				player.inventory.decrStackSize(player.inventory.currentItem, 1);
		}
		return true;
		
	}


	@Override
	public String func_150002_b(int p_150002_1_) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		
		if (!world.isRemote && Main.debug) {
			File file = new File("E://adtime", "fileConstructor.txt");
			if (!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			
	        try(FileWriter writer = new FileWriter("E://adtime//fileConstructor.txt", true))
	        {
				String stringX = String.valueOf(0+x);
				if (0+x>0)
					stringX = "+" + stringX;
				else if (x==0) 
					stringX="";
				
				String stringY = String.valueOf(y-4);
				if (0+y>4)
					stringY = "+" + stringY;
				else if (y==4) 
					stringY="";
				
				String stringZ = String.valueOf(0+z);
				if (0+z>0)
					stringZ = "+" + stringZ;
				else if (z==0) 
					stringZ="";
				
	            String text = "setFrameBlock(world, x" + stringX + ", y"+stringY+", z" + stringZ + ", LoadBuildersBlocks."+this.getUnlocalizedName().substring(5) +", " + world.getBlockMetadata(x, y, z) +");\n";
	            writer.write(text);

	            writer.flush();
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 
		}
		
	}
	
	@Override
    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return this.field_150004_a ? meta : (side != 0 && (side == 1 || (double)hitY <= 0.5D) ? meta : meta | 8);
        
    }
    

}
