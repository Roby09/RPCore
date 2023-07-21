package cc.ridgestone.rpcore.config;

import cc.ridgestone.rpcore.RPCore;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    public void createConfig() {
        FileConfiguration config = RPCore.i.getInstanceConfig();

        for(Variable variable : Variable.values()) {
            config.addDefault(variable.toString(), variable.getValue());
            if(config.get(variable.toString()) == null)
                config.set(variable.toString(), variable.getValue());
        }
        config.options().copyDefaults(true);
        RPCore.i.saveConfig();
    }

    public void loadConfig() {
        RPCore.i.reloadConfig();
        RPCore.i.reloadInstanceConfig();
        FileConfiguration config = RPCore.i.getInstanceConfig();

        for(Variable variable : Variable.values()) {
            variable.setVariable(config.getString(variable.toString()));
        }
    }
}
