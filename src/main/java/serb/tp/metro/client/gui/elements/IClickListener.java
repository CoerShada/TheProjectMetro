package serb.tp.metro.client.gui.elements;

@FunctionalInterface
public interface IClickListener<T> {
	
	public void onEntryClicked(T entry, int mouseX, int mouseY, int buttonNumber);
	
}
