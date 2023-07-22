package cc.ridgestone.rpcore.command;

import cc.ridgestone.rpcore.config.Variable;
import cc.ridgestone.rpcore.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class RollCommand implements CommandExecutor {

    private Random random = new Random();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (!(commandSender instanceof Player))
            return false;
        Player player = (Player) commandSender;
        if (arguments.length != 1) {
            player.sendMessage(ChatColor.RED + "Wrong usage: /roll <number>");
            return false;
        }
        try {
            Integer.parseInt(arguments[0]);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "No numeric value found. Please only use numbers.");
            return false;
        }
        int range = Integer.parseInt(arguments[0]);
        int randomNumber = random.nextInt(range + 1);

        int chatRange = Integer.parseInt(Variable.CHAT_RANGE.getValue());

        player.sendMessage();
        ChatUtil.sendMessage(player.getLocation(), chatRange, ChatColor.YELLOW + String.valueOf(randomNumber));
        return false;
    }

}