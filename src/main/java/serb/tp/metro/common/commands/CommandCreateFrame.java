package serb.tp.metro.common.commands;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import serb.tp.metro.DebugMessage;
import serb.tp.metro.Main;
import serb.tp.metro.common.CommonProxy;

public class CommandCreateFrame extends CommandBase{

	public static final String 
	NAME = "create frame",//Имя команды, используется при вызове.
	ALIAS = "cf",//Допустимая вариация команды, таких может быть несколько.
	USAGE = "/create frame";//Шаблон вызова, выводится при выбрасывании WrongUsageException.
	
	@Override
	public String getCommandName() {
		return this.NAME;
	}

	@Override
	public String getCommandUsage(ICommandSender p_71518_1_) {

		return this.USAGE;
	}
	
    @Override 
    public List<String> getCommandAliases() { 
    	
        List<String> aliases = new ArrayList<String>();
        
        aliases.add(this.ALIAS);
    	
        return aliases;
    } 

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
    	//Только опам или если в мире активны читы.
    	if (commandSender instanceof MinecraftServer) return false;
		return commandSender instanceof EntityPlayer ? MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayer) commandSender).getGameProfile()) : false;    	
    }
    
	@Override
	public void processCommand(ICommandSender sender, String[] args) {

		try {
			EntityPlayer player = (EntityPlayer) sender;
			Main.proxyCommon.bs.get(player).saveFrame(player.worldObj);
			ChatComponentTranslation chatMessage = new ChatComponentTranslation("frames.createSuccessful");
			chatMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
			sender.addChatMessage(chatMessage);
		}
		catch(Exception e) {
			ChatComponentTranslation chatMessage = new ChatComponentTranslation("frames.createError");
			chatMessage.getChatStyle().setColor(EnumChatFormatting.RED);
			sender.addChatMessage(chatMessage);
			e.printStackTrace();
		}
		
	}

}
