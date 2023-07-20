package cc.ridgestone.rpcore.gui;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.item.CustomItem;
import cc.ridgestone.rpcore.player.RPPlayer;
import cc.ridgestone.rpcore.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class MenuInventory implements InventoryHolder {

    private final Player player;

    public MenuInventory(Player player) {
        this.player = player;
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 27, "Menu");
        for(int i = 0; i<27; i++) {
            if (i < 9)
                inventory.setItem(i, CustomItem.FILLER_DARK_GREEN.getItem());
            else if (i < 18)
                inventory.setItem(i, CustomItem.FILLER_WHITE.getItem());
            else
                inventory.setItem(i, CustomItem.FILLER_GREEN.getItem());
        }
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwnerProfile(player.getPlayerProfile());
        meta.setDisplayName(ChatColor.GRAY + "Characters");
        skull.setItemMeta(meta);

        RPPlayer rpPlayer = RPCore.i.getPlayerManager().getRpPlayer(player.getUniqueId());
        String name = ChatColor.GRAY + "Name: " + rpPlayer.getCurrentCharacter().getName();
        String bio = ChatColor.GRAY + "Bio: " + rpPlayer.getCurrentCharacter().getBio();
        String role = ChatColor.GRAY + "Role: " + rpPlayer.getCurrentCharacter().getRole();
        String age = ChatColor.GRAY + "Age: " + rpPlayer.getCurrentCharacter().getAge();

        ItemStack info = new ItemBuilder(new ItemStack(Material.BOOK)).withName(ChatColor.YELLOW + "Character Info").withLore(name, bio, role, age).getItemStack();

        inventory.setItem(10, skull);
        inventory.setItem(12, info);
        inventory.setItem(14, CustomItem.SETTINGS.getItem());
        inventory.setItem(16, CustomItem.SHOP.getItem());
        inventory.setItem(26, CustomItem.CLOSE_MENU.getItem());
        return inventory;
    }


}
