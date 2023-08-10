package cc.ridgestone.rpcore.listener;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.config.Variable;
import cc.ridgestone.rpcore.player.Character;
import cc.ridgestone.rpcore.player.RPPlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BioListener implements Listener {

    private List<UUID> cooldown = new ArrayList<UUID>();

    @EventHandler
    public void playerInteractAtPlayer(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Player player) {
            if (event.getPlayer().isSneaking()) {
                RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());
                if (rpPlayer != null && !cooldown.contains(event.getPlayer().getUniqueId())) {
                    String format = Variable.BIO_FORMAT.getValue();
                    String[] formatArr = format.split(",");

                    for (String finalFormat : formatArr) {
                        finalFormat = ChatColor.translateAlternateColorCodes('&', finalFormat);
                        finalFormat = PlaceholderAPI.setPlaceholders(player, finalFormat);
                        event.getPlayer().sendMessage(finalFormat);
                    }

                    //String formatyt = ChatColor.translateAlternateColorCodes('&', Variable.BIO_FORMAT.getValue());
                    //String finalFormat = PlaceholderAPI.setPlaceholders(player, format);

                    Character character = rpPlayer.getCurrentCharacter();
                    //event.getPlayer().sendMessage(finalFormat);
                    cooldown.add(event.getPlayer().getUniqueId());
                    Bukkit.getScheduler().runTaskLaterAsynchronously(RPCore.i, () -> cooldown.remove(event.getPlayer().getUniqueId()), 20l);
                }
            }
        }
    }
}
