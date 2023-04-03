package at.nopro263.mnapixtest01.GameCreation;

import at.nopro263.mnapixtest01.Worlds;
import org.bukkit.Location;

public class GamePortalCoordinates {
    public static boolean isOPDUELS(Location location) {
        if(location.getWorld() == Worlds.pvp) {
            if(location.getBlockX() >= -6 && location.getBlockX() <= -4) {
                if(location.getBlockZ() < 14 && location.getBlockZ() > 12) {
                    return true;
                } else {
                    System.out.println("Z ist Falsch " + location.getBlockZ());
                }
            } else {
                System.out.println("X ist Falsch " + location.getBlockX());
            }
        } else {
            System.out.println("World ist Falsch");
        }

        return false;
    }


    public static boolean isSUMODUELS(Location location) {
        if(location.getWorld() == Worlds.pvp) {
            if(location.getBlockX() == 10) {
                if(location.getBlockZ() >= 7 && location.getBlockZ() <= 9) {
                    return true;
                } else {
                    System.out.println("S Z ist Falsch " + location.getBlockZ());
                }
            } else {
                System.out.println("S X ist Falsch " + location.getBlockX());
            }
        } else {
            System.out.println("World ist Falsch");
        }

        return false;
    }
}
