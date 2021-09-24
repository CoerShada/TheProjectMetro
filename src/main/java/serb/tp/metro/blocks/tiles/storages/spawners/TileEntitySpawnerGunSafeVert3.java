package serb.tp.metro.blocks.tiles.storages.spawners;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

public class TileEntitySpawnerGunSafeVert3 extends TileEntityStorageSpawner{

	public TileEntitySpawnerGunSafeVert3() {
		super();
	}
	
	public TileEntitySpawnerGunSafeVert3(int width, int height) {
		super(width, height);
	}
	
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord, yCoord-2, zCoord, xCoord, yCoord + 4, zCoord);

        return bb;
    }
}
