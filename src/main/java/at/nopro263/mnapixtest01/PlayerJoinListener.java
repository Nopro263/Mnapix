package at.nopro263.mnapixtest01;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void handlePlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().teleport(WorldCoordinateMapper.getSpawn());
        event.getPlayer().setFoodLevel(20);
    }

    @EventHandler
    public void onFeelHungry(FoodLevelChangeEvent event) { // No Hunger
        event.setFoodLevel(20);
        event.setCancelled(true);
    }
}
