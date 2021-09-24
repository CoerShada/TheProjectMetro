package serb.tp.metro.entities;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import serb.tp.metro.Main;

public class Bullet extends EntityThrowable
{
    // Урон от попадания
    private double damage;
    public double penetration;
    private int field_145791_d = -1;
    private int field_145792_e = -1;
    private int field_145789_f = -1;
    
    public Bullet(World world)
    {
        super(world);
        this.setSize(0.1F, 0.1F);
    }
    
    public Bullet(World world, EntityLivingBase entityLivingBase, double accuracy, double damage, double penetration)
    {
        super(world, entityLivingBase);
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 20F, (float) accuracy);
        this.damage = damage;
        this.penetration = penetration;
        this.setSize(0.1F, 0.1F);
    }
    
    // Перезаписываем то, что происходит при попадании
    @Override
    protected void onImpact(MovingObjectPosition par1MovingObjectPosition)
    {
        // Если это произошло на сервере
        if (!this.worldObj.isRemote)
        {
        	inGround = false;

            // Если мы попали в Entity и этот Entity - живой объект
            if (par1MovingObjectPosition.entityHit instanceof EntityPlayer)
            {
            	EntityPlayer player = (EntityPlayer) par1MovingObjectPosition.entityHit;
            	if (player.inventory.armorItemInSlot(2)==null) {
            		par1MovingObjectPosition.entityHit.attackEntityFrom(new DamageSource("bullet"), (float) this.damage);

            	}
            	else if (player.inventory.armorItemInSlot(2)!=null && player.inventory.armorItemInSlot(2).hasTagCompound() && (player.inventory.armorItemInSlot(2).getTagCompound().getInteger("protactionClass")*(player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durability")/player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durabilityMax")))>=penetration) {
            		par1MovingObjectPosition.entityHit.attackEntityFrom(new DamageSource(""), (float) (0.5* (penetration/(player.inventory.armorItemInSlot(2).getTagCompound().getInteger("protactionClass")*(player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durability")/player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durabilityMax")))) * this.damage - player.inventory.armorItemInSlot(2).getTagCompound().getInteger("kapMod")));
            		float damageBulletproofVest = (float) (this.damage * (player.inventory.armorItemInSlot(2).getTagCompound().getInteger("protactionClass")*(player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durability")/player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durabilityMax"))));
            		if (damageBulletproofVest>player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durability"))
            		    player.inventory.armorItemInSlot(2).getTagCompound().setFloat("durability", player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durability") - damageBulletproofVest);
            		else
            		    player.inventory.armorItemInSlot(2).getTagCompound().setFloat("durability", 0);
            		this.setDead();

            	}
            	else if(player.inventory.armorItemInSlot(2)!=null && player.inventory.armorItemInSlot(2).hasTagCompound() && (player.inventory.armorItemInSlot(2).getTagCompound().getInteger("protactionClass")*(player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durability")/player.inventory.armorItemInSlot(2).getTagCompound().getFloat("durabilityMax")))<penetration) {
            		//System.out.println(penetration - player.inventory.armorItemInSlot(2).getTagCompound().getFloat("protactionClass"));
            		par1MovingObjectPosition.entityHit.attackEntityFrom(new DamageSource("bullet"), (float) this.damage);

            		//penetration -= player.inventory.armorItemInSlot(2).getTagCompound().getFloat("protactionClass");
            	}
            	else
            	{
            		par1MovingObjectPosition.entityHit.attackEntityFrom(new DamageSource("bullet"), (float) this.damage);
            	}
            	
                // Наносим ему урон и сбрасываем задержку на урон
                
                par1MovingObjectPosition.entityHit.hurtResistantTime = 0;
            }
            else if (par1MovingObjectPosition.entityHit instanceof EntityLivingBase) 
            {
                par1MovingObjectPosition.entityHit.attackEntityFrom(new DamageSource("bullet"), (float) this.damage);
                par1MovingObjectPosition.entityHit.hurtResistantTime = 0;
            }
            else 
            {
    	        Block block = this.worldObj.getBlock(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY , par1MovingObjectPosition.blockZ);
    	        

    	        if (block.getMaterial().equals(Material.rock))
    	        {
    	        	penetration-=800F;
    	        	this.worldObj.playSoundEffect(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, Main.modid +":hit_rock", 15F, 1.5F);
    	        }
    	        else if (block.getMaterial().equals(Material.iron))
    	        {
    	        	penetration-=520F;
    	        	this.worldObj.playSoundEffect(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, Main.modid +":hit_metal", 15F, 1.5F);
    	        }
    	        else if (block.getMaterial().equals(Material.sand) || block.getMaterial().equals(Material.ground) || block.getMaterial().equals(Material.clay)) 
    	        {
    	        	penetration-=320F;
    	        	//System.out.println("Попали в землю!");
    	        }
    	        else if (block.getMaterial().equals(Material.wood))
    	        {
    	        	penetration-=220F;
    	        	//System.out.println("Попали в дерево!");
    	        }
    	        else if (block.getMaterial().equals(Material.glass) || block.getMaterial().equals(Material.ice) ) 
    	        {
    	        	this.worldObj.playSoundEffect(par1MovingObjectPosition.blockX, par1MovingObjectPosition.blockY, par1MovingObjectPosition.blockZ, Main.modid +":hit_glass", 15F, 1.5F);
    	        }



    	        
    	        /*else if (!(block.getMaterial().equals(Material.air) || block.getMaterial().equals(Material.grass) || block.getMaterial().equals(Material.web) || block.getMaterial().equals(Material.leaves) || block.getMaterial().equals(Material.circuits) || block.getMaterial().equals(Material.plants) || block.getMaterial().equals(Material.vine) || block.getMaterial().equals(Material.carpet) || block.getMaterial().equals(Material.circuits) || block.getMaterial().equals(Material.plants) || block.getMaterial().equals(Material.fire) || block.getMaterial().equals(Material.circuits) || block.getMaterial().equals(Material.plants) || block.getMaterial().equals(Material.cloth) || block.getMaterial().equals(Material.gourd) || block.getMaterial().equals(Material.))) 
    	        {
    	        	System.out.println("Значительная преграда!");
    	        	setDead(); 
    	        }
    	        else if (!(block.getMaterial() == Material.glass || block.getMaterial() == Material.air || block.getMaterial() == Material.leaves || block.getMaterial() == Material.web))
    	        {
    	        	System.out.println(block);
    	        	this.setDead();  
    	        }*/
            	
            }

            if (penetration<=0) 
            {
    		setDead();
    		System.out.println("Умер");
            }
        }
    }
    
    @Override
    public void onUpdate() 
    {

	    	super.onUpdate();
	        inGround = false;
	    	
	        float f3 = 0.99F;
	

	        if (ticksExisted>100) {
	        	this.setDead();
	        }
	        
		    this.motionY += 0.025;
		    this.setPosition(this.posX, this.posY, this.posZ);
    	 
    }
}
