package at.nopro263.mnapixtest01;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerHider {
    public static void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    for(Player target : Bukkit.getOnlinePlayers()) {
                        if(!player.getWorld().getName().equals(target.getWorld().getName())) {
                            player.hidePlayer(Main.getPlugin(), target);
                        } else {
                            player.showPlayer(Main.getPlugin(), target);
                        }
                    }
                }
            }
        }.runTaskTimer(Main.getPlugin(), 0, 10);
    }
}
