package cc.ridgestone.rpcore.command;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CharacterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] arguments) {
        if (!(commandSender instanceof Player))
            return false;
        Player player = (Player) commandSender;
        if (!(arguments.length > 2)) {
            player.sendMessage(ChatColor.RED + "Wrong usage: /character name/bio <FirstName SecondName/Bio ...>");
            return false;
        }
        if (arguments[0].equalsIgnoreCase("name")) {
            if (arguments.length - 1 != 2) {
                player.sendMessage(ChatColor.RED + "Name format should be: First_Name Second_Name");
                return false;
            }
            String newName = arguments[1] + " " + arguments[2];
            RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());
            rpPlayer.getCurrentCharacter().setName(newName);
            rpPlayer.saveCurrentCharacter();

            player.sendMessage(ChatColor.GREEN + "Set your characters new name to: " + newName);
        }
        else if (arguments[0].equalsIgnoreCase("bio")) {
            String bio = "";
            for(int i = 1; i < arguments.length; i++){
                bio = bio + " " + arguments[i];
            }

            RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());
            rpPlayer.getCurrentCharacter().setBio(bio.trim());
            rpPlayer.saveCurrentCharacter();

            player.sendMessage(ChatColor.GREEN + "Set your characters new bio to: " + bio.trim());
        }
        return false;
    }

}
