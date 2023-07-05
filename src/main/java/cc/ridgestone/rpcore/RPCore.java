package cc.ridgestone.rpcore;

import cc.ridgestone.rpcore.config.ConfigManager;
import cc.ridgestone.rpcore.listener.PlayerListener;
import cc.ridgestone.rpcore.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class RPCore extends JavaPlugin {

    public static RPCore i;
    private FileConfiguration config;

    private File playerFile;
    private FileConfiguration playerConfig;

    private ConfigManager configManager;
    private PlayerManager playerManager;

    public void onEnable() {
        i = this;

        config = this.getConfig();
        configManager = new ConfigManager();
        playerManager = new PlayerManager();

        configManager.createConfig();
        configManager.loadConfig();
        createCustomConfig();

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    private void createCustomConfig() {
        playerFile = new File(getDataFolder(), "players.yml");
        if (!playerFile.exists()) {
            playerFile.getParentFile().mkdirs();
            saveResource("players.yml", false);
        }

        playerConfig = new YamlConfiguration();
        try {
            playerConfig.load(playerFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void savePlayerFile() {
        try {
            playerConfig.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reloadInstanceConfig() {
        config = this.getConfig();
    }

    public FileConfiguration getInstanceConfig() {
        return config;
    }

    public FileConfiguration getPlayerConfig() {
        return playerConfig;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
