package serb.tp.metro.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.blocks.tiles.tunnels.TileEntityTunnelLine;
import serb.tp.metro.creativetabs.LoadTabs;

public class BlockTunnel extends BlockModeled{

	protected BlockTunnel(Material material, String name, Class<? extends TileEntity> te) {
		super(material, name, te);
		this.setCreativeTab(LoadTabs.tunnelsBlocks);
		this.setBlockUnbreakable();
		this.setResistance(6000000.0F);
	}
	



	@Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
		return false;
    }
	

}
