package cc.ridgestone.rpcore.player;

import cc.ridgestone.rpcore.RPCore;
import cc.ridgestone.rpcore.util.SerializationUtil;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RPPlayer {

    private UUID uuid;
    private Map<Integer, Character> characters;
    private int currentCharacter;

    public RPPlayer(UUID uuid, Map<Integer, Character> characters, int currentCharacter) {
        this.uuid = uuid;
        this.characters = characters;
        this.currentCharacter = currentCharacter;
    }

    public void setCurrentCharacter(int currentCharacter) {
        Player player = Bukkit.getPlayer(uuid);
        Bukkit.getScheduler().runTask(RPCore.i, () -> Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "upc RemoveGroup " + player.getName() + " " + getCurrentCharacter().getRole()));
        saveCurrentCharacter();

        this.currentCharacter = currentCharacter;

        Bukkit.getScheduler().runTask(RPCore.i, () -> Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "upc AddGroup " + player.getName() + " " + getCurrentCharacter().getRole()));
        player.getInventory().setContents(getCurrentCharacter().getInventoryContent());
        player.getInventory().setArmorContents(getCurrentCharacter().getArmorContent());
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
}
