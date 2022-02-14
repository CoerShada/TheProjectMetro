package serb.tp.metro.common.handlers;

import java.util.ArrayList;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.clans.ClanLand;
import serb.tp.metro.common.clans.Relation;
import serb.tp.metro.common.ieep.ExtendedPlayer;

public class ClanHandler extends WorldSavedData{

	public static final String NAME = "Clans";
	private int lastId;
	private ArrayList <ClanLand> lands = new ArrayList<ClanLand>();
	private ArrayList <Clan> clans = new ArrayList<Clan>();
	private ArrayList <Relation> relations = new ArrayList<Relation>();
	
	
	public ClanHandler() {
		super(NAME);
		
	}
	
	public ArrayList <Clan> getClans(){
		return clans;
	}
	
	public ClanHandler(String name) {
		super(name);
		
	}
	
	public boolean theNameIsUnique(String name) {
		for (Clan clan: clans) {
			if (name.equalsIgnoreCase(clan.getName())) return false;
		}
		return true;
	}
	
	public Clan getAPlayersClan(EntityPlayer player) {
		String playerName = player.getDisplayName();
		System.out.println(clans.size() + " количество");
		for (Clan clan: clans) {
			if (clan.isClanPlayer(playerName)) {
				return clan;
			}
		}
		return null;
	}
	
	public Clan getClanById(int id) {
		Clan result = null;
		for (Clan clan: clans) {
			if (clan.getId() == id) {
				result = clan;
				break;
			}
		}
		return result;
	}
	
	public boolean createClan(String name, EntityPlayer player) {
		if (!theNameIsUnique(name)) return false;
		Clan tempClan = new Clan(); 
		lastId++;
		tempClan.setRanksOnClanCreated();
		tempClan.setId(lastId);
		tempClan.setName(name);
		tempClan.description = "";
		tempClan.addMember(player);
		tempClan.setDefaultRank(tempClan.getRankFromIndex(1));
		clans.add(tempClan);

		DebugMessage.printMessage("Clan \""+ name +"\" has been created");
		return true;
		
	}

	public void setClanFromNBT(NBTTagCompound tag) {
		Clan clan = new Clan();
		clan.readFromNBT(tag);
		Clan potentialClan = getClanById(clan.getId());
		if (potentialClan!=null) {
			int index = clans.indexOf(potentialClan);
			clans.set(index, clan);
		}
		else {
			clans.add(clan);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		ArrayList <Clan> tempClans = new ArrayList<Clan>();
		for (int i = 0; i<tag.getInteger("clansSize"); i++) {
			NBTTagCompound clanTag = tag.getCompoundTag("clan:" + i);
			setClanFromNBT(tag);
		}
		this.clans = tempClans;
		this.lastId = tag.getInteger("lastId");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("landsSize", lands.size());
		tag.setInteger("lastId", lastId);
		
		tag.setInteger("clansSize", clans.size());
		for (int i = 0; i<clans.size(); i++) {
			NBTTagCompound clanTag = clans.get(i).getNBT();
			
			tag.setTag("clan:"+i, clanTag);
		}
		tag.setInteger("relationsSize", relations.size());
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

}
