package serb.tp.metro.common.clans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import akka.event.Logging.Debug;

import java.util.UUID;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import serb.tp.metro.DebugMessage;

public class Clan implements INBTSyncronized{
	private int id = 0;
	private String name = "";
	public String description  = "";
	private boolean isStaticClan = true;
	private ClanType clanType = ClanType.ENGINEERS;
	private ClanStructure clanStructure = ClanStructure.SMALL;
	private int lastRankSubordinationIndex = -1;
	private ArrayList<UUID> blackList = new ArrayList<UUID>();
	private ArrayList<UUID> applications = new ArrayList<UUID>();
	
	private int defaultRankIndex = 0;
	

	public Clan(NBTTagCompound tag) {
		this.readFromNBT(tag);
	}
	
	public Clan() {}

	public boolean isApplicationSubmitted(EntityPlayer player) {
		return applications.contains(player.getUniqueID());
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean applyToClan(EntityPlayer player) {
		if (blackList.contains(player.getUniqueID()) || applications.contains(player.getUniqueID())) return false;
		applications.add(player.getUniqueID());
		return true;
	}
	
	public boolean removeApplyToClan(EntityPlayer player) {
		return applications.remove(player.getUniqueID());
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setIsStaticClan(boolean isStaticClan) {
		this.isStaticClan = isStaticClan;
	}
	
	public void setType(ClanType type) {
		this.clanType = type;
	}
	
	public void setStructure(ClanStructure structure) {
		this.clanStructure = structure;
	}
	
	public ClanType getClanType() {
		return clanType;
	}
	
	public ClanStructure getClanStructure() {
		return clanStructure;
	}
	

	public void setDefaultRankId(int rankId) {
		this.defaultRankIndex = rankId;
	}
	
	public int getDefaultRankId() {
		return this.defaultRankIndex;
	}

	public void writeToNBT(NBTTagCompound nbt) {
		
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		this.id = nbt.getInteger("id");
		this.name = nbt.getString("name");
		this.description = nbt.getString("description");
		this.isStaticClan = nbt.getBoolean("isStaticClan");
		
		this.clanType = ClanType.valueOf(nbt.getString("clanType"));
		this.clanStructure = ClanStructure.valueOf(nbt.getString("clanStructure"));
		this.lastRankSubordinationIndex = nbt.getInteger("lastRankSubordinationIndex");
		this.defaultRankIndex = nbt.getInteger("defaultRankIndex");
		
	}

	@Override
	public NBTTagCompound getNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("id", this.id);
		nbt.setString("name", this.name);
		nbt.setString("description", this.description);
		nbt.setBoolean("isStaticClan", this.isStaticClan);
		nbt.setString("clanType", clanType.toString());
		nbt.setString("clanStructure", clanStructure.toString());
		nbt.setInteger("defaultRankIndex", this.defaultRankIndex);
		nbt.setInteger("lastRankSubordinationIndex", this.lastRankSubordinationIndex);
		nbt.setString("type", "clan");
		return nbt;
	}

	
	void increaseLastRankSubordinationIndex() {
		this.lastRankSubordinationIndex++;
	}
	
	void decreaseLastRankSubordinationIndex() {
		this.lastRankSubordinationIndex--;
	}
	
	public int getLastRankSubordinationIndex() {
		return this.lastRankSubordinationIndex;
	}

	private void setLastRankSubordinationIndex(int lastRankSubordinationIndex) {
		this.lastRankSubordinationIndex = lastRankSubordinationIndex;
	}
	
	
}
