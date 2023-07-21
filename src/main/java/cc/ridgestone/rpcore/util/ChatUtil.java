package cc.ridgestone.rpcore.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static void sendMessage(Location location, int range, String message) {
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
            if (entity instanceof Player player) {
                player.sendMessage(message);
            }
        }
    }
}
