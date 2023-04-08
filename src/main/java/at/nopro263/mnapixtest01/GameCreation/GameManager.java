package at.nopro263.mnapixtest01.GameCreation;

import at.nopro263.mnapixtest01.GameCreation.Games.Game;
import at.nopro263.mnapixtest01.Main;
import at.nopro263.mnapixtest01.Worlds;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class GameManager {

    private List<Game> games = new ArrayList<>();

    public void joinGame(Player player, GameType type) {
        for(Game game : games) {
            if(game.getType() == type && !game.isFull() && !game.ended) {
                game.onPlayerJoin(player);
                return;
            }
        }
        Game game = Main.getGameGenerationManager().generateNewGame(type, Worlds.pvp);
        game.onPlayerJoin(player);
        games.add(game);
    }

    public void tick() {
        for (Game game : games) {
            game.onTick();
        }
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                tick();
            }
        }.runTaskTimer(Main.getPlugin(), 1, 1);

    }

    public Game getGame(Player player) {
        for(Game game : games) {
            if(game.hasPlayer(player)) {
                return game;
            }
        }
        return null;
    }

    public void kill(Player player) {
        for(Game game : games) {
            if(game.hasPlayer(player)) {
                game.onPlayerDeath(player);
            }
        }
    }

    public void leaveGame(Player player, boolean shouldTP) {
        for (Game game : games) {
            if(game.hasPlayer(player)) {
                game.onPlayerLeave(player, shouldTP);
            }
        }
    }

    public void removeGame(Game game) {
        game.ended = true;
    }

    public void stop() {
        for (Game game : games) {
            game.onEnd(true);
        }
    }
}
