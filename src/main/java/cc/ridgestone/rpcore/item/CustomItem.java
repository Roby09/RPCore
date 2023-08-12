package cc.ridgestone.rpcore.item;

import cc.ridgestone.rpcore.util.ItemBuilder;
import cc.ridgestone.rpcore.util.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum CustomItem {

    MENU_ITEM(new ItemBuilder(new ItemStack(Material.BOOK)).withName(ChatColor.GRAY + "Menu").getItemStack()),
    FILLER_DARK_GREEN(new ItemBuilder(new ItemStack(Material.GREEN_STAINED_GLASS_PANE)).withName(ChatColor.YELLOW + "" + ChatColor.MAGIC + "-").getItemStack()),
    FILLER_GREEN(new ItemBuilder(new ItemStack(Material.LIME_STAINED_GLASS_PANE)).withName(ChatColor.YELLOW + "" + ChatColor.MAGIC + "-").getItemStack()),
    FILLER_WHITE(new ItemBuilder(new ItemStack(Material.WHITE_STAINED_GLASS_PANE)).withName(ChatColor.YELLOW + "" + ChatColor.MAGIC + "-").getItemStack()),
    ROLE(new ItemBuilder(new ItemStack(Material.BOOK)).withName(ChatColor.GRAY + "Roles").getItemStack()),
    SETTINGS(new ItemBuilder(new ItemStack(Material.CRIMSON_BUTTON)).withName(ChatColor.GRAY + "Settings").getItemStack()),
    SHOP(new ItemBuilder(new ItemStack(Material.SUNFLOWER)).withName(ChatColor.GRAY + "Shop").getItemStack()),
    CHARACTER_1(new ItemBuilder(SkullCreator.itemFromUrl("http://textures.minecraft.net/texture/d2a6f0e84daefc8b21aa99415b16ed5fdaa6d8dc0c3cd591f49ca832b575")).withName(ChatColor.GRAY + "Create character 1").getItemStack()),
    CHARACTER_2(new ItemBuilder(SkullCreator.itemFromUrl("http://textures.minecraft.net/texture/96fab991d083993cb83e4bcf44a0b6cefac647d4189ee9cb823e9cc1571e38")).withName(ChatColor.GRAY + "Create character 2").getItemStack()),
    CHARACTER_3(new ItemBuilder(SkullCreator.itemFromUrl("http://textures.minecraft.net/texture/cd319b9343f17a35636bcbc26b819625a9333de3736111f2e932827c8e749")).withName(ChatColor.GRAY + "Create character 3").getItemStack()),
    CHARACTER_4(new ItemBuilder(SkullCreator.itemFromUrl("http://textures.minecraft.net/texture/d198d56216156114265973c258f57fc79d246bb65e3c77bbe8312ee35db6")).withName(ChatColor.GRAY + "Create character 4").getItemStack()),
    OOC_ON(new ItemBuilder(new ItemStack(Material.GREEN_WOOL)).withName(ChatColor.GRAY + "Toggle OOC").getItemStack()),
    OOC_OFF(new ItemBuilder(new ItemStack(Material.RED_WOOL)).withName(ChatColor.GRAY + "Toggle OOC").getItemStack()),
    EMOTE_SETTINGS(new ItemBuilder(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Emote Settings").getItemStack()),
    CLOSE_MENU(new ItemBuilder(new ItemStack(Material.RED_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Close Menu").getItemStack()),

    EMOTE_PINK(new ItemBuilder(new ItemStack(Material.PINK_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Pink").getItemStack()),
    EMOTE_GREEN(new ItemBuilder(new ItemStack(Material.GREEN_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Green").getItemStack()),
    EMOTE_YELLOW(new ItemBuilder(new ItemStack(Material.YELLOW_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Yellow").getItemStack()),
    EMOTE_LIGHT_BLUE(new ItemBuilder(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Light Blue").getItemStack()),
    EMOTE_PURPLE(new ItemBuilder(new ItemStack(Material.PURPLE_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Purple").getItemStack()),
    EMOTE_RED(new ItemBuilder(new ItemStack(Material.RED_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Red").getItemStack()),
    EMOTE_ORANGE(new ItemBuilder(new ItemStack(Material.ORANGE_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Orange").getItemStack()),
    EMOTE_BLUE(new ItemBuilder(new ItemStack(Material.BLUE_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Blue").getItemStack()),
    EMOTE_DARK_RED(new ItemBuilder(new ItemStack(Material.RED_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Dark Red").getItemStack()),
    EMOTE_LIME(new ItemBuilder(new ItemStack(Material.LIME_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Lime").getItemStack()),
    EMOTE_CYAN(new ItemBuilder(new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Cyan").getItemStack()),
    EMOTE_LIGHT_GRAY(new ItemBuilder(new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Light Gray").getItemStack()),
    EMOTE_GRAY(new ItemBuilder(new ItemStack(Material.GRAY_STAINED_GLASS_PANE)).withName(ChatColor.GRAY + "Gray").getItemStack());

    private ItemStack item;

    CustomItem(ItemStack item) {
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }
}
