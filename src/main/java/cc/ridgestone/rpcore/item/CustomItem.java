package cc.ridgestone.rpcore.item;

import cc.ridgestone.rpcore.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomItem {

    COMPASS(new ItemBuilder(new ItemStack(Material.COMPASS)).withName(ChatColor.GRAY + "Menu").getItemStack());

    private ItemStack item;

    CustomItem(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }
}
