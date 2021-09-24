package serb.tp.metro.items.armor;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import serb.tp.metro.client.Type;

public class ItemBulletproofVest extends ItemArmor{
	
	private int protactionClass;
	private float weight;
	private int defanceArms;
	private int defanceLegs;
	private float durability;
	
	public ItemBulletproofVest(String name, int protactionClass, float weight, int defanceArms, int defanceLegs, float durability) {
		super(LoadItemArmor.ARMOR, 1, 1);
		this.setUnlocalizedName(name);
		this.protactionClass = protactionClass;
		this.weight = weight;
		this.defanceArms = defanceArms;
		this.defanceLegs = defanceLegs;
		this.durability = durability;
		GameRegistry.registerItem(this, this.getUnlocalizedName());
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
		ModelBiped model = new ModelBiped();
		model.bipedHead.showModel = 
				model.bipedHeadwear.showModel = 
				model.bipedBody.showModel = 
				model.bipedRightArm.showModel = 
				model.bipedLeftArm.showModel = 
				model.bipedRightLeg.showModel = 
				model.bipedLeftLeg.showModel = false;
		return model;
	}
	
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        NBTTagCompound nbt = itemStack.getTagCompound();
    	
    	
        if (nbt.getInteger("protactionClass")==100) list.add(Type.getTranslate("characteristic.armor.protectionclass") + ": 1");
        else if (nbt.getInteger("protactionClass")==200) list.add(Type.getTranslate("characteristic.armor.protectionclass") + ": 2");
        else if (nbt.getInteger("protactionClass")==220) list.add(Type.getTranslate("characteristic.armor.protectionclass") + ": 2a");
        else if (nbt.getInteger("protactionClass")==300) list.add(Type.getTranslate("characteristic.armor.protectionclass") + ": 3");
        else if (nbt.getInteger("protactionClass")==400) list.add(Type.getTranslate("characteristic.armor.protectionclass") + ": 4");
        else if (nbt.getInteger("protactionClass")==500) list.add(Type.getTranslate("characteristic.armor.protectionclass") + ": 5");
        else if (nbt.getInteger("protactionClass")==520) list.add(Type.getTranslate("characteristic.armor.protectionclass") + ": 5a");
        else if (nbt.getInteger("protactionClass")==600) list.add(Type.getTranslate("characteristic.armor.protectionclass") + ": 6");
        else if (nbt.getInteger("protactionClass")==620) list.add(Type.getTranslate("characteristic.armor.protectionclass") + ": 6a");
        else if (nbt.getInteger("protactionClass")>620) list.add(Type.getTranslate("characteristic.armor.protectionclass") +": 6a+");
        if (nbt.getInteger("defanceArms")==0) list.add(Type.getTranslate("characteristic.armor.defancearms")+" " +Type.getTranslate("characteristic.status.notsupported"));
        else if (nbt.getInteger("defanceArms")==1) list.add(Type.getTranslate("characteristic.armor.defancearms")+" " +Type.getTranslate("characteristic.status.notplaced_f"));
        else if (nbt.getInteger("defanceArms")==2) list.add(Type.getTranslate("characteristic.armor.defancearms")+" " +Type.getTranslate("characteristic.status.placed_f"));
        
        if (nbt.getInteger("defanceLegs")==0) list.add(Type.getTranslate("characteristic.armor.defancelegs")+" " +Type.getTranslate("characteristic.status.notsupported"));
        else if (nbt.getInteger("defanceLegs")==1) list.add(Type.getTranslate("characteristic.armor.defancelegs")+" " +Type.getTranslate("characteristic.status.notplaced_f"));
        else if (nbt.getInteger("defanceLegs")==2) list.add(Type.getTranslate("characteristic.armor.defancelegs")+" " +Type.getTranslate("characteristic.status.placed_f"));
        
        if (nbt.getInteger("kapaMod")==0) list.add(Type.getTranslate("characteristic.armor.kapmod")+" " +Type.getTranslate("characteristic.status.notplaced_m"));
        else list.add(Type.getTranslate("characteristic.armor.kapmod") +" : " + nbt.getInteger("kapMod"));

        list.add(Type.getTranslate("characteristic.all.weight")+": " + String.format("%.2f", weight/1000) + Type.getTranslate("characteristic.all.weight.kg"));
        
    }
	
	
    @Override
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
    	ItemStack itemStack = new ItemStack(item); 
		itemStack.setTagCompound(new NBTTagCompound());
		itemStack.getTagCompound().setInteger("protactionClass", protactionClass);
		itemStack.getTagCompound().setInteger("kapMod", 0);
		itemStack.getTagCompound().setInteger("defanceArms", defanceArms);
		itemStack.getTagCompound().setInteger("defanceLegs", defanceLegs);
		itemStack.getTagCompound().setFloat("durability", durability);
		itemStack.getTagCompound().setFloat("durabilityMax", durability);
		itemStack.getTagCompound().setFloat("weight", weight);
    	list.add(itemStack);
		
    }
}
