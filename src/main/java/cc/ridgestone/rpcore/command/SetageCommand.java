package cc.ridgestone.rpcore.command;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (!commandSender.hasPermission("rpcore.setage")) {
            commandSender.sendMessage(ChatColor.RED + "You are not allowed to use this command");
            return false;
        }
        if (!(arguments.length > 1)) {
            commandSender.sendMessage(ChatColor.RED + "Wrong usage: /setage player <age>");
            return false;
        }
        Player player = Bukkit.getServer().getPlayer(arguments[0]);
        if (player == null) {
            commandSender.sendMessage(ChatColor.RED + "That player is not found");
            return false;
        }

        try {
            Integer.parseInt(arguments[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "No numeric value found. Please only use numbers.");
            return false;
        }
        int age = Integer.parseInt(arguments[1]);

        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());
        if (rpPlayer != null) {
            rpPlayer.getCurrentCharacter().setAge(age);
            rpPlayer.saveCurrentCharacter();
            commandSender.sendMessage(ChatColor.GREEN + "Set age of " + player.getName() + "/" + rpPlayer.getCurrentCharacter().getName() + " to " + age);
        } else {
            commandSender.sendMessage(ChatColor.RED + "Something went wrong (no rp character found for this player)");
        }
        return false;
    }

}
