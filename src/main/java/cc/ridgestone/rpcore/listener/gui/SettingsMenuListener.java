package cc.ridgestone.rpcore.listener.gui;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.gui.CharacterInventory;
import cc.ridgestone.rpcore.gui.EmoteInventory;
import cc.ridgestone.rpcore.gui.MenuInventory;
import cc.ridgestone.rpcore.gui.SettingsInventory;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SettingsMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return;
        if (!(event.getClickedInventory().getHolder() instanceof SettingsInventory))
            return;
        if (event.getCurrentItem() == null)
            return;

        Player player = (Player) event.getWhoClicked();
        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

        event.setCancelled(true);

        switch (event.getRawSlot()) {
            case 11 -> {
                if (rpPlayer.ooc()) {
                    rpPlayer.setOoc(false);
                    player.sendMessage(ChatColor.YELLOW + "OOC " +  ChatColor.RED + "disabled");
                } else {
                    rpPlayer.setOoc(true);
                    player.sendMessage(ChatColor.YELLOW + "OOC " + ChatColor.GREEN + "enabled");
                }
                player.openInventory(new SettingsInventory(player).getInventory());
                break;
            }
            case 15 -> {
                if (!player.hasPermission("emotecolor.use")) {
                    player.sendMessage(ChatColor.RED + "You don't have permission to use emote colors");
                    break;
                }
                player.openInventory(new EmoteInventory(player).getInventory());
                break;
            }
            case 26 -> {
                player.closeInventory();
            }
        }
    }
}
