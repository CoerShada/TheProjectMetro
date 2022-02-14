package serb.tp.metro.client.gui.elements;

import net.minecraft.client.gui.GuiButton;
import serb.tp.metro.common.clans.Rank;

public class GuiButtonRank extends GuiButton{

	Rank rank;
	
    public GuiButtonRank(int id, int posX, int posY, Rank rank)
    {
    	super(id, posX, posY, 7, 18, rank.getName());
    	this.rank = rank;
    }

}
