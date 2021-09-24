package serb.tp.metro.blocks.tiles;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;

public class TileEntityPlayersBlock extends TileEntity{
	
	private Block prevBlock;
	private int prevBlockMeta;
	
	public TileEntityPlayersBlock() {
		
	}
	
	public TileEntityPlayersBlock(Block prevBlock, int prevBlockMeta) {
		this.prevBlock = prevBlock;
		this.prevBlockMeta = prevBlockMeta;
	}

	public Block getPrevBlock() {
		return this.prevBlock;
	}
	
	public int getPrevBlockMeta() {
		return this.prevBlockMeta;
	}
	

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("prevBlockId", Block.getIdFromBlock(this.prevBlock));
		nbt.setInteger("prevBlockMeta", this.prevBlockMeta);

	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.prevBlock = Block.getBlockById(nbt.getInteger("prevBlockId"));
		this.prevBlockMeta = nbt.getInteger("prevBlockMeta");
	}
	
	
	
}
