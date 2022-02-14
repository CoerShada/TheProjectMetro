package serb.tp.metro.common.clans;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.DebugMessage;

public class Member implements INBTSyncronized{
	private String playerName;
	private long entryTime;
	
	public Member(String playerName, long entryTime) {
		this.playerName = playerName;
		this.entryTime = entryTime;
	}
	
	public Member() {}
	
	public String getPlayer() {
		return playerName;
	}
	
	
	public long getEntryTime() {
		return entryTime;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.playerName = nbt.getString("player");
		DebugMessage.printMessage("Выгружен игрок" + playerName);
		this.entryTime = nbt.getLong("entryTime");
		
	}

	@Override
	public NBTTagCompound getNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		DebugMessage.printMessage("Загружен игрок " + this.getPlayer());
		tag.setString("player", this.getPlayer());
		tag.setLong("entryTime", this.getEntryTime());
		return tag;
	}
}
