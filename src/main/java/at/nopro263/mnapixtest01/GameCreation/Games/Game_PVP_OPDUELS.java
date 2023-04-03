package at.nopro263.mnapixtest01.GameCreation.Games;

import at.nopro263.mnapixtest01.GameCreation.GameType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Game_PVP_OPDUELS extends Game{


    @Override
    public GameType getType() {
        return GameType.PVP_OPDUELS;
    }

    @Override
    public void onStart(Location location) {

        MAXPLAYERS = 2;

        //location.getWorld().getBlockAt(location).getRelative(0, 2, 0).setType(Material.GOLD_BLOCK);

        super.onStart(location);
        super.load("opduels01");
    }

    @Override
    public void onTick() {

        // TODO add Game Mechanics

        for (Player player : players) {
            player.sendMessage("Op");
        }

        super.onTick();
    }
}
