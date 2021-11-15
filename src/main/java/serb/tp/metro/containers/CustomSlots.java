package serb.tp.metro.containers;

public enum CustomSlots {

	KNIFE(0),
	PISTOL(1),
	WEAPON(2),
	BEGIN_HOTBAR(3),
	END_HOTBAR(5),
	BEGIN_INV(6),
	END_INV(14),
	MASK(15),
	OUTERWEAR(16),
	PANTS(17),
	BACKPACK(18),
	CHESTRIG(19);
	
    private int index;
    CustomSlots(int index){
        this.index = index;
    }
    public int getIndex(){ return index;}
}