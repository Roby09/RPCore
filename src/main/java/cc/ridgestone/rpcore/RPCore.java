package cc.ridgestone.rpcore;

import cc.ridgestone.rpcore.command.*;
import cc.ridgestone.rpcore.command.chat.*;
import cc.ridgestone.rpcore.config.ConfigManager;
import cc.ridgestone.rpcore.listener.BioListener;
import cc.ridgestone.rpcore.listener.CharacterDeleteListener;
import cc.ridgestone.rpcore.listener.gui.CharacterMenuListener;
import cc.ridgestone.rpcore.listener.ChatListener;
import cc.ridgestone.rpcore.listener.gui.EmoteMenuListener;
import cc.ridgestone.rpcore.listener.gui.MainMenuListener;
import cc.ridgestone.rpcore.listener.PlayerListener;
import cc.ridgestone.rpcore.listener.gui.SettingsMenuListener;
import cc.ridgestone.rpcore.placeholder.RPCorePlaceholders;
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
        Bukkit.getPluginManager().registerEvents(new MainMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new CharacterMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new SettingsMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new EmoteMenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new BioListener(), this);
        Bukkit.getPluginManager().registerEvents(new CharacterDeleteListener(), this);

        getCommand("card").setExecutor(new CardCommand());
        getCommand("character").setExecutor(new CharacterCommand());
        getCommand("roll").setExecutor(new RollCommand());

        getCommand("setrole").setExecutor(new SetroleCommand());
        getCommand("setage").setExecutor(new SetageCommand());
        getCommand("rpreload").setExecutor(new RpReloadCommand());

        getCommand("shout").setExecutor(new ShoutCommand());
        getCommand("whisper").setExecutor(new WhisperCommand());
        getCommand("quiet").setExecutor(new QuietCommand());
        getCommand("ooc").setExecutor(new OocCommand());
        getCommand("looc").setExecutor(new LoocCommand());
        getCommand("wooc").setExecutor(new WoocCommand());
        //wanted this removed
        //getCommand("me").setExecutor(new MeCommand());
        getCommand("rp").setExecutor(new DefaultChatCommand());

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new RPCorePlaceholders(this).register();
        }
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

    public void reloadPlayerConfig() {
        try {
            playerConfig.load(playerFile);
        } catch (IOException | InvalidConfigurationException e) {
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

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
