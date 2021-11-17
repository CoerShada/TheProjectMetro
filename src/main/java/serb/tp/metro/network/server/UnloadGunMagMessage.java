package serb.tp.metro.network.server;

import java.util.Arrays;
import java.util.Date;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.containers.CustomSlots;
import serb.tp.metro.containers.InventoryItemStorage;
import serb.tp.metro.items.modules.ItemMag;
import serb.tp.metro.items.weapons.ItemBullet;
import serb.tp.metro.network.AbstractMessage.AbstractServerMessage;

public class UnloadGunMagMessage extends AbstractServerMessage<UnloadGunMagMessage> {

	private int type;
	private int index;

	public UnloadGunMagMessage(int type, int index) 
	{
		this.type = type;
		this.index = index;
	}

	public UnloadGunMagMessage() {
		
	}

	@Override
	protected void read(PacketBuffer buffer) {
		type = buffer.readInt();
		index = buffer.readInt();
	}

	@Override
	protected void write(PacketBuffer buffer) {
		buffer.writeInt(type);
		buffer.writeInt(index);
	}

	@Override
	public void process(EntityPlayer player, Side side) 
	{	
		ItemStack hold = player.inventory.getCurrentItem();
		player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, Main.modid +":"+hold.getUnlocalizedName()+"_unload", 1.0f, 1.0f);
        NBTTagCompound magTag =  (NBTTagCompound) hold.stackTagCompound.getTag("mag");
        ItemMag itemMag = (ItemMag) GameRegistry.findItem(Main.modid, magTag.getString("name"));
		ItemStack mag = new ItemStack(itemMag);
		float weight = 0;
		for (int i = 1; i<hold.stackTagCompound.getIntArray("bullets").length; i++) {
			ItemBullet bullet = (ItemBullet) Item.getItemById(hold.stackTagCompound.getIntArray("bullets")[i]);
			weight+= bullet.getWeight();
		}
		weight += itemMag.getWeight();
		mag.setTagCompound((NBTTagCompound) magTag.getTag("nbt"));
        hold.stackTagCompound.setFloat("weight", hold.stackTagCompound.getFloat("weight")-weight);
        mag.stackTagCompound.setFloat("weight", weight);
        if (hold.stackTagCompound.getIntArray("bullets").length>0) {
        	mag.stackTagCompound.setIntArray("bullets", Arrays.copyOfRange(hold.stackTagCompound.getIntArray("bullets"), 1, hold.stackTagCompound.getIntArray("bullets").length));
        	hold.stackTagCompound.setIntArray("bullets", new int[] {hold.stackTagCompound.getIntArray("bullets")[0]});
        }
        else {
        	mag.stackTagCompound.setIntArray("bullets", new int[] {});

        }
        hold.stackTagCompound.removeTag("mag");
        
        
        if (type==1) 
		{
			ItemStack rig = player.inventory.getStackInSlot(19);
			rig.getTagCompound().setFloat("weight", rig.getTagCompound().getFloat("weight") + weight );
			InventoryItemStorage inv = new InventoryItemStorage(player.inventory.getStackInSlot(CustomSlots.CHESTRIG.getIndex()));
			inv.openInventory();
			inv.inventory[index] = mag;
			inv.save();
			inv.closeInventory();
		}
		else if(type == 2) 
		{
			
			player.inventory.mainInventory[index] = mag;
		}
		else 
		{
			player.func_146097_a(mag, true, false);
		}
        
        player.inventoryContainer.detectAndSendChanges();
	}

}
