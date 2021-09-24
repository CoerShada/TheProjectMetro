package serb.tp.metro.entities.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import serb.tp.metro.Main;

public class PropertiesRegistry {
	
	//Жажда
	public static final IAttribute THIRSTY_MAX = new RangedAttribute(Main.modid + ".thirsty", 100.0D, 0.0D, 110.0D).setShouldWatch(true);
	public static final IAttribute STAMINA_MAX = new RangedAttribute(Main.modid + ".stamina", 110.0D, 0.0D, 120.0D).setShouldWatch(true);
	//public static final IAttribute RADIATION_MAX = new RangedAttribute(Adtime.modid + ".radiation", 20.0D, 0.0D, 30.0D).setShouldWatch(true);

	public static final int THIRSTY = 20;
	public static final int RADIATION = 21;
	public static final int WEIGHT = 22;
	public static final int STAMINA = 23;	
	public static final int TEMPERATURE = 24;
	
	@SubscribeEvent
	public void onPlayerConstructing(EntityEvent.EntityConstructing event) {
		
		if (event.entity instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) event.entity;		
					
			//Жажда
			player.getAttributeMap().registerAttribute(this.THIRSTY_MAX);//Регистрация атрибута "Жажда".
			//Регистрация нового объекта DataWatcher для управления значением "Жажды".	
			//Базовое значение равно значению атрибута.
			player.getDataWatcher().addObject(this.THIRSTY, (float) this.THIRSTY_MAX.getDefaultValue());			
			player.getDataWatcher().addObject(this.STAMINA, (float) this.STAMINA_MAX.getDefaultValue());
			//Запас монет
			//Регистрация нового объекта DataWatcher для управления значением радиации.			
			//Базовое значение - 0.
			//player.getAttributeMap().registerAttribute(this.RADIATION_MAX);
			player.getDataWatcher().addObject(this.RADIATION, 0F);
			player.getDataWatcher().addObject(this.WEIGHT, 0F);
		}		
	}
}
