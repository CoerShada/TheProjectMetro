package serb.tp.metro.common.ws;


import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.items.Item3D;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.weapons.FireMod;
import serb.tp.metro.items.weapons.ItemWeapon;
import serb.tp.metro.network.PacketDispatcher;
import serb.tp.metro.network.server.OnItemLeftClickMessage;
import serb.tp.metro.network.server.ShootMessage;


public class WeaponSystem implements IExtendedEntityProperties {
	
	public final static String TAG = Main.modid + ":WeaponSystem";
	protected EntityPlayer player;
	protected long counter;
	protected Type type = Type.OTHER;
	protected int nextRound = 0;
	protected int slotId;
	protected boolean canShoot = true;
	protected boolean isAiming = false;
	Item currentItem;
	ItemStack is;
	protected boolean[] buttons = {false, false, false};
	private boolean forcibly = false;
	
	public void setCurrents(ItemStack is) {
		this.is = is;
		if (is!=null)
			this.currentItem = is.getItem();
		else
			this.currentItem = null;
	}
	
	public void updateCurrentItem() {
		this.forcibly = true;
	}
	
	public void onUpdate() {
		if (onItemChanged(player)) {
			placeNewItem();
			canShoot = true;
			nextRound = 0;
		}
		onButtonsClick();
		if (nextRound>0) {
			ItemWeapon currentWeapon = (ItemWeapon) currentItem;
			currentWeapon.shoot(is, player.worldObj, player);
			PacketDispatcher.sendToServer(new ShootMessage());
			nextRound--;
		}
	}
	
	protected void onButtonsClick() {
		boolean isAiming = false;
		if(buttons[0] && buttons[1]) {
					
		}
		if (buttons[0]) {
			if (currentItem instanceof Item3D && !(currentItem instanceof ItemWeapon)) {
				Item3D currentItem3D = (Item3D) currentItem;
				currentItem3D.onItemLeftClick(is, player.worldObj, player);
				PacketDispatcher.sendToServer(new OnItemLeftClickMessage());
			}
			else if (currentItem instanceof ItemWeapon && nextRound<=0 && canShoot && !is.getTagCompound().getBoolean("safetyMod")) {
				switch(FireMod.valueOf(is.getTagCompound().getString("fireMod")))
			    {
			    	case SEMI:
			    		nextRound = 1;
			    		canShoot = false;
			    		break;
			    	case RD2:
			    		nextRound = 2;
			    		canShoot = false;
			    		break;
			    	case RD3:
			    		nextRound = 3;
			    		canShoot = false;
			    		break;
			    	case FULLAUTO:
			    		nextRound = 1;
			    		break;
			    	default:
			    		break;
			    }	
			}	
		}
		if (buttons[1]) {
			isAiming = true;
		}
		if(!(buttons[0])) {
			if (nextRound<=0) {
				canShoot = true;
			}
		}
		
		this.isAiming = isAiming;
	}
	
	public void onClientUpdate() {
		if (Minecraft.getMinecraft().currentScreen!=null) {
			for (boolean button: buttons)
				button = false;
			return;
		}
		if (Mouse.isButtonDown(0)) {
			buttons[0] = true;
		}
		else {
			buttons[0] = false;
		}
		if (Mouse.isButtonDown(1)) {
			buttons[1] = true;
		}
		else {
			buttons[1] = false;
		}
		
		
	}
	
	protected boolean onItemChanged(EntityPlayer player) {
		InventoryPlayer inv = player.inventory;
		boolean changed = false;

		if (currentItem==null && (inv.getCurrentItem()==null || !(inv.getCurrentItem().getItem() instanceof ItemMag || inv.getCurrentItem().getItem() instanceof ItemWeapon))) return changed;

		
		if ((currentItem==null && inv.getCurrentItem()!=null ) || (currentItem!=null && inv.getCurrentItem()==null)  || slotId!=inv.currentItem  || (!currentItem.equals(inv.getCurrentItem().getItem()) || forcibly)) {
			if (inv.getCurrentItem()==null) {
				type = Type.OTHER;
			}
			else if (inv.getCurrentItem().getItem() instanceof ItemMag) {
				type = Type.MAG;
			}
			else if (inv.getCurrentItem().getItem() instanceof ItemWeapon) {
				type = Type.WEAPON;
			}
			else {
				type = Type.OTHER;
			}
			slotId=inv.currentItem;
			DebugMessage.printMessage("Item has been changed!");
			changed = true;
		}
		if (this.forcibly) {
			this.forcibly = false;
		}
		return changed;
	}
	
	protected void placeNewItem() {
		switch(type) {
			case OTHER:
				currentItem = null;
				is = null;
				break;
			case MAG:
				is = player.inventory.getCurrentItem();
				currentItem = is.getItem();
				break;
			case WEAPON:
				is = player.inventory.getCurrentItem();
				currentItem = is.getItem();
				break;
			default:
				break;
		
		}
	}
	
	
	public void reg(EntityPlayer player) {
		player.registerExtendedProperties(TAG, new WeaponSystem());
	}
	
	public WeaponSystem get(EntityPlayer player) {
		return player != null ? (WeaponSystem)player.getExtendedProperties(TAG) : null;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
	
		
	}

	@Override
	public void init(Entity entity, World world) {
		if (entity instanceof EntityPlayer) 
			this.player = (EntityPlayer) entity;
		
	}

	
	enum Type{
		OTHER,
		MAG,
		WEAPON
	}

	

}