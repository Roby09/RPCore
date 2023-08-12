package cc.ridgestone.rpcore.listener.gui;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.gui.CharacterInventory;
import cc.ridgestone.rpcore.gui.MenuInventory;
import cc.ridgestone.rpcore.item.CustomItem;
import cc.ridgestone.rpcore.player.Character;
import cc.ridgestone.rpcore.player.PlayerSetup;
import cc.ridgestone.rpcore.player.RPPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.UUID;

public class CharacterMenuListener implements Listener {

    @EventHandler
    public void onMainMenuClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null)
            return;
        if (!(event.getClickedInventory().getHolder() instanceof CharacterInventory))
            return;
        if (event.getCurrentItem() == null)
            return;

        Player player = (Player) event.getWhoClicked();

        event.setCancelled(true);

        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

        switch (event.getRawSlot()) {
            case 10 -> {
                if (rpPlayer.getCurrentCharacterInt() == 0) {
                    player.sendMessage(ChatColor.RED + "You're already " + rpPlayer.getCurrentCharacter().getName());
                    player.closeInventory();
                    break;
                }
                if (!(rpPlayer.getCharacters().containsKey(0))) {
                    new PlayerSetup(player).getCompletableFuture().whenComplete((character, throwable) -> {
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCharacters().put(0, character);
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).setCurrentCharacter(0, false);
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).saveCurrentCharacter();
                    });
                    player.closeInventory();
                    break;
                }
                RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).setCurrentCharacter(0, false);
                player.closeInventory();
                break;
            }
            case 12 -> {
                if (rpPlayer.getCurrentCharacterInt() == 1) {
                    player.sendMessage(ChatColor.RED + "You're already " + rpPlayer.getCurrentCharacter().getName());
                    player.closeInventory();
                    break;
                }
                if (!(rpPlayer.getCharacters().containsKey(1))) {
                    new PlayerSetup(player).getCompletableFuture().whenComplete((character, throwable) -> {
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCharacters().put(1, character);
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).setCurrentCharacter(1, false);
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).saveCurrentCharacter();
                    });
                    player.closeInventory();
                    break;
                }
                RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).setCurrentCharacter(1, false);
                player.closeInventory();
                break;
            }
            case 14 -> {
                if (!player.hasPermission("rpslot.3")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to switch to this slot");
                    break;
                }
                if (rpPlayer.getCurrentCharacterInt() == 2) {
                    player.sendMessage(ChatColor.RED + "You're already " + rpPlayer.getCurrentCharacter().getName());
                    player.closeInventory();
                    break;
                }
                if (!(rpPlayer.getCharacters().containsKey(2))) {
                    new PlayerSetup(player).getCompletableFuture().whenComplete((character, throwable) -> {
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCharacters().put(2, character);
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).setCurrentCharacter(2, false);
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).saveCurrentCharacter();
                    });
                    player.closeInventory();
                    break;
                }
                RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).setCurrentCharacter(2, false);
                player.closeInventory();
                break;
            }
            case 16 -> {
                if (!player.hasPermission("rpslot.4")) {
                    player.sendMessage(ChatColor.RED + "You do not have permission to switch to this slot");
                    break;
                }
                if (rpPlayer.getCurrentCharacterInt() == 3) {
                    player.sendMessage(ChatColor.RED + "You're already " + rpPlayer.getCurrentCharacter().getName());
                    player.closeInventory();
                    break;
                }
                if (!(rpPlayer.getCharacters().containsKey(3))) {
                    new PlayerSetup(player).getCompletableFuture().whenComplete((character, throwable) -> {
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).getCharacters().put(3, character);
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).setCurrentCharacter(3, false);
                        RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).saveCurrentCharacter();
                    });
                    player.closeInventory();
                    break;
                }
                RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId()).setCurrentCharacter(3, false);
                player.closeInventory();
                break;
            }
            case 26 -> {
                player.closeInventory();
            }
        }
    }

}
