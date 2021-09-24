package serb.tp.metro.factions;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Factions
{

    public static World world = Minecraft.getMinecraft().theWorld;
    
    public static void registerFaction(String name, String tag, int idSpecialisation) {
	
    }
    
    public static String getFactionName(int idFaction) {
	return "FactionName";
    }
    
    public static void removeFaction(int idFaction) {
	
    }
    
    public static void editDescription(int idFaction, String description) {
	
    }
    
    public static void invitePlayerToFaction(int idFaction, EntityPlayer player) {
	
    }
    
    public static void removePlayerFromFaction(int idFaction, EntityPlayer player) {
	
    }
    
    public static void giveRankToPlayer(int idFaction, int idRank, EntityPlayer player) {
	
    }
    
    public static void dealareWarOn(int idFactionFrom, int idFactionTo) {
	
    }
    
    public static void offerNeutrality(int idFactionFrom, int idFactionTo) {
	
    }
    
    public static void offerAlliance(int idFactionFrom, int idFactionTo) {
	
    }
    
    public static void createRank(int factionId, String rankName) {
	
    }
    
    public static void editRank(int factionId, int rankId, int rankName) {
	
    }
    
    public static void removeRank(int factionId, int rankId) {
	
    }
}
