package serb.tp.metro.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import serb.tp.metro.Main;

public final class ItemSelector extends Item{

	public ItemSelector(String name){
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName("wood_axe");
		GameRegistry.registerItem(this, name);
	}

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
    	if (!player.worldObj.isRemote) {
	    	if (player.isSneaking())
	    	{
	    		Main.proxy.bs.get(player).clearSelection();
		    	ChatComponentText chatMessage = new ChatComponentText("Selection has been cleared!");
				chatMessage.getChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE);
				player.addChatMessage(chatMessage);
	    	}
	    	else {
		    	ChatComponentText chatMessage = new ChatComponentText("Second position [x=" + x + ", y="+ y +", z=" + (z + 1) + "]");
				chatMessage.getChatStyle().setColor(EnumChatFormatting.AQUA);
				player.addChatMessage(chatMessage);
				Main.proxy.bs.get(player).selectSecondPostion(x, y, z);
	    	}
    	}
        return false;
    }
	

    public boolean onBlockStartBreak(ItemStack itemstack, int x, int y, int z, EntityPlayer player)
    {
    	if (!player.worldObj.isRemote) {
	    	if (player.isSneaking())
	    	{
	        	ChatComponentText chatMessage = new ChatComponentText("Main block position [x=" + x + ", y="+ y +", z=" + (z + 1) + "]");
	        	chatMessage.getChatStyle().setColor(EnumChatFormatting.GOLD);
	        	player.addChatMessage(chatMessage);
	    		Main.proxy.bs.get(player).selectMainBlock(x, y, z);
	    	}
	    	else {
	        	ChatComponentText chatMessage = new ChatComponentText("First position [x=" + x + ", y="+ y +", z=" + (z + 1) + "]");
	        	chatMessage.getChatStyle().setColor(EnumChatFormatting.AQUA);
	        	player.addChatMessage(chatMessage);
	    		Main.proxy.bs.get(player).selectFirstPosition(x, y, z);
	    	}
    	}
        return true;
    }
	
    public boolean canItemEditBlocks()
    {
        return false;
    }
	
}
