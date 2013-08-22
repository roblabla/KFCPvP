package me.messageofdeath.KFCPvP.Timer;

import me.messageofdeath.KFCPvP.KFCPvP;
import me.messageofdeath.KFCPvP.Utils.Arenas.Arena;

public class PluginTimer implements Runnable {

	@Override
	public void run() {
		DatabaseTimer.run();
		
		if(!KFCPvP.getInstance().getArenaManager().getArenas().isEmpty()) // If there is an arena loaded
			for(Arena arena : KFCPvP.getInstance().getArenaManager().getArenas()) // Loop through all arenas
				if(!arena.getPlayers().isEmpty()) { // If it has players run the timer
					ArenaTimer.run();
					break;// Then stop to prevent multiple seconds from being lost
				}
	}
}
