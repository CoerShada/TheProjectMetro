package serb.tp.metro.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IItemLeftClickable {

    public ItemStack onItemLeftClick(ItemStack is, World world, EntityPlayer player);
}
