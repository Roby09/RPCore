package cc.ridgestone.rpcore.listener.gui;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.gui.EmoteInventory;
import cc.ridgestone.rpcore.gui.SettingsInventory;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class EmoteMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return;
        if (!(event.getClickedInventory().getHolder() instanceof EmoteInventory))
            return;
        if (event.getCurrentItem() == null)
            return;

        Player player = (Player) event.getWhoClicked();
        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

        event.setCancelled(true);

        switch (event.getRawSlot()) {
            case 10 -> {
                rpPlayer.setEmoteColor(ChatColor.LIGHT_PURPLE);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.LIGHT_PURPLE + "pink");
                player.closeInventory();
                break;
            }
            case 11 -> {
                rpPlayer.setEmoteColor(ChatColor.DARK_GREEN);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.DARK_GREEN + "green");
                player.closeInventory();
                break;
            }
            case 12 -> {
                rpPlayer.setEmoteColor(ChatColor.YELLOW);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.YELLOW + "yellow");
                player.closeInventory();
                break;
            }
            case 13 -> {
                rpPlayer.setEmoteColor(ChatColor.DARK_BLUE);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.DARK_BLUE + "blue");
                player.closeInventory();
                break;
            }
            case 14 -> {
                rpPlayer.setEmoteColor(ChatColor.DARK_PURPLE);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.DARK_PURPLE + "purple");
                player.closeInventory();
                break;
            }
            case 15 -> {
                rpPlayer.setEmoteColor(ChatColor.RED);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.RED + "red");
                player.closeInventory();
                break;
            }case 16 -> {
                rpPlayer.setEmoteColor(ChatColor.GOLD);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.GOLD + "orange");
                player.closeInventory();
                break;
            }
            case 20 -> {
                rpPlayer.setEmoteColor(ChatColor.BLUE);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.AQUA + "cyan");
                player.closeInventory();
                break;
            }
            case 21 -> {
                rpPlayer.setEmoteColor(ChatColor.DARK_RED);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.DARK_RED + "dark red");
                player.closeInventory();
                break;
            }
            case 22 -> {
                rpPlayer.setEmoteColor(ChatColor.AQUA);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.AQUA + "light blue");
                player.closeInventory();
                break;
            }
            case 23 -> {
                rpPlayer.setEmoteColor(ChatColor.GREEN);
                player.sendMessage(ChatColor.YELLOW + "Set emote color to: " + ChatColor.GREEN + "lime");
                player.closeInventory();
                break;
            }
            case 26 -> {
                player.closeInventory();
            }
        }
    }
}
