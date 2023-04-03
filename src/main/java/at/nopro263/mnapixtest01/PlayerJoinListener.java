package at.nopro263.mnapixtest01;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().teleport(WorldCoordinateMapper.getSpawn());
    }

}
