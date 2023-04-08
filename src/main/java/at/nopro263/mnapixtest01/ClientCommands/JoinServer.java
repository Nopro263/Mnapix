package at.nopro263.mnapixtest01.ClientCommands;

import at.nopro263.mnapixtest01.Main;
import at.nopro263.mnapixtest01.WorldCoordinateMapper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Objects;

public class JoinServer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED+ "Only Players can do this");
            return false;
        }

        if(strings.length >= 1) {
            if(warp((Player) commandSender, "world_game_" + strings[0])) {
                commandSender.sendMessage("Woosh!");
            } else {
                commandSender.sendMessage( ChatColor.RED + "Server not found");
            }
        }

        if(Objects.equals(s, "hub")) {
            warp((Player) commandSender, "world_lobby");
        } else if(Objects.equals(s, "dev")) {
            warp((Player) commandSender, "world_dev");
        }

        return false;
    }

    private boolean warp(Player player, String game) {
        if(Bukkit.getServer().getWorld(game) == null) {
            return false;
        } else {
            Main.getGameManager().leaveGame(player, null);
            Integer[] c = WorldCoordinateMapper.getCoordinate(game);
            player.teleport(new Location(Bukkit.getServer().getWorld(game), c[0], c[1], c[2]));
            customWorldCommands(player, game);
            return true;
        }
    }
    public static void customWorldCommands(Player player, String game) {
        player.setGameMode(GameMode.ADVENTURE);
        player.setInvulnerable(false);
        player.setAllowFlight(false);
        player.setFallDistance(0);
        player.setFireTicks(0);
        player.setVisualFire(false);
        player.setVelocity(new Vector(0,0,0));
        switch (game) {
            case "world_dev": {
                player.setGameMode(GameMode.SPECTATOR);
                player.setAllowFlight(true);
            }
        }
    }
}
