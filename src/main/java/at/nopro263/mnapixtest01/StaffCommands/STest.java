package at.nopro263.mnapixtest01.StaffCommands;

import at.nopro263.mnapixtest01.GameCreation.StructureLoader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Structure;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Random;

public class STest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;

        Location structureLoadPoint = player.getLocation().subtract(new Vector(23, 23, 23));
        System.out.println(structureLoadPoint);

        StructureLoader.load("minecraft", "sumoduels01", structureLoadPoint);

        return false;
    }
}
