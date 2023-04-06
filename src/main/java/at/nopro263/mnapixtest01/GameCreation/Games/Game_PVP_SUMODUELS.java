package at.nopro263.mnapixtest01.GameCreation.Games;

import at.nopro263.mnapixtest01.GameCreation.GameType;
import at.nopro263.mnapixtest01.GameCreation.StructureLoader;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Structure;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Game_PVP_SUMODUELS extends Game{

    @Override
    public GameType getType() {
        return GameType.PVP_SUMODUELS;
    }

    @Override
    public void onStart(Location location) {

        MAXPLAYERS = 1;

        //location.getWorld().getBlockAt(location).getRelative(0, 2, 0).setType(Material.AMETHYST_BLOCK);


        super.onStart(location);
        super.load("sumoduels01");
    }

    @Override
    public void onTick() {

        //TODO add Game Mechanics

        for (Player player : players) {
            player.sendMessage("Sumo");
        }

        super.onTick();
    }

}
