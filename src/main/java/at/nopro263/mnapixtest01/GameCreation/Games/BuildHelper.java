package at.nopro263.mnapixtest01.GameCreation.Games;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class BuildHelper {
    public static void fill(World w, int ix, int iy, int iz, int ixmax, int iymax, int izmax, Material m) {
        if(w == null) {
            return;
        }

        for (int x = 0; x <= ixmax; x++) {
            for (int y = 0; y <= iymax; y++) {
                for (int z = 0; z <= izmax; z++) {
                    //System.out.println((ix + x) + " " + (iy + y) + " " + (iz + z));
                    //w.getBlockAt(ix + x, iy + y, iz +z);
                    //System.out.println(l1.getBlockX() + x + " " + l1.getBlockY() + y + " " + l1.getBlockZ() + z + "   " + w.getBlockAt(l1.getBlockX() + x, l1.getBlockY() + y, l1.getBlockZ() + z).getType() + " => " + m);
                    w.getBlockAt(ix + x, iy + y, iz + z).setType(m);
                }
            }
        }

    }
}
