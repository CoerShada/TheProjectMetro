package serb.tp.metro.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRediction extends TileEntity{

	public int masterCordX;
	public int masterCordY;
	public int masterCordZ;
	
	public TileEntityRediction() {
		
	}
	
	public TileEntityRediction(int masterCordX, int masterCordY, int masterCordZ) {
		this.masterCordX = masterCordX;
		this.masterCordY = masterCordY;
		this.masterCordZ = masterCordZ;
	}


	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("masterCordX", masterCordX);
		nbt.setInteger("masterCordY", masterCordY);
		nbt.setInteger("masterCordZ", masterCordZ);

	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		masterCordX = nbt.getInteger("masterCordX");
		masterCordY = nbt.getInteger("masterCordY");
		masterCordZ = nbt.getInteger("masterCordZ");
	}
	
}
