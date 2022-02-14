package serb.tp.metro.common.clans;

import java.util.HashMap;

public enum Permission {
	RenameClan,
	ChangeDescription,
	InviteMember,
	RemoveMember,
	ImprooveMember,
	DeprooveMember,
	CreateOffer,
	AcceptOffer, 
	ChangeRelation,
	AcceptRelation,
	BeginClaim,
	ChangePerms,
	CreateOrder,
	AcceptOrder;
	
	public static HashMap<Permission, Boolean> getDefaultPermissions(){
		HashMap<Permission, Boolean> result = new HashMap<Permission, Boolean>();
		
		for (Permission perm: Permission.values()) {
			result.put(perm, false);
		}
		
		return result;
		
	}
}

