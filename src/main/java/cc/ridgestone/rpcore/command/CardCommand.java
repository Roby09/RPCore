package cc.ridgestone.rpcore.command;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.config.Variable;
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

        String format = Variable.BIO_FORMAT.getValue();
        String[] formatArr = format.split(",");

        for (String finalFormat : formatArr) {
            finalFormat = ChatColor.translateAlternateColorCodes('&', finalFormat);
            finalFormat = PlaceholderAPI.setPlaceholders(player, finalFormat);
            player.sendMessage(finalFormat);
        }
        return false;
    }
}
