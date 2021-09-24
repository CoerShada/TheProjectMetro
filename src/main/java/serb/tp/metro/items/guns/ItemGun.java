package serb.tp.metro.items.guns;


import java.util.Date;
import java.util.List;
import java.util.Random;

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
import serb.tp.metro.items.Item3D;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.ChangeFireModMessage;
import serb.tp.metro.network.server.ChangeSafetyModMessage;
import serb.tp.metro.network.server.ShootMessage;

public abstract class ItemGun extends Item3D{
	
	Random rnd = new Random();
    protected final double powerMod;
    protected final double jummingMod;
    protected final int rateOfFire;
    protected final int loadTime;
    protected final int unloadTime;
    protected final boolean autoFire;
    protected final double recoilVert;
    protected final double recoilHoriz;
    protected final double accuracy;

    public ItemGun(String name, float weight, int rateOfFire, double powerMod, double jummingMod, int loadTime, int unloadTime, double recoilVert, double recoilHoriz, double accuracy, boolean autoFire) 
    {
    	super(name, weight);
    	this.powerMod = powerMod;
    	this.jummingMod = jummingMod;
    	this.loadTime = loadTime;
    	this.unloadTime = unloadTime;
    	this.rateOfFire = rateOfFire;
    	this.autoFire = autoFire;
    	this.recoilVert = recoilVert;
    	this.recoilHoriz = recoilHoriz;
    	this.accuracy = accuracy;
    	this.setCreativeTab(CreativeTabs.tabCombat);
    	this.setMaxStackSize(1);
    	this.setFull3D();  
	}
    


    
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean isAdv) {
        if (!itemStack.hasTagCompound()) return;
        NBTTagCompound nbt = itemStack.getTagCompound();
        list.add(Type.getTranslate("characteristic.guns.ammunition") + ": " + nbt.getIntArray("bullets").length); 
        if (nbt.getBoolean("fireMod")) 
        {
        	list.add(Type.getTranslate("characteristic.guns.fmt_f"));
        }
        else 
        {
        	list.add(Type.getTranslate("characteristic.guns.fmt_s"));
        }
        
        if (nbt.getBoolean("safetyMod")) 
        {
        	list.add(Type.getTranslate("characteristic.guns.safetymod"));
        }
        else 
        {
        	list.add(Type.getTranslate("characteristic.guns.safetymod_n"));
        }
        list.add(Type.getTranslate("characteristic.all.weight")+": " + String.format("%.2f", weight/1000) + Type.getTranslate("characteristic.all.weight.kg"));

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
    	if (itemStack.getTagCompound().getBoolean("autoFire")) 
    	{
    		PacketDispatcher.sendToServer(new ChangeFireModMessage());
    	}
    	return itemStack;
    }
    
    public void safetyModChange() 
    {
    	
    	PacketDispatcher.sendToServer(new ChangeSafetyModMessage());
    }
    
    public void shoot(ItemStack itemStack, EntityPlayer player, World world)
    {
    	if(itemStack!=null && itemStack.getItem() instanceof ItemGun && itemStack.hasTagCompound() && itemStack.getTagCompound().getIntArray("bullets").length>0 && itemStack.getTagCompound().getLong("notfire")+(60/itemStack.getTagCompound().getDouble("rateOfFire")*100)<=new Date().getTime()) 
    	{
    		PacketDispatcher.sendToServer(new ShootMessage());
			//Map mapSoundPositions = Maps.newHashMap();
			//ChunkCoordinates chunkcoordinates = new ChunkCoordinates((int) player.posX, (int) player.posY, (int)player.posZ);
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
