package serb.tp.metro.client.sounds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundEventAccessorComposite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class SoundsManager {
	
	public static PositionedSoundRecord sound = null;
	
	@SideOnly(Side.CLIENT)
	public static void playPositionSound(ResourceLocation soundPath, float x, float y, float z, int playerID)
	{
		PositionedSoundRecord sound;
		if (playerID == Minecraft.getMinecraft().thePlayer.getEntityId()) {
			sound = PositionedSoundRecord.func_147673_a(soundPath);
		}
		else {
			//sound = PositionedSoundRecord.func_147675_a(soundPath, x, y, z);
			sound = new PositionedSoundRecord(soundPath, 10F, 1F, x, y, z);
		}
	    Minecraft.getMinecraft().getSoundHandler().playSound(sound);
	    SoundsManager.sound = sound;
	}
	
	@SideOnly(Side.CLIENT)
	public static void stopPositionSound()
	{	if (sound==null)
			return;
		if (Minecraft.getMinecraft().getSoundHandler().isSoundPlaying(sound))
			Minecraft.getMinecraft().getSoundHandler().stopSound(sound);
	}
	

	
}
