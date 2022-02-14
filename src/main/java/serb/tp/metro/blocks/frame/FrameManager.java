package serb.tp.metro.blocks.frame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import serb.tp.metro.Main;
import serb.tp.metro.blocks.BlockModeled;
import serb.tp.metro.utils.Point;

public class FrameManager {

	public static final FrameManager INSTANCE = new FrameManager();
	private static final String PATH = "frames";
	private ArrayList<Block> permitted = new ArrayList<Block>();
	
		
	
	public Frame loadFrame(String name) throws FileNotFoundException {
		
		File directory = new File(".", PATH);
		if (!directory.exists()) {
			directory.mkdir();
		}

		File frameFile = new File(directory, name + ".frm");
		if (!frameFile.exists()) {
			return null;
		}
		
		
		Frame frame = new Frame();
		Scanner sc = new Scanner(frameFile);
		
		while (sc.hasNextLine()) {
			String currentLine = sc.nextLine().trim();
			if (currentLine==null || currentLine.length()==0) continue;
			
			String[] currentLineArr = currentLine.split(" ");

			int xOffset = Integer.parseInt(currentLineArr[1]);
			int yOffset = Integer.parseInt(currentLineArr[2]);
			int zOffset = Integer.parseInt(currentLineArr[3]);
			int meta = Integer.parseInt(currentLineArr[4]);
			frame.addFrameBlock(currentLineArr[0], xOffset, yOffset, zOffset, meta);
			
		}
		
		sc.close();
		return frame;
		
		
	}


	public void createFrame(World world, Point mainBlockPos, Point[] selectedPositions) throws IOException {
		File directory = new File(Minecraft.getMinecraft().mcDataDir, PATH);

		Block mainBlock = world.getBlock(mainBlockPos.getX(), mainBlockPos.getY(), mainBlockPos.getZ());
		String mainBlockName = mainBlock.getUnlocalizedName().substring(5);
		
		File file = new File(directory, mainBlockName +".frm");
		file.createNewFile();
		
		int parentX = mainBlockPos.getX();
		int parentY = mainBlockPos.getY();
		int parentZ = mainBlockPos.getZ();
		
		int temp;
		
		int x1 = selectedPositions[0].getX();
		int x2 = selectedPositions[1].getX();
		
		if (x1>x2) {
			temp = x1;
			x1 = x2;
			x2 = temp;
		}
		
		int y1 = selectedPositions[0].getY();
		int y2 = selectedPositions[1].getY();
		if (y1>y2) {
			temp = y1;
			y1 = y2;
			y2 = temp;
		}
		
		int z1 = selectedPositions[0].getZ();
		int z2 = selectedPositions[1].getZ();
		
		if (z1>z2) {
			temp = z1;
			z1 = z2;
			z2 = temp;
		}
		
        try(FileWriter writer = new FileWriter(file, false))
        {
			for (int x = x1; x<=x2; x++) {
				for (int y = y1; y<=y2; y++) {
					for (int z = z1; z<=z2; z++) {
						if (world.getBlock(x, y, z).getMaterial() == Material.air) continue;
						String text = world.getBlock(x, y, z).getUnlocalizedName().substring(5) + " ";
						text+= String.valueOf(x - parentX) + " ";
						text+= String.valueOf(y - parentY) + " ";
						text+= String.valueOf(z - parentZ) + " ";
						text+= String.valueOf(world.getBlockMetadata(x, y, z)) + "\n";
						writer.write(text);
					}
				}
				
			}
			writer.flush();
			BlockModeled parent = (BlockModeled) world.getBlock(parentX, parentY, parentZ);
			parent.bindFrame(parent.getUnlocalizedName());
			
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
	}
	

	
	
}
