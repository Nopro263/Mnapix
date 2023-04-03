package at.nopro263.mnapixtest01.GameCreation;

import at.nopro263.mnapixtest01.Main;
import at.nopro263.mnapixtest01.WorldCoordinateMapper;
import at.nopro263.mnapixtest01.Worlds;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class GameManagerListener implements Listener {

    @EventHandler
    public void OnPlayerLeaveHandler(PlayerQuitEvent event) {
        Main.getGameManager().leaveGame(event.getPlayer(), true);
    }
    @EventHandler
    public void EnterPortalHandler(PlayerMoveEvent event) {
        if(event.getTo() == null) {
            return;
        }
        World w = event.getTo().getWorld();
        if(w == null) {
            return;
        }

        if(w.getBlockAt(event.getTo()).getType() == Material.NETHER_PORTAL ) {
            GameType gameType = GameType.NONE;
            if(w == Worlds.pvp) {

                if(GamePortalCoordinates.isOPDUELS(event.getTo())) {
                    event.getPlayer().sendMessage("Going to Op-Duels");
                    gameType = GameType.PVP_OPDUELS;
                } else if(GamePortalCoordinates.isSUMODUELS(event.getTo())) {
                    event.getPlayer().sendMessage("Going to Sumo-Duels");
                    gameType = GameType.PVP_SUMODUELS;
                }

            }
            if(gameType != GameType.NONE) {
                event.setCancelled(true);
                GameType finalGameType = gameType;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Main.getGameManager().leaveGame(event.getPlayer(), false);
                        Main.getGameManager().joinGame(event.getPlayer(), finalGameType);
                        //event.getPlayer().teleport(WorldCoordinateMapper.getSpawn());
                    }
                }.runTaskLater(Main.getPlugin(), 1);
            }
        }
    }
}
