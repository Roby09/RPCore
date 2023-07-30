package cc.ridgestone.rpcore.command.chat;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.player.ChatChannel;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class DefaultChatCommand implements CommandExecutor {

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

        rpPlayer.setChatChannel(ChatChannel.DEFAULT);
        player.sendMessage(ChatColor.YELLOW + "Set chat channel to: 'default'");
        return false;
    }

}
