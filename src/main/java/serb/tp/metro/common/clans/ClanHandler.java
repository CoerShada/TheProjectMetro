package serb.tp.metro.common.clans;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.common.ieep.ExtendedPlayer;
import serb.tp.metro.network.PacketDispatcher;

public class ClanHandler extends WorldSavedData{

	public static final String NAME = "Clans";
	private int lastLandsId;
	private int lastClansId;
	private int lastRanksId;
	private int lastRelationsId;
	private HashMap <Integer, ClanLand> lands = new HashMap <Integer, ClanLand>();
	private HashMap <Integer, Clan> clans = new HashMap<Integer, Clan>();
	private HashMap <Integer, Rank> ranks = new HashMap<Integer, Rank>();
	private HashMap <UUID, Member> members = new HashMap<UUID, Member>();
	private HashMap <String, Relation> relations = new HashMap<String, Relation>();
	
	
	public ClanHandler() {
		super(NAME);
		
	}
	
	public HashMap<Integer, Clan> getClans(){
		return (HashMap<Integer, Clan>) this.clans.clone();
	}
	
	public HashMap<Integer, Rank> getRanks(){
		return (HashMap <Integer, Rank>) this.ranks.clone();
	}
	
	public HashMap<String, Relation> getRelations() {
		return (HashMap<String, Relation>) this.relations.clone();
	}
	
	public boolean isExistsMember(UUID playerUUID) {
		return members.containsKey(playerUUID);
	}
	
	public ClanHandler(String name) {
		super(name);
	}
	
	public boolean theNameIsUnique(String name) {
		for (Clan clan: clans.values()) {
			if (name.equalsIgnoreCase(clan.getName())) return false;
		}
		return true;
	}
	
	public int getAPlayersClanId(UUID playerId) {
		Member member = members.get(playerId);
		if (member==null) return -1;
		return member.getClanId();
	}
	
	public Clan getClanById(int id) {
		return clans.get(id);
	}
	
	public Rank getAPlayersRank(UUID uniqueID) {
		Member member = members.get(uniqueID);
		return ranks.get(member.getRankId());
	}
	
	public Clan getAPlayersClan(UUID playerId) {
		return this.getClanById(this.getAPlayersClanId(playerId));
	}
	
	public boolean createClan(String name, UUID playerUUID) {
		if (!theNameIsUnique(name)) return false;
		Clan tempClan = new Clan(); 
		this.lastClansId++;
		
		tempClan.setId(this.lastClansId);
		tempClan.setName(name);
		tempClan.description = "";

		Rank rank = this.addRankForClan(tempClan, "Лидер");
		tempClan.setDefaultRankId(rank.getId());
		this.bindClanToMember(playerUUID, tempClan.getId(), tempClan.getDefaultRankId());
		
		rank = this.addRankForClan(tempClan, "Солдат");
		tempClan.setDefaultRankId(rank.getId());
		
		for (Entry<Integer, Clan> entryClan: clans.entrySet()) {
			Clan clan = entryClan.getValue();
			this.addRelationForClans(clan, tempClan);
		}
		
		clans.put(tempClan.getId(), tempClan);
		
		
		
		return true;
		
	}
	
	
	
	public void reassignAMember(UUID playerId) {
		Member member = new Member();
		member.setPlayerId(playerId);
		member.setEntryTime(new Date().getTime());
		member.setClanId(-1);
		member.setRankId(-1);
		members.put(playerId, member);
	}
	
	public Member bindClanToMember(UUID playerId, int clanId, int rankId) {
		
		Member member = this.members.get(playerId);
		
		member.setClanId(clanId);
		member.setEntryTime(new Date().getTime());
		member.setRankId(rankId);
		return member;
	}
	
	
	
	public Rank addRankForClan(Clan clan, String name) {
		this.lastRanksId++;
		clan.increaseLastRankSubordinationIndex();
		HashMap<Permission, Boolean> permissionsMap = new HashMap<Permission, Boolean>();
		
		Permission[] permissions = Permission.values();
		for (Permission permission: permissions) {
			permissionsMap.put(permission, clan.getLastRankSubordinationIndex()==0);
		}
		Rank rank = new Rank(lastRanksId, clan.getId(), clan.getLastRankSubordinationIndex(), name, permissionsMap);
		this.ranks.put(rank.getId(), rank);
		return rank;
	}
	
	public void removeRankForClan(Rank rank) {
		this.ranks.remove(rank.getId());
		for (Entry<Integer, Rank> eRank: this.ranks.entrySet()) {
			Rank potentialRank = eRank.getValue();
			if (potentialRank.getClanId()== rank.getClanId() && potentialRank.getSubordinationIndex()>rank.getSubordinationIndex())
				potentialRank.decreaseSubordinationIndex();
		}
		this.clans.get(rank.getClanId()).decreaseLastRankSubordinationIndex();
	}
	
	private void addRelationForClans(Clan clan1, Clan clan2) {
		Relation relation = new Relation();
		this.lastRelationsId++;
		relation.setId(lastRelationsId);
		relation.setClansId(new int[] {clan1.getId(), clan2.getId()});
		relation.toDeclare(RelationType.NEUTRAL);
		this.relations.put(clan1.getId()+"|"+clan2.getId(), relation);
	}

	public void setClanFromNBT(NBTTagCompound tag) {
		Clan clan = new Clan(tag);
		clans.put(clan.getId(), clan);

		
	}
	
	public void changeRelationByNBT(NBTTagCompound tag) {
		Relation relation = relations.get(tag.getIntArray("clansId")[0]+"|"+tag.getIntArray("clansId")[1]);
		relation.readFromNBT(tag);
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		int size = tag.getInteger("membersSize");
		for (int i = 0; i<size; i++) {
			NBTTagCompound memberTag = tag.getCompoundTag("member:" + i);
			Member member = new Member();
			member.readFromNBT(memberTag);
			members.put(member.getPlayerId(), member);
		}
		
		size = tag.getInteger("ranksSize");
		for (int i = 0; i<size; i++) {
			NBTTagCompound rankTag = tag.getCompoundTag("rank:" + i);
			Rank rank = new Rank();
			rank.readFromNBT(rankTag);
			ranks.put(rank.getId(), rank);
		}
		
		size = tag.getInteger("clansSize");
		for (int i = 0; i<size; i++) {
			NBTTagCompound clanTag = tag.getCompoundTag("clan:" + i);
			Clan clan = new Clan();
			clan.readFromNBT(clanTag);
			clans.put(clan.getId(), clan);
		}
		
		size = tag.getInteger("relationsSize");
		for (int i = 0; i<size; i++) {
			NBTTagCompound relationTag = tag.getCompoundTag("relation:" + i);
			Relation relation = new Relation();
			relation.readFromNBT(relationTag);
			String relationKey = relation.getClansId()[0] + "|" + relation.getClansId()[1];
			relations.put(relationKey, relation);
		}
		
		this.lastClansId = tag.getInteger("lastClansId");
		this.lastLandsId = tag.getInteger("lastLandsId");
		this.lastRanksId = tag.getInteger("lastRanksId");
		this.lastRelationsId = tag.getInteger("lastRelationsId");
	}
	

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("membersSize", this.members.size());
		UUID[] keysMembers = this.members.keySet().toArray(new UUID[this.members.size()]);
		for (int i = 0; i<this.members.size(); i++) {
			NBTTagCompound memberTag = this.members.get(keysMembers[i]).getNBT();
			tag.setTag("member:"+i, memberTag);
		}
		
		tag.setInteger("ranksSize", this.ranks.size());
		Integer[] keysRanks = this.ranks.keySet().toArray(new Integer[this.ranks.size()]);
		for (int i = 0; i<this.ranks.size(); i++) {
			NBTTagCompound rankTag = this.ranks.get(keysRanks[i]).getNBT();
			tag.setTag("rank:"+i, rankTag);	
		}
	
		tag.setInteger("clansSize", this.clans.size());
		Integer[] keysClans = this.clans.keySet().toArray(new Integer[this.clans.size()]);
		for (int i = 0; i<this.clans.size(); i++) {
			NBTTagCompound clanTag = this.clans.get(keysClans[i]).getNBT();
			tag.setTag("clan:"+i, clanTag);
		}
		
		tag.setInteger("relationsSize", this.relations.size());
		String[] keysRelations = this.relations.keySet().toArray(new String[this.relations.size()]);
		for (int i = 0; i<this.relations.size(); i++) {
			NBTTagCompound relationTag = this.relations.get(keysRelations[i]).getNBT();
			tag.setTag("relation:"+i, relationTag);
		}
		
		tag.setInteger("lastClansId", this.lastClansId);
		tag.setInteger("lastLandsId", this.lastLandsId);
		tag.setInteger("lastRanksId", this.lastRanksId);
		tag.setInteger("lastRelationsId", this.lastRelationsId);

		
		
	}
	
    public static ClanHandler get(World world) {
        MapStorage storage = world.mapStorage;
        ClanHandler instance = (ClanHandler) storage.loadData(ClanHandler.class, NAME);
       
        if (instance == null) {
            instance = new ClanHandler();
            storage.setData(NAME, instance);
 
        }
        return instance;
    }

	public ArrayList<Rank> getClanRanks(int clandId) {
		
		ArrayList<Rank> ranks = new ArrayList<Rank>();
		
		for (Entry<Integer, Rank> entryRank: this.ranks.entrySet()) {
			Rank rank = entryRank.getValue();
			if (rank.getClanId()!=clandId) continue;
			
			ranks.add(rank);
		}
		
		//Пузырек потому что
		
		for (int i = 1; i < ranks.size(); i++) {
	        Rank current = ranks.get(i);
	        int j = i - 1;
	        while(j >= 0 && current.getSubordinationIndex() < ranks.get(j).getSubordinationIndex()) {
	        	ranks.set(j+1, ranks.get(j));
	            j--;
	        }

	        ranks.set(j+1, current);
	    }
		
		return ranks;
	}






    

}
