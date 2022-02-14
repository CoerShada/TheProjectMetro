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
	
	private ArrayList<Rank> ranks = new ArrayList<Rank>();
	private int lastRankIndex = 0;
	
	private ArrayList<Relation> relations = new ArrayList<Relation>();
	private ArrayList<UUID> blackList = new ArrayList<UUID>();
	private ArrayList<UUID> applications = new ArrayList<UUID>();
	
	private int defaultRankIndex = 0;
	
	public void setRanksOnClanCreated() {
		Map<Permission, Boolean> permissionsMap = new HashMap<Permission, Boolean>();
		Permission[] permissions = Permission.values();
		for (Permission permission: permissions) {
			permissionsMap.put(permission, true);
		}
		addRank("Лидер", new HashMap<Permission, Boolean>(permissionsMap));
		
		permissionsMap = new HashMap<Permission, Boolean>();
		for (Permission permission: permissions) {
			permissionsMap.put(permission, false);
		}
		addRank("Солдат", new HashMap<Permission, Boolean>(permissionsMap));
	}
	
	public boolean isApplicationSubmitted(EntityPlayer player) {
		return applications.contains(player.getUniqueID());
	}
	
	public Relation getRelation(Clan clan) {
		if (clan==this) return null;
		for (Relation relation: relations) {
			if (relation.isAThisClan(clan)) {
				return relation;
			}
		}
		return null;
	}
	
	public void addRank(String name, Map<Permission, Boolean> permissions) {
		ranks.add(new Rank(this.lastRankIndex, name, permissions));
		this.lastRankIndex++;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	
	public String getLeaderName() {
			
		return ranks.get(0).getMembers().get(0).getPlayer();
	}
	
	public boolean isClanPlayer(String playerName) {
		for (Rank rank: ranks) {
			if (rank.isARankPlayer(playerName)) return true;
		}
		return false;
	}
	
	public boolean applyToClan(EntityPlayer player) {
		if (blackList.contains(player.getUniqueID()) || applications.contains(player.getUniqueID())) return false;
		applications.add(player.getUniqueID());
		return true;
	}
	
	public boolean removeApplyToClan(EntityPlayer player) {
		return applications.remove(player.getUniqueID());
	}
	
	/*
	 * Thank's tox1cozZ and timaxa007
	 */
	public boolean acceptMember(UUID idPlayer) throws IOException {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		File file = new File(DimensionManager.getCurrentSaveRootDirectory(), "playerdata/"+ idPlayer + ".dat");
		if (file != null && file.exists()) {
			FileInputStream fileinputstream = new FileInputStream(file);
			NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(fileinputstream);
			
			
		}
		return true;
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
	
	public void addRank(Rank rank) {
		this.ranks.add(rank);
	}
	
	public Rank getRankFromIndex(int index) {
		for (Rank rank: ranks) {
			if(rank.getIndex()==index) {
				return rank;
			}
		}
		return null;
	}
	
	public void removeRank(Rank rank) {
		this.ranks.remove(rank);
	}
	
	public void swapRanks(int index1, int index2) {
		if (index1==0 || index2==0) return;
		Rank temprank = this.ranks.get(index1);
		this.ranks.set(index1, ranks.get(index2));
		this.ranks.set(index2, temprank);
	}

	public void addRelation(Relation relation) {
		this.relations.add(relation);
	}
	
	public void removeRelation(Relation relation) {
		this.relations.remove(relation);
	}

	public void addMember(EntityPlayer invitedPlayer) {
		Member newMember = new Member(invitedPlayer.getDisplayName(), new Date().getTime());
		this.ranks.get(this.defaultRankIndex).addMember(newMember);	
	}
	
	
	public void removeMember(EntityPlayer player) {
		for (Rank rank: ranks) {
			if(rank.removeMember(player)) break;
		}
	}

	public void setDefaultRank(Rank rank) {
		this.defaultRankIndex = ranks.indexOf(rank);
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
		int size = nbt.getInteger("quantityRanks");
		
		ArrayList<Rank> tempRanks = new ArrayList<Rank>();
		//Выгрузка из nbt рангов данного клана
		for (int i = 0; i<size; i++) {	
			Rank rank = new Rank();
			rank.readFromNBT(nbt.getCompoundTag("rank:"+i));
			tempRanks.add(rank);
		}
		this.ranks = tempRanks;
		
		this.defaultRankIndex = nbt.getInteger("defaultRank");
		
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
		nbt.setInteger("quantityRanks", ranks.size());
		int counter = 0;
		for (Rank rank: ranks) {
			NBTTagCompound tagRank = rank.getNBT();
			nbt.setTag("rank:"+counter, tagRank);
			counter++;
		}
		

		
		nbt.setInteger("defaultRank", this.defaultRankIndex);
		return nbt;
	}
}
