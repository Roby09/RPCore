package cc.ridgestone.rpcore.item;

import cc.ridgestone.rpcore.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomItem {

    COMPASS(new ItemBuilder(new ItemStack(Material.COMPASS)).withName(ChatColor.GRAY + "Menu").getItemStack()),
    FILLER_DARK_GREEN(new ItemBuilder(new ItemStack(Material.GREEN_STAINED_GLASS_PANE)).withName(ChatColor.YELLOW + "" + ChatColor.MAGIC + "-").getItemStack()),
    FILLER_GREEN(new ItemBuilder(new ItemStack(Material.LIME_STAINED_GLASS_PANE)).withName(ChatColor.YELLOW + "" + ChatColor.MAGIC + "-").getItemStack()),
    FILLER_WHITE(new ItemBuilder(new ItemStack(Material.WHITE_STAINED_GLASS_PANE)).withName(ChatColor.YELLOW + "" + ChatColor.MAGIC + "-").getItemStack()),
    ROLE(new ItemBuilder(new ItemStack(Material.BOOK)).withName(ChatColor.GRAY + "Roles").getItemStack()),
    SETTINGS(new ItemBuilder(new ItemStack(Material.CRIMSON_BUTTON)).withName(ChatColor.GRAY + "Settings").getItemStack()),
    SHOP(new ItemBuilder(new ItemStack(Material.SUNFLOWER)).withName(ChatColor.GRAY + "Shop").getItemStack()),
    CHARACTER_1(new ItemBuilder(new ItemStack(Material.PLAYER_HEAD)).withName(ChatColor.GRAY + "Character 1").getItemStack()),
    CHARACTER_2(new ItemBuilder(new ItemStack(Material.PLAYER_HEAD)).withName(ChatColor.GRAY + "Character 2").getItemStack()),
    CHARACTER_3(new ItemBuilder(new ItemStack(Material.PLAYER_HEAD)).withName(ChatColor.GRAY + "Character 3").getItemStack()),
    CHARACTER_4(new ItemBuilder(new ItemStack(Material.PLAYER_HEAD)).withName(ChatColor.GRAY + "Character 4").getItemStack()),
    CLOSE_MENU(new ItemBuilder(new ItemStack(Material.RED_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Close Menu").getItemStack());

    private ItemStack item;

    CustomItem(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }
}
