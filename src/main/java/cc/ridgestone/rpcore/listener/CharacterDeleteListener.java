package cc.ridgestone.rpcore.listener;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.player.Character;
import cc.ridgestone.rpcore.player.PlayerSetup;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CharacterDeleteListener implements Listener {

    public static List<UUID> confirmList = new ArrayList<>();

    @EventHandler
    public void onCharacterDelete(AsyncPlayerChatEvent event) {
        if (!confirmList.contains(event.getPlayer().getUniqueId()))
            return;

        event.setCancelled(true);

        if (event.getMessage().equalsIgnoreCase("delete cancel")) {
            confirmList.remove(event.getPlayer().getUniqueId());
            event.getPlayer().sendMessage(ChatColor.GREEN + "Character deletion canceled");
        } else if (event.getMessage().equalsIgnoreCase("delete confirm")) {
            Bukkit.getScheduler().runTask(RPCore.i, () -> {
                Player player = event.getPlayer();
                RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

                RPCore.i.getPlayerConfig().set(player.getUniqueId().toString() + ".character." + rpPlayer.getCurrentCharacterInt(), null);
                RPCore.i.savePlayerFile();
                Character character = rpPlayer.getCharacters().remove(rpPlayer.getCurrentCharacterInt());

                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "upc RemoveGroup " + player.getName() + " " + character.getRole().replace(" ", "_"));

                player.sendMessage(ChatColor.GREEN + "Successfully deleted character " + character.getName());

                if (rpPlayer.getCharacters().isEmpty()) {
                    RPCore.i.getPlayerConfig().set(player.getUniqueId().toString(), null);
                    RPCore.i.savePlayerFile();
                    new PlayerSetup(player);
                } else {
                    for (Integer key : rpPlayer.getCharacters().keySet()) {
                        rpPlayer.setCurrentCharacter(key, true);
                        break;
                    }
                }
                confirmList.remove(player.getUniqueId());
            });
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (confirmList.contains(event.getPlayer().getUniqueId()))
            confirmList.remove(event.getPlayer().getUniqueId());
    }
}
