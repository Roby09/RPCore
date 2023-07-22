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

        if (containsWordsInQuotes(event.getMessage())) {
            String message = replaceWordsInQuotesWithSurroundings(rpPlayer.getEmoteColor() + event.getMessage(), ChatColor.WHITE + "", rpPlayer.getEmoteColor() + "");
            Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendMessage(event.getPlayer().getLocation(), Integer.parseInt(Variable.ME_RANGE.getValue()), message));
            return;
        }

        if (event.getMessage().startsWith("*")) {
            Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendMessage(event.getPlayer().getLocation(), range, rpPlayer.getEmoteColor() + event.getMessage()));
        } else {
            Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(event.getPlayer(), range, event.getMessage()));
        }
    }

    public static boolean containsWordsInQuotes(String input) {
        // Define the regular expression pattern to match words between double quotes
        String patternString = "\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(patternString);

        // Create a Matcher object to find the matches in the input string
        Matcher matcher = pattern.matcher(input);

        // Check if any match is found
        return matcher.find();
    }

    public static String replaceWordsInQuotesWithSurroundings(String input, String before, String after) {
        // Define the regular expression pattern to match words between double quotes
        String patternString = "\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(patternString);

        // Create a Matcher object to find the matches in the input string
        Matcher matcher = pattern.matcher(input);

        // Replace all occurrences of the pattern with the specified surroundings
        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            String matchedWord = matcher.group(1);
            String replacement = before + "\"" + matchedWord + "\"" + after;
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);

        return result.toString();
    }
    
}
