package at.nopro263.mnapixtest01.GameCreation;

import at.nopro263.mnapixtest01.GameCreation.Games.Game;
import at.nopro263.mnapixtest01.GameCreation.Games.Game_PVP_OPDUELS;
import at.nopro263.mnapixtest01.GameCreation.Games.Game_PVP_SUMODUELS;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class GameGeneratorManager {

    private int Z_Index = 100;
    private int X_Index = 100;
    public boolean hasFreeGame(GameType type) {
        return false;
    }

    public Game generateNewGame(GameType type, World world) {
        Game game;
        switch (type) {
            case PVP_OPDUELS: {
                game = new Game_PVP_OPDUELS();
                break;
            }
            case PVP_SUMODUELS: {
                game = new Game_PVP_SUMODUELS();
                break;
            }
            default: return null;
        }

        world.getBlockAt(X_Index, 0, Z_Index).setType(Material.BEDROCK);

        game.onStart(new Location(world, X_Index + 0.5, 1, Z_Index + 0.5));
        Z_Index += 50;

        return game;
    }
}
