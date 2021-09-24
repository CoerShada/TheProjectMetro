package serb.tp.metro.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import serb.tp.metro.Main;
import serb.tp.metro.creativetabs.LoadTabs;

public class BuildersBlock extends Block
{

	String texture;
    public BuildersBlock(Material material, String name, float light)
    {
    	super(material);
    	this.setBlockTextureName(Main.modid +":builders/"+name);
    	this.setBlockUnbreakable();
    	//this.setStepSound(sound);
    	this.setBlockName(name);
    	this.setResistance(6000000.0F);
    	this.setLightLevel(light);
    	this.setCreativeTab(LoadTabs.buildersBlocks);
    	GameRegistry.registerBlock(this, name);
    }
    
    @Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
		if (!world.isRemote && Main.debug) {
			File file = new File("E://adtime", "fileConstructor.txt");
			if (!file.exists())
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			
	        try(FileWriter writer = new FileWriter("E://adtime//fileConstructor.txt", true))
	        {
				String stringX = String.valueOf(0+x);
				if (0+x>0)
					stringX = "+" + stringX;
				else if (x==0) 
					stringX="";
				
				String stringY = String.valueOf(y-4);
				if (0+y>4)
					stringY = "+" + stringY;
				else if (y==4) 
					stringY="";
				
				String stringZ = String.valueOf(0+z);
				if (0+z>0)
					stringZ = "+" + stringZ;
				else if (z==0) 
					stringZ="";
				
	            String text = "setFrameBlock(world, x" + stringX + ", y"+stringY+", z" + stringZ + ", LoadBuildersBlocks."+this.getUnlocalizedName().substring(5) +");\n";
	            writer.write(text);

	            writer.flush();
	        }
	        catch(IOException ex){
	             
	            System.out.println(ex.getMessage());
	        } 

		}
		
	}
}
