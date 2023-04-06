package at.nopro263.mnapixtest01.GameCreation.Games;

import at.nopro263.mnapixtest01.GameCreation.GameType;
import at.nopro263.mnapixtest01.GameCreation.Timer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Game_PVP_OPDUELS extends Game{

    private boolean hasStarted = false;
    private Timer timer;

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

        timer = new Timer();
    }

    private void tp() {
        if(players.size() >= MAXPLAYERS) {
            Location t1 = location.clone();
            t1 = t1.subtract(0,0,19);
            players.get(0).teleport(t1);
            t1 = location.clone();
            t1 = t1.add(0,0,19);
            t1.setYaw(180);
            players.get(1).teleport(t1);
        }
    }

    @Override
    public void onTick() {

        // TODO add Game Mechanics

        if(isFull() && timer.hasStarted() && !hasStarted) { // Timer not started
            timer.setTime(20*5);
            tp();
        }
        if(isFull()) {
            timer.tick();
        }
        if(timer.hasStarted()) {
            if(timer.modulo() == 0) {
                String c = "";
                switch (timer.seconds()) {
                    case 5:
                    case 4: c = ChatColor.YELLOW.toString(); break;
                    case 3: c = ChatColor.GOLD.toString(); break;
                    case 2: c = ChatColor.RED.toString(); break;
                    case 1: c = ChatColor.DARK_RED.toString(); break;
                }
                if(timer.seconds() != 0) {
                    for (Player player : players) {
                        player.sendTitle(c + timer.seconds(), "", 5, 20, 5);
                    }
                }
            }
        }
        if(timer.hasEnded()) {
            hasStarted = true;
            tp();
        }

        /*if(timer != -1) {
            if(timer % 20 == 0) {
                String c = "";
                switch (timer / 20) {
                    case 5:
                    case 4: c = ChatColor.YELLOW.toString(); break;
                    case 3: c = ChatColor.GOLD.toString(); break;
                    case 2: c = ChatColor.RED.toString(); break;
                    case 1: c = ChatColor.DARK_RED.toString(); break;
                }
                if(timer / 20 != 0) {
                    for (Player player : players) {
                        player.sendTitle(c + (timer / 20), "", 5, 20, 5);
                    }
                }
            }
            if(timer == 0) {
                Location t1 = location.clone();
                t1 = t1.subtract(0,0,19);
                players.get(0).teleport(t1);
                t1 = location.clone();
                t1 = t1.add(0,0,19);
                t1.setYaw(180);
                players.get(1).teleport(t1);
                hasStarted = true;
            }
            timer--;
        }*/

        for (Player player : players) {
            player.sendMessage("Op");
        }

        super.onTick();
    }
}
