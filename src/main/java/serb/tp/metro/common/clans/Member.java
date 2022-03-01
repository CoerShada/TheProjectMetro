package serb.tp.metro.common.clans;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.DebugMessage;

public class Member implements INBTSyncronized{
	private int clanId;
	private int rankId;
	private UUID playerId;
	private long entryTime;
	
	public Member() {}
	
	public UUID getPlayerId() {
		return this.playerId;
	}
	
	
	public long getEntryTime() {
		return this.entryTime;
	}
	
	public int getClanId() {
		return this.clanId;
	}
	
	public int getRankId() {
		return this.rankId;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt = getNBT();
		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		this.playerId = UUID.fromString(nbt.getString("playerId"));
		this.entryTime = nbt.getLong("entryTime");
		this.rankId = nbt.getInteger("rankId");
		this.clanId = nbt.getInteger("clanId");
	}

	@Override
	public NBTTagCompound getNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("playerId", this.getPlayerId().toString());
		tag.setInteger("rankId", this.rankId);
		tag.setInteger("clanId", this.clanId);
		tag.setLong("entryTime", this.getEntryTime());
		return tag;
	}

	public final void setClanId(int clanId) {
		this.clanId = clanId;
	}

	public final void setRankId(int rankId) {
		this.rankId = rankId;
	}

	public final void setEntryTime(long entryTime) {
		this.entryTime = entryTime;
	}

	public final void setPlayerId(UUID playerId) {
		this.playerId = playerId;
	}
}
