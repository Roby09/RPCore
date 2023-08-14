package cc.ridgestone.rpcore.command;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetroleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (!commandSender.hasPermission("rpcore.setrole")) {
            commandSender.sendMessage(ChatColor.RED + "You are not allowed to use this command");
            return false;
        }
        if (!(arguments.length > 1)) {
            commandSender.sendMessage(ChatColor.RED + "Wrong usage: /setrole player <role>");
            return false;
        }
        Player player = Bukkit.getServer().getPlayer(arguments[0]);
        if (player == null) {
            commandSender.sendMessage(ChatColor.RED + "That player is not found");
            return false;
        }
        String role = "";
        for(int i = 1; i < arguments.length; i++) {
            role = role + " " + arguments[i];
        }
        role = role.trim();

        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());
        if (rpPlayer != null) {
            rpPlayer.getCurrentCharacter().setRole(role);
            rpPlayer.saveCurrentCharacter();
            commandSender.sendMessage(ChatColor.GREEN + "Set role of " + player.getName() + "/" + rpPlayer.getCurrentCharacter().getName() + " to " + role);
        } else {
            commandSender.sendMessage(ChatColor.RED + "Something went wrong (no rp character found for this player)");
        }
        return false;
    }

}
