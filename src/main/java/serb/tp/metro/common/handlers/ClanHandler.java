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
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.clans.Land;
import serb.tp.metro.common.clans.Relation;

public class ClanHandler extends WorldSavedData{

	public static final String NAME = "Clans";
	private int lastId;
	private ArrayList <Land> lands = new ArrayList<Land>();
	private ArrayList <Clan> clans = new ArrayList<Clan>();
	private ArrayList <Relation> relations = new ArrayList<Relation>();
	
	
	public ClanHandler() {
		super(NAME);
		
	}
	
	public ClanHandler(String name) {
		super(name);
		
	}
	
	public Clan getAPlayersClan(EntityPlayer player) {
		String playerName = player.getDisplayName();
		for (Clan clan: clans) {
			if (clan.isClanPlayer(playerName)) return clan;
		}
		return null;
	}
	
	public void createClan(String name, EntityPlayer player) {
		Clan tempClan = new Clan(); 
		lastId++;
		tempClan.setId(lastId);
		tempClan.setName(name);
		tempClan.description = "";
		tempClan.addMember(player);
		tempClan.setDefaultRank(tempClan.getRankFromIndex(1));
		clans.add(tempClan);
		DebugMessage.printMessage("Clan \""+ name +"\" has been created");
		
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		ArrayList <Clan> tempClans = new ArrayList<Clan>();
		for (int i = 0; i<tag.getInteger("clansSize"); i++) {
			NBTTagCompound clanTag = tag.getCompoundTag("clan:" + i);
			Clan tempClan = new Clan();
			tempClan.readFromNBT(clanTag);
			tempClans.add(tempClan);
		}
		this.clans = tempClans;
		this.lastId = tag.getInteger("lastId");
		System.out.println("Loaded "+tag.getInteger("clansSize") + " clans");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag) {
		tag.setInteger("landsSize", lands.size());
		tag.setInteger("lastId", lastId);
		
		tag.setInteger("clansSize", clans.size());
		for (int i = 0; i<clans.size(); i++) {
			NBTTagCompound clanTag = new NBTTagCompound();
			clans.get(i).writeToNBT(clanTag);
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
