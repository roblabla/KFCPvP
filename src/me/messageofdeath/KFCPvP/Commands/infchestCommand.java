package me.messageofdeath.KFCPvP.Commands;

import me.messageofdeath.Commands.NewCommandSystem.Command;
import me.messageofdeath.Commands.NewCommandSystem.IssuedCommand;
import me.messageofdeath.Commands.NewCommandSystem.MessageCommand;
import me.messageofdeath.Commands.NewCommandSystem.Type;

public class infchestCommand extends MessageCommand {

	@Override
	@Command(name = "infchest", permission = ""/*TODO Add permission for admin command*/)
	public void issue(IssuedCommand arg0, Type arg1) {
		// /infchest <item> <amount> <point value>
		// Slap a chest
	}
}
