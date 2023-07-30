package cc.ridgestone.rpcore.gui;

import cc.ridgestone.rpcore.item.CustomItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class EmoteInventory implements InventoryHolder {

    private final Player player;

    public EmoteInventory(Player player) {
        this.player = player;
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27, "Set emote color");
        for (int i = 0; i<27; i++) {
            inventory.setItem(i, CustomItem.FILLER_WHITE.getItem());
        }

        inventory.setItem(10, CustomItem.EMOTE_PINK.getItem());
        inventory.setItem(11, CustomItem.EMOTE_GREEN.getItem());
        inventory.setItem(12, CustomItem.EMOTE_YELLOW.getItem());
        inventory.setItem(13, CustomItem.EMOTE_BLUE.getItem());
        inventory.setItem(14, CustomItem.EMOTE_PURPLE.getItem());
        inventory.setItem(15, CustomItem.EMOTE_RED.getItem());
        inventory.setItem(16, CustomItem.EMOTE_ORANGE.getItem());
        inventory.setItem(19, CustomItem.EMOTE_CYAN.getItem());
        inventory.setItem(20, CustomItem.EMOTE_LIGHT_GRAY.getItem());
        inventory.setItem(21, CustomItem.EMOTE_DARK_RED.getItem());
        inventory.setItem(22, CustomItem.EMOTE_LIGHT_BLUE.getItem());
        inventory.setItem(23, CustomItem.EMOTE_LIME.getItem());
        inventory.setItem(24, CustomItem.EMOTE_GRAY.getItem());

        inventory.setItem(26, CustomItem.CLOSE_MENU.getItem());
        return inventory;
    }


}
