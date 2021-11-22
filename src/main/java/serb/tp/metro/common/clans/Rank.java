package serb.tp.metro.common.clans;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

public class Rank {

	private int index;
	private String name;
	Map<Permission, Boolean> permissions = new HashMap<Permission, Boolean>();
	
	public Rank(int index, String name, Map<Permission, Boolean> permissions) {
		super();
		this.index = index;
		this.name = name;
		if (permissions==null) {
			//Дописать создание пустых пермов
		}
		else {
			this.permissions = permissions;
		}
	}
	
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
	
}
