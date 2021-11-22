package serb.tp.metro.common.clans;

import net.minecraft.entity.player.EntityPlayer;

public class Member {
	private String playerName;
	private Rank rank;
	private long entryTime;
	
	public Member(String playerName, Rank rank, long entryTime) {
		this.playerName = playerName;
		this.rank = rank;
		this.entryTime = entryTime;
	}
	
	public String getPlayer() {
		return playerName;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public long getEntryTime() {
		return entryTime;
	}
}
