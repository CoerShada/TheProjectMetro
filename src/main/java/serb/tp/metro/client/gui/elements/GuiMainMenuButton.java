package serb.tp.metro.client.gui.elements;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;

public class GuiMainMenuButton extends GuiButton{
	
	protected static final ResourceLocation buttonTextures = new ResourceLocation(Main.modid, "textures/gui/guiScreen/guiBattonsMainMenu/background.png");

	public GuiMainMenuButton(int p_i1020_1_, int p_i1020_2_, int p_i1020_3_, String p_i1020_4_) {
		super(p_i1020_1_, p_i1020_2_, p_i1020_3_, p_i1020_4_);
		
	}

}
