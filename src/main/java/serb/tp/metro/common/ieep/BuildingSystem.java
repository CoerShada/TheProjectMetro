package serb.tp.metro.common.ieep;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.frame.FrameManager;
import serb.tp.metro.utils.Point;

public class BuildingSystem implements IExtendedEntityProperties {
	
	public final static String TAG = Main.modid + ":BuildSystem";
	private EntityPlayer player;
	private Point mainBlock = null;
	private Point[] selectedPositions = new Point[2];
	
	public void selectFirstPosition(int x, int y, int z) {
		selectedPositions[0] = new Point(x, y, z);
	}

	public void selectSecondPostion(int x, int y, int z) {
		selectedPositions[1] = new Point(x, y, z);
	}
	
	public void selectMainBlock(int x, int y, int z) {
		mainBlock = new Point(x, y, z);
		
	}
	
	public void clearSelection() {
		selectedPositions[0] = null;
		selectedPositions[1] = null;
		mainBlock = null;
	}
	
	public void saveFrame(World world) throws IOException {
		FrameManager.INSTANCE.createFrame(world, mainBlock, selectedPositions);

	}
	
	public void reg(EntityPlayer player) {
		player.registerExtendedProperties(TAG, new BuildingSystem());		
	}
	
	public BuildingSystem get(EntityPlayer player) {
		return player != null ? (BuildingSystem)player.getExtendedProperties(TAG) : null;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {

	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {

	}

	@Override
	public void init(Entity entity, World world) {
		if (entity instanceof EntityPlayer) {
			this.player = (EntityPlayer) entity;

		}
		
	}

}
