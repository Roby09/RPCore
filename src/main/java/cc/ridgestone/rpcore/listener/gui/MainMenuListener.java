package cc.ridgestone.rpcore.listener.gui;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.gui.CharacterInventory;
import cc.ridgestone.rpcore.gui.MenuInventory;
import cc.ridgestone.rpcore.gui.SettingsInventory;
import cc.ridgestone.rpcore.item.CustomItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class MainMenuListener implements Listener {

    @EventHandler
    public void onOpenMenu(PlayerInteractEvent event) {
        if (!RPCore.i.getPlayerManager().getPlayersInSetup().contains(event.getPlayer())) {
            if (event.getItem() != null && event.getItem().isSimilar(CustomItem.MENU_ITEM.getItem()))
                event.getPlayer().openInventory(new MenuInventory(event.getPlayer()).getInventory());
        }
    }

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
                player.openInventory(new SettingsInventory(player).getInventory());
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
