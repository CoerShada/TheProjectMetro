package serb.tp.metro.blocks.tiles.tunnels;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityTunnelLineExitRight extends TileEntityTunnelLine{
	
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord - 21, yCoord-3, zCoord - 21, xCoord + 21, yCoord + 9, zCoord + 21);

        return bb;
    }

}
