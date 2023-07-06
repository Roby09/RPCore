package cc.ridgestone.rpcore.listener;

import cc.ridgestone.rpcore.gui.MenuInventory;
import cc.ridgestone.rpcore.item.CustomItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GuiListener implements Listener {

    @EventHandler
    public void onOpenMenu(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().isSimilar(CustomItem.COMPASS.getItem()))
            event.getPlayer().openInventory(new MenuInventory(event.getPlayer()).getInventory());
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(event.getClickedInventory() == null)
            return;
        if(!(event.getClickedInventory().getHolder() instanceof MenuInventory))
            return;

        event.setCancelled(true);

        //TODO
    }
}
