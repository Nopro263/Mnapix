package at.nopro263.mnapixtest01.GameCreation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.structure.Structure;

import java.util.Random;

public class StructureLoader {
    public static void load(String namespace, String name, Location l) {
        Structure s = Bukkit.getStructureManager().loadStructure(new NamespacedKey(namespace,name));
        s.place(l, false, StructureRotation.NONE, Mirror.NONE, 0, 1, new Random());
    }
}
