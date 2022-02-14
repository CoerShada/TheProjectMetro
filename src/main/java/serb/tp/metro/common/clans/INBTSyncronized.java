package serb.tp.metro.common.clans;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTSyncronized {

	public void writeToNBT(NBTTagCompound nbt);
	
	public void readFromNBT(NBTTagCompound nbt);
	
	public NBTTagCompound getNBT();
}
