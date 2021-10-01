package serb.tp.metro.items.modules;

import serb.tp.metro.creativetabs.LoadTabs;

public class ItemWeaponModule extends ItemModule{
	

	private float verticalRecoilMod;
	private float horizontalRecoilMod;
	private float convenienceMod;
	private float accuracyMod;
	private float penetrationMod;
	private float jummingMod;
	
	public ItemWeaponModule(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation, float verticalRecoilMod, 
			float horizontalRecoilMod, float convenienceMod, float accuracyMod, float penetrationMod, float jummingMod) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation);
		this.verticalRecoilMod = verticalRecoilMod;
		this.horizontalRecoilMod = horizontalRecoilMod;
		this.convenienceMod = convenienceMod;
		this.accuracyMod = accuracyMod;
		this.penetrationMod = penetrationMod;
		this.jummingMod = jummingMod;
	}


}
