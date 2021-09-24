package serb.tp.metro.items.modules;

public class SlotInstalledModules {

	public final int x;
	public final int y;
	ItemModule[] modules;
	
	public SlotInstalledModules(int x, int y, ItemModule[] modules) {
		this.modules = modules;
		this.x = x;
		this.y = y;
	}
	
	public ItemModule[] getModules() {
		return this.modules;
	}
}
