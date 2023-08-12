package cc.ridgestone.rpcore.listener;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.config.Variable;
import cc.ridgestone.rpcore.player.RPPlayer;
import cc.ridgestone.rpcore.util.ChatUtil;
import com.google.errorprone.annotations.Var;
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
        if (CharacterDeleteListener.confirmList.contains(event.getPlayer().getUniqueId()))
            return;

        event.setCancelled(true);

        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(event.getPlayer().getUniqueId());

        switch (rpPlayer.getChatChannel()) {
            case DEFAULT -> {
                int range = Integer.parseInt(Variable.CHAT_RANGE.getValue());
                if (event.getMessage().startsWith("**")){
                    String message = event.getMessage();
                    message = message.substring(2);
                    if (message.isEmpty()){return;}
                    final String finalMessage = message;
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), range, Variable.IT_MESSAGE,rpPlayer.getEmoteColor() + finalMessage));
                } else if (ChatUtil.containsWordsInQuotes(event.getMessage())) {
                    String message = event.getMessage();
                    if (message.startsWith("*")) {
                        message = message.substring(1);
                    }
                    if (message.isEmpty()){return;}
                    message = ChatUtil.replaceWordsInQuotesWithSurroundings(rpPlayer.getEmoteColor() + message, ChatColor.WHITE + "", rpPlayer.getEmoteColor() + "");
                    String chatFormat = ChatColor.translateAlternateColorCodes('&', Variable.QUOTE_FORMAT.getValue().replace("%message%", message));
                    String finalFormat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chatFormat);
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendMessage(event.getPlayer().getLocation(), range, finalFormat));
                } else if (event.getMessage().startsWith("*")) {
                    String message = event.getMessage();
                    final String finalMessage = message.substring(1);
                    if (finalMessage.isEmpty()){return;}
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), range, Variable.EMOTE_FORMAT,rpPlayer.getEmoteColor() + finalMessage));
                } else {
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), range, Variable.CHAT_FORMAT, null, event.getMessage()));
                }
                break;
            }
            case ME -> {
                Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), Integer.valueOf(Variable.ME_RANGE.getValue()), Variable.EMOTE_FORMAT,event.getMessage()));
                break;
            }
            case QUIET -> {
                int range = Integer.parseInt(Variable.QUIET_RANGE.getValue());
                if (event.getMessage().startsWith("**")){
                    String message = event.getMessage();
                    message = message.substring(2);
                    if (message.isEmpty()){return;}
                    final String finalMessage = message;
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), range, Variable.IT_QUIET,rpPlayer.getEmoteColor() + finalMessage));
                } else if (ChatUtil.containsWordsInQuotes(event.getMessage())) {
                    String message = event.getMessage();
                    if (message.startsWith("*")) {
                        message = message.substring(1);
                    }
                    if (message.isEmpty()){return;}
                    message = ChatUtil.replaceWordsInQuotesWithSurroundings(rpPlayer.getEmoteColor() + message, ChatColor.WHITE + "", rpPlayer.getEmoteColor() + "");
                    String chatFormat = ChatColor.translateAlternateColorCodes('&', Variable.QUIET_QUOTE_FORMAT.getValue().replace("%message%", message));
                    String finalFormat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chatFormat);
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendMessage(event.getPlayer().getLocation(), range, finalFormat));
                } else if (event.getMessage().startsWith("*")) {
                    String message = event.getMessage();
                    final String finalMessage = message.substring(1);
                    if (finalMessage.isEmpty()){return;}
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), range, Variable.QUIET_EMOTE,rpPlayer.getEmoteColor() + finalMessage));
                } else {
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), range, Variable.QUIET_FORMAT, null, event.getMessage()));
                }

                //Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), Integer.valueOf(Variable.QUIET_RANGE.getValue()), Variable.QUIET_FORMAT, Variable.QUIET_COLOR, event.getMessage().toLowerCase()));
                break;
            }
            case SHOUT -> {
                int range = Integer.parseInt(Variable.SHOUT_RANGE.getValue());
                if (event.getMessage().startsWith("**")){
                    String message = event.getMessage();
                    message = message.substring(2);
                    if (message.isEmpty()){return;}
                    final String finalMessage = message;
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), range, Variable.IT_SHOUT,rpPlayer.getEmoteColor() + finalMessage));
                } else if (ChatUtil.containsWordsInQuotes(event.getMessage())) {
                    String message = event.getMessage();
                    if (message.startsWith("*")) {
                        message = message.substring(1);
                    }
                    if (message.isEmpty()){return;}
                    message = ChatUtil.replaceWordsInQuotesWithSurroundings(rpPlayer.getEmoteColor() + message, ChatColor.WHITE + "", rpPlayer.getEmoteColor() + "");
                    String chatFormat = ChatColor.translateAlternateColorCodes('&', Variable.SHOUT_QUOTE_FORMAT.getValue().replace("%message%", message));
                    String finalFormat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chatFormat);
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendMessage(event.getPlayer().getLocation(), range, finalFormat));
                } else if (event.getMessage().startsWith("*")) {
                    String message = event.getMessage();
                    final String finalMessage = message.substring(1);
                    if (finalMessage.isEmpty()){return;}
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), range, Variable.SHOUT_EMOTE,rpPlayer.getEmoteColor() + finalMessage));
                } else {
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), range, Variable.SHOUT_FORMAT, null, event.getMessage()));
                }
                //Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), Integer.valueOf(Variable.SHOUT_RANGE.getValue()), Variable.SHOUT_FORMAT, Variable.SHOUT_COLOR, event.getMessage().toUpperCase()));
                break;
            }
            case WHISPER -> {
                int range = Integer.parseInt(Variable.WHISPER_RANGE.getValue());
                if (event.getMessage().startsWith("**")){
                    String message = event.getMessage();
                    message = message.substring(2);
                    if (message.isEmpty()){return;}
                    final String finalMessage = message;
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), range, Variable.IT_WHISPER,rpPlayer.getEmoteColor() + finalMessage));
                } else if (ChatUtil.containsWordsInQuotes(event.getMessage())) {
                    String message = event.getMessage();
                    if (message.startsWith("*")) {
                        message = message.substring(1);
                    }
                    if (message.isEmpty()){return;}
                    message = ChatUtil.replaceWordsInQuotesWithSurroundings(rpPlayer.getEmoteColor() + message, ChatColor.WHITE + "", rpPlayer.getEmoteColor() + "");
                    String chatFormat = ChatColor.translateAlternateColorCodes('&', Variable.WHISPER_QUOTE_FORMAT.getValue().replace("%message%", message));
                    String finalFormat = PlaceholderAPI.setPlaceholders(event.getPlayer(), chatFormat);
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendMessage(event.getPlayer().getLocation(), range, finalFormat));
                } else if (event.getMessage().startsWith("*")) {
                    String message = event.getMessage();
                    final String finalMessage = message.substring(1);
                    if (finalMessage.isEmpty()){return;}
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(event.getPlayer(), range, Variable.WHISPER_EMOTE,rpPlayer.getEmoteColor() + finalMessage));
                } else {
                    Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), range, Variable.WHISPER_FORMAT, null, event.getMessage()));
                }
                //Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), Integer.valueOf(Variable.WHISPER_RANGE.getValue()), Variable.WHISPER_FORMAT, Variable.WHISPER_COLOR, event.getMessage().toLowerCase()));
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
