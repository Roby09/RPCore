package cc.ridgestone.rpcore.gui;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.item.CustomItem;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class SettingsInventory implements InventoryHolder {

    private final Player player;

    public SettingsInventory(Player player) {
        this.player = player;
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27, "Settings");
        for(int i = 0; i<27; i++) {
            if (i < 9)
                inventory.setItem(i, CustomItem.FILLER_DARK_GREEN.getItem());
            else if (i < 18)
                inventory.setItem(i, CustomItem.FILLER_WHITE.getItem());
            else
                inventory.setItem(i, CustomItem.FILLER_GREEN.getItem());
        }

        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

        if (rpPlayer.ooc())
            inventory.setItem(11, CustomItem.OOC_ON.getItem());
        else
            inventory.setItem(11, CustomItem.OOC_OFF.getItem());
        inventory.setItem(15, CustomItem.EMOTE_SETTINGS.getItem());
        inventory.setItem(26, CustomItem.CLOSE_MENU.getItem());
        return inventory;
    }


}
