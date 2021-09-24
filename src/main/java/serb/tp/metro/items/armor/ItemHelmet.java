package serb.tp.metro.items.armor;

import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import serb.tp.metro.client.Type;
import serb.tp.metro.client.render.RenderImages;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.entities.player.ExtendedPlayer;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeVisorMessage;
import serb.tp.metro.network.server.LoadGunMagMessage;

public class ItemHelmet extends ItemArmor{
	int protactionClass;
	float weight;
	String visor;
	public ItemHelmet(String name, int protactionClass, float weight, String visor) {
		super(LoadItemArmor.ARMOR, 1, 0);
		this.setUnlocalizedName(name);
		this.protactionClass = protactionClass;
		this.weight = weight;
		this.visor = visor;
		this.maxStackSize = 1;
		GameRegistry.registerItem(this, this.getUnlocalizedName());
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
        list.add(Type.getTranslate("characteristic.all.weight")+": " + String.format("%.2f", weight/1000) + Type.getTranslate("characteristic.all.weight.kg"));
        list.add(nbt.getString("visor"));


        
        
        
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
	
	
    @Override
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
    	ItemStack itemStack = new ItemStack(item);
		itemStack.setTagCompound(new NBTTagCompound());
		itemStack.getTagCompound().setInteger("protactionClass", protactionClass);
		itemStack.getTagCompound().setFloat("weight", weight);
		itemStack.getTagCompound().setString("visor", visor);
		list.add(itemStack);
		
    }
	
	public ItemStack openVisor(ItemStack equiped, EntityPlayer player) 
	{
		if ((equiped.getTagCompound().getString("visor").equalsIgnoreCase("close") || (equiped.getTagCompound().getString("visor").equalsIgnoreCase("open")) && ExtendedPlayer.get(player).inventory.getStackInSlot(CustomSlots.MASK.ordinal())==null)) 
		{
			PacketDispatcher.sendToServer(new ChangeVisorMessage());
		}
		
		return equiped;
		
	}

}
