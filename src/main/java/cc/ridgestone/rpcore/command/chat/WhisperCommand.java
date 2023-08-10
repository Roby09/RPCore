package cc.ridgestone.rpcore.command.chat;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.config.Variable;
import cc.ridgestone.rpcore.player.ChatChannel;
import cc.ridgestone.rpcore.player.RPPlayer;
import cc.ridgestone.rpcore.util.ChatUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class WhisperCommand implements CommandExecutor {

    private Random random = new Random();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (!(commandSender instanceof Player))
            return false;
        /*Player player = (Player) commandSender;
        if (!(arguments.length > 0)) {
            player.sendMessage(ChatColor.RED + "Wrong usage: /whisper <... ...>");
            return false;
        }
        String message = String.join(" ", arguments).toLowerCase();
        ChatUtil.sendInCharacterMessage(player, Integer.valueOf(Variable.WHISPER_RANGE.getValue()), Variable.WHISPER_FORMAT, message);*/

        Player player = (Player) commandSender;
        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

        if (arguments.length == 0) {
            rpPlayer.setChatChannel(ChatChannel.WHISPER);
            player.sendMessage(ChatColor.YELLOW + "Set chat channel to: 'whisper'");
            return true;
        }

        StringBuilder messageSBB = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            messageSBB.append(arguments[i]).append(" ");
        }

        String messageSB = messageSBB.substring(0, messageSBB.length() - 1);

        int range = Integer.parseInt(Variable.WHISPER_RANGE.getValue());
        if (ChatUtil.containsWordsInQuotes(messageSB)) {
            String message = messageSB;
            if (message.startsWith("*")) {
                message = message.substring(1);
            }
            message = ChatUtil.replaceWordsInQuotesWithSurroundings(rpPlayer.getEmoteColor() + message, ChatColor.WHITE + "", rpPlayer.getEmoteColor() + "");
            String chatFormat = ChatColor.translateAlternateColorCodes('&', Variable.WHISPER_QUOTE_FORMAT.getValue().replace("%message%", message));
            String finalFormat = PlaceholderAPI.setPlaceholders(player, chatFormat);
            Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendMessage(player.getLocation(), range, finalFormat));
        } else if (messageSB.startsWith("*")) {
            String message = messageSB;
            final String finalMessage = message.substring(1);
            Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendEmote(player, range, Variable.WHISPER_EMOTE,rpPlayer.getEmoteColor() + finalMessage));
        } else {
            Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendInCharacterMessage(player, range, Variable.WHISPER_FORMAT, null, messageSB));
        }
        
        return false;
    }

}
