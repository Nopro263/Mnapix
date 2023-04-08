package at.nopro263.mnapixtest01.GameCreation.Games;

import at.nopro263.mnapixtest01.GameCreation.GameType;
import at.nopro263.mnapixtest01.GameCreation.Timer;
import at.nopro263.mnapixtest01.Worlds;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
        if(alivePlayers.size() >= MAXPLAYERS) {
            Location t1 = location.clone();
            //t1 = t1.subtract(0,0,19);
            alivePlayers.get(0).teleport(t1);
            t1 = location.clone();
            //t1 = t1.add(0,0,19);
            t1.setYaw(180);
            alivePlayers.get(1).teleport(t1);
        }
    }

    private void equip(Player player) {
        ItemStack s = new ItemStack(Material.DIAMOND_HELMET);
        s.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        player.getInventory().setHelmet(s);

        s = new ItemStack(Material.DIAMOND_CHESTPLATE);
        s.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        player.getInventory().setChestplate(s);

        s = new ItemStack(Material.DIAMOND_LEGGINGS);
        s.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        player.getInventory().setLeggings(s);

        s = new ItemStack(Material.DIAMOND_BOOTS);
        s.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        player.getInventory().setBoots(s);

        s = new ItemStack(Material.DIAMOND_AXE);
        s.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        player.getInventory().setItem(0, s);
    }

    @Override
    public void onTick() {

        if(!isFull() && timer.hasStarted() || !isFull() && hasStarted) {
            hasStarted = false;
            timer = new Timer();
        }

        if(isFull() && !timer.hasStarted() && !hasStarted) { // Timer not started

            for(Player player : players) {
                equip(player);
            }

            timer.setTime(20 * 5);
            tp();
        }

        if(isFull() && timer.hasStarted() && !hasStarted) {
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
                    for (Player player : alivePlayers) {
                        player.sendTitle(c + timer.seconds(), "", 5, 20, 5);
                    }
                }
            }
        }
        if(isFull()) {
            timer.tick();
        }
        if(isFull() && timer.hasEnded() && !hasStarted) {
            hasStarted = true;
            tp();
            for(Player player : alivePlayers) {
                player.setInvulnerable(false);
            }
        }

        for (Player player : alivePlayers) {
            player.sendMessage("Op");
        }

        if(alivePlayers.size() == 1 && isFull()) {
            alivePlayers.get(0).sendMessage("Du hast gewonnen");
            for(Player player : players) {
                onPlayerLeave(player, Worlds.pvp, false);
            }
            players.clear();
            onEnd(false);
        }

        super.onTick();
    }
    @Override
    public void onPlayerJoin(Player player) {
        player.setInvulnerable(true);
        super.onPlayerJoin(player);
    }

    @Override
    public void onPlayerDeath(Player player) {

        player.sendMessage("You died");
        super.onPlayerDeath(player);
    }
}
