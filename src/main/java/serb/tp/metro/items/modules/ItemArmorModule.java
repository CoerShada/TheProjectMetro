package serb.tp.metro.items.modules;

public class ItemArmorModule extends ItemModule{
	
	protected final int[] defence;

	public ItemArmorModule(String name, String description, float weight, String model, float[] sizeModel, float[] pos,
			float[] rotation, float[] onInventoryPos, float[] rightHandPos, float[] rightHandRotation, int[] defence) {
		super(name, description, weight, model, sizeModel, pos, rotation, onInventoryPos, rightHandPos, rightHandRotation);
		this.defence = defence;
	}
}
