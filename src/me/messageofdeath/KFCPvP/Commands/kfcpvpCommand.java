package me.messageofdeath.KFCPvP.Commands;

import java.util.ArrayList;

import me.messageofdeath.Commands.NewCommandSystem.Command;
import me.messageofdeath.Commands.NewCommandSystem.IssuedCommand;
import me.messageofdeath.Commands.NewCommandSystem.MessageCommand;
import me.messageofdeath.Commands.NewCommandSystem.Type;
import me.messageofdeath.KFCPvP.Utils.PageLists.Option;
import me.messageofdeath.KFCPvP.Utils.PageLists.PageList;

public class kfcpvpCommand extends MessageCommand {

	private boolean helpSetup = false;
	private PageList help;
	
	@Override
	@Command(name = "kfcpvp", permission = "noPerm", description = "")
	public void issue(IssuedCommand cmd, Type type) {
		if(!this.helpSetup) {
			setupHelp();
			this.helpSetup = true;
		}
		
		if(cmd.getLength() == 0)
			help(cmd, 1);
		else if(cmd.getLength() == 1)
			help(cmd, 1);
		
		else
			help(cmd, 1);
	}
	
	private void help(IssuedCommand cmd, int page) {
		
	}
	
	private void setupHelp() {
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new Option("", "noPerm"));
	}
}
