package serb.tp.metro.client.resources;

import net.minecraft.util.ResourceLocation;
import serb.tp.metro.Main;

public class Resources {

	public static final String[] names = new String[] {
			"mask_screen_default",//0
			"filter_screen"//1
			};
	public static final ResourceLocation[] armor = new ResourceLocation[] {
			new ResourceLocation(Main.modid, "textures/models/items/armor/masks/mask_screen/default.png"),//0
			new ResourceLocation(Main.modid, "textures/models/items/armor/masks/mask_screen/filter.png")//1
			};

	public static final ResourceLocation food_icon = new ResourceLocation(Main.modid, "textures/potions/food.png");
	public static final ResourceLocation thirst_icon = new ResourceLocation(Main.modid, "textures/potions/thirst.png");
	public static final ResourceLocation weight_icon = new ResourceLocation(Main.modid, "textures/potions/weight.png");
	public static final ResourceLocation container_texture = new ResourceLocation(Main.modid, "textures/gui/containers/container.png");
	public static final ResourceLocation creator_texture = new ResourceLocation(Main.modid, "textures/gui/containers/creator.png");

	public static final ResourceLocation container_spawner_texture = new ResourceLocation(Main.modid, "textures/gui/containerSpawner/inventory.png");
	public static final ResourceLocation container_spawner_slots_texture = new ResourceLocation(Main.modid, "textures/gui/containerSpawner/slots.png");
	public static final ResourceLocation backpack_texture = new ResourceLocation(Main.modid, "textures/gui/containers/backpack.png");
	public static final ResourceLocation chestrig_Texture = new ResourceLocation(Main.modid, "textures/gui/containers/chestrig.png");
	public static final ResourceLocation factionStartPageTexture = new ResourceLocation(Main.modid, "textures/gui/faction_page.png");
	public static final ResourceLocation inventoryTexture = new ResourceLocation(Main.modid, "textures/gui/containers/inventory.png");
	public static final ResourceLocation customizationTexture = new ResourceLocation(Main.modid, "textures/gui/containers/customization.png");
	

}