package cc.ridgestone.rpcore.command;

import cc.ridgestone.rpcore.RPCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RpReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (!commandSender.hasPermission("rpcore.reload")) {
            commandSender.sendMessage(ChatColor.RED + "You are not allowed to use this command");
            return false;
        }
        RPCore.i.getConfigManager().loadConfig();
        RPCore.i.reloadPlayerConfig();
        commandSender.sendMessage(ChatColor.GREEN + "RPCore config reloaded");
        return false;
    }

}
