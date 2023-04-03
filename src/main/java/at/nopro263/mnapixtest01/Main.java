package at.nopro263.mnapixtest01;

import at.nopro263.mnapixtest01.ClientCommands.JoinServer;
import at.nopro263.mnapixtest01.GameCreation.GameGeneratorManager;
import at.nopro263.mnapixtest01.GameCreation.GameManager;
import at.nopro263.mnapixtest01.GameCreation.GameManagerListener;
import at.nopro263.mnapixtest01.GameCreation.Games.BuildHelper;
import at.nopro263.mnapixtest01.StaffCommands.STest;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static GameManager gameManager;
    private static GameGeneratorManager gameGenerationManager;

    public static Main getPlugin() {
        return instance;
    }
    public static GameManager getGameManager() {
        return gameManager;
    }
    public static GameGeneratorManager getGameGenerationManager() {
        return gameGenerationManager;
    }

    @Override
    public void onLoad() {
        instance = this;
        gameManager = new GameManager();
        gameGenerationManager = new GameGeneratorManager();
    }
    @Override
    public void onEnable() {

        Objects.requireNonNull(getCommand("test")).setExecutor(new STest());
        Objects.requireNonNull(getCommand("joinserver")).setExecutor(new JoinServer());

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new GameManagerListener(), this);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Mnapix Dimensioner loaded");

        PlayerHider.run();
        gameManager.start();

        WorldCreator wc_world_game_test = new WorldCreator("world_game_pvp");
        wc_world_game_test.generator(new VoidGenerator());
        Worlds.pvp = wc_world_game_test.createWorld();

        WorldCreator wc_world_dev = new WorldCreator("world_dev");
        wc_world_dev.generator(new VoidGenerator());
        Worlds.dev = wc_world_dev.createWorld();

        WorldCreator wc_world_lobby = new WorldCreator("world_lobby");
        wc_world_lobby.generator(new VoidGenerator());
        Worlds.lobby = wc_world_lobby.createWorld();
        Worlds.lobby.getWorldBorder().setCenter(-557, -1694); // Center at Spawn
        Worlds.lobby.getWorldBorder().setSize(250);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Dimensiones loaded");

        //BuildHelper.fill(Worlds.pvp, new Location(Worlds.pvp, 20, 20, 20), 10, 10, 10, Material.DIORITE);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        gameManager.stop();
    }
}
