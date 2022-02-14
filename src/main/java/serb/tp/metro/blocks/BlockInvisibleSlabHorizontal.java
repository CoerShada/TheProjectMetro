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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityPlayersBlock;
import serb.tp.metro.creativetabs.LoadTabs;

public class BlockInvisibleSlabHorizontal extends BlockInvisible{


	public BlockInvisibleSlabHorizontal(String name) {
		super(name);

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
	public void setBlockBoundsForItemRender() {
	    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
	}
    
	@Override
    public void setBlockBoundsBasedOnState(IBlockAccess block, int x, int y, int z)
    {
    	int meta = block.getBlockMetadata(x, y, z);
        if 	(meta%2==0)	setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        else 			setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);  
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



    

}
