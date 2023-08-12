package cc.ridgestone.rpcore.placeholder;

import cc.ridgestone.rpcore.RPCore;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

public class RPCorePlaceholders extends PlaceholderExpansion {

    private final Plugin plugin;

    public RPCorePlaceholders(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getAuthor() {
        return "Quin";
    }

    @Override
    public String getIdentifier() {
        return "rpcore";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()) == null)
            return "CREATING CHARACTER...";
        if (RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCurrentCharacter() == null)
            return "";

        if(params.equalsIgnoreCase("name")){
            return RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCurrentCharacter().getName();
        }

        if(params.equalsIgnoreCase("age")){
            return String.valueOf(RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCurrentCharacter().getAge());
        }

        if(params.equalsIgnoreCase("bio")){
            return RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCurrentCharacter().getBio();
        }

        if(params.equalsIgnoreCase("role")){
            return RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCurrentCharacter().getRole();
        }

        return null; // Placeholder is unknown by the Expansion
    }

}
