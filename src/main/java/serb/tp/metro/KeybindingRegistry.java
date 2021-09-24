package serb.tp.metro;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import serb.tp.metro.client.Type;

public class KeybindingRegistry {
    private static final String catergory = Type.getTranslate("category.Metro");
    private static final String catergory1 = Type.getTranslate("category.General");


    public static final KeyBinding
    		
    	KEY_RELOAD = new KeyBinding(Type.getTranslate("key.reload"), Keyboard.KEY_R, catergory),
    	KEY_FILTER  = new KeyBinding(Type.getTranslate("key.filter"), Keyboard.KEY_C, catergory),
    	KEY_BACKPACK  = new KeyBinding(Type.getTranslate("key.backpack"), Keyboard.KEY_I, catergory),
    	KEY_PICKUP = new KeyBinding(Type.getTranslate("key.pickup"), Keyboard.KEY_F, catergory),
    	KEY_FMTSM = new KeyBinding(Type.getTranslate("key.fmtsm"), Keyboard.KEY_B, catergory),
    	KEY_MODIFY = new KeyBinding(Type.getTranslate("key.modify"), Keyboard.KEY_X, catergory),
    	KEY_SAFETY = new KeyBinding(Type.getTranslate("key.safety"), Keyboard.KEY_P, catergory),
        KEY_VISOR   = new KeyBinding(Type.getTranslate("key.visor"), Keyboard.KEY_V, catergory),
        KEY_FACTIONS = new KeyBinding(Type.getTranslate("key.factions"), Keyboard.KEY_G, catergory);
    
    	
    		
    	
    public static void register()
    {
        setRegister(KEY_RELOAD);
        setRegister(KEY_FILTER);
        setRegister(KEY_BACKPACK);
        setRegister(KEY_MODIFY);
        setRegister(KEY_PICKUP);
        setRegister(KEY_VISOR);
        setRegister(KEY_FMTSM);
        setRegister(KEY_SAFETY);
        setRegister(KEY_FACTIONS);
    }

    private static void setRegister(KeyBinding binding)
    {
        ClientRegistry.registerKeyBinding(binding);
    }
    
    
}
