package cc.ridgestone.rpcore.gui;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.item.CustomItem;
import cc.ridgestone.rpcore.player.RPPlayer;
import cc.ridgestone.rpcore.util.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class CharacterInventory implements InventoryHolder {

    private final Player player;

    public CharacterInventory(Player player) {
        this.player = player;
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27, "Characters");
        for(int i = 0; i<27; i++) {
            if (i < 9)
                inventory.setItem(i, CustomItem.FILLER_DARK_GREEN.getItem());
            else if (i < 18)
                inventory.setItem(i, CustomItem.FILLER_WHITE.getItem());
            else
                inventory.setItem(i, CustomItem.FILLER_GREEN.getItem());
        }
        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());

        inventory.setItem(10, CustomItem.CHARACTER_1.getItem());
        inventory.setItem(12, CustomItem.CHARACTER_2.getItem());
        inventory.setItem(14, CustomItem.CHARACTER_3.getItem());
        inventory.setItem(16, CustomItem.CHARACTER_4.getItem());
        inventory.setItem(26, CustomItem.CLOSE_MENU.getItem());

        for (Integer integer : rpPlayer.getCharacters().keySet()) {
            if (integer == 0) {
                SkullMeta itemMeta = (SkullMeta) inventory.getItem(10).getItemMeta();
                itemMeta.setDisplayName(ChatColor.GRAY + rpPlayer.getCharacters().get(integer).getName());
                if (rpPlayer.getCurrentCharacterInt() == 0) {
                    itemMeta.setOwnerProfile(player.getPlayerProfile());
                }
                inventory.getItem(10).setItemMeta(itemMeta);
            } else if (integer == 1) {
                SkullMeta itemMeta = (SkullMeta) inventory.getItem(12).getItemMeta();
                itemMeta.setDisplayName(ChatColor.GRAY + rpPlayer.getCharacters().get(integer).getName());
                if (rpPlayer.getCurrentCharacterInt() == 1) {
                    itemMeta.setOwnerProfile(player.getPlayerProfile());
                }
                inventory.getItem(12).setItemMeta(itemMeta);
            } else if (integer == 2) {
                SkullMeta itemMeta = (SkullMeta) inventory.getItem(14).getItemMeta();
                itemMeta.setDisplayName(ChatColor.GRAY + rpPlayer.getCharacters().get(integer).getName());
                if (rpPlayer.getCurrentCharacterInt() == 2) {
                    itemMeta.setOwnerProfile(player.getPlayerProfile());
                }
                inventory.getItem(14).setItemMeta(itemMeta);
            } else if (integer == 3) {
                SkullMeta itemMeta = (SkullMeta) inventory.getItem(16).getItemMeta();
                itemMeta.setDisplayName(ChatColor.GRAY + rpPlayer.getCharacters().get(integer).getName());
                if (rpPlayer.getCurrentCharacterInt() == 3) {
                    itemMeta.setOwnerProfile(player.getPlayerProfile());
                }
                inventory.getItem(16).setItemMeta(itemMeta);
            }
        }
        return inventory;
    }


}
