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

public class OocCommand implements CommandExecutor {

    private Random random = new Random();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (!(commandSender instanceof Player))
            return false;
        /*Player player = (Player) commandSender;
        if (!(arguments.length > 0)) {
            player.sendMessage(ChatColor.RED + "Wrong usage: /Ooc <... ...>");
            return false;
        }
        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());
        if (rpPlayer.isOocCooldown()) {
            player.sendMessage(ChatColor.RED + "You need to wait before you can send an OOC message again.");
            return false;
        }

        String message = String.join(" ", arguments);
        ChatUtil.sendOocToAll(player, message);

        if (!player.hasPermission("ooc.bypass")) {
            rpPlayer.setOocCooldown(true);
            Bukkit.getScheduler().runTaskLaterAsynchronously(RPCore.i, () -> rpPlayer.setOocCooldown(false), 20 * Integer.valueOf(Variable.OOC_COOLDOWN.getValue()));
        }*/
        Player player = (Player) commandSender;
        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

        if (arguments.length == 0) {
            rpPlayer.setChatChannel(ChatChannel.OOC);
            player.sendMessage(ChatColor.YELLOW + "Set chat channel to: 'OOC'");
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < arguments.length; i++) {
            message.append(arguments[i]).append(" ");
        }

        if (rpPlayer.isOocCooldown()) {
            player.sendMessage(ChatColor.RED + "You need to wait before you can send an OOC message again.");
            return true;
        }
        Bukkit.getScheduler().runTask(RPCore.i, () -> ChatUtil.sendOocToAll(player, message.toString()));
        if (!player.hasPermission("ooc.bypass")) {
            rpPlayer.setOocCooldown(true);
            Bukkit.getScheduler().runTaskLaterAsynchronously(RPCore.i, () -> rpPlayer.setOocCooldown(false), 20 * Integer.valueOf(Variable.OOC_COOLDOWN.getValue()));
        }

        return true;
    }

}
