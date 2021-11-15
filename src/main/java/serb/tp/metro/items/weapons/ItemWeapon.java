package serb.tp.metro.items.weapons;


import java.util.ArrayList;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import serb.tp.metro.Main;
import serb.tp.metro.client.Type;
import serb.tp.metro.common.ws.WeaponSystem;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.creativetabs.LoadTabs;
import serb.tp.metro.customization.AbstractCustomizableSlot;
import serb.tp.metro.customization.ICustomizable;
import serb.tp.metro.entities.Bullet;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.client.SoundsToPlayersMessage;

public abstract class ItemWeapon extends Item3D implements ICustomizable {
	
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
    protected final float convenience;
     
    public final float[] leftHandPos;
    public final float[] leftHandRotation; 
    public final float[] onAimingPos;
    public final float[] onAimingRotation;
    public final float[] onAimingLeftHandPos;
    public final float[] onAimingLeftHandRotation; 
    public final float[] onAimingRightHandPos;
    public final float[] onAimingRightHandRotation;
	ArrayList<AbstractCustomizableSlot> slotsCustomization = new ArrayList<AbstractCustomizableSlot>();
	

    public ItemWeapon(String name, String description, float weight, String model, float[] sizeModel, float[] pos, float[] rotation, float[] onInventoryPos,float[] rightHandPos, float[] rightHandRotation, 
    		float[] leftHandPos, float[] leftHandRotation, float[] onAimingPos, float[] onAimingRotation, float[] onAimingLeftHandPos, float[] onAimingLeftHandRotation, float[] onAimingRightHandPos, float[] onAimingRightHandRotation,
    		int soundRadius, int rateOfFire, float penetrationMod, float jummingChance, int reloadTime, float recoilVert, float recoilHoriz, float accuracy, float convenience, FireMod[] fireMods) 
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
    	this.convenience = convenience;
    	this.fireMods = fireMods;
    	this.setCreativeTab(LoadTabs.weapons);
    	this.setMaxStackSize(1);
    	this.setFull3D();  
	}
    
    public float getSoundRadius(ItemStack itemStack) {
    	return soundRadius;
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs ct, List list)
    {
    	super.getSubItems(item, ct, list);
		
   
 
    	this.baseItemStack.getTagCompound().setFloat("verticalRecoil", recoilVert);
    	this.baseItemStack.getTagCompound().setFloat("horizontalRecoil", recoilHoriz);
    	this.baseItemStack.getTagCompound().setFloat("convenience", convenience);
	    
    	this.baseItemStack.getTagCompound().setFloat("penetrationMod", penetrationMod);
    	this.baseItemStack.getTagCompound().setFloat("jummingMod", jummingChance);
    	this.baseItemStack.getTagCompound().setFloat("accuracy", accuracy);
    	this.baseItemStack.getTagCompound().setFloat("rateOfFire", rateOfFire);
    	this.baseItemStack.getTagCompound().setBoolean("safetyMod", true);
    	this.baseItemStack.getTagCompound().setString("fireMod", fireMods[0].toString());
    	this.baseItemStack.getTagCompound().setBoolean("jummingMod", false);
    	this.baseItemStack.getTagCompound().setLong("notfire", 0);
    	this.baseItemStack.getTagCompound().setLong("notreload", 0);   
     
    	this.baseItemStack.getTagCompound().setIntArray("bullets", new int[]{});
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
        
        list.add(String.format("%.4f", nbt.getFloat("accuracy")));
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
    
    

    public void fireModChange(ItemStack itemStack, World world, EntityPlayer player) 
    {
    	if(fireMods.length==1) return ;
    	int index = ArrayUtils.indexOf(fireMods, FireMod.valueOf(itemStack.getTagCompound().getString("fireMod")));
    	if (index==fireMods.length-1) {
    		itemStack.getTagCompound().setString("fireMod", this.fireMods[0].toString());
    		
    	}
    	else {
    		itemStack.getTagCompound().setString("fireMod", this.fireMods[index+1].toString());
    	}
    	WeaponSystem ws = Main.proxy.ws.get(player);
    	ws.updateCurrentItem();
    	//PacketDispatcher.sendToServer(new ChangeFireModMessage(fireMods[index].toString()));
    	
    }
    
    public void safetyModChange(ItemStack itemStack, World world, EntityPlayer player) 
    {
    	itemStack.getTagCompound().setBoolean("safetyMod", !itemStack.getTagCompound().getBoolean("safetyMod"));
    	WeaponSystem ws = Main.proxy.ws.get(player);
    	ws.updateCurrentItem();
    }
    
   
    public void shoot(ItemStack itemStack, World world, EntityPlayer player)
    {
    	if(itemStack!=null && itemStack.getItem() instanceof ItemWeapon && itemStack.hasTagCompound() && itemStack.getTagCompound().getIntArray("bullets").length>0 && itemStack.getTagCompound().getLong("notfire")+(60/itemStack.getTagCompound().getDouble("rateOfFire")*100)<=new Date().getTime()) 
    	{
    		ItemWeapon holdItem = (ItemWeapon) itemStack.getItem();
    		int[] bullets = itemStack.getTagCompound().getIntArray("bullets");
    		itemStack.getTagCompound().setLong("notfire", world.getTotalWorldTime());
    		if (bullets.length>0) {
    			int[] bulletsNew = new int[bullets.length-1];
    			ItemBullet bullet = (ItemBullet) Item.getItemById(bullets[0]);
    			if (!itemStack.getTagCompound().getBoolean("jumming") && holdItem.getJummingChance()*bullet.jamming<rnd.nextInt(101)) 
    			{
    				
    				for (int i = 0; i<bullets.length - 1; i++) 
    				{
    					bulletsNew[i] = bullets[i+1];
    				}
    				
    				PacketDispatcher.sendToAllAround(new SoundsToPlayersMessage(player.getEntityId(), Item.getIdFromItem(holdItem)), player, holdItem.getSoundRadius(itemStack)*2);
    				world.spawnEntityInWorld(new Bullet(world, player, itemStack.getTagCompound().getDouble("accuracy"), bullet.damage, bullet.penetration));
    				itemStack.getTagCompound().setFloat("weight", itemStack.getTagCompound().getFloat("weight")- bullet.weight);
    				itemStack.getTagCompound().setIntArray("bullets", bulletsNew);
    				player.inventoryContainer.detectAndSendChanges();
    				
    				
    	    			if (rnd.nextBoolean()) 
    	    			{
    	    				Minecraft.getMinecraft().thePlayer.setAngles( (float) this.recoilHoriz, (float) this.recoilVert);
    	    			}
    	    			else 
    	    			{
    	    				Minecraft.getMinecraft().thePlayer.setAngles(-1 * (float) this.recoilHoriz, (float) this.recoilVert);

    	    			}
    				
    				
    				return;
    			}
    			else 
    			{
    				itemStack.getTagCompound().setBoolean("jumming", true);
    			}
    		}
    		
    	}
    }
    
	public void addSlot(AbstractCustomizableSlot slotCustomization) {
		slotsCustomization.add(slotCustomization);
		
	}
	
	public ArrayList<AbstractCustomizableSlot> getSlotsCustomization() {
		return this.slotsCustomization;
	}
	
	public int getIndexNewSlot() {
		return this.slotsCustomization.size();
	}
	
	public boolean isCustomizable() {
		return slotsCustomization.size()>0;
	}
	
	@Override
	public NBTTagCompound updateCharacteristics(ItemStack is) {
		if (is==null || !is.hasTagCompound()) return null;
		InventoryItemStorage inv = new InventoryItemStorage(is);
		inv.openInventory();
		NBTTagCompound tag = new NBTTagCompound();
		tag.setFloat("verticalRecoil", recoilVert);
		tag.setFloat("horizontalRecoil", recoilHoriz);
		tag.setFloat("convenience", convenience);
		tag.setFloat("accuracy", accuracy);
		tag.setFloat("penetrationMod", penetrationMod);
		tag.setFloat("jumming", jummingChance);

		for (int i = 0; i<inv.getSizeInventory(); i++) {
			
			if (inv.getStackInSlot(i)==null || !(inv.getStackInSlot(i).getItem() instanceof ICustomizable)) continue;
			NBTTagCompound tempTag = new NBTTagCompound();
			ICustomizable customizable = (ICustomizable) inv.getStackInSlot(i).getItem();
			tempTag = customizable.updateCharacteristics(inv.getStackInSlot(i));
			tag.setFloat("verticalRecoil", tag.getFloat("verticalRecoil") * tempTag.getFloat("verticalRecoilMod"));
			tag.setFloat("horizontalRecoil", tag.getFloat("horizontalRecoil") * tempTag.getFloat("horizontalRecoilMod"));
			tag.setFloat("convenience", tag.getFloat("convenience") * tempTag.getFloat("convenienceMod"));
			tag.setFloat("accuracy", tag.getFloat("accuracy") * tempTag.getFloat("accuracyMod"));
			
			tag.setFloat("penetrationMod", tag.getFloat("penetrationMod") * tempTag.getFloat("penetrationMod"));
			tag.setFloat("jumming", tag.getFloat("jumming") * tempTag.getFloat("jummingMod"));
		}
		
		/*tag.setFloat("verticalRecoil", recoilVert - tag.getFloat("verticalRecoil"));
		tag.setFloat("horizontalRecoil", recoilHoriz - tag.getFloat("horizontalRecoil"));
		tag.setFloat("convenience", convenience - tag.getFloat("convenience"));
		tag.setFloat("accuracy", accuracy + (tag.getFloat("accuracy")-1));
		
		tag.setFloat("penetrationMod", penetrationMod - tag.getFloat("penetrationMod"));
		tag.setFloat("jumming", jummingChance - tag.getFloat("jumming"));*/
		
		return tag;
		
	}
	
	@SideOnly(Side.CLIENT)
	public float[] getModsCoordsForRendere(ItemRenderType type) {
		return new float[] {0, 0, 0};

	}
}
