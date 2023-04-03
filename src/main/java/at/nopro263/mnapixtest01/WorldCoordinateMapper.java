package at.nopro263.mnapixtest01;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

public class WorldCoordinateMapper {
    public static Integer[] getCoordinate(String name) {
        Integer[] res = new Integer[3];
        switch (name) {
            case "world_game_pvp": res[0] = 0; res[1] = 0; res[2] = 0; break;
            case "world_lobby": res[0] = -557; res[1] = 71; res[2] = -1694; break;
            default: res[0] = 0; res[1] = 0; res[2] = 0;
                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Attempted to get Coordinate of World \"" + name + "\", but it is not defined");
        }
        return res;
    }
    public static Location getSpawn() {
        return new Location(Worlds.lobby, -557, 71, -1694);
    }
}
