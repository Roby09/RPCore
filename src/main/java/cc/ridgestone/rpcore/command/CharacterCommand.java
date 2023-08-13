package cc.ridgestone.rpcore.command;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.listener.CharacterDeleteListener;
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
        if (RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString()) == null){
            player.sendMessage(ChatColor.RED + "Please only input what the prompt asks. Not a command.");
            return false;
        }

        if (arguments[0].equalsIgnoreCase("name")) {
            if (!(arguments.length > 2)) {
                player.sendMessage(ChatColor.RED + "Wrong usage: /character name <FirstName SecondName>");
                return false;
            }
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
            if (!(arguments.length > 2)) {
                player.sendMessage(ChatColor.RED + "Wrong usage: /character bio <Bio ...>");
                return false;
            }
            String bio = "";
            for(int i = 1; i < arguments.length; i++){
                bio = bio + " " + arguments[i];
            }

            RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());
            rpPlayer.getCurrentCharacter().setBio(bio.trim());
            rpPlayer.saveCurrentCharacter();

            player.sendMessage(ChatColor.GREEN + "Set your characters new bio to: " + bio.trim());
        } else if (arguments[0].equalsIgnoreCase("delete")) {
            RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

            if (rpPlayer.getCurrentCharacter() == null) {
                player.sendMessage(ChatColor.RED + "You currently don't have a character");
                return false;
            }

            if (!CharacterDeleteListener.confirmList.contains(player.getUniqueId())) {
                CharacterDeleteListener.confirmList.add(player.getUniqueId());
            }

            player.sendMessage(ChatColor.DARK_RED + "Type 'delete confirm' to delete character " + rpPlayer.getCurrentCharacter().getName());
            player.sendMessage(ChatColor.DARK_RED + "Type 'delete cancel' to cancel deletion");
        }
        return false;
    }

}
