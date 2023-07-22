package cc.ridgestone.rpcore.listener;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.player.Character;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class BioListener implements Listener {

    @EventHandler
    public void playerInteractAtPlayer(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof Player player) {
            if (event.getPlayer().isSneaking()) {
                RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());
                if (rpPlayer != null) {
                    Character character = rpPlayer.getCurrentCharacter();
                    event.getPlayer().sendMessage(ChatColor.YELLOW + character.getName() + "'s bio is " + ChatColor.GRAY + character.getBio());
                }
            }
        }
    }
}
