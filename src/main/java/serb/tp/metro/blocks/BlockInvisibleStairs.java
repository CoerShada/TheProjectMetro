package serb.tp.metro.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.MapColor;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.tiles.TileEntityPlayersBlock;
import serb.tp.metro.creativetabs.LoadTabs;

public class BlockInvisibleStairs extends BlockStairs{


	public BlockInvisibleStairs(String name) {
		super(LoadBuildersBlocks.blockInvisible, 0);
		this.setBlockTextureName(Main.modid+":blocksInvisible/"+name);
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

    
}