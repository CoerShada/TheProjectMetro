package serb.tp.metro.common.clans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.DebugMessage;

public class Rank implements INBTSyncronized{

	private int id;
	private int clanId;
	private int subordinationIndex = 0;
	
	private String name;
	Map<Permission, Boolean> permissions = new HashMap<Permission, Boolean>();
	

	public Rank(int id, int clanId, int subordinationIndex, String name, Map<Permission, Boolean> permissions) {
		this.id = id;
		this.clanId = clanId;
		this.subordinationIndex = subordinationIndex;
		this.name = name;
		
		if (permissions==null) {
			permissions = new HashMap<Permission, Boolean>();
			permissions.putAll(Permission.getDefaultPermissions());
		}
		this.permissions = permissions;

	}
	
	public Rank() {}
	
	public boolean isPermitted(Permission permission) {
		return this.permissions.get(permission);
	}
	
	public void setId(int index) {
		this.id = index;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getClanId() {
		return this.clanId;
	}

	public void increaseSubordinationIndex() {
		this.subordinationIndex++;
	}
	
	public void decreaseSubordinationIndex() {
		this.subordinationIndex--;
	}
	
	public int getSubordinationIndex() {
		return this.subordinationIndex;
	}
	
	public void updatePermissions() {
		Permission[] permissions = Permission.values();
		for(int i = 0; i<permissions.length; i++) {
			if (this.permissions.containsKey(permissions[i])) continue;
			this.permissions.put(permissions[i], false);
			
		}
	}
	
	public Map<Permission, Boolean> getPermissions() {
		return this.permissions;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		nbt = this.getNBT();
		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		id = nbt.getInteger("id");
		name = nbt.getString("name");
		clanId = nbt.getInteger("clanId");
		subordinationIndex = nbt.getInteger("subordinationIndex");
		NBTTagCompound perms = nbt.getCompoundTag("perms");

		for(Permission key: Permission.values()) {	
			permissions.put(key, perms.getBoolean(key.toString()));
		}
		if (permissions==null) {
			permissions.putAll(Permission.getDefaultPermissions());
		}
	}

	@Override
	public NBTTagCompound getNBT() {
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("id", this.getId());
		tag.setString("name", this.getName());
		tag.setInteger("clanId", clanId);
		tag.setInteger("subordinationIndex", subordinationIndex);
		Map<Permission, Boolean> perms = this.getPermissions();
		tag.setInteger("permsSize", perms.size());
		
		NBTTagCompound tagPerms = new NBTTagCompound();
		for (Entry<Permission, Boolean> perm: perms.entrySet()) {
			tagPerms.setBoolean(perm.getKey().toString(), perm.getValue());
		}
		tag.setTag("perms", tagPerms);
		return tag;
	}

}
