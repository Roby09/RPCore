package cc.ridgestone.rpcore.command.chat;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.config.Variable;
import cc.ridgestone.rpcore.player.ChatChannel;
import cc.ridgestone.rpcore.player.RPPlayer;
import cc.ridgestone.rpcore.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class LoocCommand implements CommandExecutor {

    private Random random = new Random();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        /*Player player = (Player) commandSender;
        if (!(arguments.length > 0)) {
            player.sendMessage(ChatColor.RED + "Wrong usage: /Looc <... ...>");
            return false;
        }
        String message = String.join(" ", arguments);
        ChatUtil.sendOoc(player, Integer.parseInt(Variable.LOOC_RANGE.getValue()), Variable.LOOC_FORMAT, message);*/

        Player player = (Player) commandSender;
        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

        if (arguments.length == 0) {
            rpPlayer.setChatChannel(ChatChannel.LOOC);
            player.sendMessage(ChatColor.YELLOW + "Set chat channel to: 'LOOC'");
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            message.append(arguments[i]).append(" ");
        }
        Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendOoc(player, Integer.parseInt(Variable.LOOC_RANGE.getValue()), Variable.LOOC_FORMAT, message.toString()));
        return true;
    }

}
