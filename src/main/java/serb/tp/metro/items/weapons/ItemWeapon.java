package serb.tp.metro.items.weapons;


import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import serb.tp.metro.client.Type;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeFireModMessage;
import serb.tp.metro.network.server.ChangeSafetyModMessage;
import serb.tp.metro.network.server.ShootMessage;

public abstract class ItemWeapon extends Item3D {
	
	Random rnd = new Random();
    protected final float penetrationMod;
    protected final float jummingChance;
    protected final int rateOfFire;
    protected final int reloadTime;
    protected final FireMod[] fireMods;
    protected final float recoilVert;
    protected final float recoilHoriz;
    protected final float accuracy;
    protected final int soundRadius;
     
    public final float[] leftHandPos;
    public final float[] leftHandRotation; 
    public final float[] onAimingPos;
    public final float[] onAimingRotation;
    public final float[] onAimingLeftHandPos;
    public final float[] onAimingLeftHandRotation; 
    public final float[] onAimingRightHandPos;
    public final float[] onAimingRightHandRotation;


    public ItemWeapon(String name, String description, float weight, String model, float[] sizeModel, float[] pos, float[] rotation, float[] onInventoryPos,float[] rightHandPos, float[] rightHandRotation, 
    		float[] leftHandPos, float[] leftHandRotation, float[] onAimingPos, float[] onAimingRotation, float[] onAimingLeftHandPos, float[] onAimingLeftHandRotation, float[] onAimingRightHandPos, float[] onAimingRightHandRotation,
    		int soundRadius, int rateOfFire, float penetrationMod, float jummingChance, int reloadTime, float recoilVert, float recoilHoriz, float accuracy, FireMod[] fireMods) 
    {
		super(name, description, weight, model, sizeModel, pos ,rotation, onInventoryPos, rightHandPos, rightHandRotation);
    	
        this.leftHandPos = leftHandPos;  
        this.leftHandRotation = leftHandRotation;  
        this.onAimingPos = onAimingPos;
        this.onAimingRotation = onAimingRotation;
        this.onAimingLeftHandPos = onAimingLeftHandPos;
        this.onAimingLeftHandRotation = onAimingLeftHandRotation;
        this.onAimingRightHandPos = onAimingRightHandPos;
        this.onAimingRightHandRotation = onAimingRightHandRotation;
    	
    	
    	this.penetrationMod = penetrationMod;
    	this.jummingChance = jummingChance;
    	this.reloadTime = reloadTime;
    	this.rateOfFire = rateOfFire;
    	this.recoilVert = recoilVert;
    	this.recoilHoriz = recoilHoriz;
    	this.accuracy = accuracy;
    	this.soundRadius = soundRadius;
    	this.fireMods = fireMods;
    	this.setCreativeTab(LoadTabs.weapons);
    	this.setMaxStackSize(1);
    	this.setFull3D();  
	}
    
    public float getSoundRadius(ItemStack itemStack) {
    	return soundRadius;
    }

    public float getJummingChance() {
    	return this.jummingChance;
    }
    
    public void onUpdate(ItemStack hold, World world, Entity entity, int integer, boolean isHold) {
    	if (!isHold) return;
    	
    	
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        NBTTagCompound nbt = itemStack.getTagCompound();
        list.add(Type.getTranslate("characteristic.guns.ammunition") + ": " + nbt.getIntArray("bullets").length); 
        switch (FireMod.valueOf(nbt.getString("fireMod"))) 
        {
        	case SEMI:
        		list.add(Type.getTranslate("characteristic.guns.fire_mod.semi"));
        		break;
        	case FULLAUTO: 
        		list.add(Type.getTranslate("characteristic.guns.fire_mod.fullauto"));
        		break;
        	case RD2: 
        		list.add(Type.getTranslate("characteristic.guns.fire_mod.rd2"));
        		break;
        	case RD3: 
        		list.add(Type.getTranslate("characteristic.guns.fire_mod.rd3"));
        		break;
		default:
			break;
        }
        
        if (nbt.getBoolean("safetyMod")) 
        {
        	list.add(Type.getTranslate("characteristic.guns.safetymod"));
        }
        else 
        {
        	list.add(Type.getTranslate("characteristic.guns.safetymod_n"));
        }
        super.addInformation(itemStack, entityPlayer, list, isAdv);
        //list.add(Type.getTranslate("characteristic.all.weight")+": " + String.format("%.2f", weight/1000) + Type.getTranslate("characteristic.all.weight.kg"));

    }

    @Override
    public boolean onLeftClickEntity(ItemStack is, EntityPlayer player, Entity entity) {
    	return true;
    }
    
    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
        return true;
    }
    
    public void load(ItemStack itemStack, EntityPlayer player) {
    }
    
    public void unload(ItemStack itemStack, EntityPlayer player) {
    	
    }
    
    

    public ItemStack fireModChange(ItemStack itemStack) 
    {
    	if(fireMods.length==1) return itemStack;
    	int index = ArrayUtils.indexOf(fireMods, FireMod.valueOf(itemStack.getTagCompound().getString("fireMod")));
    	if (index==fireMods.length-1)
    		index = 0;
    	else
    		index++;
    	PacketDispatcher.sendToServer(new ChangeFireModMessage(fireMods[index].toString()));
    	
    	return itemStack;
    }
    
    public void safetyModChange() 
    {

    	PacketDispatcher.sendToServer(new ChangeSafetyModMessage());
    }
    
    @SideOnly(Side.CLIENT)
    public void shoot(ItemStack itemStack, EntityPlayer player)
    {
    	if(itemStack!=null && itemStack.getItem() instanceof ItemWeapon && itemStack.hasTagCompound() && itemStack.getTagCompound().getIntArray("bullets").length>0 && itemStack.getTagCompound().getLong("notfire")+(60/itemStack.getTagCompound().getDouble("rateOfFire")*100)<=new Date().getTime()) 
    	{
    		PacketDispatcher.sendToServer(new ShootMessage());
    		if (!itemStack.getTagCompound().getBoolean("jumming"))
    		{

    			if (rnd.nextBoolean()) 
    			{
    				Minecraft.getMinecraft().thePlayer.setAngles( (float) this.recoilHoriz, (float) this.recoilVert);
    			}
    			else 
    			{
    				Minecraft.getMinecraft().thePlayer.setAngles(-1 * (float) this.recoilHoriz, (float) this.recoilVert);

    			}
    			
    		}
    	}
    }
}
