package serb.tp.metro.blocks.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityRack extends TileEntityStorage{
	
	public TileEntityRack() {}

	public TileEntityRack(int width, int height) {
		super(width, height);
	}

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord - 2, yCoord-1, zCoord - 2, xCoord + 2, yCoord + 3, zCoord + 2);

        return bb;
    }
}
