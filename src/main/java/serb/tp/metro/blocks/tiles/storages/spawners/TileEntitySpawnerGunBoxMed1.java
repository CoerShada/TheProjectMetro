package serb.tp.metro.blocks.tiles.storages.spawners;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.AxisAlignedBB;

public class TileEntitySpawnerGunBoxMed1 extends TileEntityStorageSpawner{

	public TileEntitySpawnerGunBoxMed1() {
		super();
	}
	
	public TileEntitySpawnerGunBoxMed1(int width, int height) {
		super(width, height);
	}
	
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord-3, yCoord-2, zCoord-3, xCoord+3, yCoord + 2, zCoord+3);

        return bb;
    }
}
