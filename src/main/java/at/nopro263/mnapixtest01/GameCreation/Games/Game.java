package at.nopro263.mnapixtest01.GameCreation.Games;

import at.nopro263.mnapixtest01.GameCreation.GameManager;
import at.nopro263.mnapixtest01.GameCreation.GameType;
import at.nopro263.mnapixtest01.GameCreation.StructureLoader;
import at.nopro263.mnapixtest01.Main;
import at.nopro263.mnapixtest01.WorldCoordinateMapper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

    public GameType getType() {
        return GameType.NONE;
    }

    protected List<Player> players = new ArrayList<>();
    protected List<Player> alivePlayers = new ArrayList<>();
    protected Location location;
    public boolean ended = false;

    protected int MAXPLAYERS = 100;

    public void onStart(Location location) {
        this.location = location;
    }

    public void load(String namespace, String name) {
        Location structureLoadPoint = location.clone();

        structureLoadPoint = structureLoadPoint.subtract(new Vector(23, 24, 23));

        StructureLoader.load(namespace, name, structureLoadPoint);
    }
    public void load(String name) {
        load("minecraft", name);
    }

    public void onPlayerJoin(Player player) {
        players.add(player);
        alivePlayers.add(player);
        player.teleport(location);
    }

    public void onPlayerDeath(Player player) {
        alivePlayers.remove(player);
    }

    public void onTick() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            for(Player target : Bukkit.getOnlinePlayers()) {
                if(player.getWorld().getName().equals(target.getWorld().getName()) && player.getWorld().getName().equals("world_game_pvp")) {
                    if(Main.getGameManager().getGame(player) != Main.getGameManager().getGame(target)) {
                        player.hidePlayer(Main.getPlugin(), target);
                    } else {
                        player.showPlayer(Main.getPlugin(), target);
                    }
                }
            }
        }
    }

    public void onPlayerLeave(Player player, boolean shouldTP) {
        players.remove(player);
        alivePlayers.remove(player);
        if(shouldTP) {
            player.teleport(WorldCoordinateMapper.getSpawn());
        }
        if(players.size() == 0) {
            onEnd(false);
        }
    }

    public void onEnd(boolean shouldTP) {
        int SIDE = 50;
        BuildHelper.fill(location.getWorld(), location.getBlockX()-SIDE/2, location.getBlockY()-SIDE/2, location.getBlockZ()-SIDE/2, SIDE, SIDE, SIDE, Material.AIR);

        if(shouldTP) {
            for(Player player : players) {
                player.teleport(WorldCoordinateMapper.getSpawn());
            }
        }
        //BuildHelper.fill(location.getWorld(), location.getBlockX()+SIDE/2, location.getBlockY()+SIDE/2, location.getBlockZ()+SIDE/2, SIDE*2, SIDE*2, SIDE*2, Material.AIR);
        Main.getGameManager().removeGame(this);
    }

    public boolean isFull() {
        return players.size() >= MAXPLAYERS;
    }
    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }
}
