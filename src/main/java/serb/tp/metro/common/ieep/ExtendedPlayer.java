package serb.tp.metro.common.ieep;

import java.util.ArrayList;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.clans.ClanHandler;


public class ExtendedPlayer implements IExtendedEntityProperties {
	
	public final static String TAG = Main.modid + ":ExtendedPlayer";
	protected EntityPlayer player;
	private ArrayList<Clan> invites = new ArrayList<Clan>();
	private ArrayList<Clan> ignore = new ArrayList<Clan>();
	private short reputation; 

	
	/*public boolean inviteToClan(Clan clan) {
		if (this.clan!=null || invites.contains(clan)) return false;
		if (!ignore.contains(clan))
			invites.add(clan);
		return true;
	}
	
	public void acceptInvite(Clan clan, World world) {
		if (invites.contains(clan))
			clan.addMember(player);
		setClan(clan);
		invites.clear();
	}
	
	public void declineInvite(Clan clan) {
		invites.remove(clan);
	}
	
	public void applyToClan(Clan clan) {
		
	}
	
	public void setClan(Clan clan) {
		this.clan = clan;
	}
	
	public void reg(EntityPlayer player) {
		player.registerExtendedProperties(TAG, new ExtendedPlayer());		
	}*/
	
	public ExtendedPlayer get(EntityPlayer player) {
		return player != null ? (ExtendedPlayer)player.getExtendedProperties(TAG) : null;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setShort("reputation", reputation);
		
		
		NBTTagCompound invites = new NBTTagCompound();
		for (int i = 0; i<this.invites.size(); i++) {
			invites.setInteger("inv"+i, this.invites.get(i).getId());
		}
		invites.setInteger("size", this.invites.size());
		compound.setTag("invites", invites);
		
		
		NBTTagCompound ignore = new NBTTagCompound();
		for (int i = 0; i<this.invites.size(); i++) {
			ignore.setInteger("ign"+i, this.ignore.get(i).getId());
		}
		ignore.setInteger("size", this.ignore.size());
		compound.setTag("ignore", ignore);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		ClanHandler handler = ClanHandler.get(MinecraftServer.getServer().getEntityWorld());
		this.reputation = compound.getShort("reputation");
	
		NBTTagCompound tag = compound.getCompoundTag("invites");
		int size = tag.getInteger("size");
		for (int i = 0; i<size; i++) {
			int id = tag.getInteger("inv" + i);
			this.invites.add(handler.getClanById(id));
		}
		
		tag = compound.getCompoundTag("ignore");
		size = tag.getInteger("size");
		for (int i = 0; i<size; i++) {
			int id = tag.getInteger("ign" + i);
			this.ignore.add(handler.getClanById(id));
		}
	}

	@Override
	public void init(Entity entity, World world) {
		if (entity instanceof EntityPlayer) {
			this.player = (EntityPlayer) entity;

		}
		
	}

	public void reg(EntityPlayer player) {
		player.registerExtendedProperties(TAG, new ExtendedPlayer());		
	}


	

}
