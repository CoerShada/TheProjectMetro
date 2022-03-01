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

public class CommandSyncDatabase extends CommandBase{

	public static final String 
	NAME = "synchronize",//Имя команды, используется при вызове.
	ALIAS = "sync",//Допустимая вариация команды, таких может быть несколько.
	USAGE = "/synchronize";//Шаблон вызова, выводится при выбрасывании WrongUsageException.
	
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
    	if (commandSender instanceof MinecraftServer) return true;
		return commandSender instanceof EntityPlayer ? MinecraftServer.getServer().getConfigurationManager().func_152596_g(((EntityPlayer) commandSender).getGameProfile()) : false;    	
    }
    
	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		try {
			Main.proxyCommon.loadOrUpdateContent();
			ChatComponentTranslation chatMessage = new ChatComponentTranslation("database.updated");
			chatMessage.getChatStyle().setColor(EnumChatFormatting.GREEN);
			sender.addChatMessage(chatMessage);
		}
		catch(Exception e) {
			ChatComponentTranslation chatMessage = new ChatComponentTranslation("database.error_updated");
			chatMessage.getChatStyle().setColor(EnumChatFormatting.RED);
			sender.addChatMessage(chatMessage);
			System.err.println(e);
		}
		
	}

}
