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

	private int index;
	private String name;
	private ArrayList<Member> members = new ArrayList<Member>();
	Map<Permission, Boolean> permissions = new HashMap<Permission, Boolean>();
	
	@SuppressWarnings("null")
	public Rank(int index, String name, Map<Permission, Boolean> permissions) {
		this.index = index;
		this.name = name;
		this.permissions = permissions;
		if (permissions==null) {
			permissions.putAll(Permission.getDefaultPermissions());
		}

	}
	
	public Rank() {}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public String getName() {
		return this.name;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		
		index = nbt.getInteger("index");
		String name = nbt.getString("name");
		DebugMessage.printMessage("Выгружен ранг " + name);
		NBTTagCompound perms = nbt.getCompoundTag("perms");
		
		int size = nbt.getInteger("quantityMembers");
		ArrayList<Member> tempMembers = new ArrayList<Member>();
		for (int i = 0; i<size; i++) {

			Member member = new Member();
			member.readFromNBT(nbt.getCompoundTag("member:"+i));

			tempMembers.add(member);
		}
		this.members = tempMembers;
		
		for(Permission key: Permission.values()) {	
			permissions.put(key, nbt.getBoolean(key.toString()));
		}
		if (permissions==null) {
			permissions.putAll(Permission.getDefaultPermissions());
		}
	}

	@Override
	public NBTTagCompound getNBT() {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("index", this.getIndex());
		tag.setString("name", this.getName());
		DebugMessage.printMessage("Загружен ранг " + this.getName());
		Map<Permission, Boolean> perms = this.getPermissions();
		tag.setInteger("permsSize", perms.size());
		
		NBTTagCompound tagPerms = new NBTTagCompound();
		for (Entry<Permission, Boolean> perm: perms.entrySet()) {
			tagPerms.setBoolean(perm.getKey().toString(), perm.getValue());
		}
		
		int counter = 0;
		tag.setInteger("quantityMembers", members.size());
		for (Member member: members) {
			NBTTagCompound tagMember = member.getNBT();
			tag.setTag("member:"+counter, tagMember);
			counter++;
		}
		
		tag.setTag("perms", tagPerms);
		return tag;
	}

	public void addMember(Member newMember) {
		
		this.members.add(newMember);
	}

	public boolean removeMember(EntityPlayer player) {
		for (Member member: members) {
			if (member.getPlayer().equals(player.getDisplayName())) {
				members.remove(member);
				return true;
			}
		}
		return false;
	}

	public boolean isARankPlayer(String playerName) {
		for (Member member: members) {
			DebugMessage.printMessage(member.getPlayer() + " " + playerName);
			if (member.getPlayer().equalsIgnoreCase(playerName)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Member> getMembers() {
		return members;
	}
	
	
}
