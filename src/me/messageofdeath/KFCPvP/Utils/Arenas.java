package me.messageofdeath.KFCPvP.Utils;

import java.util.ArrayList;

/**
 *
 * @author messageofDEATH
 */
public class Arenas {
    
    private static ArrayList<Arena> arenas;
    
    public static void register(Arena arena) {
        if(arenas == null)
            arenas = new ArrayList<Arena>();
        if(!contains(arena.getName()))
            arenas.add(arena);
    }
    
    public static ArrayList<Arena> getArenas() {
        return arenas;
    }
    
    public static void unregister(String name) {
        arenas.remove(getArena(name));
    }
    
    public static boolean contains(String name) {
        return getArena(name) != null;
    }
    
    public static Arena getArena(String name) {
        for(Arena arena : arenas)
            if(arena.getName().equalsIgnoreCase(name))
                return arena;
        return null;
    }
}
