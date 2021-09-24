package serb.tp.metro.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import serb.tp.metro.items.food.ItemFoodMetro;
import serb.tp.metro.items.guns.ItemBullet;
import serb.tp.metro.items.guns.ItemFirearmMagGun;
import serb.tp.metro.items.guns.modules.ItemMag;

public class LoadItems {
    
    
	public static Item
	
	foodTest,
	
	filter_factory,
	filter_artisanal
	
	;
	
	public LoadItems() 
	{

	    foodTest = new ItemFoodMetro(3, 0, 2, 3, 0, 0);
	    GameRegistry.registerItem(foodTest, "foodTest");
		
	    filter_factory = new ItemFilter("filter_factory", 54000, 200F).setCreativeTab(CreativeTabs.tabCombat);
		
	    filter_artisanal = new ItemFilter("filter_artisanal", 18000, 300F).setCreativeTab(CreativeTabs.tabCombat);
		
	    //bullets 9mm
	    
		
	    //bullets 12k
	    /*bullet_12k_SlugFactory = new ItemBullet("bullet_12k_SlugFactory", 32, 130, 200, 1, 15, 0, 1).setCreativeTab(CreativeTabs.tabCombat);
		
	    bullet_12k_ExpansiveFactory = new ItemBullet("bullet_12k_ExpansiveFactory", 32, 150, 150, 1, 7, 100, 1).setCreativeTab(CreativeTabs.tabCombat);
		
	    bullet_12k_CartridgeFactory = new ItemBullet("bullet_12k_CartridgeFactory", 32, 200, 10, 1, 0.1, 0, 12).setCreativeTab(CreativeTabs.tabCombat);
		
	    bullet_12k_BuckshotFactory = new ItemBullet("bullet_12k_BuckshotFactory", 32, 170, 50, 1, 0.15, 0, 7).setCreativeTab(CreativeTabs.tabCombat);
	    ItemBullet[] bullets12k= {(ItemBullet) bullet_12k_SlugFactory, (ItemBullet) bullet_12k_ExpansiveFactory, (ItemBullet) bullet_12k_CartridgeFactory, (ItemBullet) bullet_12k_BuckshotFactory};
	    
		
	    //bullets 9x39
	    bullet_9x39_sp5Factory= new ItemBullet("bullet_9x39_sp5Factory", 24, 35, 360, 1, 13, 0.15, 1).setCreativeTab(CreativeTabs.tabCombat);
		
	    bullet_9x39_sp6Factory= new ItemBullet("bullet_9x39_sp6Factory", 24, 38, 420, 1, 15, 0.05, 1).setCreativeTab(CreativeTabs.tabCombat);
		
	    bullet_9x39_sppFactory= new ItemBullet("bullet_9x39_sppFactory", 24, 41, 510, 1, 18, 0.01, 1).setCreativeTab(CreativeTabs.tabCombat);
	    ItemBullet[] bullets9x39 = {(ItemBullet) bullet_9x39_sp5Factory, (ItemBullet) bullet_9x39_sp6Factory, (ItemBullet) bullet_9x39_sppFactory};*/
		

	}


}
