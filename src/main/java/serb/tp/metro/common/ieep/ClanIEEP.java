package serb.tp.metro.common.ieep;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import serb.tp.metro.Main;
import serb.tp.metro.common.clans.Clan;
import serb.tp.metro.common.handlers.ClanHandler;


public class ClanIEEP implements IExtendedEntityProperties {
	
	public final static String TAG = Main.modid + ":CanIEEP";
	protected EntityPlayer player;
	private Clan clan;

	public Clan getClan() {
		return clan;
	}
	
	public void setClan() {
		ClanHandler handler = ClanHandler.get(player.worldObj);
		clan = handler.getAPlayersClan(player);
	}
	
	public void reg(EntityPlayer player) {
		player.registerExtendedProperties(TAG, new ClanIEEP());
	}
	
	public ClanIEEP get(EntityPlayer player) {
		return player != null ? (ClanIEEP)player.getExtendedProperties(TAG) : null;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		setClan();
		
	}

	@Override
	public void init(Entity entity, World world) {
		if (entity instanceof EntityPlayer) {
			this.player = (EntityPlayer) entity;

		}
		
	}

	

}
