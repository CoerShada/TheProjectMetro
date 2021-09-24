package serb.tp.metro.items.jewels;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import serb.tp.metro.Main;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.items.jewels.JewelsType.customJewel;
import serb.tp.metro.items.jewels.JewelsType.jewelTypePistol;

public class ItemArtifact extends CustomItemJewel implements jewelTypePistol, customJewel {	

	public ItemArtifact(String texture) {
		this.setTextureName(Main.modid + ":" + texture);
		this.setCreativeTab(LoadTabs.tabRPGi);
		this.setMaxStackSize(1);
		this.setMaxDamage(1000);
	}

	@SideOnly(Side.CLIENT)
	public void addAdtimermation(ItemStack stack, EntityPlayer player, List line, boolean par4) {
		JewelsType.getDescription(line);
	}	

}