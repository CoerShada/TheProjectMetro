package serb.tp.metro.client.gui.menu;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class ModifiedMainMenu extends GuiMainMenu {

    private static final ResourceLocation texture = new ResourceLocation("textures/gui/achievement/achievement_icons.png");

    public ModifiedMainMenu() {
        super();
    }

    @Override
    public void initGui() {
        int i = this.height / 4 + 48;
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, i + 72 + 12, 98, 20, I18n.format("menu.options", new Object[0])));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 2, i + 72 + 12, 98, 20, I18n.format("menu.quit", new Object[0])));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float parTick) {
        GL11.glColor4f(1, 1, 1, 1);
        drawDefaultBackground();
        mc.renderEngine.bindTexture(texture);
        drawTexturedModalRect(0, 0, 0, 0, mc.displayWidth, mc.displayHeight);
        //---
        int k;

        for (k = 0; k < this.buttonList.size(); ++k)
            ((GuiButton)this.buttonList.get(k)).drawButton(this.mc, mouseX, mouseY);

        for (k = 0; k < this.labelList.size(); ++k)
            ((GuiLabel)this.labelList.get(k)).func_146159_a(this.mc, mouseX, mouseY);

    }

}