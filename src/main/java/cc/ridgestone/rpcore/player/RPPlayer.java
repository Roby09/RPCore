package cc.ridgestone.rpcore.player;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.item.CustomItem;
import cc.ridgestone.rpcore.util.SerializationUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

public class RPPlayer {

    private UUID uuid;
    private Map<Integer, Character> characters;
    private int currentCharacter;
    private boolean ooc = true;
    private ChatColor emoteColor;
    private boolean oocCooldown;
    private ChatChannel chatChannel;

    public RPPlayer(UUID uuid, Map<Integer, Character> characters, int currentCharacter, ChatColor emoteColor) {
        this.uuid = uuid;
        this.characters = characters;
        this.currentCharacter = currentCharacter;
        this.emoteColor = emoteColor;
        oocCooldown = false;
        chatChannel = ChatChannel.DEFAULT;
    }

    public void setCurrentCharacter(int currentCharacter, boolean delete) {
        Bukkit.getScheduler().runTask(RPCore.i, () -> {
            try {
                Player player = Bukkit.getPlayer(uuid);

                if (!delete) {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "upc RemoveGroup " + player.getName() + " " + getCurrentCharacter().getRole().replace(" ", "_"));
                    saveCurrentCharacter();
                }

                this.currentCharacter = currentCharacter;

                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "upc AddGroup " + player.getName() + " " + getCurrentCharacter().getRole().replace(" ", "_"));
                player.getInventory().setContents(getCurrentCharacter().getInventoryContent());
                player.getInventory().setArmorContents(getCurrentCharacter().getArmorContent());

                RPCore.i.getPlayerConfig().set(uuid.toString() + ".currentCharacter", currentCharacter);
                RPCore.i.savePlayerFile();

                player.teleport(getCurrentCharacter().getLocation().add(0, 1, 0));
                player.getInventory().setItem(8, CustomItem.MENU_ITEM.getItem());

                player.sendMessage(ChatColor.GREEN + "Switched to character: " + getCurrentCharacter().getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void saveCurrentCharacter() {
        Player player = Bukkit.getPlayer(uuid);

        getCurrentCharacter().setLocation(player.getLocation());
        getCurrentCharacter().setInventoryContent(player.getInventory().getContents());
        getCurrentCharacter().setArmorContent(player.getInventory().getArmorContents());
        String[] inventory64 = SerializationUtil.playerInventoryToBase64(player.getInventory());

        RPCore.i.getPlayerConfig().set(uuid.toString() + ".character." + currentCharacter + ".name", getCurrentCharacter().getName());
        RPCore.i.getPlayerConfig().set(uuid.toString() + ".character." + currentCharacter + ".bio", getCurrentCharacter().getBio());
        RPCore.i.getPlayerConfig().set(uuid.toString() + ".character." + currentCharacter + ".role", getCurrentCharacter().getRole());
        RPCore.i.getPlayerConfig().set(uuid.toString() + ".character." + currentCharacter + ".age", getCurrentCharacter().getAge());
        RPCore.i.getPlayerConfig().set(uuid + ".character." + currentCharacter + ".world", getCurrentCharacter().getLocation().getWorld().getName());
        RPCore.i.getPlayerConfig().set(uuid + ".character." + currentCharacter + ".locX", getCurrentCharacter().getLocation().getBlockZ());
        RPCore.i.getPlayerConfig().set(uuid + ".character." + currentCharacter + ".locY", getCurrentCharacter().getLocation().getBlockY());
        RPCore.i.getPlayerConfig().set(uuid + ".character." + currentCharacter + ".locZ", getCurrentCharacter().getLocation().getBlockZ());
        RPCore.i.getPlayerConfig().set(uuid + ".character." + currentCharacter + ".inventoryContent", inventory64[0]);
        RPCore.i.getPlayerConfig().set(uuid + ".character." + currentCharacter + ".inventoryArmor", inventory64[1]);

        RPCore.i.savePlayerFile();
    }

    public void setChatChannel(ChatChannel chatChannel) {
        this.chatChannel = chatChannel;
    }

    public void setOoc(boolean ooc) {
        this.ooc = ooc;
    }

    public void setEmoteColor(ChatColor emoteColor) {
        this.emoteColor = emoteColor;

        RPCore.i.getPlayerConfig().set(uuid.toString() + ".emoteColor", emoteColor.name());
        RPCore.i.savePlayerFile();
    }

    public void setOocCooldown(boolean oocCooldown) {
        this.oocCooldown = oocCooldown;
    }

    public Map<Integer, Character> getCharacters() {
        return characters;
    }

    public Character getCurrentCharacter() {
        return characters.get(currentCharacter);
    }

    public int getCurrentCharacterInt() {
        return currentCharacter;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean ooc() {
        return ooc;
    }

    public ChatColor getEmoteColor() {
        return emoteColor;
    }

    public boolean isOocCooldown() {
        return oocCooldown;
    }

    public ChatChannel getChatChannel() {
        return chatChannel;
    }
}
