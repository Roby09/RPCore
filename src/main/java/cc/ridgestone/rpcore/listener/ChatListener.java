package cc.ridgestone.rpcore.listener;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.config.Variable;
import cc.ridgestone.rpcore.util.ChatUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        String format = ChatColor.translateAlternateColorCodes('&', Variable.CHAT_FORMAT.getValue().replace("%player%", event.getPlayer().getName())).replace("%message%", event.getMessage());
        String finalFormat = PlaceholderAPI.setPlaceholders(event.getPlayer(), format);
        event.setCancelled(true);
        int range = Integer.parseInt(Variable.CHAT_RANGE.getValue());
        Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendMessage(event.getPlayer().getLocation(), range, finalFormat));
    }
    
}
