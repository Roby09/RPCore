package cc.ridgestone.rpcore.util;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.config.Variable;
import cc.ridgestone.rpcore.player.RPPlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class ChatUtil {

    public static void sendInCharacterMessage(Player player, int range, String message) {
        String chatFormat = ChatColor.translateAlternateColorCodes('&', Variable.CHAT_FORMAT.getValue().replace("%player%", player.getName())).replace("%message%", message);
        String finalFormat = PlaceholderAPI.setPlaceholders(player, chatFormat);

        for (Entity entity : player.getLocation().getWorld().getNearbyEntities(player.getLocation(), range, range, range)) {
            if (entity instanceof Player _player) {
                _player.sendMessage(finalFormat);
            }
        }
    }

    public static void sendInCharacterMessageToAll(Player player, String message) {
        String chatFormat = ChatColor.translateAlternateColorCodes('&', Variable.CHAT_FORMAT.getValue().replace("%player%", player.getName())).replace("%message%", message);
        String finalFormat = PlaceholderAPI.setPlaceholders(player, chatFormat);

        for (Player _player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(finalFormat);
        }
    }

    public static void sendMessage(Location location, int range, String message) {
        for (Entity entity : location.getWorld().getNearbyEntities(location, range, range, range)) {
            if (entity instanceof Player player) {
                player.sendMessage(message);
            }
        }
    }

    public static void sendEmote(Player player, int range, String message) {
        ChatColor emoteColor = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getEmoteColor();

        String format =  ChatColor.translateAlternateColorCodes('&', Variable.EMOTE_FORMAT.getValue().replace("%player%", player.getName())).replace("%message%", emoteColor + "" + ChatColor.ITALIC + message);
        String finalFormat = PlaceholderAPI.setPlaceholders(player, format);
        for (Entity entity : player.getLocation().getWorld().getNearbyEntities(player.getLocation(), range, range, range)) {
            if (entity instanceof Player _player) {
                _player.sendMessage(finalFormat);
            }
        }
    }

    public static void sendOoc(Player player, int range, String message) {
        String format = ChatColor.translateAlternateColorCodes('&', Variable.OOC_FORMAT.getValue().replace("%player%", player.getName())).replace("%message%", message);
        String finalFormat = PlaceholderAPI.setPlaceholders(player, format);
        for (Entity entity : player.getLocation().getWorld().getNearbyEntities(player.getLocation(), range, range, range)) {
            if (entity instanceof Player _player) {
                RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(_player.getUniqueId());

                if (rpPlayer.ooc()) {
                    _player.sendMessage(finalFormat);
                }
            }
        }
    }

    public static void sendOocToAll(Player player, String message) {
        String format = ChatColor.translateAlternateColorCodes('&', Variable.OOC_FORMAT.getValue().replace("%player%", player.getName())).replace("%message%", message);
        String finalFormat = PlaceholderAPI.setPlaceholders(player, format);
        for (Player _player : Bukkit.getServer().getOnlinePlayers()) {
            RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(_player.getUniqueId());

            if (rpPlayer.ooc()) {
                _player.sendMessage(finalFormat);
            }
        }
    }
}
