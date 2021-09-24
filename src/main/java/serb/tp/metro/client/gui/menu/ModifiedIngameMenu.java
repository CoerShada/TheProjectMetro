package serb.tp.metro.client.gui.menu;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.resources.I18n;

public class ModifiedIngameMenu extends GuiIngameMenu{

	public ModifiedIngameMenu() {
		this.buttonList.clear();
		boolean flag = true;
        this.buttonList.add(new GuiButton(1, this.width/2-74, 4*22+75, 148, 20, I18n.format("menu.returnToMenu", new Object[0])));

        /*if (!this.mc.isIntegratedServerRunning())
        {
            ((GuiButton)this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
        }*/

        /*this.buttonList.add(new GuiButton(4, this.width / 2 - 74, 75, 148, 20, I18n.format("menu.returnToGame", new Object[0])));
        this.buttonList.add(new GuiButton(0, this.width / 2 - 74, 2*22+75, 148, 20, I18n.format("menu.options", new Object[0])));
        GuiButton guibutton;
        this.buttonList.add(guibutton = new GuiButton(7, this.width / 2 - 74, 6*22+75, 200, 20, I18n.format("menu.shareToLan", new Object[0])));
        this.buttonList.add(new GuiButton(6, this.width / 2 - 74, 1*22+75, 148, 20, I18n.format("gui.stats", new Object[0])));
       // guibutton.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();*/
		
	}
}
