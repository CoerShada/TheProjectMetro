package serb.tp.metro.blocks.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

public class TileEntitySpawnerGunSafeVert1 extends TileEntityStorageSpawner{

	public TileEntitySpawnerGunSafeVert1() {
		super();
	}
	
	public TileEntitySpawnerGunSafeVert1(int width, int height) {
		super(width, height);
	}
	
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord, yCoord-2, zCoord, xCoord, yCoord + 4, zCoord);

        return bb;
    }
}
