package me.messageofdeath.KFCPvP.Utils.Arenas;

import java.util.ArrayList;

/**
 *
 * @author messageofDEATH
 */
public class ArenaManager {
    
    protected ArrayList<Arena> arenas;
    
    public void register(Arena arena) {
        if(arenas == null)
            arenas = new ArrayList<Arena>();
        if(!contains(arena.getName()))
            arenas.add(arena);
    }
    
    public ArrayList<Arena> getArenas() {
    	if(arenas == null)
            arenas = new ArrayList<Arena>();
        return arenas;
    }
    
    public void unregister(String name) {
        arenas.remove(getArena(name));
    }
    
    public boolean contains(String name) {
        return getArena(name) != null;
    }
    
    public Arena getArena(String name) {
    	if(arenas == null)
            arenas = new ArrayList<Arena>();
        for(Arena arena : arenas)
            if(arena.getName().equalsIgnoreCase(name))
                return arena;
        return null;
    }
}
