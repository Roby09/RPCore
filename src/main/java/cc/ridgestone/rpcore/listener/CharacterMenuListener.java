package cc.ridgestone.rpcore.listener;

import cc.ridgestone.rpcore.gui.CharacterInventory;
import cc.ridgestone.rpcore.gui.MenuInventory;
import cc.ridgestone.rpcore.item.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CharacterMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return;
        if (!(event.getClickedInventory().getHolder() instanceof MenuInventory))
            return;
        if (event.getCurrentItem() == null)
            return;

        Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);

        switch (event.getRawSlot()) {
            case 10 -> {
                player.openInventory(new CharacterInventory(player).getInventory());
                break;
            }
            case 14 -> {
                //TODO settings
                break;
            }
            case 16 -> {
                player.performCommand("buy");
                player.closeInventory();
            }
            case 26 -> {
                player.closeInventory();
            }
        }
    }
}
