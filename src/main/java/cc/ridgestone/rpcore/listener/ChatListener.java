package cc.ridgestone.rpcore.listener;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.config.Variable;
import cc.ridgestone.rpcore.player.RPPlayer;
import cc.ridgestone.rpcore.util.ChatUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (RPCore.i.getPlayerManager().getPlayersInSetup().contains(event.getPlayer()))
            return;

        int range = Integer.parseInt(Variable.CHAT_RANGE.getValue());
        event.setCancelled(true);

        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(event.getPlayer().getUniqueId());

        switch (rpPlayer.getChatChannel()) {
            case DEFAULT -> {
                if (ChatUtil.containsWordsInQuotes(event.getMessage())) {
                    String message = ChatUtil.replaceWordsInQuotesWithSurroundings(rpPlayer.getEmoteColor() + event.getMessage(), ChatColor.WHITE + "", rpPlayer.getEmoteColor() + "");
                    String chatFormat = ChatColor.translateAlternateColorCodes('&', Variable.QUOTE_FORMAT.getValue().replace("%message%", message));
                    String finalFormat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chatFormat);
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendMessage(event.getPlayer().getLocation(), Integer.parseInt(Variable.ME_RANGE.getValue()), finalFormat));
                } else if (event.getMessage().startsWith("*")) {
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), range, rpPlayer.getEmoteColor() + event.getMessage()));
                } else {
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), range, Variable.CHAT_FORMAT, null, event.getMessage()));
                }
                break;
            }
            case ME -> {
                Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), Integer.valueOf(Variable.ME_RANGE.getValue()), event.getMessage()));
                break;
            }
            case QUIET -> {
                Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), Integer.valueOf(Variable.QUIET_RANGE.getValue()), Variable.QUIET_FORMAT, Variable.QUIET_COLOR, event.getMessage().toLowerCase()));
                break;
            }
            case SHOUT -> {
                Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), Integer.valueOf(Variable.SHOUT_RANGE.getValue()), Variable.SHOUT_FORMAT, Variable.SHOUT_COLOR, event.getMessage().toUpperCase()));
                break;
            }
            case WHISPER -> {
                Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), Integer.valueOf(Variable.WHISPER_RANGE.getValue()), Variable.WHISPER_FORMAT, Variable.WHISPER_COLOR, event.getMessage().toLowerCase()));
                break;
            }
            case OOC -> {
                if (rpPlayer.isOocCooldown()) {
                    event.getPlayer().sendMessage(ChatColor.RED + "You need to wait before you can send an OOC message again.");
                    break;
                }
                Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendOocToAll(event.getPlayer(), event.getMessage()));
                if (!event.getPlayer().hasPermission("ooc.bypass")) {
                    rpPlayer.setOocCooldown(true);
                    Bukkit.getScheduler().runTaskLaterAsynchronously(RPCore.i, () -> rpPlayer.setOocCooldown(false), 20 * Integer.valueOf(Variable.OOC_COOLDOWN.getValue()));
                }
                break;
            }
            case LOOC -> {
                Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendOoc(event.getPlayer(), Integer.parseInt(Variable.LOOC_RANGE.getValue()), Variable.LOOC_FORMAT, event.getMessage()));
                break;
            }
            case WOOC -> {
                Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendOoc(event.getPlayer(), Integer.parseInt(Variable.WOOC_RANGE.getValue()), Variable.WOOC_FORMAT, event.getMessage()));
                break;
            }
        }
    }
    
}
