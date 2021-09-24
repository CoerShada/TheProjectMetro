package serb.tp.metro.items.armor;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import serb.tp.metro.common.CommonProxy;
import serb.tp.metro.items.ItemBackpack;
import serb.tp.metro.items.ItemChestrig;
import serb.tp.metro.items.ItemMask;

public class LoadItemArmor {

	public static ItemArmor helmet_6b47, helmet_altyn, armor_redut, pants, boots;
	public static Item shoulders, bracers, mask_screen, backpack_gekkon, chestrig_smersh;

	public static ArmorMaterial ARMOR;

	/*public static void registerItems() {
		ARMOR = EnumHelper.addArmorMaterial("ARMOR", 100, new int[] {0, 0, 0, 0}, 0);
		armor_redut = (ItemArmor) new ItemBulletproofVest("armor_redut", 520, 200F, 2, 0, 75F).setCreativeTab(CreativeTabs.tabCombat);
		helmet_6b47 = (ItemArmor) new ItemHelmet("helmet_6b47", 200, (float)3.5, "null").setCreativeTab(CreativeTabs.tabCombat);
		helmet_altyn = (ItemArmor) new ItemHelmet("helmet_altyn", 200, (float)3.5, "open").setCreativeTab(CreativeTabs.tabCombat);
		backpack_gekkon = new ItemBackpack("backpack_gekkon", 200F, CommonProxy.GUI_BACPACK, 15).setCreativeTab(CreativeTabs.tabCombat);
		chestrig_smersh = new ItemChestrig("chestrig_smersh", 200F, 6).setCreativeTab(CreativeTabs.tabCombat);
		mask_screen = new ItemMask("mask_screen", 200F, 0.9).setTextureName(null).setCreativeTab(CreativeTabs.tabCombat);
		

	}*/

}