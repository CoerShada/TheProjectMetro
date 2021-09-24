package serb.tp.metro.recipes;

import net.minecraft.item.Item;

public class Recipe {
	
	public final Item[] neededItems;
	public final int[] quantityNeededItems;
	public final Item[] receivedItems;
	public final int[] quantityReceivedItems;
	public final long craftTime;
	
	public Recipe(Item[] neededItems, int[] quantityNeededItems, Item[] receivedItems, int[] quantityReceivedItems, long craftTime) {
		this.neededItems = neededItems;
		this.quantityNeededItems = quantityNeededItems;
		this.receivedItems = receivedItems;
		this.quantityReceivedItems = quantityReceivedItems;
		this.craftTime = craftTime*1000;
	}
	
	public Recipe(Item neededItem, int quantityNeededItem, Item[] receivedItems, int[] quantityReceivedItems, long craftTime) {
		this(new Item[] {neededItem}, new int[] {quantityNeededItem}, receivedItems, quantityReceivedItems, craftTime);
	}


	public Recipe(Item neededItem, int quantityNeededItem, Item receivedItem, int quantityReceivedItem, long craftTime) {
		this(new Item[] {neededItem}, new int[] {quantityNeededItem}, new Item[] {receivedItem}, new int[] {quantityReceivedItem}, craftTime);
	}
	
	public Recipe(Item[] neededItems, int[] quantityNeededItems, Item receivedItems, int quantityReceivedItems, long craftTime) {
		this(neededItems,  quantityNeededItems, new Item[] {receivedItems}, new int[] {quantityReceivedItems}, craftTime);
	}
}
