package at.nopro263.mnapixtest01;

import org.bukkit.Location;

public class CUtils {
    public static Location getHighestElevation(Location l) {
        if(l.getWorld() == null) {
            throw new RuntimeException("getHighestElevation: l.getWorld is null");
        }
        Location res = l.clone();
        res.setY(l.getWorld().getHighestBlockYAt(l));
        return res;
    }
}
