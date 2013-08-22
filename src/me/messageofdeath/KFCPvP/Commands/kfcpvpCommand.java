package me.messageofdeath.KFCPvP.Commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;

import me.messageofdeath.Commands.NewCommandSystem.Command;
import me.messageofdeath.Commands.NewCommandSystem.IssuedCommand;
import me.messageofdeath.Commands.NewCommandSystem.MessageCommand;
import me.messageofdeath.Commands.NewCommandSystem.Type;
import me.messageofdeath.KFCPvP.Utils.PageLists.Option;
import me.messageofdeath.KFCPvP.Utils.PageLists.PageList;
import me.messageofdeath.Logging.Messenger;

public class kfcpvpCommand extends MessageCommand {

	private boolean helpSetup = false;
	private PageList help;
	
	@Override
	@Command(name = "kfcpvp", permission = ""/*TODO Add permission to admin command*/)
	public void issue(IssuedCommand cmd, Type type) {
		if(messenger == null)
			messenger = new Messenger(ChatColor.translateAlternateColorCodes('&', "&0[&eKFC&f-&4P&fv&4P&0] &f"));
		if(!this.helpSetup) {
			setupHelp();
			this.helpSetup = true;
		}
		
		if(cmd.getLength() == 0)
			help(cmd, 1);
		else if(cmd.getLength() == 1)
			if(cmd.getArg(0).equalsIgnoreCase("help"))
				help(cmd, 1);
			else
				help(cmd, 1);
		else if(cmd.getLength() == 2)
			if(cmd.getArg(0).equalsIgnoreCase("help"))
				if(cmd.isNumeric(1))
					help(cmd, Integer.parseInt(cmd.getArg(1)));
				else
					error(cmd, "Wrong input type! Must use an Integer!");
			else
				help(cmd, 1);
		else
			help(cmd, 1);
	}
	
	private void help(IssuedCommand cmd, int page) {
		page = help.checkPage(cmd.getSender(), page);
		ArrayList<String> texts = help.getOptions(cmd.getSender(), page);
		super.msgPrefix(cmd, "Available Commands (Page "+(page + 1)+"/"+(help.getTotalPages(cmd.getSender()))+"):");
		if(texts.isEmpty()) {
			texts.add(ChatColor.DARK_GRAY + "   - " + ChatColor.GRAY + "There are no available commands.");
			return;
		}
		for(String text : texts)
			super.msg(cmd, text);
	}
	
	private void setupHelp() {
		ArrayList<Option> options = new ArrayList<Option>();
		String bull = ChatColor.DARK_GRAY + "   - " + ChatColor.GRAY;

		options.add(new Option(bull + "", "noPerm"));
		
		this.help = new PageList(options, 5);
	}
}
