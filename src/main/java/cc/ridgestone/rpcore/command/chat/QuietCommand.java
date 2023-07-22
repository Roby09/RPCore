package cc.ridgestone.rpcore.command.chat;

import cc.ridgestone.rpcore.config.Variable;
import cc.ridgestone.rpcore.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class QuietCommand implements CommandExecutor {

    private Random random = new Random();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (!(commandSender instanceof Player))
            return false;
        Player player = (Player) commandSender;
        if (!(arguments.length > 0)) {
            player.sendMessage(ChatColor.RED + "Wrong usage: /quiet <... ...>");
            return false;
        }
        String message = String.join(" ", arguments).toLowerCase();
        ChatUtil.sendInCharacterMessage(player, Integer.valueOf(Variable.QUIET_RANGE.getValue()), message);
        return false;
    }

}