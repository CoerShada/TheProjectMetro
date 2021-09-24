package serb.tp.metro.factions;



import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;


public class FactionsData extends WorldSavedData
{
    String factionName;
    String factionTag;
    String factionDescription;
    byte[] factionRanksIds;
    byte[] factionDiplomaty;
    int factionId;
    int[] factionsIds;
    Boolean[] factionInterest;
    String[] factionsNames;
    String[] factionRanksNames;
    int[][] factionRanksPlayers;
    Boolean[] factionRanksCanInvite;
    Boolean[] factionRanksCanKick;
    Boolean[] factionRanksCanSetDiplomatic;
    Boolean[] factionRanksCanEditBoard;
    Boolean[] factionRanksCanClaim;
    Boolean[] factionRanksCanGiveTerritory;
    Boolean[] factionRanksCanEditRanks;
    Boolean[] factionRanksCanBeRemoved;
    
    int lastFactionsId;
    
    public FactionsData(String mapName, String factionName, String factionTag)
    {
	super(mapName);
	this.factionName = factionName;
	this.factionTag = factionTag;
    }
    	
    public FactionsData(String mapName, int factionId)
    {
	super(mapName);
	this.factionId = factionId;
    }
    
    public void createFaction(EntityPlayer player) {
	factionId = lastFactionsId+1;
	int[] tempFactionsIds = factionsIds.clone();
	String[] tempFactionsNames = factionsNames.clone();
	factionsIds = new int[factionsIds.length+1];
	factionsNames = new String[factionsNames.length+1];
	
	for (int i = 0; i<factionsIds.length-1; i++) {
	    factionsIds[i] = tempFactionsIds[i];
	    factionsNames[i] = tempFactionsNames[i];
	}
	factionsIds[factionsIds.length-1] = factionId;
	factionRanksPlayers = new int[2][]; 
	factionRanksPlayers[0][0] = player.getEntityId(); 
	factionRanksIds = new byte[] {0,1};
	factionRanksNames = new String[] {"Лидер", "Рядовой"};
	factionRanksCanInvite = new Boolean[] {true, false};
	factionRanksCanKick = new Boolean[] {true, false};
	factionRanksCanSetDiplomatic = new Boolean[] {true, false};
	factionRanksCanEditBoard = new Boolean[] {true, false};
	factionRanksCanClaim = new Boolean[] {true, false};
	factionRanksCanGiveTerritory = new Boolean[] {true, false};
	factionRanksCanEditRanks = new Boolean[] {true, false};
	factionRanksCanBeRemoved = new Boolean[] {false, false};
    }
    

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
	factionsIds = nbt.getIntArray("factionsId");
	lastFactionsId = factionsIds[factionsIds.length-1];
	NBTTagCompound nbtFaction = nbt.getCompoundTag("faction" + factionId);
	factionName = nbtFaction.getString("factionName");
	factionTag = nbtFaction.getString("factionTag");
	factionDescription = nbtFaction.getString("factionDescription");
	factionDiplomaty = new byte[factionsIds.length];
	factionInterest = new Boolean[factionsIds.length];
	for (int i = 0; i<factionsIds.length; i++) 
	{
	    NBTTagCompound diplomaty = nbtFaction.getCompoundTag("diplomaty"+factionsIds[i]);
	    factionDiplomaty[i] = diplomaty.getByte("diplomaty");
	    factionInterest[i] = diplomaty.getBoolean("interestOurFaction");
	}
	factionRanksIds = nbtFaction.getByteArray("factionRanksIds");
	factionRanksPlayers = new int[factionRanksIds.length][];
	factionRanksCanInvite = new Boolean[factionRanksIds.length];
	factionRanksCanKick = new Boolean[factionRanksIds.length];
	factionRanksCanSetDiplomatic = new Boolean[factionRanksIds.length];
	factionRanksCanEditBoard = new Boolean[factionRanksIds.length];
	factionRanksCanClaim = new Boolean[factionRanksIds.length];
	factionRanksCanGiveTerritory = new Boolean[factionRanksIds.length];
	factionRanksCanEditRanks = new Boolean[factionRanksIds.length];
	factionRanksCanBeRemoved = new Boolean[factionRanksIds.length];
	for (int i = 0; i<factionRanksIds.length; i++) 
	{
	    NBTTagCompound ranks = nbt.getCompoundTag("rank"+factionRanksIds[i]);
	    factionRanksNames[i] = ranks.getString("name");
	    factionRanksPlayers[i] = ranks.getIntArray("players");
	    factionRanksCanInvite[i] = ranks.getBoolean("canInvite");
	    factionRanksCanKick[i] = ranks.getBoolean("canKick");
	    factionRanksCanSetDiplomatic[i] = ranks.getBoolean("canSetDiplomatic");
	    factionRanksCanEditBoard[i] = ranks.getBoolean("canEditBoard");
	    factionRanksCanClaim[i] = ranks.getBoolean("canClaim");
	    factionRanksCanGiveTerritory[i] = ranks.getBoolean("canGiveTerritory");
	    factionRanksCanEditRanks[i] = ranks.getBoolean("canEditRanks");
	    factionRanksCanBeRemoved[i] = ranks.getBoolean("canBeRemoved");
	}
	
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
	nbt.setIntArray("factionsIds", factionsIds);
	NBTTagCompound nbtFaction = new NBTTagCompound();
	nbtFaction.setInteger("id", factionId);
	nbtFaction.setString("factionName", factionName);
	nbtFaction.setString("factionTag", factionTag);
	nbtFaction.setString("factionDescription", factionDescription);
	nbtFaction.setByteArray("factionRanksIds", factionRanksIds);
	for(int i = 0; i<factionRanksIds.length; i++) {
	    NBTTagCompound nbtRank = new NBTTagCompound();
	    nbtRank.setString("name", factionRanksNames[i]);
	    nbtRank.setIntArray("players", factionRanksPlayers[i]);
	    nbtRank.setBoolean("canInvite", factionRanksCanInvite[i]);
	    nbtRank.setBoolean("canKick", factionRanksCanKick[i]);
	    nbtRank.setBoolean("canSetDiplomatic", factionRanksCanSetDiplomatic[i]);
	    nbtRank.setBoolean("canEditBoard", factionRanksCanEditBoard[i]);
	    nbtRank.setBoolean("canClaim", factionRanksCanClaim[i]);
	    nbtRank.setBoolean("canGiveTerritory", factionRanksCanGiveTerritory[i]);
	    nbtRank.setBoolean("canEditRanks", factionRanksCanEditRanks[i]);
	    nbtRank.setBoolean("canBeRemoved", factionRanksCanBeRemoved[i]);
	    nbtFaction.setTag("rank"+factionRanksIds[i], nbtRank);
	}
	for(int i = 0; i<factionsIds.length; i++) {
	    if (factionsIds[i]!=factionId) {
		NBTTagCompound nbtDiplomaty = new NBTTagCompound();
		nbtDiplomaty.setInteger("otherFactionId", factionsIds[i]);
		nbtDiplomaty.setByte("diplomaty", factionDiplomaty[i]);
		nbtDiplomaty.setBoolean("interestOtherFaction", false); //Доделать!
		nbtDiplomaty.setBoolean("interestOurFaction", factionInterest[i]);
		nbtFaction.setTag("diplomaty"+factionsIds[i], nbtDiplomaty);
	    }
	}
	nbt.setTag("faction"+ factionId, nbtFaction);
    }


}
