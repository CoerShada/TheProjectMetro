package serb.tp.metro.common.clans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class Clan {
	private int id = 0;
	private String name = "";

	public String description  = "";
	private boolean isStaticClan = true;
	private ClanType clanType = ClanType.Engineers;
	private ClanStructure clanStructure = ClanStructure.Small;
	
	private ArrayList<Rank> ranks = new ArrayList<Rank>();
	private int lastRankIndex = 0;
	private ArrayList<Member> members = new ArrayList<Member>();
	//private ArrayList<Land> lands = new ArrayList<Land>();
	private ArrayList<Relation> relations = new ArrayList<Relation>();
	
	private int defaultRankIndex = 0;
	
	public Clan() {
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
	
	public void addRank(String name, Map<Permission, Boolean> permissions) {
		this.lastRankIndex++;
		ranks.add(new Rank(this.lastRankIndex, name, permissions));
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public boolean isClanPlayer(String playerName) {
		for (Member member: members) {
			if (member.getPlayer().equals(playerName)) return true;
		}
		return false;
	}
	
	public boolean setName(String name) {
		if (name.length()>=4) {
			this.name = name;
			return true;
		}
		return false;
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
	
	/*public void addLand(Land land) {
		if(land!=null) {
			this.lands.add(land);
		}
	}
	
	public void removeLand(Land land) {
		if(land!=null) {
			this.lands.remove(land);
		}
	}*/
	
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

	public void addMember(EntityPlayer player) {
		Member newMember = new Member(player.getDisplayName(), ranks.get(defaultRankIndex), new Date().getTime());
		this.members.add(newMember);
	}
	
	
	public void removeMember(EntityPlayer player) {
		for (Member member: members) {
			if (member.getPlayer().equals(player.getDisplayName())) {
				members.remove(member);
				return;
			}
		}
	}

	public void setDefaultRank(Rank rank) {
		this.defaultRankIndex = ranks.indexOf(rank);
	}

	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("id", this.id);
		nbt.setString("name", this.name);
		nbt.setString("description", this.description);
		nbt.setBoolean("isStaticClan", this.isStaticClan);
		nbt.setString("clanType", clanType.toString());
		nbt.setString("clanStructure", clanStructure.toString());
		nbt.setInteger("quantityRanks", ranks.size());
		int counter = 0;
		for (Rank rank: ranks) {
			NBTTagCompound tagRank = new NBTTagCompound();
			tagRank.setInteger("index", rank.getIndex());
			tagRank.setString("name", rank.getName());
			
			Map<Permission, Boolean> perms = rank.getPermissions();
			tagRank.setInteger("permsSize", perms.size());
			
			NBTTagCompound tagPerms = new NBTTagCompound();
			for (Entry<Permission, Boolean> perm: perms.entrySet()) {
				tagPerms.setBoolean(perm.getKey().toString(), perm.getValue());
			}
			tagRank.setTag("perms:", tagPerms);
			
			nbt.setTag("rank:"+counter, tagRank);
			counter++;
		}
		
		counter = 0;
		nbt.setInteger("quantityMembers", members.size());
		for (Member member: members) {
			NBTTagCompound tagMember = new NBTTagCompound();
			tagMember.setString("player", member.getPlayer());
			tagMember.setInteger("rankIndex", member.getRank().getIndex());
			tagMember.setLong("entryTime", member.getEntryTime());
			nbt.setTag("member:"+counter, tagMember);
			counter++;
		}
		
		nbt.setInteger("defaultRank", this.defaultRankIndex);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		this.id = nbt.getInteger("id");
		this.name = nbt.getString("name");
		this.description = nbt.getString("description");
		this.isStaticClan = nbt.getBoolean("isStaticClan");
		this.clanType = ClanType.valueOf(nbt.getString("clanType"));
		this.clanStructure = ClanStructure.valueOf(nbt.getString("clanStructure"));
		int size = nbt.getInteger("permsSize");
		ArrayList<Rank> tempRanks = new ArrayList<Rank>();
		//Выгрузка из nbt рангов данного клана
		for (int i = 0; i<size; i++) {	
			NBTTagCompound tagRank = nbt.getCompoundTag("rank:"+i);
			int index = tagRank.getInteger("index");
			String name = tagRank.getString("name");
			
			//Выгрузка прав этого ранга
			Map<Permission, Boolean> permissions = new HashMap<Permission, Boolean>();
			for(Permission key: Permission.values()) {	
				boolean value = tagRank.getBoolean("perms:" + key.toString());
				permissions.put(key, value);
			}
			tempRanks.add(new Rank(index, name, permissions));
		}
		this.ranks = tempRanks;
		size = nbt.getInteger("quantityMembers");
		ArrayList<Member> tempMembers = new ArrayList<Member>();
		for (int i = 0; i<size; i++) {
			
			NBTTagCompound tagMember = nbt.getCompoundTag("member:"+i);
			String playerName = nbt.getString("player");
			Rank rank = this.getRankFromIndex(nbt.getInteger("rankIndex"));
			Long entryTime = nbt.getLong("entryTime");
			tempMembers.add(new Member(playerName, rank, entryTime));
		}
		this.members = tempMembers;
		this.defaultRankIndex = nbt.getInteger("defaultRank");
		
	}
}
