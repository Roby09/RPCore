package cc.ridgestone.rpcore.command;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.player.Character;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if(!(commandSender instanceof Player))
            return false;
        Player player = (Player) commandSender;
        Character character = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCurrentCharacter();
        player.sendMessage(ChatColor.GOLD + "=-Character Info-=");
        player.sendMessage(ChatColor.GRAY + "Name: " + ChatColor.WHITE + character.getName());
        player.sendMessage(PlaceholderAPI.setPlaceholders(player, ChatColor.GRAY + "Role: " + ChatColor.WHITE + "%uperms_prefixes%"));
        player.sendMessage(ChatColor.GRAY + "Bio: " + ChatColor.WHITE + character.getBio());
        player.sendMessage(ChatColor.GRAY + "Age: " + ChatColor.WHITE + character.getAge());
        return false;
    }
}
