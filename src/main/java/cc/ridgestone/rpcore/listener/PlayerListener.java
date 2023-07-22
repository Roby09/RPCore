package cc.ridgestone.rpcore.listener;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.item.CustomItem;
import cc.ridgestone.rpcore.player.PlayerSetup;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.getInventory().setItem(8, CustomItem.COMPASS.getItem());

        if (RPCore.i.getPlayerConfig().getString(player.getUniqueId().toString()) == null) {
            new PlayerSetup(player).getCompletableFuture().whenComplete((character, throwable) -> RPCore.i.getPlayerManager().registerPlayer(player.getUniqueId(), character));
        } else {
            RPCore.i.getPlayerManager().loadPlayer(player);
        }
    }

    @EventHandler
    public void playerRespawn(PlayerRespawnEvent event) {
        event.getPlayer().getInventory().setItem(8, CustomItem.COMPASS.getItem());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        ItemStack toRemove = null;
        for (ItemStack item : event.getDrops()) {
            if (item.isSimilar(CustomItem.COMPASS.getItem()))
                toRemove = item;
        }
        if (toRemove != null)
            event.getDrops().remove(toRemove);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        RPCore.i.getPlayerManager().removePlayer(event.getPlayer());
    }

    @EventHandler
    public void playerDropItem(PlayerDropItemEvent event) {
        event.setCancelled(event.getItemDrop().getItemStack().isSimilar(CustomItem.COMPASS.getItem()));
    }

    @EventHandler
    public void moveItem(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player && event.getClickedInventory() != null && event.getCurrentItem() != null)
            event.setCancelled(event.getCurrentItem().isSimilar(CustomItem.COMPASS.getItem()));
    }

    @EventHandler
    public void swapItem(PlayerSwapHandItemsEvent event) {
        if (event.getOffHandItem() != null)
            event.setCancelled(event.getOffHandItem().isSimilar(CustomItem.COMPASS.getItem()));
    }

}
